package java.mc.ports;


public final class Port {

    public native static Port getPort(byte port);

    public native void setEvent(byte event);

    public native void clearEvent(byte event);

    public native void setPinToZero(byte pin);

    public native void setPinToOne(byte pin);

    public native void setPins(byte pins);

    public native void setInputPin(byte pin);

    public native void setOutputPin(byte pin);

    public native void setIO(byte directions);

}