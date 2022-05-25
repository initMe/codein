package com.initMe.algorithm.search.text;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Sunday算法是Daniel M.Sunday于1990年提出的字符串模式匹配
 * 在匹配过程中，模式串并不被要求一定要按从左向右进行比较还是从右向左进行比较，它在发现不匹配时，算法能跳过尽可能多的字符以进行下一步的匹配，从而提高了匹配效率
 * <p>
 * https://www.cnblogs.com/MacrossFT/p/14159106.html
 * https://github.com/superzhu/Algorithms/blob/master/String/src/main/java/string/pattern/Sunday.java
 * @Author: jiqing
 * @Date: 2022/4/16 5:04 PM
 **/
public class SundaySearch {
    public static List<Integer> search(String mainStr, String patternStr) {
        //词汇索引（在字典中的顺序）列表
        List<Integer> index = new ArrayList<>();

        //转成字符数组
        char[] mainArr = mainStr.toCharArray();
        char[] targetArr = patternStr.toCharArray();
        int i = 0;

        //开始循环处理
        //主串位置
        while (i < mainArr.length) {
            //模式串位置
            int j = 0;
            //字符循环匹配，匹配成功一个字符，模式串位置右移一位，知道出现没有匹配的字符
            while (j < targetArr.length && i + j < mainArr.length && mainArr[i + j] == targetArr[j]) {
                j++;
            }

            //长度相等，说明匹配成功
            if (j == targetArr.length) {
                //匹配成功，将索引加入列表（主串位置）
                index.add(i);
                //右移一位，重新开始匹配
                i++;
                continue;
            }

            //判断长度是否越界，否则继续匹配
            if (i + targetArr.length < mainArr.length) {
                //计算与子串主串对齐最后一位右移一位字符相同的子串字符位置
                for (j = targetArr.length - 1; j >= 0; j--) {
                    //有一个字符匹配上则停止
                    if (targetArr[j] == mainArr[i + targetArr.length]) {
                        break;
                    }
                }
            }
            //右移，计算下一次主串开始匹配位置
            i += targetArr.length - j;
        }

        return index;
    }

    public static void main(String[] args) {
        String mainStr = "bcdabcedfabcdsegsgabc";
        String targetStr = "abc";
        System.out.println(search(mainStr, targetStr));
    }
}
