package creational.singleton;

//On demand creation.
//thread safe
//But not reflection safe
//Had issues in 1.5
public class ThreadSafeLazy {
    public static void main(String[] args) {
        ThreadSafeLazySingleton.getInstance().print();
    }
}

class ThreadSafeLazySingleton {
    private static ThreadSafeLazySingleton instance;

    private ThreadSafeLazySingleton() {
        System.out.println("Constructor");
    }

    public static ThreadSafeLazySingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeLazySingleton.class){
                if (instance == null) {
                    instance = new ThreadSafeLazySingleton();
                }
            }
        }
        return instance;
    }

    public void print() {
        System.out.println("Thread-safe Lazy");
    }
}
