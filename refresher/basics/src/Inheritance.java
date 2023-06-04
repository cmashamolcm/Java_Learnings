public class Inheritance {
    public static void main(String[] args) {

    }

    class A{
        final int x = 10;
        private int y = 20;
        public void add(){

        }
    }

    class B extends A{
        public void subtract(){
            System.out.println(x);
            //x = 200;// not allowed to change final.
            //y  = 20;// y not visible

        }

    }

    interface C{
        void add();

        default void subtract(){
            System.out.println("Subtract from C");
        }
    }

    class D extends A implements C{// have to override add() if access modifier is lower one in A.
        // Interface by default has method public abstract
    }

    interface E{
        void add();

        default void subtract(){
            System.out.println("Subtract from E");
        }
    }

    class F implements C, E{// need to override add() to avoid confusion of which one to use.

        @Override
        public void add() {
            subtract();
            E.super.subtract();

        }

        @Override
        public void subtract() {
            C.super.subtract();
        }
    }
}
