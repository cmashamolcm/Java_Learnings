import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        Set<Order> orders = new HashSet<>();
        orders.addAll(List.of(
                new Order(1L, LocalDate.now(), LocalDate.now(), "new",
                            new Customer(10L, "Tim", 1)),
                new Order(2L, LocalDate.now(), LocalDate.now(), "old",
                        new Customer(10L, "Jim", 1)),
                new Order(3L, LocalDate.now(), LocalDate.now(), "new",
                        new Customer(20L, "Tim", 1))));
        products.add(new Product(1L, "Bread", "food", 1.2, orders));

        Set<Order> orders2 = new HashSet<>();
        orders2.addAll(List.of(
                new Order(1L, LocalDate.now(), LocalDate.now(), "new",
                        new Customer(10L, "Tim", 1)),
                new Order(2L, LocalDate.now(), LocalDate.now(), "old",
                        new Customer(10L, "Jim", 1)),
                new Order(3L, LocalDate.now(), LocalDate.now(), "new",
                        new Customer(20L, "Tim", 1))));
        products.add(new Product(2L, "Car", "vehicle", 1.5, orders2));

        Set<Order> orders3 = new HashSet<>();
        orders3.addAll(List.of(
                new Order(1L, LocalDate.now(), LocalDate.now(), "new",
                        new Customer(10L, "Tim", 1)),
                new Order(2L, LocalDate.now(), LocalDate.now(), "old",
                        new Customer(10L, "Jim", 1)),
                new Order(3L, LocalDate.now(), LocalDate.now(), "new",
                        new Customer(20L, "Tim", 1))));
        products.add(new Product(3L, "Car", "vehicle", 1.8, orders3));

        //Obtain a list of products belongs to category “vehicle” with price > 1.4
        List<Product> result = products.stream()
                .filter(product -> product.category.equals("car"))
                .filter(product -> product.price>1.5)
                .toList();
        System.out.println("products belongs to category “vehicle” with price > 1.4" + result);

        //Obtain a list of order with products belong to category “car”
        List<Order> orderResult = products.stream()
                .filter(product -> product.category.equals("vehicle"))
                .map(p->p.orders)
                .flatMap(ordersList->ordersList.stream())
                .collect(Collectors.toList());
        orderResult.forEach(order->System.out.println(order.id));

        //Get the cheapest products of “vehicle” category
        List<Product> sortedProduct = products.stream().filter(p->p.category.equals("vehicle"))
                .sorted(Comparator.comparing((p)-> p.price))// apply distinct if needed
                .collect(Collectors.toList());
        sortedProduct.forEach(product->System.out.println(product.name));
        //or
        Product min = products.stream().filter(p->p.category.equals("vehicle"))
                .min(Comparator.comparing(p->p.price)).get();
        System.out.println(min.name);

        //Get a list of orders which were ordered on a date, log the order records to the console and then return its product list
        List<Order> ordersCombined = products.stream()
                .map(product -> product.orders)
                .peek(orderList->
                        orderList.stream().peek(orderItem->System.out.println("Here: "+ orderItem.id)).collect(Collectors.toList()))
                .flatMap(ordersOnDate->orders.stream())
                .collect(Collectors.toList());

        System.out.println(ordersCombined);

        // Calculate total lump sum of all orders placed
        System.out.println("Sum of orders: " + products.stream().map(p->p.orders)
                .flatMap(orderList->orderList.stream())
                .mapToLong(orderItem->orderItem.id)
                .sum());

        System.out.println("Summary of orders: " + products.stream().map(p->p.orders)
                .flatMap(orderList->orderList.stream())
                .mapToLong(orderItem->orderItem.id)
                .summaryStatistics());

        //map of product->sum of order count
        Map<Product, Integer> productsOrderMap = products.stream().collect(Collectors.toMap(Function.identity(), p->p.orders.size()));
        System.out.println(productsOrderMap);

        //data map with order records grouped by customer
        System.out.println(products.stream().map(prod->prod.orders)
                .flatMap(ordrs->ordrs.stream())
                .collect(Collectors.groupingBy(ordr->ordr.customer.name, Collectors.toSet())));
    }


    public static class Customer {
        private Long id;

        private String name;
        private Integer tier;

        public Customer(Long id, String name, Integer tier) {
            this.id = id;
            this.name = name;
            this.tier = tier;
        }
    }


    public static class Order {

        private Long id;

        private LocalDate orderDate;
        private LocalDate deliveryDate;
        private String status;


        private Customer customer;


        Set<Product> products;

        public Order(Long id, LocalDate orderDate, LocalDate deliveryDate, String status, Customer customer) {
            this.id = id;
            this.orderDate = orderDate;
            this.deliveryDate = deliveryDate;
            this.status = status;
            this.customer = customer;
        }
    }



    public static class Product {
        public Product(Long id, String name, String category, Double price, Set<Order> orders) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.price = price;
            this.orders = orders;
        }

        private Long id;

        private String name;
        private String category;

        private Double price;


        private Set<Order> orders;
    }
}