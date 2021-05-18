package com.java8.practice.maps;

import com.java8.practice.PracticeQuestions;
import com.java8.practice.Student;

import java.time.DayOfWeek;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MapImpl {
    public static void main(String[] args) throws InterruptedException {
        hashTable();//11
        hashMap();//16
        linkedHashMap();//16
        treeMap();//since it's r-b-tree way, no initial size taken.
        identityHashMap();//21
        enumMap();
        weakHashMapSimplified();
        concurrentHashMap();

        //weakHashMap();
    }

    private static void concurrentHashMap() {
        ConcurrentHashMap<String, String> map =  new ConcurrentHashMap<>();
        map.put("Asha","Mol");
        map.put("Mol", "C M");
        map.get("Mol");
        System.out.println(map.contains("Asha"));// same as containsValue();---false
        System.out.println(map.contains("C M"));//true

        Iterator itr = map.entrySet().iterator();
        while (itr.hasNext()){
            map.put("Xyz", "XYZ");//no ConcurrentModificationException
            itr.next();
        }

        System.out.println(map);
    }

    private static void weakHashMapSimplified() throws InterruptedException {
        Map<String, String> weakHashMap =  new WeakHashMap<>();
        weakHashMap.put(new String("abc"), "ABC");
        weakHashMap.put(new String("bcd"), "BC");
        String c = new String("cde");
        weakHashMap.put(c, "C");
        System.out.println("Before : " + weakHashMap.size());//3
        System.gc();
        Thread.sleep(3000);
        System.out.println("After : " + weakHashMap.size());//1


    }

    private static void weakHashMap() throws InterruptedException {
        Map<String, String> weakHashMap =  new WeakHashMap<>();
        //String a = new String("Abc");//if used a, it will be string ref.
        weakHashMap.put(/*a*/new String("Abc"), "Abc");//gets gc-ed shortly as there is no string ref for new String("Abc")

        Thread t1 =new Thread(()->{
            while (weakHashMap.containsKey("Abc")) {
                System.out.println(weakHashMap.get("Abc"));
                System.gc();//required
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        t1.start();
        t1.join();
    }

    private static void enumMap() {
        Map<DayOfWeek, Integer> enumMap = new EnumMap<>(DayOfWeek.class);
        enumMap.put(DayOfWeek.FRIDAY, 10);
        //enumMap.put(null, 10);
        System.out.println(enumMap);
    }

    private static void identityHashMap() {//==
        Map<String, Integer> identityHashMap = new IdentityHashMap<>();
        identityHashMap.put("A", 10);
        identityHashMap.put(null, 10);
        System.out.println(identityHashMap);
    }

    private static void treeMap() {
        //Map<Student, Integer> treeMap = new TreeMap<>();//ClassCastException comes if Student is not comparable.
        Map<Student, Integer> treeMap = new TreeMap<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        PracticeQuestions.getStudents().stream().forEach(p->{
            treeMap.put(p, p.getAge());
        });
        //treeMap.put(null, 0);
        //treeMap.put(PracticeQuestions.getStudents().get(0), null);
        System.out.println(treeMap);
    }

    private static void linkedHashMap() {
        Map<Student, Integer> linkedHashMap = new LinkedHashMap<>();
        PracticeQuestions.getStudents().stream().forEach(p->{
            linkedHashMap.put(p, p.getAge());
        });
        System.out.println(linkedHashMap);
    }

    private static void hashMap() {
        Map<Student, Integer> hashMap = new HashMap<>();
        hashMap.putIfAbsent(PracticeQuestions.getStudents().get(0), 200);
        System.out.println(hashMap);
        //hashMap.contains(); is not there.

        Iterator itr = hashMap.entrySet().iterator();
        while (itr.hasNext()){
            //hashMap.put(PracticeQuestions.getStudents().get(0), 1000);//comes ConcurrentModificationException
            itr.next();
        }
    }

    private static void hashTable() {
        Hashtable<Student, Integer> hashTable = new Hashtable<>();
        //hashTable.put(PracticeQuestions.getStudents().get(0), null);
        hashTable = new Hashtable<>(PracticeQuestions.getStudents().stream()
                .collect(Collectors.toMap(p->p, Student::getAge, (old, young)-> young)));
        Enumeration<Integer> elements = hashTable.elements();//values only
        while (elements.hasMoreElements()){
            System.out.println(elements.nextElement());
        }
    }
}
