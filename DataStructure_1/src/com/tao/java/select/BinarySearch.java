package com.tao.java.select;

/**
 * @author AIERXUAN
 * @create 2022/3/16 - 14:26
 * @description
 */
public class BinarySearch {

    public static int binarySearch(int[] arrays, int target) {
        int min = 0;
        int max = arrays.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if(arrays[mid] == target)
                return mid;
            if(arrays[mid] > target)
                max = mid - 1;
            if(arrays[mid] < target)
                min = mid + 1;
        }
        return -1;
    }
}
