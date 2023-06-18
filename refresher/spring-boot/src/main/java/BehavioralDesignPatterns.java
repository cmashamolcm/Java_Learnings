import org.aspectj.weaver.ast.Or;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BehavioralDesignPatterns {
    //observer
    class Producer{
        List<Consumer> consumerList = new ArrayList<>();
        void register(Consumer c){
            consumerList.add(c);
        }

        void producer(){
            consumerList.stream().forEach(p->p.consume(Instant.now().toString()));
        }
    }

    interface Consumer{
        void consume(String data);

    }

    class DataConsumer implements Consumer{
        public void consume(String data){
            System.out.println("Data:" + data);
        }

    }

    //strategic = factory
    class GC{
        void gc(int type){
            switch (type){
                case 1-> new G1GC().collect();
                default -> new ParallelGC().collect();
            }

        }
    }

    interface GCMode{
        void collect();
    }

    class G1GC implements GCMode{
        public void collect(){
            System.out.println("G1GC");
        }
    }

    class ParallelGC implements GCMode{
        public void collect(){
            System.out.println("Parallel");
        }
    }

    //Command = abstract factory - https://www.tutorialspoint.com/design_pattern/command_pattern.htm
    class Order{
        void execute(){

        }
    }

    class BuyOrder extends Order {
        void execute(){
            buy();

        }

        void buy(){

        }
    }

    class SellOrder extends Order{
        void execute(){
            sell();
        }

        void sell(){

        }
    }

    class InventoryManager{
        List<Order> orders = new ArrayList<>();

        void placeOrder(Order order){
            orders.add(order);
        }

        void executeOrders(){
            orders.stream().forEach(o->o.execute());// buy and sell
        }
    }

    //visitor - here, we can add new visitors and
    interface Item{
        void printPrice();

    }
    static class Pen implements Item{
        PriceVisior visitor;
        int price;
        Pen(PriceVisior visitor, int price){
            this.visitor = visitor;
            this.price = price;
        }


        @Override
        public void printPrice() {
            System.out.println(visitor.getPrice(price));

        }
    }

    static class PriceVisior{
        int getPrice(int price){
            return price;
        }
    }

    static class DiscountPriceVisior extends PriceVisior{
        int getPrice(int price){
            return price / 10;
        }
    }

    public static void main1(String[] args) {
        Pen p = new Pen(new DiscountPriceVisior(),10);
        p.printPrice();

        p = new Pen(new PriceVisior(), 10);
        p.printPrice();
    }

    //chain of responsibility
    interface Filter{
        Filter doSomething();
    }
    static class AuthFilter implements Filter{
        Filter next;
        AuthFilter(Filter next){
            this.next = next;
        }
        @Override
        public Filter doSomething() {
            System.out.println("Auth");
            return next.doSomething();
        }
    }

    static class StatusFilter implements Filter{
        Filter next;
        StatusFilter(Filter next){
            this.next = next;
        }
        @Override
        public Filter doSomething() {
            System.out.println("Status");
            if(next == null){
                return null;
            }
            return next.doSomething();
        }
    }

    public static void main(String[] args) {
        Filter filter = new AuthFilter(new StatusFilter(null)).doSomething();
    }

    //template
    class HouseBuilder{
        void build(){
            setBase();
            setWall();
            setRoof();

        }

        private void setRoof() {
        }

        public void setWall() {
        }

        private void setBase() {
        }
    }

    class WoodHouseBuilder extends HouseBuilder{
        public void setWall() {
            System.out.println("set wooden wall");
        }
    }
}
