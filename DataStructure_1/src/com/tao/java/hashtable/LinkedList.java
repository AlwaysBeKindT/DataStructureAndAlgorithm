package com.tao.java.hashtable;

/**
 * @author AIERXUAN
 * @create 2022/3/16 - 18:17
 * @description
 */
public class LinkedList<S extends LinkedList.LinkedNext<S>> {

    private S head;

    public void add(S s) {
        if(head == null)
            head = s;
        else{
            S temp = head;
            while (temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(s);
        }
    }

    public void list() {
        if(head == null) {
            System.out.println("链表为空");
            return;
        }
        S temp = head;
        while (temp != null){
            System.out.print(temp + "\t");
            temp = temp.getNext();
        }
        System.out.println();
    }

    public S getById(int id) {
        if(head == null) {
            System.out.println("链表为空");
            return null;
        }
        S temp = head;
        while (temp != null){
            if(temp.getId() == id)
                return temp;
            temp = temp.getNext();
        }
        return null;
    }

    public LinkedList<S>[] getList(int size) {
        return new LinkedList[size];
    }

    interface LinkedNext<T>{
        int getId();
        T getNext();
        void setNext(T t);
        T[] getList(int size);
    }

}
