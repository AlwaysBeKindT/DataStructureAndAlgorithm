package com.tao.java.sparsearray;

/**
 * @author AIERXUAN
 * @create 2022/3/14 - 11:45
 * @description 稀疏数组
 */
public class SparseArray {

    public static void main(String[] args) {

        // 原始数组 11*11 0表示没有棋子，1表示黑子，2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][0] = 1;
        chessArr1[4][10] = 2;
        chessArr1[5][8] = 1;

        // 输出原始二维数组
        System.out.println("原始二维数组：");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 转换成稀疏数组
        // 遍历获取非零数个数
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                if(data != 0)
                    sum++;
            }
        }

        // 创建稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 保存值
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        // 输出稀疏数组
        System.out.println("对应的稀疏数组：");
        for (int[] row : sparseArr) {
            System.out.printf("%d\t%d\t%d\n", row[0], row[1], row[2]);
        }

        // 转换成二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i <= sparseArr[0][2]; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        // 输出转换后的二维数组
        System.out.println("转换后的二维数组：");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }
}
