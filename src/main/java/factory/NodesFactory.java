package factory;

import exception.InvalidFactoryArgumentsException;
import model.node.Node;

import java.util.List;

public interface NodesFactory {


    List<Node> buildNodes(int count) throws InvalidFactoryArgumentsException;
}
