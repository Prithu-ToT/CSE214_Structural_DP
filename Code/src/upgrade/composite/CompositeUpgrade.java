    package src.upgrade.composite;

    import src.SmartDevice;
    import src.composite.CompositeDevice;

    public abstract class CompositeUpgrade implements SmartDevice{

        protected CompositeDevice  compositeDevice;

        public void addToChildern(SmartDevice device){
            compositeDevice.addToChildern(device);
        }

        public void removeDevice(SmartDevice device){
            compositeDevice.removeDevice(device);
        }

        @Override
        public boolean activate() {
            return compositeDevice.activate();
        }

        @Override
        public boolean deactivate() {
            return compositeDevice.deactivate();
        }
        
        @Override
        public double getPowerUsage() {
            return compositeDevice.getPowerUsage();
        }

        @Override
        public final boolean isOn(){
            return compositeDevice.isOn();
        }

        
        @Override
        public Class<?> getWrappedClass() {
            return compositeDevice.getWrappedClass();
        }

    }
