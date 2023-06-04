import com.sun.source.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.addAll(List.of(2, 3, 4, 5));
        list.stream().forEach(p-> System.out.println(p));//terminal
        //list.forEach();

        groupEmployeesByTitle();
    }

    private static void groupEmployeesByTitle() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Asha", "Manager"));
        employees.add(new Employee(2, "CM", "CEO"));
        employees.add(new Employee(3, "Mani", "Manager"));
        employees.add(new Employee(4, "Kutty", "Developer"));
        employees.add(new Employee(5, "John", "Manager"));
        employees.add(new Employee(6, "Deo", "Manager"));
        employees.add(new Employee(7, "Jose", "Developer"));

        // group employees by title
        Map<String, Set<Employee>> emps = employees.stream()
                .collect(
                        Collectors.groupingBy(
                                emp-> emp.title,
                                ()->new TreeMap<String, Set<Employee>>(),
                                Collectors.toSet()
                        )
                );
        System.out.println(emps);

        //get grouped by title. Then group them by id
        Map<String, Map<Integer, List<Employee>>> result = employees.stream().collect(Collectors.groupingBy(e->e.title, Collectors.groupingBy(e->e.id)));//downstream to group by name and then by id.
        System.out.println("multi group:::::" + result);

        Map<String, Integer> summedInd = employees.stream().collect(Collectors.groupingBy(e->e.title, Collectors.summingInt(e->e.id)));
        // get max employee id for each emp type
        Map<String, Optional<Employee>> maxIdPerTitle = employees.stream().collect(Collectors.groupingBy(e->e.title, Collectors.maxBy(Comparator.comparingInt(e->e.id))));
        System.out.println("max id: " + maxIdPerTitle);

        // map of title->employee. Not list of employee
        Map<String, Employee> empMap = employees.stream().collect(Collectors.toMap(employee->employee.title, employee->employee, (oldVal, newVal)->newVal));// only lst employee comes. Not list with same title.


        // sum up salary and find average
        averageSalary();

    }

    private static void averageSalary() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Asha", "Manager", 10000));
        employees.add(new Employee(2, "CM", "CEO", 100000));
        employees.add(new Employee(3, "Mani", "Manager", 20000));
        employees.add(new Employee(4, "Kutty", "Developer", 15000));
        employees.add(new Employee(5, "John", "Manager", 25000));
        employees.add(new Employee(6, "Deo", "Manager", 30000));
        employees.add(new Employee(7, "Jose", "Developer", 20000));

        int sum = employees.stream().map(emp->emp.salary).reduce((a, b)->a+b).orElseGet(()->0);
        System.out.println("Sum = " + sum);
        double average = employees.stream().mapToInt(emp->emp.salary).average().getAsDouble();
        System.out.println("Avg = " + average);

        //per dept avg salary
        Map<String, Double> empTitleAvgSalary = employees.stream().collect(Collectors.groupingBy(employee -> employee.title, Collectors.averagingDouble(employee-> employee.salary)));
        System.out.println("Dept avg: " + empTitleAvgSalary);
    }


    private static class Employee{
        int id;
        String name;
        String title;

        int salary;

        public Employee(int id, String name, String title){
            this.id = id;
            this.name = name;
            this.title = title;

        }

        public Employee(int id, String name, String title, int salary){
            this.id = id;
            this.name = name;
            this.title = title;
            this.salary = salary;

        }

        public String toString(){
            return name;
        }
    }

}


