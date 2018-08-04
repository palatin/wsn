package tools.clustering.ga.crossover;

import tools.clustering.ga.Chromosome;
import util.Random;

import java.util.ArrayList;
import java.util.List;


public class TwoPointCrossover implements Crossover {

    @Override
    public void crossover(Chromosome first, Chromosome second) {
        int firstPoint = Random.randomIntInRange(0, first.genes.size() - 2);
        int secondPoint = Random.randomIntInRange(1, first.genes.size() - 1);
        if(firstPoint > secondPoint) {
            int temp = firstPoint;
            firstPoint = secondPoint;
            secondPoint = temp;
        }

        List<Boolean> temp = new ArrayList<>(first.genes);
        for (int i = firstPoint; i <= secondPoint; i++) {
            first.genes.set(i, second.genes.get(i));
            second.genes.set(i, temp.get(i));
        }
    }
}
