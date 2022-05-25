package com.initMe.algorithm.search.text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @Description:
 * @Author: jiqing
 * @Date: 2022/4/20 11:47 PM
 **/
public class DictionarySearcher {
    //创建字典列表，用于存储字典单词
    private static final List<String> words = new ArrayList<>();

    public DictionarySearcher(String filename) {
        //读取文件内容
        //将每行单词存储到字典列表，数据量较大情况下，使用BufferedReader提高性能和降低内存占用
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            //按行读取内容
            while ((line = bufferedReader.readLine()) != null) {
                //加入字典列表
                words.add(line);
            }
        } catch (IOException e) {
            //处理异常
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filename = "/Users/fish/Desktop/input_file.txt";
        String document = "美国规划协会中国办公室揭牌仪式及美国规划领域合作研讨会在浙江大学城乡规划设计研究院208会议室举行。" +
                "美国规划协会CEO James Drinan，国际项目及外联主任Jeffrey Soule先生，浙江大学党委副书记任少波，" +
                "浙江大学控股集团领导杨其和，西湖区政府代表应权英副主任....";
        DictionarySearcher ds = new DictionarySearcher(filename);
        long method1 = System.currentTimeMillis();
        System.out.println("匹配结果: " + ds.search(document, SimpleSearch::search));
        System.out.println("匹配耗时::" + (System.currentTimeMillis() - method1));

        long method2 = System.currentTimeMillis();
        System.out.println("匹配结果: " + ds.search(document, KMPSearch::search));
        System.out.println("匹配耗时::" + (System.currentTimeMillis() - method2));

        long method3 = System.currentTimeMillis();
        System.out.println("匹配结果: " + ds.search(document, SundaySearch::search));
        System.out.println("匹配耗时::" + (System.currentTimeMillis() - method3));

        /*long method4 = System.currentTimeMillis();
        System.out.println("bm匹配结果: " + ds.search(document, BoyerMooreSearch::search));
        System.out.println("bm匹配耗时::" + (System.currentTimeMillis() - method4));*/
    }

    /**
     * 字典查找
     *
     * @param document 目标文本
     * @return 单词匹配位置列表
     */
    public Map<String, List<Integer>> search(String document) {
        //输出结果 key：词汇-> value: 词汇所在字典位置列表
        Map<String, List<Integer>> output = new HashMap<>();

        //遍历字典，开始匹配
        for (String word : words) {
            //词汇索引（在字典中的顺序）列表
            List<Integer> index = match(document, word);
            //输出结果
            if (!index.isEmpty()) {
                output.put(word, index);
            }
        }

        return output;
    }

    public Map<String, List<Integer>> search(String document, BiFunction<String, String, List<Integer>> searchFunc) {
        //输出结果 key：词汇-> value: 词汇所在字典位置列表
        Map<String, List<Integer>> output = new HashMap<>();

        //遍历字典，开始匹配
        for (String word : words) {
            //词汇索引（在字典中的顺序）列表
            List<Integer> index = searchFunc.apply(document, word);
            //输出结果
            if (!index.isEmpty()) {
                output.put(word, index);
            }
        }

        return output;
    }

    /**
     * 字符串匹配
     *
     * @param mainStr    主串
     * @param patternStr 模式串
     * @return 当前模式串匹配位置（多个）
     */
    private List<Integer> match(String mainStr, String patternStr) {
        //return SundaySearch.search(mainStr, patternStr);
        //return BoyerMooreSearch.search(mainStr, patternStr);
        return KMPSearch.search(mainStr, patternStr);
        //return SimpleSearch.search(mainStr, patternStr);
    }
}
