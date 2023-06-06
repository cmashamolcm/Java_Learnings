package threads;

import java.util.Random;

public class Volatile {
    public static void main(String[] args) {

       for(int i = 0; i< 100; i++){
           new Thread(()->{
               try {
                   Thread.sleep(2000/(new Random().nextInt(1, 20)));
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
               Singleton s = Singleton.instance();
               System.out.println(s.hashCode());
           }).start();
       }

    }
}

class Singleton{
    private static volatile Singleton s;

    private Singleton(){}


    public static Singleton instance(){
        if(s == null){
            synchronized (Singleton.class) {
                if(s == null){
                    s = new Singleton();
                }
            }
        }


        return s;
    }
}
