package factory;

import exception.InvalidFactoryArgumentsException;
import exception.InvalidRandomRangeException;
import model.Point;
import model.node.Node;
import model.node.transmitter.Transmitter;
import util.Random;

import java.util.ArrayList;
import java.util.List;

public class RandomNodesFactory implements NodesFactory {


    private Point from;
    private Point to;
    private Transmitter transmitter;

    public RandomNodesFactory(Point from, Point to, Transmitter transmitter) {
        this.from = from;
        this.to = to;
        this.transmitter = transmitter;
    }

    @Override
    public List<Node> buildNodes(int count) throws InvalidFactoryArgumentsException {

        List<Node> nodes = new ArrayList<>(count);

        List<Integer> x = null;
        List<Integer> y = null;
        List<Integer> z = null;
        try {
            x = Random.uniqueRandomIntsInRange(from.x, to.x, count);
            y = Random.uniqueRandomIntsInRange(from.y, to.y, count);
            z = Random.uniqueRandomIntsInRange(from.z, to.z, count);
        } catch (InvalidRandomRangeException e) {
            throw new InvalidFactoryArgumentsException(e);
        }

        for (int i = 0; i < count; i++) {
            nodes.add(new Node(i, new Point(x.get(i), y.get(i), z.get(i)),100, 1000, transmitter));
        }

        return nodes;
    }

}
