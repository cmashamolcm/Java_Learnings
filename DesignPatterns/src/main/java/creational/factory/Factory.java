package creational.factory;

public class Factory {
    public static void main(String[] args) {
        System.out.println(ComputerFactory.getComputer("LAPTOP").getRam());
    }
}

class ComputerFactory{
    public static Computer getComputer(String type){
        Computer computer = switch (type){
            case "LAPTOP"->new Laptop();
            case "DESKTOP"->new Desktop();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        return computer;
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