package threads;

public class BasicsOfThread {
    public static void main(String[] args) {
        System.out.println("Threads");
        Thread thread = new Thread();
        thread.setDaemon(true);
        System.out.println(thread.getPriority());
        thread.start();

        SharedResource counter = new SharedResource();
        Thread producer = new Producer(counter, "producer");
        producer.start();

        Thread consumer = new Consumer(counter, "consumer 1");
        consumer.start();

        Thread consumer2 = new Consumer(counter, "consumer 2");
        consumer2.start();
    }
}

class SharedResource{

    Object lock = new Object();
    int counter = 0;

    int increment(){
        //synchronized (lock){
            counter++;
        //}
        return counter;
    }

    int getCounter(){
        synchronized (lock) {
            System.out.println("Currently locked by: " + Thread.currentThread().getName());
            return counter;
        }
    }
}

class Producer extends Thread{
    SharedResource counter;
    Producer(SharedResource counter, String threadName){
        this.counter = counter;
        this.setName(threadName);
    }
    @Override
    public void run() {
        int i = 0;
        while(i<10){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Producer: " + counter.increment());
            i++;
        }
    }
}

class Consumer extends Thread{

    SharedResource counter;
    Consumer(SharedResource counter, String threadName){
        this.counter = counter;
        this.setName(threadName);
    }
    @Override
    public void run() {
        int i = 0;
        while(i<10){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Consumer: " + getName() + ": " + counter.getCounter());
            i++;
        }

    }
}
