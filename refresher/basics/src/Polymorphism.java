public class Polymorphism {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.print();// calls to print from Animal
        animal = new Cat();
        animal.print();//calls print() from Cat

    }
}

class Animal{
    void print(){
        System.out.println("Animal");
    }
}

class Cat extends Animal{
    void print(){
        System.out.println("Cat");
    }
}
