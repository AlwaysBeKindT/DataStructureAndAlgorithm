package com.tao.java.datastruture.stack;

/**
 * @author AIERXUAN
 * @create 2022/3/14 - 21:27
 * @description
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        stack.list();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.list();
        System.out.println();
        for (int i = 0; i < 4; i++) {
            System.out.println(stack.pop());
        }
        try {
            System.out.println(stack.pop());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        for (int i = 0; i < 4; i++) {
            System.out.println(stack.pop());
        }
    }
}

class ArrayStack {
    private int maxSize; // 栈的大小
    private int[] stack; // 存储数组
    private int top = -1; // 栈顶

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        stack[++top] = value;
    }

    public int pop() {
        if(isEmpty())
            throw new RuntimeException("栈空");
        return stack[top--];
    }

    public void list() {
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}