package tools.clustering;

import exception.InvalidClusteringArgumentsException;

import javafx.util.Pair;
import model.Clusters;
import model.Point;
import model.node.Node;
import util.ArrayHelper;
import util.geometry.Geometry;

import java.util.ArrayList;
import java.util.List;

public abstract class Clustering {

    private Geometry geometry;


    public Clustering(Geometry geometry) {
        this.geometry = geometry;
    }

    protected Pair<Integer, Double> findNearestPoint(Point center, List<Point> points) {
        List<Double> distances = new ArrayList<>(points.size());
        points.forEach(point -> distances.add(geometry.distanceBetweenPoints(center, point)));
        return ArrayHelper.getSmallestElement(distances);
    }

    protected void assignNodesToClusters(List<Point> centers, Clusters clusters, List<Node> nodes) {

        for (int i = 0; i < nodes.size(); i++) {
            Pair<Integer, Double> nearest = findNearestPoint(nodes.get(i).getLocation(), centers);
            //put node's index to nearest cluster with distance
            clusters.addNodeToCluster(nearest.getKey(), i, nearest.getValue());
        }

    }

    public abstract Clusters buildClusters(List<Node> nodes) throws InvalidClusteringArgumentsException;

}
