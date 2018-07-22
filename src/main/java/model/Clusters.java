package model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Clusters {

    private List<TreeMap<Double, Integer>> clusters;

    public Clusters(int count) {
        clusters = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            getClusters().add(new TreeMap<>());
        }
    }

    public int size() {
        return getClusters().size();
    }

    public void clearClusters() {
        for (int i = 0; i < size(); i++) {
            clusters.get(i).clear();
        }
    }

    public void addNodeToCluster(int cluster, int node, double distance) {
        getClusters().get(cluster).put(distance, node);
    }

    public List<TreeMap<Double, Integer>> getClusters() {
        return clusters;
    }
}
