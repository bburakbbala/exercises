package G181210058;

public class Main {
    public static void main(String[] args) throws InterruptedException {
           SmartDevice smartDevice = SmartDevice.getInstance(PostgreSQLDBUser.getInstance());
           smartDevice.start();
    }
}