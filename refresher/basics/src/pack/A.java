package pack;

public class A {
    int a; // default access modifier.
    public int b;// available to inherit from anywhere.
    private int c; // can use only within this class.
    protected int d;// can be available to other classes in same package and in classes inheriting A.

}

