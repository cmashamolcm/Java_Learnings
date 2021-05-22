package creational.prototype;

public class PrototypeMain {
    public static void main(String[] args) throws CloneNotSupportedException {
        Prototype benz = new Prototype();
        benz.wheels = 4;
        benz.name = "benz";

        System.out.println(benz);

        Prototype audi = benz.clone();
        audi.name = "audi";

        System.out.println(audi);
    }

}

class Prototype implements Cloneable{
    int wheels;
    String name;
    int doorCount;

    @Override
    public String toString() {
        return "Prototype{" +
                "wheels=" + wheels +
                ", name='" + name + '\'' +
                ", doorCount=" + doorCount +
                '}';
    }

    @Override
    protected Prototype clone() throws CloneNotSupportedException {
        Prototype clone  = new Prototype();
        clone.doorCount = this.doorCount;
        clone.wheels = this.wheels;
        clone.name  = this.name;
        return clone;
    }
}
