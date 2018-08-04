package tools.clustering.ga.selection;

import exception.InvalidRandomRangeException;
import tools.clustering.ga.Chromosome;

import java.util.List;

public interface Selection {

    List<Chromosome> select(List<Chromosome> chromosomes, boolean maximizeFitness, int elitism) throws InvalidRandomRangeException;

}
