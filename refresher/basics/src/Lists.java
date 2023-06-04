import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Lists {
    public static void main(String[] args) {
        arrayList();
        copyOnWrite();
        linkedList();
        vector();

    }

    private static void vector() {
        Vector<Integer> vector = new Vector<>();
        vector.add(10);
        System.out.println(vector.get(0));
        vector.add(null);
    }

    private static void linkedList() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.addAll(List.of(2, 3));
        list.add(null);
        System.out.println(list);

    }

    private static void copyOnWrite() {
        List<Integer> copy = new CopyOnWriteArrayList<>();
        copy.add(1);// take snapshot. Update it and rewrite back to original backed array inside synchronized block.
        copy.add(2);
        copy.get(1);
        Iterator itr = copy.iterator();
        while(itr.hasNext()){
            System.out.println("copy: " + itr.next());
            copy.add(20);// fail-safe
            //itr.remove();//not supported. May be since direct updates are ok
        }
        System.out.println("Removed items" + copy);

    }

    private static void arrayList() {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3));
        // List.of() gives unmodifiable list. 10(default initial capacity) variants. Since Java9.

        list.add(1, 200);
        System.out.println("Intruded items" + list);

        Iterator itr = list.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
            //list.add(20);// ConcurrentModificationException.
            itr.remove();//is ok and removes just previous one. So, no problem of data miss.
        }
        System.out.println("Removed items" + list);


    }
}
