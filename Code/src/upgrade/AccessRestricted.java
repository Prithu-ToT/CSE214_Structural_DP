package src.upgrade;

import src.SmartDevice;

public class AccessRestricted extends SmartDeviceUpgrade {

    private int pin;
    private boolean isLocked;

    public AccessRestricted(SmartDevice device, int pin) {
        super(device);
        this.pin = pin;
        isLocked = true;
    }

    public boolean activate() {
        if (isLocked) return false;
        else return super.activate();
    }

    public boolean deactivate() {
        if (isLocked) return false;
        else return super.deactivate();
    }

    public boolean unlock(int testPin) {
        if (pin == testPin) {
            isLocked = false;
            return true;
        } else return false;
    }

    public boolean lock(int testPin) {
        if (pin == testPin) {
            isLocked = true;
            return true;
        } else return false;
    }

    public String getStatus() {
        if (isLocked) return super.getStatus() + " [LOCKED]";
        else return super.getStatus();
    }
}
