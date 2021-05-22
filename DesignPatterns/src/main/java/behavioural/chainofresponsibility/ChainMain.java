package behavioural.chainofresponsibility;

import java.util.Random;

public class ChainMain {
    public static void main(String[] args) {
        FrontOffice frontOffice = new FrontOffice();
        frontOffice.startProcess();
    }
}


class FrontOffice{
    Interviewer nextChain = new Interviewer();

    public void startProcess(){
        if(nextChain.evaluate()){
            System.out.println("Selected...");
        }else {
            System.out.println("Better Luck Next Time...");
        }
    }
}

class Interviewer{
    HrManager hr = new HrManager();
    boolean evaluate(){
        if(new Random().nextBoolean()){
            return hr.isSelected();
        }
        return false;
    }
}

class HrManager{
    boolean isSelected(){
        return new Random().nextBoolean();
    }
}