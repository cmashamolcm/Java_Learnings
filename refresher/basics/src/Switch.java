public class Switch {
    public static void main(String[] args) {
        String animal = "rat";

        final String dog = "dog";
        final String cat = "cat";

        //switch statement
        switch (animal) {
            case dog:
                System.out.println("DOG");
                break;
            case cat:
                System.out.println("CAT");
                break;
            default:
                System.out.println("Unknown");

        }

        newSwitchExpresson();
        //switchWithJava17();
    }

//    private static void switchWithJava17() {// these are not visible in Java 19 but came in 17.
//        //String month = "January";
//        Object month = null;
//        int result = switch (month){
//            case month !=null && (month == "January" || month == "February") -> 1;
//            case "March"-> {
//                System.out.println("March");
//                yield 10;
//                //return 10;
//            }
//            case null->-1;
//            default -> 0;
//        };

        //System.out.println("result = " + result);
    //}

    private static void newSwitchExpresson(){//Java 12
        String month = "January";
        int result = switch (month){
            case "January", "February" -> 1;
            case "March"-> {
                System.out.println("March");
                yield 10;
                //return 10;
            }
            default -> 0;
        };

        System.out.println("result = " + result);
    }
}
