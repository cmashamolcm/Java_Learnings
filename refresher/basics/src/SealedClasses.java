public class SealedClasses {
    public static void main(String[] args) {

    }

    sealed class Animal permits Dog, Cat{// only Cat and Dog can inherit

    }

    class Bird /*extends Animal*/{// not allowed to extend Bird

    }


    non-sealed class Dog extends Animal{// anyone can inherit

    }

    final class Cat extends Animal{// no one can inherit

    }



}
