package G181210058;

public interface INetworkInterface {
    String getUsername();
    String getPassword();
    String getUserChoice();
    void getTemperature();
    void sendRequest(int choice) throws InterruptedException;
    void inputMessage(String message);
    void printMessage(String message);
}