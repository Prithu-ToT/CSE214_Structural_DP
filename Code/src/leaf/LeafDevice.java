 package src.leaf;

import src.SmartDevice;

public abstract class LeafDevice implements SmartDevice {
    protected double power;
    protected boolean on = false;

    public boolean isOn() {
		return on;
	}

    public double getPowerUsage(){
        if(on) return power;
        else return 0;
    }
}