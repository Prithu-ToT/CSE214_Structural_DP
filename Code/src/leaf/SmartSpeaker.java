package src.leaf;

import src.composite.Room;

public class SmartSpeaker extends LeafDevice {

    private static final double stdSpeakerPower = 5;

    public SmartSpeaker() {
        power = stdSpeakerPower;
        on = false;
    }

    public boolean activate() {
        on = true;
        return true;
    }

    public boolean deactivate() {
        on = false;
        return true;
    }

    public String getStatus() {
        String s = "Speaker: " + (on ? "Playing" : "Idle");
        return s;
    }

    @Override
    public Class<?> getWrappedClass() {
        return getClass();
    }
}

