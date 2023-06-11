package G181210058;

public interface ISubject {
    void attach(IObserver observer);
    void detach(IObserver observer);
    void notify(String message);
}
