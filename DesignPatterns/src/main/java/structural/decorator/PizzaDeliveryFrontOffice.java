package structural.decorator;

public class PizzaDeliveryFrontOffice {
    public static void main(String[] args) {
        Pizza pizza = new TomatoTopped(new DoubleCheese());//double cheese is wrapped in tomato topping
        System.out.println(pizza.getPrice());
        System.out.println(pizza.getDescription());
    }
}

interface Pizza{
    int getPrice();
    String getDescription();
}

class thinkCrust implements Pizza{

    @Override
    public int getPrice() {
        return 200;
    }

    @Override
    public String getDescription() {
        return "Thin Crust";
    }
}

class DoubleCheese implements Pizza{

    @Override
    public int getPrice() {
        return 500;
    }

    @Override
    public String getDescription() {
        return "Double Cheese";
    }
}

interface ToppingDecorator extends Pizza{
    String addTopping();
}

class TomatoTopped implements ToppingDecorator{

    Pizza basePizza;

    public TomatoTopped(Pizza basePizza){
        this.basePizza = basePizza;
    }

    @Override
    public int getPrice() {
        return basePizza.getPrice();
    }

    @Override
    public String getDescription() {
        return basePizza.getDescription() +" With Tomato topping";
    }

    @Override
    public String addTopping() {
        return "Tomato Topping Added";
    }
}
