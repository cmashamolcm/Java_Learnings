package main.java.com.java9.features;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TryWithResources {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //But if we try to modify br before try, it will say, for br to be inside try(), it have to be effectively final.
        try(br){

        }catch (Exception e){
            System.out.println("Error: "  +e);
        }
    }
}
