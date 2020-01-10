package tools.clustering.ga;

import tools.clustering.ga.crossover.Crossover;
import tools.clustering.ga.fitness.Fitness;
import tools.clustering.ga.mutation.Mutation;
import tools.clustering.ga.selection.Selection;

public class GAConfigurationBuilder {
    private Selection selection;
    private Crossover crossover;
    private Mutation mutation;
    private Fitness fitness;

    public GAConfigurationBuilder selection(Selection selection) {
        this.selection = selection;
        return this;
    }

    public GAConfigurationBuilder crossover(Crossover crossover) {
        this.crossover = crossover;
        return this;
    }

    public GAConfigurationBuilder mutation(Mutation mutation) {
        this.mutation = mutation;
        return this;
    }

    public GAConfigurationBuilder fitness(Fitness fitness) {
        this.fitness = fitness;
        return this;
    }

    public GAConfiguration createGAConfiguration() {
        return new GAConfiguration(selection, crossover, mutation, fitness);
    }
}