package G181210058;

public class Subscriber1 implements IObserver {

    @Override
    public void update(String message) {
        System.out.println("Subscriber1 gets = " + message);
    }
}