package creational.singleton;
//On demand creation.
//Not thread safe
public class LazyInitialization  {
    public static void main(String[] args) {
        LazySingleton.getInstance().print();
    }
}

class LazySingleton{
    private static LazySingleton instance;

    private LazySingleton(){
        System.out.println("Constructor");
    }
    public static LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }

    public void print(){
        System.out.println("Lazy");
    }
}

