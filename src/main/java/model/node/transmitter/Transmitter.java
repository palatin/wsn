package model.node.transmitter;

import model.data.Data;
import model.node.Node;

public interface Transmitter {

    /// Method that calculate energy dissipation for sending data from sender to receiver
    /// and call @method receiveDataByNode
    /// @param data
    /// @param sender
    /// @param receiver
    /// @return energy dissipation
    double sendData(Data data, Node sender, Node receiver);

    /// Method that calculate energy dissipation for receiving data
    /// @param data
    /// @param receiver
    /// @return energy dissipation
    double receiveData(Data data, Node receiver);
}
