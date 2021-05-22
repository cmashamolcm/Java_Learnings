package openclose;

public class OMain {
    public static void main(String[] args) {
        Employee regular = new RegularEmployee(1, "Emp1", 10000);
        Employee contractor = new ContractEmployee(1, "Emp2", 5000);
        regular.calculateBonus();
        contractor.calculateBonus();
        System.out.println(regular.bonus);
        System.out.println(contractor.bonus);
    }
}

class ModifiableEmployee{
    int id;
    String name;
    int salary;

    //if we want to calculate bonus
    public int calculateBonus(){
        return salary * 10/100;
        //what if we want to change it based on employee type.
        //have to add a type attribute. Change this method with conditions etc.
        //better is make employee abstract and extend.
    }
}


abstract class Employee{
    int id;
    String name;
    int salary;
    int bonus;

    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public abstract int calculateBonus();
}

class RegularEmployee extends Employee{

    public RegularEmployee(int id, String name, int salary) {
        super(id, name, salary);
    }

    @Override
    public int calculateBonus() {
        return bonus = salary * 10/100;
    }
}

class ContractEmployee extends Employee{

    public ContractEmployee(int id, String name, int salary) {
        super(id, name, salary);
    }

    @Override
    public int calculateBonus() {
        return bonus = salary * 5/100;
    }
}