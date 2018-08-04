package tools.clustering.ga;

import tools.clustering.ga.crossover.Crossover;
import tools.clustering.ga.fitness.Fitness;
import tools.clustering.ga.mutation.Mutation;
import tools.clustering.ga.selection.Selection;

public class GAConfiguration {

    private final Selection selection;
    private final Crossover crossover;
    private final Mutation mutation;
    private final Fitness fitness;

    GAConfiguration(Selection selection, Crossover crossover, Mutation mutation, Fitness fitness) {
        this.selection = selection;
        this.crossover = crossover;
        this.mutation = mutation;
        this.fitness = fitness;
    }


    public Selection getSelection() {
        return selection;
    }

    public Crossover getCrossover() {
        return crossover;
    }

    public Mutation getMutation() {
        return mutation;
    }

    public Fitness getFitness() {
        return fitness;
    }
}
