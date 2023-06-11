package G181210058;

import java.util.HashMap;
import java.util.Map;

public class SmartDevice {
    public enum UserChoice {
        OPEN_COOLER(1),
        CLOSE_COOLER(2),
        TEMPERATURE_MEASUREMENT(3),
        EXIT(4),
        INVALID(5);

        private final int value;
        private static final Map<Object, Object> map = new HashMap<>();

        UserChoice(int value) {
            this.value = value;
        }

        static {
            for (UserChoice pageType : UserChoice.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static UserChoice valueOf(int pageType) {
            return (UserChoice) map.get(pageType);
        }
    }
    public enum CoolerStatus {
        COOLER_OPENED,
        COOLER_CLOSED
    }
    private final INetworkInterface networkInterface;
    private final IDB database;
    private static SmartDevice instance;

    private SmartDevice(IDB database) {
        networkInterface = NetworkInterface.getInstance();
        this.database = database;
    }

    public static SmartDevice getInstance(IDB database) {
        if(instance == null) {
            instance = new SmartDevice(database);
        }
        return instance;
    }

    public void start() throws InterruptedException {
        networkInterface.printMessage("Device is opening...");
        networkInterface.printMessage("Device is open.");
        networkInterface.printMessage("Type your username and password to access the smart device!");

        String username = null;
        String password = null;
        int count = 0;
        boolean isUserValid = false;
        while (count < 3 && !isUserValid) {
            networkInterface.inputMessage("Username: ");
            username = networkInterface.getUsername();
            networkInterface.inputMessage("Password: ");
            password = networkInterface.getPassword();
            isUserValid = database.authenticateUser(username,password);
            count++;
            if (!isUserValid) System.out.println("User is not valid!");
        }

        if (isUserValid) {
            User user = new User.UserBuilder(username, password)
                    .email("burak.bala@ogr.sakarya.edu.tr").build();
            System.out.println("User: " + user.getUsername() + " email: " + user.getUserMail());
            this.chooseAction();
        } else {
            networkInterface.printMessage("User could not be authenticated!");
        }
    }
    private void chooseAction() throws InterruptedException {
        UserChoice choice;
        CoolerStatus coolerStatus = CoolerStatus.COOLER_CLOSED;
        do {
            choice = this.showMenu();
            switch (choice) {
                case OPEN_COOLER -> {
                    if (coolerStatus.equals(CoolerStatus.COOLER_OPENED)) {
                        System.out.println("Cooler is already open!");
                        continue;
                    }
                    networkInterface.sendRequest(UserChoice.OPEN_COOLER.value);
                    coolerStatus = CoolerStatus.COOLER_OPENED;
                }
                case CLOSE_COOLER -> {
                    if (coolerStatus.equals(CoolerStatus.COOLER_CLOSED)) {
                        System.out.println("Cooler is not open!");
                        continue;
                    }
                    networkInterface.sendRequest(UserChoice.CLOSE_COOLER.value);
                    coolerStatus = CoolerStatus.COOLER_CLOSED;
                }
                case TEMPERATURE_MEASUREMENT -> {
                    networkInterface.getTemperature();
                }
                case EXIT -> System.out.println("Exiting...");
                case INVALID -> System.out.println("Invalid input!");
            }
        } while(!choice.equals(UserChoice.EXIT));
    }

    private UserChoice showMenu() {
        networkInterface.printMessage("**********************");
        networkInterface.printMessage("1-Open cooler");
        networkInterface.printMessage("2-Close cooler");
        networkInterface.printMessage("3-Display temperature");
        networkInterface.printMessage("4-Exit");
        networkInterface.printMessage("**********************");
        UserChoice userChoice = null;
        String choice = networkInterface.getUserChoice();
        switch (choice) {
            case "1" -> userChoice = UserChoice.valueOf(1);
            case "2" -> userChoice = UserChoice.valueOf(2);
            case "3" -> userChoice = UserChoice.valueOf(3);
            case "4" -> userChoice = UserChoice.valueOf(4);
            default -> userChoice = UserChoice.valueOf(5);
        }
        return userChoice;
    }
}
