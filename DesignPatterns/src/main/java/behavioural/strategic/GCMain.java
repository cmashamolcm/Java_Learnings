package behavioural.strategic;

public class GCMain {
    public static void main(String[] args) {
        GCSystem.gc(2);
    }
}

class GCSystem{
    public static void gc(int id){
        GC gc = switch (id){
            case 1 -> new G1GC();
            case 2 -> new CMS();
            default -> new Parallel();
        };
        gc.collectGarbage();
    }
}

interface GC{
    void collectGarbage();
}
class G1GC implements GC{
    public void collectGarbage(){
        System.out.println("G1GC : Done");
    }
}

class CMS implements GC{
    public void collectGarbage(){
        System.out.println("CMS : Done");
    }
}

class Parallel implements GC{
    public void collectGarbage(){
        System.out.println("ParallelGC : Done");
    }
}
