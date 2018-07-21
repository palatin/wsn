package model.node.transmitter;

import model.data.Data;
import model.node.Node;
import util.geometry.Geometry;

public class FirstOrderRadioTransmitter implements Transmitter {

    private Geometry geometry;
    private double eElec = 0.00000005;
    private double eAmp = 0.00000000000000013;
    private double eFs = 0.00000000001;

    public FirstOrderRadioTransmitter(Geometry geometry) {
        this.geometry = geometry;
    }

    public FirstOrderRadioTransmitter(Geometry geometry, double eElec, double eAmp, double eFs) {
        this.geometry = geometry;
        this.eElec = eElec;
        this.eAmp = eAmp;
        this.eFs = eFs;
    }

    public double sendData(Data data, Node sender, Node receiver) {
        double d0 = Math.sqrt(eFs / eAmp);
        double d = geometry.distanceBetweenPoints(sender.getLocation(), receiver.getLocation());
        return eElec + data.getDataLength() * eFs * Math.pow(d, d < d0 ? 2 : 4);
    }

    public double receiveData(Data data, Node receiver) {
        return data.getDataLength() * eElec;
    }
}
