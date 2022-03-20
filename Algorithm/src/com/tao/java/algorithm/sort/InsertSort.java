package com.tao.java.algorithm.sort;

/**
 * @author AIERXUAN
 * @create 2022/3/15 - 19:22
 * @description 将数组分为有序数组（第一个）和无序数组，然后将后续值插入有序数组
 */
public class InsertSort {
    public static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for(int i = 1; i < arr.length; i++) {
            // 定义待插入的数
            insertVal = arr[i];
            // 已完成排序的数组最后一位
            insertIndex = i - 1;

            // 判断插入位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // 插入已排序数组
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
