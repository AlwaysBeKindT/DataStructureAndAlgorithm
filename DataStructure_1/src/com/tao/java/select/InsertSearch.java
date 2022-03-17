package com.tao.java.select;

/**
 * @author AIERXUAN
 * @create 2022/3/16 - 15:13
 * @description
 */
public class InsertSearch {

    public static int insertSearch(int[] arrays, int target) {
        int left = 0;
        int right = arrays.length - 1;
        int mid = left + (target - arrays[left]) / (arrays[right] - arrays[left]) * (right - left);
        while (left <= right) {
            if (arrays[mid] == target)
                return mid;
            if (arrays[mid] > target)
                right = mid - 1;
            if (arrays[mid] < target)
                left = mid + 1;
            mid = left + (target - arrays[left]) / (arrays[right] - arrays[left]) * (right - left);
        }
        return -1;
    }
}
