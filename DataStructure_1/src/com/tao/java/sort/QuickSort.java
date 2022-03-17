package com.tao.java.sort;

/**
 * @author AIERXUAN
 * @create 2022/3/15 - 20:26
 * @description
 */


public class QuickSort {

    /**
     *
     * @param arr
     * @param low 数组下标下界
     * @param high 数组下标上界，不是数组长度！！！
     */
    public static void quickSort(int[] arr, int low, int high) {

        if (low < high) {
            // 找寻基准数据的正确索引
            int index = getIndexAfterPartition(arr, low, high);

            // 进行迭代对index之前和之后的数组进行相同的操作使整个数组变成有序
            //quickSort(arr, 0, index - 1); 之前的版本，这种姿势有很大的性能问题，谢谢大家的建议
            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);
        }

    }

    /**
     * 对数组前后位置内的数按与基准数arr[low]对比的大小进行分区，并在完成后返回分区后的基准数位置
     * @param arr 需要处理的数组
     * @param low 需要处理数组的前位置
     * @param high 需要处理数组的后位置
     * @return 返回对arr分区后基准数的位置
     */
    private static int getIndexAfterPartition(int[] arr, int low, int high) {
        // 基准数据
        int benchmark = (int) (Math.random() * (arr.length - 1));
        int tmp = arr[low];
        while (low < high) {
            // 当队尾的元素大于等于基准数据时,向前挪动high指针
            while (low < high && arr[high] >= tmp) {
                high--;
            }
            // 如果队尾元素小于tmp了,需要将其赋值给基准位置
            arr[low] = arr[high];
            // 当队首元素小于等于tmp时,向前挪动low指针
            while (low < high && arr[low] <= tmp) {
                low++;
            }
            // 当队首元素大于tmp时,需要将其赋值给high
            arr[high] = arr[low];

        }
        // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
        // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
        arr[low] = tmp;
        return low; // 返回tmp的正确位置
    }

}
