package G181210058;

public interface ICentralProcessingUnit {
    void sendToActuator(int userChoice) throws InterruptedException;
    void readTemperatureFromSensor();
}