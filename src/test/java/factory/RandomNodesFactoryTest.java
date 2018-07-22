package factory;

import exception.InvalidFactoryArgumentsException;
import model.Point;
import model.node.Node;
import model.node.transmitter.FirstOrderRadioTransmitter;
import org.junit.jupiter.api.Test;
import util.geometry.EuclidGeometry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RandomNodesFactoryTest {

    @Test
    public void nodeCounts() throws InvalidFactoryArgumentsException {
        RandomNodesFactory factory = new RandomNodesFactory(new Point(0,0,0), new Point(20, 20, 0), new FirstOrderRadioTransmitter(new EuclidGeometry()));

        List<Node> nodes = factory.buildNodes(10);
        assertEquals(nodes.size(), 10);
    }

    @Test
    public void nodeDuplicatesLocation() throws InvalidFactoryArgumentsException {
        RandomNodesFactory factory = new RandomNodesFactory(new Point(0,0,0), new Point(20, 20, 0), new FirstOrderRadioTransmitter(new EuclidGeometry()));

        List<Node> nodes = factory.buildNodes(20);
        final Set<Point> points = new HashSet();

        nodes.forEach(node -> points.add(node.getLocation()));

        assertEquals(points.size(), nodes.size());
    }

}