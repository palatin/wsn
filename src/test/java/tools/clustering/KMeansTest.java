package tools.clustering;

import exception.InvalidClusteringArgumentsException;
import exception.InvalidFactoryArgumentsException;
import factory.RandomNodesFactory;
import model.Point;
import model.node.transmitter.FirstOrderRadioTransmitter;
import org.junit.jupiter.api.Test;
import util.geometry.EuclidGeometry;
import util.geometry.Geometry;

import static org.junit.jupiter.api.Assertions.*;

class KMeansTest {

    //TODO write tests
    @Test
    void buildClusters() throws InvalidFactoryArgumentsException, InvalidClusteringArgumentsException {
        Geometry geometry = new EuclidGeometry();
        KMeans kMeans = new KMeans(geometry, 20);
        kMeans.buildClusters(new RandomNodesFactory(new Point(0,0,0), new Point(1000,1000,0), new FirstOrderRadioTransmitter(geometry)).buildNodes(800));
    }
}