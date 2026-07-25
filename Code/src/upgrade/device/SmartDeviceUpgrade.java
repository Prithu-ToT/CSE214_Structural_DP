package src.upgrade.device;
import src.SmartDevice;

public abstract class SmartDeviceUpgrade implements SmartDevice{

    protected SmartDevice device;

    public SmartDeviceUpgrade(SmartDevice device) {
        this.device = device;
    }

    public final SmartDevice getDevice() {
        return device;
    }

    public final boolean isOn() {
        return device.isOn();
    }

    public double getPowerUsage() {
        return device.getPowerUsage();
    }

    public boolean activate() {
        return device.activate();
    }

    public boolean deactivate() {
        return device.deactivate();
    }

    public String getStatus() {
        return device.getStatus();
    }

    
    @Override
    public Class<?> getWrappedClass() {
        return device.getWrappedClass();
    }

}