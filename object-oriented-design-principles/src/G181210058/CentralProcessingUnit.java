package G181210058;

public class CentralProcessingUnit implements ICentralProcessingUnit {
    private final IActuator actuator;
    private final ITemperatureSensor temperatureSensor;
    public CentralProcessingUnit() {
        actuator = Actuator.getInstance();
        temperatureSensor = TemperatureSensorCelsius.getInstance(new Publisher());
        temperatureSensor.addSubscriber(new Subscriber1());
    }

    public void sendToActuator(int userChoice) throws InterruptedException {
        if (userChoice == 1) {
            actuator.openCooler();
        }
        if (userChoice == 2) {
            actuator.closeCooler();
        }
    }

    @Override
    public void readTemperatureFromSensor() {
        temperatureSensor.readTemperature();
    }
}