package com.initMe.algorithm.search.text;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: jiqing
 * @Date: 2022/4/16 4:03 PM
 **/
public class KMPSearch {

    /**
     * kmp匹配算法
     *
     * @param mainStr    主串
     * @param patternStr 模式串
     * @return 返回匹配第一个字符所在主串的位置，存在多个返回多个
     */
    public static List<Integer> search(String mainStr, String patternStr) {
        //词汇索引（在字典中的顺序）列表
        List<Integer> index = new ArrayList<>();

        //部分匹配表
        int[] next = kmpNext(patternStr);
        for (int i = 0, j = 0; i < mainStr.length(); i++) {
            //需要处理 str1.charAt(i) ！= str2.charAt(j), 去调整j的大小
            //KMP算法核心点, 可以验证...
            while (j > 0 && mainStr.charAt(i) != patternStr.charAt(j)) {
                j = next[j - 1];
            }

            if (mainStr.charAt(i) == patternStr.charAt(j)) {
                j++;
            }
            if (j == patternStr.length()) {//找到了 // j = 3 i
                index.add(i - j + 1);
                j = 0;
            }
        }
        return index;
    }

    /**
     * 获取字符串的部分匹配表
     *
     * @param dest
     * @return
     */
    public static int[] kmpNext(String dest) {
        //创建一个next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; //如果字符串是长度为1 部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j) ，我们需要从next[j-1]获取新的j
            //直到我们发现 有  dest.charAt(i) == dest.charAt(j)成立才退出
            //这时kmp算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            //当dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
