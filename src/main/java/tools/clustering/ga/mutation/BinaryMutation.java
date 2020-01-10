package tools.clustering.ga.mutation;

import tools.clustering.ga.Chromosome;
import util.Random;

import java.util.stream.Collectors;

public class BinaryMutation implements Mutation {


    @Override
    public void mutate(Chromosome chromosome) {
        float probability = 1f / chromosome.genes.size();
        for (int i = 0; i < chromosome.genes.size(); i++) {
            chromosome.genes.set(i, Random.randomFloatInRange(0, 1) > probability ? chromosome.genes.get(i) : !chromosome.genes.get(i));
        }
    }
}
