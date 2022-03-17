package com.tao.java.linkedlist;

/**
 * @author AIERXUAN
 * @create 2022/3/14 - 19:24
 * @description
 */
public class CircleLinkedListDemo {

    public static void main(String[] args) {
        int num = 15;
        int start = 1;
        int m = 23;
        CircleLinkedList<Boy> list = new CircleLinkedList();
        for (int i = 1; i <= num; i++) {
            list.put(new Boy(i));
        }
        for (int i = start; list.getSize() > 0; i++) {
            if (i % m == 0) {
                Boy b = list.getNext();
                if(list.remove(b))
                    System.out.println(b.getNum());
                continue;
            }
            list.getNext();
        }
    }

    static class CircleLinkedList<V> {

        private CircleNode first;
        private CircleNode last;
        private CircleNode current;
        private int size = 0;

        public void put(V v) {
            CircleNode circleNode;
            size++;
            if (first == null) {
                circleNode = new CircleNode<>(first, last, v);
                first = circleNode;
                last = circleNode;
                return;
            }
            circleNode = new CircleNode<>(last, first, v);
            first.setPre(circleNode);
            last.setNext(circleNode);
            last = circleNode;
        }

        public int getSize() {
            return size;
        }

        public V getNext() {
            if (current == null)
                current = first;
            V value = (V) current.getValue();
            current = current.getNext();
            return value;
        }

        public boolean remove(V value) {
            CircleNode node = first;
            for (int i = 1; i <= size; i++) {
                if (value.equals(node.getValue())) {
                    node.getPre().setNext(node.getNext());
                    node.getNext().setPre(node.getPre());
                    if(node == first)
                        first = first.getNext();
                    if(node == last)
                        last = last.getPre();
                    size--;
                    return true;
                } else
                    node = node.getNext();
            }
            return false;
        }

        class CircleNode<V> {

            private CircleNode pre;
            private CircleNode next;
            private V value;

            public V getValue() {
                return value;
            }

            public CircleNode(CircleNode pre, CircleNode next, V value) {
                this.pre = pre;
                this.next = next;
                this.value = value;
            }

            public CircleNode getPre() {
                return pre;
            }

            public void setPre(CircleNode pre) {
                this.pre = pre;
            }

            public CircleNode getNext() {
                return next;
            }

            public void setNext(CircleNode next) {
                this.next = next;
            }

        }
    }
}

class Boy {
    private int num;

    public Boy(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boy boy = (Boy) o;
        return num == boy.num;
    }

}