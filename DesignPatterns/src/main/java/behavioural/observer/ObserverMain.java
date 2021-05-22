package behavioural.observer;

import java.util.ArrayList;
import java.util.List;

public class ObserverMain {
    public static void main(String[] args) {
        Subject subject = new Subject();
        Observer observer1 = new Observer(subject, "OBS-1");
        Observer observer2 = new Observer(subject, "OBS-2");
        subject.changed("New message1");
        subject.changed("New message2");
    }
}

class Subject{
    List<Observer> observerList = new ArrayList<>();

    public void register(Observer observer){
        System.out.println("Registered " + observer.getName());
        observerList.add(observer);
    }

    public void changed(String message){
        observerList.forEach(observer -> observer.processMessage(message));
    }
}

class Observer{
    public String getName() {
        return name;
    }

    String name;

    Observer(Subject subject, String name){
        this.name = name;
        subject.register(this);
    }

    public void processMessage(String msg){
        System.out.println("Received message " + msg);
    }

}
