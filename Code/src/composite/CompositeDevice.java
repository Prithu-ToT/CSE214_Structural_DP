package src.composite;

import java.util.List;

import src.SmartDevice;

public abstract class CompositeDevice implements SmartDevice{
    protected String name;
    protected List<SmartDevice> childList;
    
    public void addToChildern(SmartDevice device){
        childList.addLast(device);
    }

    public void removeDevice(SmartDevice device){
        childList.removeIf(child -> child.equals(device));
    }

    @Override
    public boolean activate() {
        boolean success = true;
        for (SmartDevice smartDevice : childList) {
            success &= smartDevice.activate();
        }
        return success;
    }

    @Override
    public boolean deactivate() {
        boolean success = true;
        for (SmartDevice smartDevice : childList) {
            success &= smartDevice.deactivate();
        }
        return success;
    }
    
    @Override
    public double getPowerUsage() {

        double p = 0;
        for (SmartDevice smartDevice : childList) {
            p += smartDevice.getPowerUsage();
        }
        return p;
    }

    @Override
    public boolean isOn(){
        boolean o = false;
        for (SmartDevice smartDevice : childList) {
            o |= smartDevice.isOn();
        }
        return o;
    }
}
