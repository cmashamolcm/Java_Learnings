import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TryWithResources {

    public static void main(String[] args) {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Feature from Java 7 ownwards. But will support only if we declare like this. References like
        //try(br){} if br is already defined is not supported. But in Java9+, it's supported. But if and only if br is effectively final.
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){

        }catch (Exception e){
            System.out.println("Error: "  +e);
        }
    }
}
