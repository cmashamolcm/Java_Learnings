package creational.abstractfactory;
//Abstract Factory is more extensible than factory.
public class Factory {
    public static void main(String[] args) {
        ComputerAbstractFactory computerFactory = new LaptopFactory();
        System.out.println(ComputerFactory.getComputer(computerFactory).getRam());
    }
}

class ComputerFactory{
    public static Computer getComputer(ComputerAbstractFactory factory){
        return factory.createComputer();
    }
}


interface Computer{
    String getRam();
}

class Laptop implements Computer{

    @Override
    public String getRam() {
        return "Laptop RAM";
    }
}

class Desktop implements Computer{

    @Override
    public String getRam() {
        return "Desktop RAM";
    }
}