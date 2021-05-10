public class Lambda {
    public static void main(String[] args) {

        //lambda();
        Lambda lambda = new Lambda();
        lambda.testThisReference();
        System.out.println("Actual object = " + lambda);

        lambda.methodReference();
    }

    private void methodReference() {
        Greeting g = Lambda::methodRef;
        g.greet();
        g = System.out::println;
        g.greet();

        MethodRefClass ref = this::methodRef;
        ref.ref(10, 20);

    }

    private static void methodRef() {
        System.out.println("Method ref");
    }

    private static void lambda() {
        Greeting morning = ()->{
            System.out.println("Good Morning");
        };

        Greeting noon = ()->{
            System.out.println("Good After Noon");
        };

        Greeting evening = ()->{
            System.out.println("Good Evening");
        };
//Defined 3 methods without even creating inherited classes or overloaded methods and used it.
        morning.greet();
        noon.greet();
        evening.greet();

        //This creates .class file Lambda$1.
        Greeting anonymous = new Greeting(){

            @Override
            public void greet() {

            }
        };
        anonymous.greet();


       /* TestFunctionalClass testFunctionalClass = ()->{
            System.out.println("Test");
        };*/

        //exception handling by wrapper.
        ExceptionWrapper wrapper = (g)->{
            System.out.println("Inside wrapper");
            try{
                g.greet();
            }catch (Exception e){
                System.out.println("Error");
            }
        };
        wrapper.wrap(()->{
            System.out.println("Greetings wrapped");
        });
    }

    private void testThisReference() {
        Greeting greeting = new Greeting() {
            @Override
            public void greet() {
                System.out.println("this = " + this);//prints hashcode for anonymous object. Eg: Lambda$2@29453f44
            }
        };
        greeting.greet();

        Greeting g = () -> System.out.println("This in lambda = " + this);
        g.greet();
    }

    int methodRef(int a, int b){
        System.out.println(a + " : " + b);
        return a * b;
    }
}

interface ExceptionWrapper{
    void wrap(Greeting g);
}
interface Greeting{
    void greet();
}

abstract class TestFunctionalClass{
    abstract void greet();
}

interface MethodRefClass{
    void ref(int a, int b);
}