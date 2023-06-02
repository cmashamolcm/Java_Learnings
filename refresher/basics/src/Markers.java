public class Markers {
    public static void main(String[] args) {

        Deletable d = new DeleteB();
        System.out.println(d instanceof Deletable);
        System.out.println(d instanceof DeleteA);
        System.out.println(d instanceof DeleteB);

    }
}

interface Deletable{

}

class DeleteA implements Deletable{

}

class DeleteB extends DeleteA{

}

