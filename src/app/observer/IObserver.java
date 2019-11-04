package app.observer;

/*The substitute for deprecated Java class java.util.Observable
  NOTE: The class that implement this interface needs to have List<IListener> observers among its attributes, see: Model
*/
public interface IObserver {
    void addObserver(IListener listener);
    void removeObserver(IListener listener);
    void notifyObservers(Object event);
}

