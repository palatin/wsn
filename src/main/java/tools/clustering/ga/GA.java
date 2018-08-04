package tools.clustering.ga;

import exception.InvalidClusteringArgumentsException;
import exception.InvalidRandomRangeException;
import model.Clusters;
import model.Point;
import model.node.Node;
import tools.clustering.Clustering;
import util.ArrayHelper;
import util.Random;
import util.geometry.Geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GA extends Clustering {

    private GAConfiguration configuration;
    private int populationSize;
    private float crossoverProbability = 0.6f;

    public GA(GAConfiguration configuration, Geometry geometry, int populationSize) {
        super(geometry);
        this.configuration = configuration;
        this.populationSize = populationSize;
    }

    @Override
    public Clusters buildClusters(List<Node> nodes) throws InvalidClusteringArgumentsException {

        List<Chromosome> populations;
        try {
            populations = init(nodes);
        } catch (InvalidRandomRangeException e) {
            throw new InvalidClusteringArgumentsException(e);
        }

        calculateFitnesses(populations, nodes);
        do {
            try {
                populations = configuration.getSelection().select(populations, configuration.getFitness().isMaximize() ,0);
            } catch (InvalidRandomRangeException e) {
                throw new InvalidClusteringArgumentsException(e);
            }
            crossover(populations, nodes);
            populations.forEach(chromosome -> configuration.getMutation().mutate(chromosome));
            calculateFitnesses(populations, nodes);
        }while (!configuration.getFitness().stopCriteria(populations));

        Chromosome chromosome = configuration.getFitness().isMaximize() ? ArrayHelper.getLargestElement(populations).getValue() : ArrayHelper.getSmallestElement(populations).getValue();
        return chromosomeToClusters(chromosome, nodes);
    }

    List<Chromosome> init(List<Node> nodes) throws InvalidRandomRangeException {

        List<Chromosome> chromosomes = new ArrayList<>(populationSize);

        for (int i = 0; i < populationSize; ++i) {
            chromosomes.add(Chromosome.random(nodes.size(), 10));
        }

        return chromosomes;
    }

    private void calculateFitnesses(List<Chromosome> chromosomes, List<Node> nodes)  {
        chromosomes.forEach(chromosome -> chromosome.fitness = configuration.getFitness().calculateFitness(chromosomeToClusters(chromosome, nodes)));
    }

    private void crossover(List<Chromosome> chromosomes, List<Node> nodes) {
        //if populations has odd parents - ignore last
        int count = chromosomes.size() % 2 == 0 ? chromosomes.size() : chromosomes.size() - 1;

        for(int i = 0 ; i < count - 1; i++) {
            if(Random.randomFloatInRange(0,1) >= crossoverProbability) {
                configuration.getCrossover().crossover(chromosomes.get(i), chromosomes.get(i + 1));
            }
        }
    }


    private Chromosome clustersToChromosome(Clusters clusters) {
        int count = 0;
        for (Map<Double, Integer> cluster : clusters.getClusters()) {
            count += cluster.size();
        }
        List<Boolean> genes = Collections.nCopies(count, false);
        for (int i = 0; i < clusters.size(); i++) {
            genes.set(clusters.getClusters().get(i).firstEntry().getValue(), true);
        }
        return new Chromosome(genes);
    }

    private Clusters chromosomeToClusters(Chromosome chromosome, List<Node> nodes) {

        List<Point> centers = new ArrayList<>();
        for (int i = 0; i < chromosome.genes.size(); i++) {
            if(chromosome.genes.get(i))
                centers.add(nodes.get(i).getLocation());
        }
        Clusters clusters = new Clusters(centers.size());
        assignNodesToClusters(centers, clusters, nodes);

        return clusters;
    }
}
