public class AbstractClass {
    public static void main(String[] args) {

        AbstractA a = new AbstractA() {// anonymous class
            @Override
            void add() {
                super.add();
            }
        };

    }
}

abstract class AbstractA{
    void add(){

    }
}

class AbstractB extends  AbstractA{

}

abstract class AbstractC{
    abstract void add();
}

class AbstractD extends AbstractC{//
    void add(){

    }
}
