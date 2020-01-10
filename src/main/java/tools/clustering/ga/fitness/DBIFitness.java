package tools.clustering.ga.fitness;

import model.Clusters;
import model.node.Node;
import tools.clustering.ga.Chromosome;
import util.ArrayHelper;
import util.geometry.Geometry;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DBIFitness implements Fitness {


    private Geometry geometry;
    private List<Node> nodes;

    public DBIFitness(Geometry geometry, List<Node> nodes) {
        this.geometry = geometry;
        this.nodes = nodes;
    }

    @Override
    public double calculateFitness(Clusters clusters) {

        double[] s = new double[clusters.size()];
        for (int i = 0; i < clusters.size(); i++) {
            Map<Double, Integer> cluster = clusters.getClusters().get(i);
            for(double dist : cluster.keySet()) {
                s[i] += dist;
            }
            if(cluster.size() > 1)
                s[i] /= (cluster.size() - 1);
        }

        double[][] Rij = new double[clusters.size()][clusters.size()];

        for (int i = 0; i < clusters.size(); i++) {
            for (int j = 0; j < clusters.size(); j++) {
                if(i != j)
                    Rij[i][j] = (s[i] + s[j]) / geometry.distanceBetweenPoints(nodes.get(clusters.getClusters().get(i).firstEntry().getValue()).getLocation(),
                            nodes.get(clusters.getClusters().get(j).firstEntry().getValue()).getLocation());
            }
        }

        double[] Ri = new double[clusters.size()];

        for (int i = 0; i < clusters.size(); i++) {
            Ri[i] = ArrayHelper.getLargestElement(Arrays.stream(Rij[i]).boxed().collect(Collectors.toList())).getValue();
        }

        double DB = 0;
        for (int i = 0; i < clusters.size(); i++) {
            DB += Ri[i];
        }
        return DB / clusters.size();
    }

    @Override
    public boolean stopCriteria(List<Chromosome> chromosomes) {
        return false;
    }

    @Override
    public boolean isMaximize() {
        return false;
    }
}
