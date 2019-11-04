package app.observer;

//Substitute for Java interface java.util.Observer
public interface IListener {
    void update(Object event);
}