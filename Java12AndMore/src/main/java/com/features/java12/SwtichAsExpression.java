package com.features.java12;
public class SwtichAsExpression {
    public static void main(String[] args) {
        int a = 10;
        switch (a){
            case 1:
                a = a+1;
                System.out.println("One");
                break;
            case 10:
                a = a+10;
                System.out.println("ten");
                break;
            default:
                System.out.println("unknown");
        }
//From Java12, this way, switch can be made as expression.
        int b = switch (a){
            case 1->a+1;
            case 10->a+10;
            default -> a;
        };

        System.out.println(b);
    }
}
