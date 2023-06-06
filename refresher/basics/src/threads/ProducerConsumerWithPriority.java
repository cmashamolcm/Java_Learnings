package threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumerWithPriority {
    private Queue<Integer> q = new LinkedList<>();
    private int capacity = 5;

    private void produce() throws InterruptedException {
        synchronized (q){
            while (q.size() == capacity){
                q.wait();
            }
            q.add(new Random().nextInt());
            q.notifyAll();
        }

    }

    private void consume() throws InterruptedException {
        synchronized (q){
            while (q.size() == 0 ){
                q.wait();
            }
            System.out.println(q.peek());
            q.notifyAll();
        }

    }

    public static void main(String[] args) {
        ProducerConsumerWithPriority p =  new ProducerConsumerWithPriority();
        new Thread(()->{
            try {
                p.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(()->{
            try {
                p.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
