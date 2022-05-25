package com.initMe.algorithm.sort;

import java.util.Arrays;

/**
 * @Description: 插入排序
 * @Author: fish
 * @DateTime: 2022-02-09 21:46
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] data = new int[]{4, 1, 2, 5, 10, 6, 5, 7, 3, 9, 2};
        sort(data);
        System.out.println(Arrays.toString(data));

        int[] data2 = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        sort(data2);
        System.out.println(Arrays.toString(data2));
    }

    private static void sort(int[] data) {
        int swap = 0, loop = 0;
        int card;
        for (int i = 1; i < data.length; i++) {
            card = data[i];
            int y = i - 1;
            while (y >= 0 && card < data[y]) {
                data[y + 1] = data[y];
                y--;
                loop++;
            }
            data[y + 1] = card;
            swap++;
        }

        /*for (int i = 1; i < data.length ; i++) {
            card = data[i];
            for (int j = 0; j < i; j++) {
                if (data[j] > card) {
                    //移动数组
                    for (int k = i; k > j; k--) {
                        data[k] = data[k-1];
                        loop++;
                    }
                    data[j] = card;
                    swap++;
                    break;
                }
            }
        }*/
        System.out.println("swap: " + swap + ", loop:" + loop);
    }
}
