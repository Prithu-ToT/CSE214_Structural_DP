package src.upgrade;

import src.SmartDevice;

public class TimerControlled extends SmartDeviceUpgrade {

    private int timerSeconds;
    private boolean timerRunning = false;

    public TimerControlled(SmartDevice device, int timerSeconds) {
        super(device);
        this.timerSeconds = timerSeconds;
    }

    public boolean activate() {
        timerRunning = true;
        return super.activate();
    }

    public boolean deactivate() {
        timerRunning = false;
        return super.deactivate();
    }

    public void simulateTimerExpiry() {
        if (timerRunning) deactivate();
    }

    public String getStatus() {
        if (timerRunning) return super.getStatus() + " (auto-off in " + timerSeconds + "s)";
        else return super.getStatus();
    }
}
