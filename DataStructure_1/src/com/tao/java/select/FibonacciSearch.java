package com.tao.java.select;

import java.util.Arrays;

/**
 * @author AIERXUAN
 * @create 2022/3/16 - 15:59
 * @description
 */
public class FibonacciSearch {

    public static int fibonacciSearch(int[] arrays, int target) {
        int left = 0, mid;
        int right = arrays.length - 1;
        double GOLDENSECTION = 0.618033989;

        while (left <= right) {
            mid = (int) (left + (right - left) * GOLDENSECTION);
            if (arrays[mid] == target)
                return mid;
            if (arrays[mid] > target) {
                right = mid - 1;
            } else if (arrays[mid] < target) {
                left = mid + 1;
            } else
                return mid;
        }
        return -1;
    }

    public static int fibonacciSearchByList(int[] arrays, int target) {
        int left = 0, mid = 0, fibKey = 0;
        int right = arrays.length - 1;
        int[] fib = fibonacci(right + 1);

        // 找到黄金分割后一项数值，前一项就是分割点
        while (right > fib[fibKey] - 1) {
            fibKey++;
            if (fibKey > fib.length)
                break;
        }

        // 数组长度不够时，填充为数组最后一位数
        int[] temp = Arrays.copyOf(arrays, fib[fibKey]);
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arrays[right];
        }

        while (left <= right) {
            mid = left + fib[fibKey - 1] - 1;
            if (temp[mid] == target)
                return mid;
            if (temp[mid] > target) {
                right = mid - 1;
                fibKey--;
            } else if (temp[mid] < target) {
                left = mid + 1;
                fibKey -= 2;
            } else
                return mid < right ? mid : right;
        }
        return -1;
    }

    /**
     * @param length
     * @return
     */
    private static int[] fibonacci(int length) {
        int fib1 = 1;
        int fib2 = 2;
        int i = 2;
        for (; fib2 < length && fib1 < length; i++) {
            if (fib1 > Integer.MAX_VALUE * 0.618 && fib2 > Integer.MAX_VALUE * 0.618)
                throw new RuntimeException("过大的长度！");
            if (fib1 < fib2)
                fib1 += fib2;
            else
                fib2 += fib1;
        }
        int[] fib = new int[++i];
        fib[0] = 1;
        fib[1] = 1;
        for (int j = 2; j < i; j++) {// && fib[i - 1] < length
            fib[j] = fib[j - 1] + fib[j - 2];
        }
        return fib;
    }

}
