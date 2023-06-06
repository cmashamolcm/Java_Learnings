package threads;

public class DeadLock {


    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        new Thread(()->{
            synchronized (lock1){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2){
                    while (true){
                        System.out.println("Waiting 1");
                    }
                }
            }

        }).start();

        new Thread(()->{
            synchronized (lock2){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock1){
                    while (true) {
                        System.out.println("Waiting 2");
                    }
                }
            }

        }).start();
    }
}
