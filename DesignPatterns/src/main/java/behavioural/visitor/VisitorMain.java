package behavioural.visitor;
//If we have to use any other pricing, can implement a new Class with PriceVisitor and use it.
public class VisitorMain {
    public static void main(String[] args) {
        Item pen = new Pen();
        Item paper = new Paper();

        System.out.println("Festival Price = " + pen.getPrice(new FestivalPriceVisitor()));
        System.out.println("Normal Price = " + pen.getPrice(new NormalPriceVisitor()));

        System.out.println("Festival Price = " + paper.getPrice(new FestivalPriceVisitor()));
        System.out.println("Normal Price = " + paper.getPrice(new NormalPriceVisitor()));

    }
}

interface Item{
    int getPrice(PriceDiscountVisitor visitor);
}

class Pen implements Item{
    int actualPrice = 1000;
    @Override
    public int getPrice(PriceDiscountVisitor visitor) {
        return visitor.getDiscount(actualPrice);
    }
}


class Paper implements Item{
    int actualPrice = 2000;
    @Override
    public int getPrice(PriceDiscountVisitor visitor) {
        return visitor.getDiscount(actualPrice);
    }
}

interface PriceDiscountVisitor{
    int getDiscount(int actualPrice);
}

class FestivalPriceVisitor implements PriceDiscountVisitor{
    public int getDiscount(int actualPrice){
        return 10*actualPrice;
    }
}

class NormalPriceVisitor implements PriceDiscountVisitor{
    public int getDiscount(int actualPrice){
        return 5*actualPrice;
    }
}