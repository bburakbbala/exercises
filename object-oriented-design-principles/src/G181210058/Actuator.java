package G181210058;

public class Actuator implements IActuator {
    private static Actuator instance;
    private final IProcess openCooler;
    private final IProcess closeCooler;

    private Actuator() {
        openCooler = OpenCooler.getInstance();
        closeCooler = CloseCooler.getInstance();
    }

    public static Actuator getInstance() {
        if (instance == null) {
            instance = new Actuator();
        }
        return instance;
    }

    @Override
    public void openCooler() {
        openCooler.transact();
    }

    @Override
    public void closeCooler() {
        closeCooler.transact();
    }
}