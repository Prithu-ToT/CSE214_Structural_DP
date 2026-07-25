package src.composite;

import java.util.ArrayList;

import src.SmartDevice;

public class Home extends CompositeDevice{
    public Home(String name){
        this.childList = new ArrayList<>();
        this.name = name;
    }

    @Override
    public String getStatus() {
        StringBuilder sb = new StringBuilder("=== " + name + " ===");
        for (SmartDevice device : childList) {
            sb.append("\n  ")
                .append(device.getStatus());
        }

        return sb.toString();
    }

    public void addRoom(SmartDevice device){
        if(device.getWrappedClass().equals(Home.class)){
            throw new IllegalArgumentException("A Home cannot contain another Home.");
        }
        addToChildern(device);
    }

    @Override
    public Class<?> getWrappedClass() {
        return getClass();
    }

    
}
