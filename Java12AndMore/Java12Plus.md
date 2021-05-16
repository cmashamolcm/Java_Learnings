##Java12:
****
1. Switch As Expression: Available only in Preview Mode
   Assignable. Break is no more required.
   int a = switch(b){
               case 1->return b*10;
               case 2->return b*100;
               default -> return b;
            }
   or
      switch(b){
            case 1->System.out.println(b);
            case 2->doSomething();
            default -> doNothing();
      }
   These ways are possible.
   
****
   ##Java 13:
****
1. Enhancement to Switch Expression with yield. Only in preview mode
   var a = switch(b){
               case 1->{yield b*10;}
               case 2->{yield b*100;}
               default -> {yield b;}
            }
2. ZGC Improvements
****
   ##Java 14:
****
1. Switch expression is made part of standard. So, can use in production.

****
  ##Java 15:
****
1. record class
   * To create immutable DTOs.
   public <b>class</b> Person{
     private int id;
     private String name;
     //getters and setters
     }
   can now easily create as;
   public <b>record</b> Person(int id, String name){
     
   }
Rest of the part including getters, setters, toString, hashCode, equals0 etc will be taken care of by itself.