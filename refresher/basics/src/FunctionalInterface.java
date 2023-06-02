public class FunctionalInterface {
    public static void main(String[] args) {
        IFun fun = (num)->{
            return "I am from Lambda" + num;
        };
        System.out.println(fun.add(20));

    }
}

@java.lang.FunctionalInterface
interface IFun{
    String add(int num);
    //void sub();
    default void print(){
        System.out.println("print");
    }
}