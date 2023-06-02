import java.io.IOException;
import java.net.BindException;
import java.sql.SQLException;

public class Exceptions {
    public static void main(String[] args) {
try{


        for(int i = 0; i<10; i++){
            System.out.println(i + " Hai");
            if(i == 5){
                throw new ArithmeticException("Exit");
            }
        }
}catch(Exception e){
    System.out.println("Wrong");

}
        System.out.println("End");
    }
    public static void main1(String[] args) {
        try {
            int x = 10 / 10;
            int[] y = new int[1];
            System.out.println(y[5]);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error ArrayIndexOutOfBoundsException" + e.getMessage());
        }catch(ArithmeticException e){
            System.out.println("Error" + e.getMessage());
        }

        ParentE p = new ChildE();
        try {
            p.add();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

class ParentE {
    void add() throws IOException {// compile time

    }

    void sub() {

    }

    void divide() throws IOException, SQLException{//more than one separated by comma

    }
}

class ChildE extends ParentE{
    void add() {// child without exception also is fine.
            //throws BindException{// BindException is child of IOException. So, it is fine.
        //throws Exception{// Here we cannot use Exception. Child overridden method should have same exception subtype of exception from parent.
        // else parent = new Child(); if someone used, they will think that at the max IOException will come but due to child, it will have to face Exception.

    }

    void sub(){//throws ArithmeticException{// if child throws unchecked exception, parent may or may not throw it.
        //throws IOException{// cannot throw checked exception as parent is not throwing.

    }
}
