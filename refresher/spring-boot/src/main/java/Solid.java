public class Solid {
    // Controller with all user CRUD is `single responsibility`
    abstract class Employee{// extend and not modify
        abstract void getName();

    }

    class ContractEmployee extends Employee{
         void getName(){}
         void getContractDetails(){}

    }

    class PermanentEmployee extends  Employee{
        void getName(){}
         void getOfferDetails(){}

    }

    class PermanentManager extends PermanentEmployee{
        void getName(){

        }

//        void getName(){
//            System.out.println("Say hai");
//
//        }

    }

   void substitute() {
       Employee pe = new PermanentEmployee();
       pe.getName(); //behaves same as
       pe = new PermanentManager();
       pe.getName(); //even if it's a manager, still getName gives name and no extra things. If it was commened getName() impl, this principle is violated.
       // This Liskov substitution helps to be predictable.
   }

//interface segregation
       interface Animal {
           void cry();

           void myau();

           void bowBow();
       }

       class Dog implements Animal {


           @Override
           public void cry() {

           }

           @Override
           public void myau() {// this is not useful for a Dog. Better idea is to separate it to another interface fpr Cat

           }

           @Override
           public void bowBow() {// this is not useful for a Dog. Better idea is to separate it to another interface for Dog and implement it side by side in Dog


           }
       }

       //dependency inversion - code for abstraction not for concrete to plug and play
    //Service and ServiceImpl does it.


}
