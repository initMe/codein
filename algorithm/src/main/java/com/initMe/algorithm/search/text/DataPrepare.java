package com.initMe.algorithm.search.text;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description: 字典数据数据准备
 * @Author: yuyuchi
 * @Date: 2022/4/20 10:42 PM
 **/
public class DataPrepare {
    /**
     * 随机生成中文词组
     *
     * @param startSize 中文词组最小长度
     * @param endSize   中文词组最大长度
     * @return 中文词组
     */
    public static String getRandomChineseToken(int startSize, int endSize) {
        StringBuilder ret = new StringBuilder();
        ThreadLocalRandom current = ThreadLocalRandom.current();
        //随机大小
        int size = current.nextInt(startSize, endSize);
        for (int i = 0; i < size; i++) {
            String str = null;
            // 定义字符高低位，汉字分为高低位字节，分别代表区与位，所以可以定义两个变量，记录随机的区号和随机的位号
            int hightPos, lowPos;
            // 随机获取高位值 B0 + 0~39(16~55) 一级汉字所占区
            hightPos = (176 + Math.abs(current.nextInt(39)));
            // 随机获取低位值 A1 + 0~93 每区有94个汉字
            lowPos = (161 + Math.abs(current.nextInt(93)));
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                // 转成中文
                str = new String(b, "GBK");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret.append(str);
        }
        return ret.toString();
    }

    /**
     * 生成字典文件
     *
     * @param path      字典文件存储位置
     * @param wordCount 字典文件词组数量
     */
    public static void generateInputFile(String path, int wordCount) throws Exception {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
            System.out.println("文件已存在：" + path + "，即将删除");
        }

        System.out.println("创建文件：" + path);
        file.createNewFile();

        PrintWriter printWriter = new PrintWriter(new FileWriter(path));

        //随机词汇
        for (int i = 0; i < wordCount; i++) {
            printWriter.println(getRandomChineseToken(2, 5));
        }

        printWriter.flush();
        printWriter.close();

        System.out.println("生成完毕！");
    }

    public static void main(String[] args) throws Exception {
        //生成文件
        generateInputFile("/Users/fish/Desktop/input_file.txt", 20000000);
    }
}
