package com.initMe.algorithm.sort;

import java.util.Arrays;

/**
 * @Description: 冒泡排序
 * @Author: fish
 * @DateTime: 2022-02-09 21:08
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] data = new int[]{4, 1, 2, 5, 10, 6, 5, 7, 3, 9, 2};
        sort(data);
        System.out.println(Arrays.toString(data));

        int[] data2 = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        sort(data2);
        System.out.println(Arrays.toString(data2));
    }

    public static int[] sort(int[] data) {
        int swap = 0, loop = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[i] > data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                    swap++;
                }
                loop++;
            }
        }
        System.out.println("swap: " + swap + ", loop:" + loop);
        return data;
    }
}
