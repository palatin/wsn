package tools.clustering;

import exception.InvalidClusteringArgumentsException;
import exception.InvalidRandomRangeException;
import model.Clusters;
import model.Point;
import model.node.Node;
import util.Random;
import util.geometry.Geometry;

import java.util.ArrayList;
import java.util.List;

public class KMeans extends Clustering {

    public KMeans(Geometry geometry, int clusterCounts) {
        super(geometry, clusterCounts);
    }

    public Clusters buildClusters(List<Node> nodes) throws InvalidClusteringArgumentsException {

        List<Point> centers = null;
        try {
            centers = initClusters(nodes, clusters.size());
        } catch (InvalidRandomRangeException e) {
            throw new InvalidClusteringArgumentsException(e);
        }


        boolean stop = false;

        while(!stop) {
            clusters.clearClusters();
            assignNodesToClusters(centers, nodes);
            stop = calculateNewCenters(clusters, centers, nodes);
        }


        //change centers to nearest nodes
        for (int i = 0; i < clusters.size(); i++) {
            centers.set(i, nodes.get(clusters.getClusters().get(i).firstEntry().getValue()).getLocation().copy());
        }

        clusters.clearClusters();
        assignNodesToClusters(centers, nodes);

        return clusters;
    }

    private List<Point> initClusters(List<Node> nodes, int clusterCount) throws InvalidRandomRangeException {

        List<Point> clusters = new ArrayList<>(clusterCount);
        Random.uniqueRandomIntsInRange(0, nodes.size(), clusterCount).forEach(cluster -> clusters.add(nodes.get(cluster).getLocation().copy()));
        return clusters;
    }

    private boolean calculateNewCenters(Clusters clusters, List<Point> centers, List<Node> nodes) {

        boolean isSameCentroids = true;

        for (int i = 0; i < clusters.size(); i++) {
            Point old = centers.get(i).copy();
            Point newPoint = new Point();
            clusters.getClusters().get(i).forEach((dist, node) -> newPoint.add(nodes.get(node).getLocation()));
            newPoint.divide(clusters.getClusters().get(i).size());
            centers.set(i, newPoint);
            if(!old.equals(newPoint))
                isSameCentroids = false;
        }

        return isSameCentroids;
    }
}
