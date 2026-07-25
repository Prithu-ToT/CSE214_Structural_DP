package src.leaf;

import src.composite.Room;

public class SmartThermostat extends LeafDevice {

    private static final double stdThermostatPower = 150;

    public SmartThermostat() {
        power = stdThermostatPower;
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
        String s = "Thermostat: " + (on ? "ON" : "OFF");
        return s;

    }

    @Override
    public Class<?> getWrappedClass() {
        return getClass();
    }

}

