package model.data;

import model.node.Node;

public class Data {


    private Node sender;
    private Node receiver;
    private long dataLength;

    ///
    /// @param sender - data sender node
    /// @param receiver -data receiver node
    /// @param dataLength - data length in bits
    public Data(Node sender, Node receiver, long dataLength) {
        this.sender = sender;
        this.receiver = receiver;
        this.dataLength = dataLength;
    }


    public Node getSender() {
        return sender;
    }

    public Node getReceiver() {
        return receiver;
    }

    public long getDataLength() {
        return dataLength;
    }
}
