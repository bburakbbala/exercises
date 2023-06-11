package G181210058;

public class OpenCooler implements IProcess {
    private static OpenCooler instance;
    private final ITemperatureSensor temperatureSensorCelsius;

    private OpenCooler() {
        temperatureSensorCelsius = TemperatureSensorCelsius.getInstance(new Publisher());
    }

    public static OpenCooler getInstance(){
        if(instance == null) {
            instance=new OpenCooler();
        }
        return instance;
    }

    @Override
    public void transact() {
        System.out.println("Cooler is opening...");
        System.out.println("Cooler: opened");
        int temp = temperatureSensorCelsius.getTemperature();
        if (temp > 25) {
            temperatureSensorCelsius.setTemperature(25);
            System.out.println("Temperature is over 25 celsius cooler dropped temperature to 25 celsius.");
        } else {
            System.out.println("Temperature is below 25 celsius cooler is active but not working until temperature reached out 25 celsius.");
        }
    }
}