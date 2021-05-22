package creational.singleton;
// Thread safe.
// Initialises even if not used
public class EagerInitialization {
    public static void main(String[] args) {
        EagerSingleton.getInstance().print();
    }
}

class EagerSingleton{
    private static final EagerSingleton instance =  new EagerSingleton();

    private EagerSingleton(){
        System.out.println("Constructor");
    }
    public static EagerSingleton getInstance(){
        return instance;
    }

    public void print(){
        System.out.println("Eager");
    }
}
