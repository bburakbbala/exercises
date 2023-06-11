package G181210058;

import java.util.ArrayList;
import java.util.List;

public class Publisher implements ISubject {

    private final List<IObserver> subscribers = new ArrayList<>();

    public Publisher() { }

    @Override
    public void attach(IObserver observer) {
        subscribers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notify(String message) {
        for(IObserver obs : subscribers){
            obs.update(message);
        }
    }
}
