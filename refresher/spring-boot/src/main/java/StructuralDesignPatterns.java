import org.hibernate.jdbc.Work;

import java.util.*;
import java.util.stream.Collectors;

public class StructuralDesignPatterns {
    //Adaptor: make something fit into ours
    interface Pen{
        void write();
    }
    class FountainPen implements Pen{

        @Override
        public void write() {

        }
    }
    class SpacePen{
        public void writeInSpace(){

        }
    }

    class PenAdapter implements Pen{// This helps to make space pen work as a normal pen.
        private SpacePen pen = new SpacePen();

        @Override
        public void write() {
            pen.writeInSpace();
        }
    }

    //Composite: tree structure to contain itself recursively
    static class Carton{
        public Carton(List<Carton> childCartons, int price) {
            this.childCartons = childCartons;
            this.price = price;
        }

        List<Carton> childCartons;
        int price;

        int getPrice(){
            return price + Optional.ofNullable(childCartons).orElseGet(Collections::emptyList)
            .stream().map(p->p.getPrice()).reduce((a, b)->a+b).orElse(0);
        }
    }

    public static void main1(String[] args) {
        Carton c = new Carton( List.of(new Carton( List.of(new Carton(null, 10)), 10)), 10);
        System.out.println(c.getPrice());
    }

    //Proxy: Don't give origin. Give a replica
    class AccountData{
        int amount;
        int accountPassword = 12345;
        AccountData(int amount){
            this.amount = amount;
        }

    }

    class DummyAccountData{
        int amount;
        DummyAccountData(int amount){
            this.amount = amount;
        }

    }

    class Atm{
        AccountData data = new AccountData(2000);
        DummyAccountData getData(){
            return new DummyAccountData(data.amount);// here, we hide the password. Only necessary data is given to outside.
        }
    }

    //Facade: gateway to other services or classes. Similar to factory. Condition based navigation
    class ApiGateway{
        Map<Integer, ? extends Service> serviceMap = new HashMap<>();
        void callService(int serviceId){
            switch(serviceId) {
                case 2->{
                    UserService service = (UserService) serviceMap.get(serviceId);
                    service.callUser();
                }
                default -> serviceMap.get(serviceId).invoke();
            }
        }
    }

    interface Service{
        void invoke();

    }

    class UserService implements Service{

        @Override
        public void invoke() {
            System.out.println("User");
        }

        public void callUser(){
            System.out.println("User Called");
        }
    }

    class OrderService implements Service{

        @Override
        public void invoke() {
            System.out.println("Order");
        }
    }

    //decorator/ wrapper:
    class Wrapper{
        GiftItem giftItem;

        String getGiftName(){
            return giftItem.name;
        }
    }

    class GiftItem{
        String name;
    }

    //flyweight: pooling
    static class WorkerPool{
        List<Worker> pool = new ArrayList<>();

        Worker getWorker(){
            Worker w = pool
                    .stream().filter(p->!p.isOccupied).findFirst().orElseGet(()-> {
                Worker newW = new Worker();
                pool.add(newW);
                return newW;
            });
            return w;
        }


    }

    static class Worker{
        boolean isOccupied;
    }

    public static void main(String[] args) {
        WorkerPool p = new WorkerPool();
        System.out.println(p.getWorker().isOccupied);
        p.getWorker().isOccupied = true;
        System.out.println(p.getWorker().isOccupied);

    }

    //bridge - one inheritance and one composition instead of single inheritance to avoid too many implementations.
    //Eg: Shape -- RedCircle, GreenCircle, BlackSquare etc.
    // instead, Make Color as class and add composition to Shape.
    //Shape{Color}. So, any shape and any color can be there. One square is enough to use for any color square.
    // here, color is bridge between color and shape.
    // https://refactoring.guru/design-patterns/bridge


}
