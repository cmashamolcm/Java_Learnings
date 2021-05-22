package structural.facade;

public class FacadeMain {
    public static void main(String[] args) {
        Gateway gateway = new Gateway();
        gateway.run();
    }
}

class Gateway{
    private SubSystem1 s1 = new SubSystem1();
    private SubSystem2 s2 = new SubSystem2();
    private SubSystem3 s3 = new SubSystem3();

    public void run(){
        SubSystem4 s4 = new SubSystem4();
        s1.runSubSystem1();
        s2.runSubSystem2();
        s3.runSubSystem3();
        s4.runSubSystem4();
    }
}
class SubSystem1{
    public void runSubSystem1(){
        System.out.println("SubSystem1");
    }
}


class SubSystem2{
    public void runSubSystem2(){
        System.out.println("SubSystem2");
    }
}

class SubSystem3{
    public void runSubSystem3(){
        System.out.println("SubSystem3");
    }
}

class SubSystem4{
    public void runSubSystem4(){
        System.out.println("SubSystem4");
    }
}