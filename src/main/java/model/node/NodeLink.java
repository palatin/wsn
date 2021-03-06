package model.node;

public class NodeLink {

    private Node link;
    private float distance;

    static long linkMemory = 10;

    public NodeLink() {}

    public NodeLink(Node link, float distance) {
        this.link = link;
        this.distance = distance;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;
        return ((NodeLink) obj).link == link;
    }

    public NodeLink clone() {
        return new NodeLink(link, distance);
    }
}
