package src.leaf;

import src.composite.Room;

public class SmartLight extends LeafDevice {
	
    private static final double stdLightPower = 10;

    public SmartLight(){
        power = stdLightPower;
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
		String s = "Light: " + (on ? "ON" : "OFF");
		return s;
	}

	@Override
    public Class<?> getWrappedClass() {
        return getClass();
    }
}
