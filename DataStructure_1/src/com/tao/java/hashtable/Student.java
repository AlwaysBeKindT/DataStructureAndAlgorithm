package com.tao.java.hashtable;

/**
 * @author AIERXUAN
 * @create 2022/3/16 - 18:14
 * @description
 */
public class Student implements LinkedList.LinkedNext<Student> {
    private String name;
    private int studentNumber;
    private Student next;

    public Student(String name, int studentNumber) {
        this.name = name;
        this.studentNumber = studentNumber;
    }

    @Override
    public int getId() {
        return studentNumber;
    }

    @Override
    public Student getNext() {
        return next;
    }

    @Override
    public void setNext(Student next) {
        this.next = next;
    }

    @Override
    public Student[] getList(int size) {
        return new Student[size];
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", studentNumber=" + studentNumber +
                '}';
    }
}
