package src.composite;

import java.util.ArrayList;
import src.SmartDevice;

public class Room extends CompositeDevice{


    public Room(String name){
        this.childList = new ArrayList<>();
        this.name = name;
    }

    @Override
    public String getStatus() {
        StringBuilder sb = new StringBuilder("[" + name + "]");
        for (SmartDevice device : childList) {
            sb.append("\n  ")
                .append(device.getStatus());
        }

        return sb.toString();

    }

    public void addDevice(SmartDevice device){
        if (CompositeDevice.class.isAssignableFrom(device.getWrappedClass())) {
            throw new IllegalArgumentException("Rooms cannot contain composite devices.");
        }
        addToChildern(device);
    }

    @Override
    public Class<?> getWrappedClass() {
        return getClass();
    }

}
