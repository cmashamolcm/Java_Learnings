package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerProblem {
    static class MyQueue{
        List<Integer> list = new ArrayList<>();
        int capacity = 5;
        void produce(int number) throws InterruptedException {
            synchronized (list){
                while(list.size() == capacity){
                    list.wait();
                }
                list.add(number);
                list.notifyAll();
            }

        }

        void consume() throws InterruptedException {
            synchronized (list){
                while(list.size() == 0){
                    list.wait();
                }
                System.out.println(list.get(0));
                list.remove(list.get(0));
                list.notifyAll();
            }

        }
    }

    public static void main(String[] args) {
        //withSimpleIntrinsicLock();
        withReentrantLock();

    }

    private static void withReentrantLock() {
        MyQueueWithLock q = new MyQueueWithLock();
        new Thread(()->{
            while(true){
                try {

                    q.produce(new Random().nextInt(10));

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(()->{
            while(true){
                try {

                    q.consume();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private static void withSimpleIntrinsicLock() {
        MyQueue q = new MyQueue();
        new Thread(()->{
            while(true){
                try {

                    q.produce(new Random().nextInt(10));

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(()->{
            while(true){
                try {

                    q.consume();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }


    static class MyQueueWithLock{
        List<Integer> list = new ArrayList<>();
        int capacity = 5;

        Lock lock = new ReentrantLock();
        Condition full = lock.newCondition();
        Condition empty = lock.newCondition();
        void produce(int number) throws InterruptedException {
            lock.lock();
                while(list.size() == capacity){
                    full.await();
                }
                list.add(number);
                empty.signal();
            lock.unlock();

        }

        void consume() throws InterruptedException {
            lock.lock();
                while(list.size() == 0){
                    empty.await();
                }
                System.out.println(list.get(0));
                list.remove(list.get(0));
                full.signal();
            lock.unlock();

        }
    }
}

