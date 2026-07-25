package src.upgrade.device;
import src.SmartDevice;
public class TimerControlled extends SmartDeviceUpgrade {

    private int timerSeconds;
    private boolean timerRunning = false;

    public TimerControlled(SmartDevice device, int timerSeconds) {
        super(device);
        this.timerSeconds = timerSeconds;
    }

    public boolean activate() {
        timerRunning = true;            // code for timer activation goes here
        return super.activate();
    }

    public boolean deactivate() {
        timerRunning = false;            // code for timer deactivation goes here
        return super.deactivate();
    }

    public void simulateTimerExpiry() {     // is called when timer has been done
        if(timerRunning) deactivate();
    }

    public String getStatus() {
        if(timerRunning) return super.getStatus() + " (auto-off in " + timerSeconds + "s)";
        else return super.getStatus();
    }
    
}