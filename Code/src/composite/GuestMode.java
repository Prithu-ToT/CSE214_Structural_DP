package src.composite;

import src.SmartDevice;

import java.util.Set;

public class GuestMode extends CompositeUpgrade {

    private Set<Class<?>> allowedTypes;

    public GuestMode(CompositeDevice device, Set<Class<?>> allowedTypes) {
        this.compositeDevice = device;
        this.allowedTypes = allowedTypes;
    }

    @Override
    public boolean activate() {
        boolean success = true;
        for (SmartDevice device : compositeDevice.childList) {
            if (allowedTypes.contains(device.getWrappedClass())) {
                success &= device.activate();
            }
        }
        return success;
    }

    @Override
    public double getPowerUsage() {
        double total = 0;
        for (SmartDevice device : compositeDevice.childList) {
            if (allowedTypes.contains(device.getWrappedClass())) {
                total += device.getPowerUsage();
            }
        }
        return total;
    }

    @Override
    public String getStatus() {
        StringBuilder sb = new StringBuilder("[GUEST MODE]\n");
        sb.append("[").append(compositeDevice.name).append("]");
        for (SmartDevice device : compositeDevice.childList) {
            sb.append("\n  ").append(device.getStatus());
            if (!allowedTypes.contains(device.getWrappedClass())) {
                sb.append(" [guest-restricted]");
            }
        }
        return sb.toString();
    }
}
