package com.initMe.algorithm.sort;

import java.util.Arrays;

/**
 * @Description: 希尔排序, 1959年Shell发明，第一个突破O(n2)的排序算法，是简单插入排序的改进版。
 * 它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
 * @Author: jiqing
 * @Date: 2022/2/10 11:56 AM
 **/
public class ShellSort {
    public static void main(String[] args) {
        int[] data = new int[]{4, 1, 2, 5, 10, 6, 5, 7, 3, 9, 2};
        sort(data);
        System.out.println(Arrays.toString(data));

        int[] data2 = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        sort(data2);
        System.out.println(Arrays.toString(data2));
    }

    private static void sort(int[] data) {
        int length = data.length;
        //增量gap，并逐步缩小增量
        for (int gap = length / 2; gap > 0; gap = gap / 2) {
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for (int i = gap; i < length; i++) {
                int j = i;
                int current = data[i];
                while (j - gap >= 0 && current < data[j - gap]) {
                    data[j] = data[j - gap];
                    j = j - gap;
                }
                data[j] = current;
            }
        }
    }
}
