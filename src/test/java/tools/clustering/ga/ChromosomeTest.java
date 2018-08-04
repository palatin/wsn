package tools.clustering.ga;

import exception.InvalidRandomRangeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChromosomeTest {

    @Test
    void randomCount() throws InvalidRandomRangeException {
        Chromosome chromosome = Chromosome.random(100, 10);
        assertEquals(chromosome.genes.size(), 100);
    }

    @Test
    void randomClustersCheck() throws InvalidRandomRangeException {
        Chromosome chromosome = Chromosome.random(100, 10);
        int clusters = 0;
        for (boolean gene : chromosome.genes) {
            if(gene)
                clusters++;
        }
        assertEquals(clusters, 10);
    }
}