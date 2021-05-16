import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamImmutableCollection {
    static int count = 0;

    public static void main(String[] args) {
        Map<Integer, Integer> map = List.of(1, 2, 3, 4, 5, 1).stream()
                .collect(Collectors.toUnmodifiableMap(p->p, p-> ++count + p*2, (p, q)->q));// merger (p, q) indicates p as existing key, p as new duplicate key
        System.out.println(map);
    }
}
