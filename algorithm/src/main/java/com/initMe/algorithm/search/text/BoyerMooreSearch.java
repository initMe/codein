package com.initMe.algorithm.search.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: https://github.com/superzhu/Algorithms/blob/master/String/src/main/java/string/pattern/BoyerMoore.java
 * @Author: jiqing
 * @Date: 2022/4/23 4:46 PM
 **/
public class BoyerMooreSearch {
    public static List<Integer> search(String mainStr, String patternStr) {
        //词汇索引（在字典中的顺序）列表
        List<Integer> index = new ArrayList<>();
        //base case
        //单词为空，直接返回
        if (patternStr == null || patternStr.length() == 0) return index;
        //目标文档为空或者单词长度超过文档长度，直接返回
        if (mainStr == null || patternStr.length() > mainStr.length()) return index;

        // position of rightmost occurrence of c in the pattern
        Map<Integer, Integer> map = new HashMap<>();
        int[] right = new int[256];
        for (int c = 0; c < 256; c++)
            right[c] = -1;
        for (int j = 0; j < patternStr.length(); j++) {
            //right[patternStr.charAt(j)] = j;
            map.put((int) patternStr.charAt(j), j);
        }


        //开始循环处理
        //主串位置
        int pLen = patternStr.length();
        int mLen = mainStr.length();
        int skip;
        for (int i = 0; i <= mLen - pLen; i += skip) {
            skip = 0;
            for (int j = pLen - 1; j >= 0; j--) {
                if (patternStr.charAt(j) != mainStr.charAt(i + j)) {
                    //skip = Math.max(1, j - right[mainStr.charAt(i + j)]);
                    Integer m = map.getOrDefault((int) mainStr.charAt(i + j), -1);
                    skip = Math.max(1, m);
                    break;
                }
            }
            if (skip == 0) {
                index.add(i);
                skip = 1;
            }
        }

        return index;
    }

    public static void main(String[] args) {
        String mainStr = "中国你好啊";
        String targetStr = "你好";
        System.out.println(search(mainStr, targetStr));
    }
}
