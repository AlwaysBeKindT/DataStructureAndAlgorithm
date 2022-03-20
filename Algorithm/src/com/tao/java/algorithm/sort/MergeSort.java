package com.tao.java.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author AIERXUAN
 * @create 2022/3/16 - 12:00
 * @description
 */
public class MergeSort {

    public static void mergeSort(int[] arrays, int left, int right) {

        int mid = (left + right) / 2;
        if (right - left > 1) { // 剩余数量大于2个则分组排序
            mergeSort(arrays, left, mid);
            mergeSort(arrays, mid + 1, right);
        }

        // 分组排序完成后合并
        int[] temp = new int[right - left + 1];
        for (int i = left, j = mid + 1, t = 0; i <= mid || j <= right; t++) { // 左右两侧指针分别越过mid和right时退出，此时两边数组已排序放入temp中
            if (i > mid) { // 如果左侧指针超过mid，就将右侧数组直接加入temp
                temp[t] = arrays[j++];
            } else if (j > right) { // 如果右侧指针超过right，就将左侧数组直接加入temp
                temp[t] = arrays[i++];
            } else if (arrays[i] < arrays[j]) { // 如果左侧值小于右侧值，就将左侧值加入temp，并且i++；
                temp[t] = arrays[i++];
            } else { // 如果左侧值大于右侧值，就将右侧值加入temp，并且j++；
                temp[t] = arrays[j++];
            }
        }

        for (int index = left, k = 0; index <= right; index++, k++) { //将排序完成的数组放回原数组
            arrays[index] = temp[k];
        }

    }


}
