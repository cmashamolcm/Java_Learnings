import java.util.List;
import java.util.Map;

public class CopyOf {
    public static void main(String[] args) {
        List.copyOf(List.of(10, 20, 30)).stream().forEach(System.out::println);
        //List.copyOf(List.of(10, 20, 30)).add(10);//UnsupportedOperationException - list returned is immutable.
        Map.copyOf(Map.of(10, "10", 20, "20")).entrySet().stream().forEach(System.out::println);
    }
}
