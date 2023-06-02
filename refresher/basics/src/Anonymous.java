public class Anonymous {
    public static void main(String[] args) {
        test();
        Anno a = new Anno(){
            @Override
            void add() {
                System.out.println("Hai from anonymous inner class");
            }
        };
        a.add();
    }

    static void test(){
        Anno a = new Anno() {// anonymous class
            @Override
            void add() {
                System.out.println("Test");
            }
        };
        a.add();
    }
}

class Anno{
    void add(){

    }
}
