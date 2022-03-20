package com.tao.java.algorithm.sort;


/**
 * @author AIERXUAN
 * @create 2022/3/16 - 10:28
 * @description
 */
public class BasicSort {
    public static void basicSort(int[] arrays) {
        int max = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            if(max < arrays[i])
                max = arrays[i];
        }
        int maxLength = (max + "").length();
        int[][] bucket = new int[10][arrays.length];
        int[] buketElementCount = new int[10];
        for (int i = 1; i <= maxLength; i++) {
            for (int j = 0; j < arrays.length; j++) {
                int locateElement = (arrays[j]/((int) Math.pow(10, i - 1))%10);
                bucket[locateElement][buketElementCount[locateElement]] = arrays[j];
                buketElementCount[locateElement]++;
            }
            int index = 0;
            for (int j = 0; j < buketElementCount.length; j++) {
                if (buketElementCount[j] != 0) {
                    for (int k = 0; k < buketElementCount[j]; k++) {
                        arrays[index] = bucket[j][k];
                        index++;
                    }
                }
            }
            buketElementCount = new int[10];
        }
    }

}
