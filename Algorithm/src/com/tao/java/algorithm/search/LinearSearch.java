package com.tao.java.algorithm.search;

/**
 * @author AIERXUAN
 * @create 2022/3/16 - 14:17
 * @description
 */
public class LinearSearch {
    public static int linearSearch(int[] arrays, int target) {
        for (int i = 0; i < arrays.length; i++) {
            if(arrays[i] == target)
                return i;
        }
        return -1;
    }
}
