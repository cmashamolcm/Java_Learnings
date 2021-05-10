public class DefaultAndStatic {
    public static void main(String[] args) {
        AB ab =  new AB();
        ab.print();

        A a = new AB();
        a.print();

    }
}

class AB implements A, B{

    @Override
    public int getInt() {
        return 0;
    }

    @Override
    public void noMoreImpl() {

    }

    @Override
    public void print() {
        System.out.println("In AB");
        A.super.print();
        B.super.print();
    }

    @Override
    public void defaultImpl() {
        A.super.defaultImpl();
    }
}

interface A{
    int getInt();
    void noMoreImpl();

    //not allowed method signature as of Object class as default methods in interface.
    /*default int equals(Object a) {
        return 0;
    }*/

    default void print(){
        System.out.println("In A");
    }

    default void defaultImpl(){

    }
}

interface B{
    int getInt();

    default void print(){
        System.out.println("In B");
    }
}

class C {
    public void print() {
        System.out.println("In C");
    }

    void noMoreImpl(){

    }

    public void defaultImpl(){

    }
}

class ABC extends C implements A, B{
    @Override
    public int getInt() {
        return 0;
    }

    //This needed to implemented because, class A gives it default scope and interface gave public scope. To make it consistent.
    @Override
    public void noMoreImpl() {

    }
    //defaultImpl() is public in class and interface, so, took from class itself.
}