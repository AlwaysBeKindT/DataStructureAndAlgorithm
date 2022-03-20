package com.tao.java.datastruture.linkedlist;

import java.util.Stack;

/**
 * @author AIERXUAN
 * @create 2022/3/14 - 14:11
 * @description
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) throws ExsitNoException {
        SingleLinkedList list1 = new SingleLinkedList();
        list1.add(3, "吴用", "智多星");
        list1.add(4, "林冲", "豹子头");
        list1.add(1, "宋江", "及时雨");
        list1.add(2, "卢俊义", "玉麒麟");
        list1.forEach();
//        System.out.println();
//        list1.reverseList();
//        list1.forEach();
        System.out.println();
        list1.reversePrint();
        System.out.println();

        SingleLinkedList list2 = new SingleLinkedList(true);
        list2.add(13, "吴用", "智多星");
        list2.add(4, "林冲", "豹子头");
        list2.add(11, "宋江", "及时雨");
        list2.add(3, "卢俊义", "玉麒麟");
        list2.add(1, "张顺", "浪里白条");
        list2.forEach();
//        System.out.println();
//        list2.reverseList();
//        list2.forEach();
        System.out.println();
        list2.reversePrint();
    }
}

class SingleLinkedList {

    private boolean noSort;
    private boolean positive;
    private HeroNode head;
    private HeroNode end;
    private HeroNode headNo;

    public SingleLinkedList() {
        head = new HeroNode(0, "", "");
        this.noSort = false;
        end = head;
    }

    public SingleLinkedList(boolean positive) {
        this();
        this.noSort = true;
        this.positive = positive;
        if (noSort)
            headNo = new HeroNode(0, "", "");
    }

    public void add(int no, String name, String nickname) throws ExsitNoException {
        HeroNode heroNode = new HeroNode(no, name, nickname);
        if (noSort) {
            addByNo(heroNode);
        } else {
            end.setNext(heroNode);
            end = heroNode;
        }
    }

    private void addByNo(HeroNode heroNode) throws ExsitNoException {

        // 查找插入点
        HeroNode before = headNo;
        HeroNode temp = before.getNext();
        while (temp != null) {
            if (temp.getNo() == heroNode.getNo())
                throw new ExsitNoException("已经存在:" + temp + " 而再次添加:" + heroNode);
            else if (heroNode.getNo() > temp.getNo() ^ positive) {
                //异或：插入的no小(false)且正序排列(true) 插入的no大(true)且不是正序排列(false) 则找到插入点
                before.setNext(heroNode);
                heroNode.setNext(temp);
                return;
            } else {
                // 没找到插入点
                before = temp;
                temp = before.getNext();
            }
        }
        before.setNext(heroNode);
    }

    public void reversePrint(){
        Stack<HeroNode> stack = new Stack<>();
        HeroNode current;
        if(noSort)
            current = headNo.getNext();
        else
            current = head.getNext();
        while (current != null){
            stack.push(current);
            current = current.getNext();
        }
        while (stack.size() > 0)
            System.out.println(stack.pop());
    }

    public void reverseList() {
        HeroNode current = null;
        HeroNode next;
        if (noSort) {
            positive = !positive;
            if (headNo.getNext() == null || headNo.getNext().getNext() == null)
                return;
            current = headNo.getNext();
        } else {
            if (head.getNext() == null || head.getNext().getNext() == null)
                return;
            current = head.getNext();
        }
        HeroNode reverseHead = new HeroNode(0, "", "");
        while (current != null) {
            next = current.getNext();
            current.setNext(reverseHead.getNext());
            reverseHead.setNext(current);
            current = next;
        }
        if (noSort)
            headNo.setNext(reverseHead.getNext());
        else
            head.setNext(reverseHead.getNext());
    }

    public void forEach() {
        if (noSort) {
            forEachByNo();
            return;
        }
        if (head == end) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.getNext();
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getNext();
        }
    }

    private void forEachByNo() {
        if (headNo.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = headNo.getNext();
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getNext();
        }
    }

    class HeroNode {

        private int no;
        private String name;
        private String nickname;
        private HeroNode next;

        public HeroNode(int no, String name, String nickname) {
            this.no = no;
            this.name = name;
            this.nickname = nickname;
        }

        public HeroNode getNext() {
            return next;
        }

        public void setNext(HeroNode next) {
            this.next = next;
        }

        public int getNo() {
            return no;
        }

        public String getName() {
            return name;
        }

        public String getNickname() {
            return nickname;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname +
                    '}';
        }
    }
}

class ExsitNoException extends Exception {
    public ExsitNoException(String message) {
        super(message);
    }
}
