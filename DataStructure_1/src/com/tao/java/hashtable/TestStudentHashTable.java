package com.tao.java.hashtable;

import org.junit.Test;

/**
 * @author AIERXUAN
 * @create 2022/3/16 - 19:34
 * @description
 */
public class TestStudentHashTable {
    @Test
    public void test() {
        HashTable<Student> table = new HashTable<>(10);

        Student s1 = new Student("Tom", 1001);
        Student s2 = new Student("Jerry", 1002);
        Student s3 = new Student("Frank", 1003);
        Student s4 = new Student("Jim", 1004);
        Student s5 = new Student("Ben", 1005);
        Student s6 = new Student("Anda", 1014);

        table.add(s1);
        table.add(s2);
        table.add(s3);
        table.add(s4);
        table.add(s5);
        table.add(s6);

        table.list();

        table.getById(1003);

    }
}
