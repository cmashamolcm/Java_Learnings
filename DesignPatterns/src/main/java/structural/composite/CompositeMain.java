package structural.composite;

import java.util.List;

public class CompositeMain {
    public static void main(String[] args) {
        Carton carton = new Carton(List.of(new Carton(null, new Item(10))), new Item(20));
        System.out.println(carton.getPrice());
    }
}

interface PricedItem {
    int getPrice();
}

class Carton implements PricedItem {
    List<Carton> cartons;
    Item item;
    public Carton(List<Carton> cartons, Item item){
        this.cartons = cartons;
        this.item = item;
    }
    public int getPrice(){
        int sum = 0;
        if(item != null){
            sum += item.getPrice();
        }
        if (cartons != null){
            sum += cartons.stream()
                    .map(p->p.getPrice())
                    .reduce((accumulator, carton)->accumulator+ carton).orElse(0);
        }
        return sum;
    }
}

class Item implements PricedItem {
    String name;
    int price;

    public Item(int price){
        this.price = price;
    }

    public int getPrice(){
        return price;
    }
}