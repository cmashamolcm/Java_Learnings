public class Constructors {
    public static void main(String[] args) {
        new B();

    }
}

class A{
//    A(){
//        System.out.println("In A");
//    }

    A(int a){
        System.out.println("In A int");
    }
}
class B extends A{
    B(){
        super(10);
        System.out.println("In B");
    }
}
