import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        List<String> data = new ArrayList<>();
        data.add("Asha");
        data.add("Mol");
        data.add("C M");
        data.add("Asha");
        //order is not preserved
        data.stream().filter((p)->p.length()>2).collect(Collectors.toSet()).forEach(p-> System.out.println("Unordered: " + p));
        data.stream().filter((p)->p.length()>2).collect(Collectors.toCollection(LinkedHashSet::new)).forEach(p-> System.out.println("Ordered: " + p));

        //never gets printed till a terminal operation is added.
        data.stream().filter((p)->{
            System.out.println("Data flowing : " + data);
            return p.length()>2;});

    }
}
