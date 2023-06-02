public class Final {
    public static void main(String[] args) {
        AFinal aFinal = new BFinal();
        System.out.println(aFinal.a);
        BFinal bFinal = new BFinal();
        System.out.println(bFinal.a);

        bFinal.print();

        int x = aFinal.privateVar;
        String y  = bFinal.privateVar;

    }


}

class AFinal{
    final int a = 10;
    protected final int b = 20;

    int privateVar = 1000;

    private int fromA = 500;

    private final void addPrivateFinal(){

    }

    final void addFinal(){

    }

    final void add(){
        System.out.println("A");
    }
}

class BFinal extends AFinal{

    String privateVar = "hai";
    final int a = 20;
    //final int b = 200;// this makes b from A hidden. Not overridden.

    final void addPrivateFinal(){
        //System.out.println(fromA);// will not be visible as fromA is private

    }

//    final void addFinal(){// not allowed
//
//    }
    final private void addPrivate(){// this is possible. Reason: private means, not inherited. So, this one is not same as that of parent.

    }
    void print(){
        System.out.println(a + ":::::" + b);
    }

//        final void add(){// cannot override final methods.
//            System.out.println("A");
//        }
}

