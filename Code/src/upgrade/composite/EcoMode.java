package src.upgrade.composite;
import src.composite.CompositeDevice;
import src.SmartDevice;

import java.util.List;

public class EcoMode extends CompositeUpgrade{

    private double ecoBudget;

    public EcoMode(CompositeDevice device, double ecoBudget) {
        this.compositeDevice = device;
        this.ecoBudget = ecoBudget;
    }

    @Override
    public boolean activate(){
        boolean activated = compositeDevice.activate();
        shedExcessPower();
        return activated;
    }

    private void shedExcessPower() {
        if (compositeDevice.getPowerUsage() <= ecoBudget) {
            return;
        }

        List<SmartDevice> children = compositeDevice.childList;
        for (int i = children.size() - 1; i >= 0 && compositeDevice.getPowerUsage() > ecoBudget; i--) {
            children.get(i).deactivate();
        }
    }



    @Override
    public String getStatus() {
        return "[ECO: " + ecoBudget + "W budget]\n" + compositeDevice.getStatus();
    }
    
}