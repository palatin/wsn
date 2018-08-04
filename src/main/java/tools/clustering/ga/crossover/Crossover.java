package tools.clustering.ga.crossover;

import tools.clustering.ga.Chromosome;

public interface Crossover {

    void crossover(Chromosome first, Chromosome second);

}
