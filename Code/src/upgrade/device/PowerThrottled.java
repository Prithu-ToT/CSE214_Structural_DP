package src.upgrade.device;
import src.SmartDevice;
public class PowerThrottled extends SmartDeviceUpgrade {

    private double powerCap;

    public PowerThrottled(SmartDevice device, double powerCap) {
        super(device);
        this.powerCap = powerCap;
    }

    public double getPowerUsage() {
        SmartDevice device = getDevice();
        if(!device.isOn()) return 0;
        if(device.getPowerUsage() > powerCap) return powerCap;
        return device.getPowerUsage();
    }

    public String getStatus() {
        SmartDevice device = getDevice();
        if(!device.isOn()) return device.getStatus();
        if(device.getPowerUsage() > powerCap) return device.getStatus() + " [throttled to " + powerCap + "W]";
        return device.getStatus();
    }

}