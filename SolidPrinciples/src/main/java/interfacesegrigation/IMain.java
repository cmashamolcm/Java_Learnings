package interfacesegrigation;

public class IMain {
    public static void main(String[] args) {
        CorrectIPrinter printer = new CorrectSuperPrinter();
        printer.print();
        ((IScanner)printer).scan();
        ((IMailer)printer).sendMail();

        printer = new CorrectNormalPrinter();
        printer.print();
    }
}


interface IPrinter{
    void print();
    void scan();
    void sendMail();
}


//Normal printer had to keep some methods untouched.
//This means the interface is not in it's atomic level.
//Can split/ segregate it again.
//move scan() to another interface, sendMail() to another.
class NormalPrinter implements IPrinter{

    @Override
    public void print() {
        System.out.println("Printing...");
    }

    @Override
    public void scan() {
        //do nothing as functionality not available
    }

    @Override
    public void sendMail() {
        //do nothing as functionality not available
    }
}

class SuperPrinter implements IPrinter{

    @Override
    public void print() {
        System.out.println("Printing...");
    }

    @Override
    public void scan() {
        System.out.println("Scanning...");
    }

    @Override
    public void sendMail() {
        System.out.println("Send mail...");
    }
}

//correct way

interface CorrectIPrinter{
    void print();
}
interface IScanner{
    void scan();
}
interface IMailer{
    void sendMail();
}

class CorrectNormalPrinter implements CorrectIPrinter{

    @Override
    public void print() {
        System.out.println("Printing...");
    }
}

class CorrectSuperPrinter implements CorrectIPrinter, IScanner, IMailer{

    @Override
    public void print() {
        System.out.println("Printing...");
    }

    @Override
    public void scan() {
        System.out.println("Scanning...");
    }

    @Override
    public void sendMail() {
        System.out.println("Send mail...");
    }
}