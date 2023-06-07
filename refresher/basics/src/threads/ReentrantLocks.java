package threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLocks {
    private int counter = 0;
    private Lock lock =  new ReentrantLock();
    private Condition condition =  lock.newCondition();

    public void add() throws InterruptedException {

        lock.lock();// same as synchronized block definition start

        condition.await();// wait
        System.out.println("Adding");
        counter++;
        lock.unlock();// same as synchronized block definition end
    }

    public void print(){
        lock.lock();
        System.out.println("Printing" + counter);
        condition.signal();// notify
        lock.unlock();
    }
    public static void main(String[] args) {

        ReentrantLocks counter = new ReentrantLocks();
        new Thread(()-> {
            try {
                counter.add();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(()-> {
            counter.print();
        }).start();


    }
}
