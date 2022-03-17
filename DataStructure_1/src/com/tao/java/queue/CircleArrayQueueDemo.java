package com.tao.java.queue;

import java.util.Scanner;

/**
 * @author AIERXUAN
 * @create 2022/3/14 - 12:37
 * @description
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue arrayQueue = new CircleArrayQueue(5);
        char key;
        try (Scanner scanner = new Scanner(System.in)) {
            boolean flag = true;
            while (flag) {
                System.out.println("s:显示队列\te:退出程序\ta:添加队列\tg:取出数据\th:查看下一位");
                key = scanner.next().charAt(0);
                switch (key) {
                    case 's':
                        arrayQueue.showQueue();
                        break;
                    case 'e':
                        flag = false;
                        break;
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
}

class CircleArrayQueue {
    private int maxSize;// 数组的最大容量
    private int size;// 队列数
    private int front;// 队列头
    private int rear;// 队列尾的后一个位置
    private int[] arr;// 存放队列

    // 初始化
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    // 队列是否已满
    public boolean isFull() {
        return size == maxSize;
    }

    // 队列是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 添加队列
    public boolean addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满，添加失败");
            return false;
        }
        arr[rear++] = n;
        rear = rear % maxSize;
        size++;
        return true;
    }

    // 获取队列数据，出列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        int value = arr[front++];
        front = front % maxSize;
        size--;
        return value;
    }

    //显示队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        // 遍历
        for (int i = front ; i < size + front; i++) {
            System.out.printf("队列%d值为%d\n", i + 1, arr[i % maxSize]);
        }
    }

    // 显示队列头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front % maxSize];
    }
}
