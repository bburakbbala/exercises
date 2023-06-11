package G181210058;

public interface ITemperatureSensor {
    void readTemperature();
    void addSubscriber(IObserver subscriber);
    void setTemperature(int temperature);
    int getTemperature();
}