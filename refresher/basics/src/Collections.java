import java.sql.Array;
import java.util.*;

public class Collections {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();new ArrayList<>(-1);
        list.add(1);
        list.addAll(List.of(2, 3, 4));
        list.add(null);
        for(Integer i: list){
            System.out.println(i);
        }

        Iterator itr = list.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }

        Map<String, Integer> map =  new HashMap<>();
        map.put("Asha", 1);
        map.putAll(Map.of("CM", 2, "Mani", 3));
        for(String key: map.keySet()){
            System.out.println(map.get(key));
        }

        sortStrings();

        vectorTest();

    }

    private static void vectorTest() {
        Vector<Integer> vector = new Vector<>();
        vector.add(10);
        System.out.println(vector.get(0));
    }

    private static void sortStrings() {
        List<String> strList = new ArrayList<>(List.of("Asha", "CM", "Mani", "Manikutty"));
        Comparator<String> strComparator = new Comparator<String>() {
           public int compare(String s1, String s2){
               return s1.length()>s2.length()?1:-1;
           }
        };
        java.util.Collections.sort(strList, strComparator);
        System.out.println(strList);

        List<Student> students = new ArrayList<>();
        students.add(new Student(10));
        students.add(new Student(1));
        students.add(new Student(20));
        students.add(new Student(11));
        java.util.Collections.sort(students);

        for(Student student: students){
            System.out.println("Age: " + student.age);
        }
    }


    static class Student implements  Comparable<Student>{
        int age;

        Student(int age){
            this.age = age;
        }

        public int compareTo(Student other){
            return this.age>other.age?1:-1;
        }
    }
}
