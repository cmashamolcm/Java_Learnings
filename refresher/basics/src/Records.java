public class Records {
    public static void main(String[] args) {
        Product p = new Product();
        p = new Product(2, "Second item");
        System.out.println(p);// toString and equals hashCode by default implemented.
        //p.id();

    }

}

record Product(int id, String title){// same as full fledged data models.
    Product(){
        this(1, "Default title");
    }

    static int x;
    //int y;// add to canonical constructor instead of declaring non-static variables here.

}
