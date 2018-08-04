package tools.clustering.ga;

import exception.InvalidRandomRangeException;
import util.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Chromosome implements Comparable<Chromosome> {

    public List<Boolean> genes;

    public double fitness;

    public Chromosome(List<Boolean> genes) {
        this.genes = genes;
    }


    @Override
    public int compareTo(Chromosome o) {
        return Double.compare(fitness, o.fitness);
    }

    public static Chromosome random(int genesCount, int clusters) throws InvalidRandomRangeException {
        List<Boolean> genes = new ArrayList<>(Collections.nCopies(genesCount, false));
        Random.uniqueRandomIntsInRange(0, genesCount, clusters).forEach(integer -> genes.set(integer, true));
        return new Chromosome(genes);
    }
}
