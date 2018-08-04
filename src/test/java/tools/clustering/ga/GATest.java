package tools.clustering.ga;

import exception.InvalidClusteringArgumentsException;
import exception.InvalidFactoryArgumentsException;
import factory.RandomNodesFactory;
import model.Point;
import model.node.Node;
import model.node.transmitter.FirstOrderRadioTransmitter;
import org.junit.jupiter.api.Test;
import tools.clustering.ga.crossover.TwoPointCrossover;
import tools.clustering.ga.fitness.DBIFitness;
import tools.clustering.ga.mutation.BinaryMutation;
import tools.clustering.ga.selection.TournamentSelection;
import util.geometry.EuclidGeometry;
import util.geometry.Geometry;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GATest {

    @Test
    void buildClusters() throws InvalidFactoryArgumentsException, InvalidClusteringArgumentsException {
        Geometry geometry = new EuclidGeometry();
        List<Node> nodes = new RandomNodesFactory(new Point(0,0,0), new Point(1000,1000,0), new FirstOrderRadioTransmitter(geometry)).buildNodes(800);
        GAConfiguration configuration =  new GAConfigurationBuilder()
                .selection(new TournamentSelection(2))
                .crossover(new TwoPointCrossover())
                .mutation(new BinaryMutation())
                .fitness(new DBIFitness(geometry, nodes)).createGAConfiguration();
        GA ga = new GA(configuration, geometry, 20);
        ga.buildClusters(nodes);
    }
}