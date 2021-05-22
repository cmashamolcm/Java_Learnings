package creational.abstractfactory;

public interface ComputerAbstractFactory {
    Computer createComputer();
}

class LaptopFactory implements ComputerAbstractFactory{

    @Override
    public Computer createComputer() {
        return new Laptop();
    }
}

class DesktopFactory implements ComputerAbstractFactory{

    @Override
    public Computer createComputer() {
        return new Desktop();
    }
}
