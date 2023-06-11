package G181210058;

import java.util.Scanner;

public class NetworkInterface implements INetworkInterface {
    private final Scanner scan = new Scanner(System.in);
    private static NetworkInterface instance;
    private final ICentralProcessingUnit centralProcessingUnit;

    private NetworkInterface() {
        centralProcessingUnit = new CentralProcessingUnit();
    }

    public static NetworkInterface getInstance() {
        if (instance == null) {
            instance = new NetworkInterface();
        }
        return instance;
    }

    public String getUsername() {
        return scan.next();
    }

    public String getPassword() {
        return scan.next();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void inputMessage(String message) {
        System.out.print(message);
    }

    public String getUserChoice() {
        return scan.next();
    }

    @Override
    public void sendRequest(int choice) throws InterruptedException {
        centralProcessingUnit.sendToActuator(choice);
    }

    @Override
    public void getTemperature() {
        centralProcessingUnit.readTemperatureFromSensor();
    }
}