package com.tao.java.algorithm.sort;

/**
 * @author AIERXUAN
 * @create 2022/3/16 - 11:30
 * @description
 */
public class BubbleSort {

    public static void bubbleSort(int[] arrays) {
        boolean change = false;
        for (int i = 0; i < arrays.length - 1; i++) {
            change = false;
            for (int j = 0; j < arrays.length - 1 - i; j++) {
                if(arrays[j] > arrays[j+1]) { //a,b
                    arrays[j] += arrays[j + 1]; //a+b,b
                    arrays[j + 1] = arrays[j] - arrays[j + 1]; //a+b,a
                    arrays[j] -= arrays[j + 1]; //b,a
                    change = true;
                }
            }
            if(!change)
                break;
        }
    }

}
