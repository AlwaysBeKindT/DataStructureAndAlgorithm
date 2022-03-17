package com.tao.java.select;

import org.junit.Test;
import static com.tao.java.select.BinarySearch.binarySearch;
import static com.tao.java.select.FibonacciSearch.fibonacciSearch;
import static com.tao.java.select.InsertSearch.insertSearch;
import static com.tao.java.select.LinearSearch.linearSearch;
import static com.tao.java.sort.QuickSort.quickSort;

/**
 * @author AIERXUAN
 * @create 2022/3/16 - 14:18
 * @description
 */
public class SearchTest {
    int num = 9000 * 10000;
    int[] arr = new int[num];
    long start = 0, end = 0;
    int result = -2;
    @Test
    public void test() {
//        int[] arr = new int[]{1, 3, 5, 7, 9, 15, 78, 156, 7426, 12354, 12355, 12355, 13546};
        for (int i = 0; i < num; i++) {
            arr[i] = (int) (Math.random() * (num * 100));
//            arr[i] = i;
        }
        int target = arr[arr.length / 2];
        System.out.printf("数组长度：%d 查找目标:%d\t", arr.length, target);
        start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        outPrint(result, "快速排序：");

        result = -1;
        start = System.currentTimeMillis();
        result = binarySearch(arr, target);
        outPrint(result, "二分查找：");

        start = System.currentTimeMillis();
        result = linearSearch(arr, target);
        outPrint(result, "线性查找：");

        start = System.currentTimeMillis();
        result = insertSearch(arr, target);
        outPrint(result, "插值查找：");

        start = System.currentTimeMillis();
        result = fibonacciSearch(arr, target);
        outPrint(result, "斐波那契查找：");

    }

    private void outPrint(int result, String message) {
        end = System.currentTimeMillis();
        if(result > -1)
            System.out.printf("arrays[%d]=%d\t", result, arr[result]);
        else if(result == -1)
            System.out.print("未找到 \t");
        System.out.println(message + "\t" + (end - start) / 1000.00 + "s");
    }

}
