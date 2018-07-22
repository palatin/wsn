package model.node;

import model.Point;
import model.data.Data;
import model.node.transmitter.Transmitter;

import java.util.List;

public class Node {

    private long id = 0;
    private Point location;
    private double energy = 0;
    private List<NodeLink> links;
    private long memoryLimit;
    private long currentMemory = 0;
    private boolean alive = true;
    private final Transmitter transmitter;

    public Node(long id, Point location, double energy, long memoryLimit, Transmitter transmitter) {
        this.id = id;
        this.location = location;
        this.energy = energy;
        this.memoryLimit = memoryLimit;
        this.transmitter = transmitter;
    }

    public long getId() {
        return id;
    }

    public Point getLocation() {
        return location.copy();
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

    public boolean sendData(Data data, Node receiver) {
        return spendEnergy(transmitter.sendData(data, this, receiver));
    }

    public boolean receiveData(Data data) {
        return spendEnergy(transmitter.receiveData(data, this)); //TODO receive data
    }

    private boolean spendEnergy(double energy) {
        boolean enoughEnergy = getEnergy() >= energy;
        setEnergy(enoughEnergy ? getEnergy() - energy : 0);
        return enoughEnergy;
    }

    private boolean canStore(long memory) {
        return currentMemory + memory <= memoryLimit;
    }
}
