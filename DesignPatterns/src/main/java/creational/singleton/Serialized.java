package creational.singleton;

import java.io.*;
//Safe but two hashcodes in serializing.
public class Serialized {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerialSingleton serialSingleton = SerialSingleton.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(("serialSingleton.txt")));
        oos.writeObject(serialSingleton);
        System.out.println("Old HashCode = " + serialSingleton.hashCode());

        SerialSingleton serialSingletonParsed = (SerialSingleton) new ObjectInputStream(new FileInputStream("serialSingleton.txt")).readObject();
        System.out.println("New HashCode = " + serialSingletonParsed.hashCode());
    }
}


class SerialSingleton implements Serializable {
    private static final long serialVersionUID = -7604766932017737115L;
    private static SerialSingleton instance;

    private SerialSingleton() {
        System.out.println("Constructor");
    }

    public static SerialSingleton getInstance() {
        if (instance == null) {
            synchronized (SerialSingleton.class){
                if (instance == null) {
                    instance = new SerialSingleton();
                }
            }
        }
        return instance;
    }

    public void print() {
        System.out.println("Thread-safe Lazy");
    }

    //If we use readResolve() method, it will give same singleton object even in deserialization
    public Object readResolve(){
        return getInstance();
    }
}
