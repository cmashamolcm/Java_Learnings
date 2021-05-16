import java.util.Optional;

public class OrElseThrowWithEmptyParam {
    public static void main(String[] args) {
        //Need exception supplier in 1.8
        //Optional.empty().orElseThrow(()->new NullPointerException());
        //Default throws NoSuchElementException.
        Optional.empty().orElseThrow();
    }
}
