package G181210058;

public class CloseCooler implements IProcess {
    private static CloseCooler instance;

    private CloseCooler() { }

    public static CloseCooler getInstance() {
        if(instance == null){
            instance = new CloseCooler();
        }
        return instance;
    }
    @Override
    public void transact() {
        System.out.println("Cooler is closing...");
        System.out.println("Cooler: closed");
    }
}