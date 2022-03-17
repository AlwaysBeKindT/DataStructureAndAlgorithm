package com.tao.java.queue;

import java.util.Scanner;

/**
 * @author AIERXUAN
 * @create 2022/3/14 - 12:37
 * @description
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(5);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);

        label:
        while (true) {
            System.out.println("s:显示队列\te:退出程序\ta:添加队列\tg:取出数据\th:查看下一位");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    break label;
                case 'a':
                    System.out.print("输入一个数：");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.println("取出的数据是" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.println("下一位是" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }

        }
    }
}

class ArrayQueue {
    private int maxSize;// 数组的最大容量
    private int front;// 队列头前一个位置
    private int rear;// 队列尾
    private int[] arr;// 存放队列

    // 初始化
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        front = -1;
        rear = -1;
        arr = new int[maxSize];
    }

    // 队列是否已满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加队列
    public boolean addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满，添加失败");
            return false;
        }
        arr[++rear] = n;
        return true;
    }

    // 获取队列数据，出列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[++front];
    }

    //显示队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        // 遍历
        for (int i = front == -1 ? 0 : front; i <= rear; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    // 显示队列头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front + 1];
    }
}
