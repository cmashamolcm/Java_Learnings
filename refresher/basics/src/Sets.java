import java.util.*;
import java.util.Collections;

public class Sets {
    public static void main(String[] args) {
        hashSet();
        linkedHashSet();
        treeSet();
    }

    private static void treeSet() {
        SortedSet<Integer> hashSet = new TreeSet<>();
        hashSet.add(1);
        hashSet.add(2);
        Iterator itr = hashSet.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
            //hashSet.add(1000);//java.util.ConcurrentModificationException
            //itr.remove();
        }
        //hashSet.add(null);// no
        Collections.synchronizedSortedSet(hashSet);
    }

    private static void linkedHashSet() {
        Set<Integer> hashSet = new LinkedHashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        Iterator itr = hashSet.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
            //hashSet.add(1000);//java.util.ConcurrentModificationException
            //itr.remove();
        }
        hashSet.add(null);

    }

    static void hashSet(){
        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(null);


    }
}
