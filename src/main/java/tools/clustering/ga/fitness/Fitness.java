package tools.clustering.ga.fitness;

import model.Clusters;
import tools.clustering.ga.Chromosome;

import java.util.List;

public interface Fitness {

    double calculateFitness(Clusters clusters);

    boolean stopCriteria(List<Chromosome> chromosomes);

    boolean isMaximize();

}
