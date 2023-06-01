public class StaticBlock {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(Test.i);
        System.out.println("here");
        new Test();
        new Test();

        Class t = Class.forName("Test");// here, already in previous new Test(), class loaded. So, not calling static block again.
        t.getConstructors();


    }
}

class Test{
    static int i = 10;
    static{
        System.out.println("Class loading happens");
    }

    Test(){
        System.out.println("New Instance");
    }
}
