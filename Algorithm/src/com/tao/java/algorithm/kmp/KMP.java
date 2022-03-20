package com.tao.java.algorithm.kmp;

import org.junit.Test;

/**
 * @author AIERXUAN
 * @create 2022/3/19 - 19:41
 * @description
 */
public class KMP {

    /**
     * @param str1 主串
     * @param str2 子串
     * @return 如果没有匹配返回-1 找到返回下标
     */
    public static int kmpSearch(String str1, String str2) {
        int[] next = getNext(str2);
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j))
                j = next[j - 1];
            if (str1.charAt(i) == str2.charAt(j))
                j++;
            if (j == str2.length())
                return i - j + 1;
        }
        return -1;
    }

    /**
     * @param dest 待获取部分匹配值字符串
     * @return 字符串对应的部分匹配值
     */
    private static int[] getNext(String dest) { //ABCDABD 0000120
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j))
                j = next[j - 1];
            if (dest.charAt(i) == dest.charAt(j))
                j++;
            next[i] = j;
        }
        return next;
    }

    @Test
    public void test() {
        System.out.println(kmpSearch("BBC ABCDAB ABCDABCDABDE", "ABCDABD"));
    }

}
