public class ProducerConsumerThread {
    int counter = 0;
    boolean isAvailable = false;

    synchronized void increment() throws InterruptedException {
        while(isAvailable){
            wait();
        }
        counter++;
        isAvailable = true;
        notify();
    }

    synchronized void get() throws InterruptedException {
        while(!isAvailable){
            wait();
        }
        System.out.println(counter);
        isAvailable = false;
        notify();
    }
    public static void main(String[] args) {
        ProducerConsumerThread pst = new ProducerConsumerThread();
        Thread consumer = new Thread(()->{
            try {
                while(true) {
                    pst.get();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread producer = new Thread(()->{
            try {
                while(true) {
                    pst.increment();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }
}
