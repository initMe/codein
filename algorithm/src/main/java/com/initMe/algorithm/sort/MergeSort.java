package com.initMe.algorithm.sort;

import java.util.Arrays;

/**
 * @Description: 归并排序, 利用归并思想对数列进行排序。根据具体的实现，归并排序包括"从上往下"和"从下往上"2种方式。
 * 1. 从下往上的归并排序：将待排序的数列分成若干个长度为1的子数列，然后将这些数列两两合并；得到若干个长度为2的有序数列，再将这些数列两两合并；得到若干个长度为4的有序数列，再将它们两两合并；直接合并成一个数列为止。这样就得到了我们想要的排序结果。(参考下面的图片)
 * 2. 从上往下的归并排序：它与"从下往上"在排序上是反方向的。它基本包括3步：
 * ① 分解 -- 将当前区间一分为二，即求分裂点 mid = (low + high)/2;
 * ② 求解 -- 递归地对两个子区间a[low...mid] 和 a[mid+1...high]进行归并排序。递归的终结条件是子区间长度为1。
 * ③ 合并 -- 将已排序的两个子区间a[low...mid]和 a[mid+1...high]归并为一个有序的区间a[low...high]。
 * @Author: jiqing
 * @Date: 2022/2/10 5:29 PM
 **/
public class MergeSort {
    public static void main(String[] args) {
        int[] data = new int[]{4, 1, 2, 5, 10, 6, 5, 7, 3, 9, 2};
        int[] temp = new int[data.length];
        sort(data, 0, data.length - 1, temp);
        System.out.println(Arrays.toString(data));

        int[] data2 = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] temp2 = new int[data.length];
        sort(data2, 0, data.length - 1, temp2);
        System.out.println(Arrays.toString(data2));
    }

    public static int[] sort(int[] data, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(data, left, mid, temp);
            sort(data, mid + 1, right, temp);
            merge(data, left, mid, right, temp);
        }
        return data;
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//左序列指针
        int j = mid + 1;//右序列指针
        int t = 0;//临时数组指针
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while (j <= right) {//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }
}
