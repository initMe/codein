package com.initMe.algorithm.sort;

import java.util.Arrays;

/**
 * @Description: 快速排序, 使用分治法策略, 它的基本思想是：
 * 选择一个基准数，通过一趟排序将要排序的数据分割成独立的两部分；
 * 其中一部分的所有数据都比另外一部分的所有数据都要小。然后，
 * 再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 * @Author: jiqing
 * @Date: 2022/2/10 10:22 AM
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] data = new int[]{4, 1, 2, 5, 10, 6, 5, 7, 3, 9, 2};
        sort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));

        int[] data2 = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        sort(data2, 0, data2.length - 1);
        System.out.println(Arrays.toString(data2));
    }

    private static void sort(int[] data, int low, int high) {
        System.out.println("start: low = " + low + ", high = " + high);
        if (low < high) {
            int key = data[low];
            int l = low;
            int h = high;
            while (low < high) {
                while (low < high && data[high] > key) {
                    high--;
                }
                if (low < high) {
                    data[low++] = data[high];
                }
                while (low < high && data[low] < key) {
                    low++;
                }
                if (low < high) {
                    data[high--] = data[low];
                }
                System.out.println("swap:low = " + low + ", high = " + high);
            }
            data[low] = key;
            sort(data, l, low - 1);
            sort(data, low + 1, h);
        }
    }
}
