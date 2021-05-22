package structural.proxy;

public class ProxyAtmClientMain {
    public static void main(String[] args) {
        AtmData atmData = new ProxyAtmData();
        System.out.println(atmData.getCashAmount());
        System.out.println(atmData.isCashAvailable());
        //System.out.println(atmData.getPin());//getPin won't work as it is not in visibility of proxy. So, safe.
    }
}


interface AtmData{
    int getCashAmount();

    boolean isCashAvailable();
}

class ActualAtmData implements AtmData{
    private int balance = 2000;
    private int pin = 1000;

    @Override
    public int getCashAmount() {
        System.out.println("Actual Atm have a balance of :" + balance);
        return balance;
    }

    @Override
    public boolean isCashAvailable() {
        return true;
    }

    public int getPin(){
        return pin;
    }
}

class ProxyAtmData implements AtmData{
    private ActualAtmData actualAtmData;

    @Override
    public int getCashAmount() {
        ActualAtmData actualAtmData = new ActualAtmData();
        System.out.println("Actual Atm have a balance of :" + actualAtmData.getCashAmount());
        return actualAtmData.getCashAmount();
    }

    @Override
    public boolean isCashAvailable() {
        ActualAtmData actualAtmData = new ActualAtmData();
        System.out.println("Is cash available :" + actualAtmData.isCashAvailable());
        return actualAtmData.isCashAvailable();
    }
}