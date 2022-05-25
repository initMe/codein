package com.initMe.algorithm.search.text;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: jiqing
 * @Date: 2022/4/20 10:14 PM
 **/
public class SimpleSearch {
    public static List<Integer> search(String mainStr, String patternStr) {
        //词汇索引（在字典中的顺序）列表
        List<Integer> index = new ArrayList<>();

        //base case
        //单词为空，直接返回
        //if (patternStr == null || patternStr.length() == 0) return index;
        //目标文档为空或者单词长度超过文档长度，直接返回
        //if (mainStr == null || patternStr.length() > mainStr.length()) return index;

        //转成字符数组
        char[] mainArr = mainStr.toCharArray();
        char[] targetArr = patternStr.toCharArray();
        int i; //主串位置
        int j; //模式串位置

        for (i = 0; i <= mainArr.length - targetArr.length; i++) {
            for (j = 0; j < targetArr.length; j++)
                if (mainArr[i + j] != targetArr[j]) break;
            if (j == targetArr.length) {
                index.add(i);
            }
        }
        return index;
    }

    public static void main(String[] args) {
        String mainStr = "bcdabcedfabcdsegsgabc";
        String targetStr = "abc";
        System.out.println(search(mainStr, targetStr));
    }
}
