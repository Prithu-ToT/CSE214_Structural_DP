package src;

public interface SmartDevice {
    public boolean activate();
    public boolean deactivate();
    public double getPowerUsage();
    public String getStatus();
    public boolean isOn();
    Class<?> getWrappedClass();
}
