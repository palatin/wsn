package model.node;

import model.Point;

import java.util.List;

public class Node {

    private long id = 0;
    private Point location;
    private double energy = 0;
    private List<NodeLink> links;
    private float memoryLimit;
    private float currentMemory = 0;
    private boolean alive = true;

    public Node(long id, Point location, double energy, float memoryLimit) {
        this.id = id;
        this.location = location;
        this.energy = energy;
        this.memoryLimit = memoryLimit;
    }

    public long getId() {
        return id;
    }

    public Point getLocation() {
        return location.clone();
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public float getMemoryLimit() {
        return memoryLimit;
    }

    public float getCurrentMemory() {
        return currentMemory;
    }

    public List<NodeLink> getLinks() {
        return links;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;
        return ((Node) obj).id == id;
    }

    public boolean addLink(NodeLink nodeLink) {
        if(!canStore(NodeLink.linkMemory))
            return false;
        links.add(nodeLink);
        return true;
    }

    public void removeLink(int position) {
        links.remove(position);
        currentMemory -= NodeLink.linkMemory;
    }

    private boolean spendEnergy(double energy) {
        boolean enoughEnergy = getEnergy() >= energy;
        setEnergy(enoughEnergy ? getEnergy() - energy : 0);
        return enoughEnergy;
    }

    private boolean canStore(float memory) {
        return currentMemory + memory <= memoryLimit;
    }
}
