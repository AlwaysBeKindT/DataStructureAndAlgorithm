package com.tao.java.hashtable;

/**
 * @author AIERXUAN
 * @create 2022/3/16 - 18:14
 * @description
 */
public class HashTable<S extends LinkedList.LinkedNext<S>> {

    private int size;
    private LinkedList<S> [] list;

    public HashTable(int size) {
        this.size = size;
        LinkedList<S> linkedList = new LinkedList<>();
        list = linkedList.getList(size);   //通过对象获取泛型数组
        list[0] = linkedList;
        for (int i = 1; i < size; i++) {
            list[i] = new LinkedList<>();
        }
    }

    private int hashCodes(int sid) {
        return sid % size;
    }

    public void add(S s) {
        int hashVal = hashCodes(s.getId());
        list[hashVal].add(s);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            list[i].list();
        }
    }

    public void getById(int id) {
        int hashVal = hashCodes(id);
        S s = list[hashVal].getById(id);
        if(s != null)
            System.out.printf("在第%d条链表中找到学员(%d)\n", hashVal + 1, id);
        else
            System.out.printf("未找到学员(%D)\n", id);
    }

}
