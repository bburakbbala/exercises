package G181210058;

import java.util.Random;

public class TemperatureSensorCelsius implements ITemperatureSensor {
    private static final Random random = new Random();
    private static TemperatureSensorCelsius instance;
    private int temperature;
    private final ISubject publisher;
    private final IProcess process;

    private TemperatureSensorCelsius(ISubject publisher) {
        this.publisher = publisher;
        this.process = TemperatureMeasurement.getInstance();
        this.temperature = Math.abs(random.nextInt() % 100);
    }

    public static TemperatureSensorCelsius getInstance(ISubject publisher) {
        if (instance == null) {
            instance = new TemperatureSensorCelsius(publisher);
        }
        return instance;
    }

    public void setTemperature(int temperature) {
        if (temperature < 100) {
            this.temperature = temperature;
        }
    }

    public int getTemperature() {
        return temperature;
    }

    public void addSubscriber(IObserver subscriber) {
        publisher.attach(subscriber);
    }

    @Override
    public void readTemperature() {
        process.transact();
        System.out.println(temperature + " celsius");

        if (temperature > 70) {
            publisher.notify("Temperature is over 70 celsius, you should open the cooler!");
        }
    }
}