import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Maps {
    public static void main(String[] args) {
        hashTable();
        hashMap();
        linkedHashMap();
        treeMap();
        concurrentHashMap();
    }

    private static void hashTable() {
        Map<Integer, String> map = new Hashtable<>();
        map.put(1, "one");
        //map.put(null, "null-int");
        //System.out.println(map.get(null));
        //map.put(2, null);
        System.out.println(map.get(2));
        map.remove(2);
        map.put(1, "one one");
        map.get(1);

        Iterator itr = map.entrySet().iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
            map.put(10, "Ten");
            //itr.remove();
        }
    }

    private static void concurrentHashMap() {
        Map<Integer, String> map = new ConcurrentHashMap<>(5, 0.75f, 5);
        map.put(1, "One");
        map.put(2, "Two");
        System.out.println(map);
        System.out.println(map.get(2));

        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(()->{map.put(2, new Date().toString());});
        service.submit(()->{map.put(2, new Date().toString());});

    }

    private static void treeMap() {
        System.out.println("TreeMap");
        Map<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());// 3, 1
        map.put(1, "one");
        //map.put(null, "null-int");// not allowed null key
        //System.out.println(map.get(null));
        map.put(2, null);// null value ok
        System.out.println(map.get(2));
        map.remove(2);
        map.put(1, "one one");
        map.get(1);

        Iterator itr = map.entrySet().iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
            //map.put(10, "Ten");
            //itr.remove();
        }
        map.put(3, "Three");
        map.put(1, "ONE");
        System.out.println(map);
    }

    private static void linkedHashMap() {
        Map<Integer, String> map = new LinkedHashMap<>(10, 0.75f, true);
        map.put(1, "one");
        map.put(null, "null-int");
        System.out.println(map.get(null));
        map.put(2, null);
        System.out.println(map.get(2));
        map.remove(2);
        map.put(1, "one one");
        map.get(1);

        Iterator itr = map.entrySet().iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
            //map.put(10, "Ten");
            //itr.remove();
        }
        map.put(3, "Three");
        map.put(1, "ONE");
        System.out.println(map);
    }

    private static void hashMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(null, "null-int");
        System.out.println(map.get(null));
        map.put(2, null);
        System.out.println(map.get(2));
        map.remove(2);
        map.put(1, "one one");
        map.get(1);
    }
}
