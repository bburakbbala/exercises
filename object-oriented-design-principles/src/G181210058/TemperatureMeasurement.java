package G181210058;

public class TemperatureMeasurement implements IProcess {
    private static TemperatureMeasurement instance;

    private TemperatureMeasurement() { }

    public static TemperatureMeasurement getInstance() {
        if (instance == null) {
            instance = new TemperatureMeasurement();
        }
        return instance;
    }

    @Override
    public void transact() {
        System.out.print("Temperature: ");
    }
}