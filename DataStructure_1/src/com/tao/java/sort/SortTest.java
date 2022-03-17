package com.tao.java.sort;

import org.junit.Test;

import java.util.Arrays;

import static com.tao.java.sort.BasicSort.basicSort;
import static com.tao.java.sort.BubbleSort.bubbleSort;
import static com.tao.java.sort.InsertSort.insertSort;
import static com.tao.java.sort.MergeSort.mergeSort;
import static com.tao.java.sort.QuickSort.quickSort;
import static com.tao.java.sort.ShellSort.shellSort;

/**
 * @author AIERXUAN
 * @create 2022/3/15 - 19:34
 * @description 100000个数排序
 * 冒泡排序：23.022s
 * 插入排序：1.223s
 * 基数排序：0.051s
 * 希尔排序：0.037s
 * 归并排序：0.036
 * 快速排序：0.032s
 */
public class SortTest {

    long start = 0, end = 0;

    @Test
    public void test() {

        // 创建长度为num的随机数组
        int num = 100000;
        int[] arr1 = new int[num];
        int[] arr2, arr3, arr4, arr5, arr6;
        for (int i = 0; i < num; i++) {
            arr1[i] = (int) (Math.random() * (num * 100));
        }
        arr2 = arr1.clone(); arr3 = arr1.clone();
        arr4 = arr1.clone(); arr5 = arr1.clone(); arr6 = arr1.clone();

        start = System.currentTimeMillis();
        quickSort(arr1, 0, num - 1);
        outPrint("快速排序：");

        start = System.currentTimeMillis();
        mergeSort(arr2, 0, num - 1);
        outPrint("归并排序：");

        start = System.currentTimeMillis();
        shellSort(arr3);
        outPrint("希尔排序：");

        start = System.currentTimeMillis();
        basicSort(arr4);
        outPrint("基数排序：");

        start = System.currentTimeMillis();
        insertSort(arr5);
        outPrint("插入排序：");

        if (num < 11000) { //速度慢，数量大了就不运行
            start = System.currentTimeMillis();
            bubbleSort(arr6);
            outPrint("冒泡排序：");
        }

        show(num, arr1, arr2, arr3, arr4, arr5, arr6);

    }

    private void outPrint(String message) {
        end = System.currentTimeMillis();
        System.out.println(message + "\t" + (end - start) / 1000.00 + "s");
    }

    private void show(int num, int[]... arrays) {
        for (int[] arr : arrays) {
            if (num > 10)
                arr = Arrays.copyOfRange(arr, num / 2, num / 2 + 10);
            System.out.println(Arrays.toString(arr));
        }
    }
}
