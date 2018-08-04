package tools.clustering.ga.selection;

import exception.InvalidRandomRangeException;
import tools.clustering.ga.Chromosome;
import util.ArrayHelper;
import util.Random;

import java.util.ArrayList;
import java.util.List;

public class TournamentSelection implements Selection {

    private int tournamentSize;

    public TournamentSelection(int tournamentSize) {
        this.tournamentSize = tournamentSize;

    }


    @Override
    public List<Chromosome> select(List<Chromosome> chromosomes, boolean maximizeFitness, int elitism) throws InvalidRandomRangeException {

        List<Chromosome> newPopulation = new ArrayList<>(chromosomes.size());
        for (int i = 0; i < chromosomes.size(); i++) {
            List<Chromosome> applicants = new ArrayList<>();
            Random.uniqueRandomIntsInRange(0, chromosomes.size(), tournamentSize).forEach(applicant -> applicants.add(chromosomes.get(applicant)));
            newPopulation.add(maximizeFitness ? ArrayHelper.getLargestElement(applicants).getValue() : ArrayHelper.getSmallestElement(applicants).getValue());
        }
        return newPopulation;
    }
}
