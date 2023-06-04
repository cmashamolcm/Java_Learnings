public class Threads {

    public static void main(String[] args) throws InterruptedException {
        Thread haiThread = new A();
        haiThread.start();// calls run of A

        Thread helloThread = new Thread(new B());
        helloThread.start();//calls run of B

        //Thread.yield(); - says scheduler that I can be patient. You can take cpu if need.

        System.out.println("Counter= " + Counter.counter);
        haiThread.join();
        helloThread.join();// join makes the thread where it is called wait here so that other thread is finished.
        //Here, main waits for hai and hello threads to finish.
        System.out.println("Counter after join= " + Counter.counter);// So, counter will be 10.
    }

    public class Counter{
        static int counter = 0;// need synchronized block to make it correct

        static synchronized void increment(){
            counter++;
        }
    }

    static class A extends Thread{
        void hai() throws InterruptedException {
            for (int i = 0; i<5; i++){
                System.out.println("Hai");
                Thread.sleep(1000);
                Counter.increment();
            }

        }

        @Override
        public void run() {
            try {
                hai();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class B implements Runnable{
        void hello(){
            for (int i = 0; i<5; i++) {
                System.out.println("Hello");
                Counter.increment();
            }
        }

        @Override
        public void run() {
            hello();
        }
    }
}
