public class ObjectsAndClasses {
    public static void main(String[] args) {
        Person person = new Person(1, "Asha");//onject
    }
}

class Person{
    private int age;
    private String name;

    public Person(int age, String name){// class
        this.age = age;
        this.name= name;
    }
}
