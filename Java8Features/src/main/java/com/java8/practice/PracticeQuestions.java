package com.java8.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PracticeQuestions {

    public static void main(String[] args) {
        List<Student> students = getStudents();
        //Get student with exact match name "jayesh"
        System.out.println(students.stream().filter(p->p.getName().equals("Jayesh")).findFirst().orElse(null));

        //Get student with matching address "1235"
        System.out.println(students.stream().filter(p->p.getAddress().getZipcode().equals("1235")).findFirst().orElse(null));

        //Get all student having mobile numbers 3333.
        System.out.println(students.stream().filter(p->p.getMobileNumbers().stream().filter(q->q.getNumber().equals("3333")).count() >0).map(p->p.getName()).collect(Collectors.toList()));

        //Get all student having mobile number 1233 and 1234
        System.out.println(students.stream().filter(p->p.getMobileNumbers().stream().allMatch(q->q.getNumber().equals("1234") || q.getNumber().equals("1233"))).map(p->p.getName()).collect(Collectors.toList()));

        System.out.println(students.get(0).getMobileNumbers().stream().allMatch(p->p.getNumber().length() > 5));

        //Create a List<Student> from the List<TempStudent>
        TempStudent tmpStud1 = new TempStudent(
                "Jayesh1",
                201,
                new Address("12341"),
                Arrays.asList(new MobileNumber("12331"), new MobileNumber("12341")));

        TempStudent tmpStud2 = new TempStudent(
                "Khyati1",
                202,
                new Address("12351"),
                Arrays.asList(new MobileNumber("11111"), new MobileNumber("33331"), new MobileNumber("12331")));
        System.out.println(Arrays.asList(tmpStud1, tmpStud2)
        .stream().map(p->new Student(p.name, p.age, p.address, p.mobileNumbers)).collect(Collectors.toList()));

        //Convert List<Student> to List<String> of student name
        System.out.println(students.stream().map(p->p.getName()).collect(Collectors.toList()));

        //Convert List<students> to String
        System.out.println(students.stream().map(p->p.getName()).reduce((a, b)->a + ", " + b).get());

        System.out.println(students.stream().map(p->p.getName()).collect(Collectors.joining(",", "{", "}")));

        //Change the case of List<String>
        System.out.println(students.stream().map(p->p.getName().toUpperCase()).collect(Collectors.toList()));

        //Sort List<String>
        System.out.println(students.stream().map(p->p.getName().toUpperCase()).sorted().collect(Collectors.toList()));

        //Sort Students by Name.
        System.out.println(students.stream().sorted(Comparator.comparing(Student::getName)).collect(Collectors.toList()));

        //Sort by name and length of name descending
        System.out.println(students.stream().sorted(Comparator.comparing(Student::getName, Comparator.reverseOrder()).thenComparing(s->s.getName().length(), Comparator.reverseOrder())).map(p->p.getName()).collect(Collectors.toList()));

        //Convert list of students to map by name
        System.out.println(students.stream().collect(Collectors.toMap(Student::getName, s->s, (a, b)->b)));

        //map of list of students with key as age.
        System.out.println(students.stream().collect(Collectors.groupingBy(Student::getAge)));

        //map based on 1st mobile number of student.
        System.out.println(students.stream().collect(Collectors.groupingBy(p->p.getMobileNumbers().get(0))));



    }

    private static List<Student> getStudents() {
        Student student1 = new Student(
                "Jayesh",
                20,
                new Address("1234"),
                Arrays.asList(new MobileNumber("1233"), new MobileNumber("1234")));

        Student student2 = new Student(
                "Khyati",
                20,
                new Address("1235"),
                Arrays.asList(new MobileNumber("1111"), new MobileNumber("3333"), new MobileNumber("1233")));

        Student student3 = new Student(
                "Jason",
                20,
                new Address("1236"),
                Arrays.asList(new MobileNumber("3333"), new MobileNumber("4444")));

        return Arrays.asList(student1, student2, student3);
    }


}
