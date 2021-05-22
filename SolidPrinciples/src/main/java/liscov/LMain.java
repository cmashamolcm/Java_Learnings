package liscov;

public class LMain {
    public static void main(String[] args) {
        //expectation is for both type of employee, it will just print type in printBonus method.
        //But in TemporaryEmployee, instead of printing, its changing emp id and nothing else.
        //It makes TemporaryEmployee not behaving as expected if somebody tries to access it just like an Employee.
        Employee per = new PermanentEmployee();
        Employee temp = new TemporaryEmployee();
        per.printBonus();
        temp.printBonus();
        System.out.println(per.empId);
        System.out.println(temp.empId);//this makes us surprised and can be treated as unexpected behaviour.
        //So, as per Liscov, Both temp and per have to be replaceable for Employee object, which is not the case here.
        //This is violating Liscov's principle.
        //So, getBonus() have to do expected job only to obey the principle.
    }
}

abstract class Employee{
    int empId;
    abstract void printBonus();
}


class PermanentEmployee extends Employee{

    @Override
    public void printBonus() {
        System.out.println("Permanent");
    }
}

class TemporaryEmployee extends Employee{

    @Override
    public void printBonus() {
        empId = 10000000;
    }
}