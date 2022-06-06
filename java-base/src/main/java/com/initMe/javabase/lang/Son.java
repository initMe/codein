package com.initMe.javabase.lang;

/**
 * @Description: 子类
 * @Author: jiqing
 * @Date: 2022/5/25 10:22 AM
 **/
public class Son extends Parent {
    static int sj = 66;

    static {
        System.out.println("子类第一个静态代码块");
    }

    static {
        System.out.println("子类静态代码块" + "sj=" + sj);
    }

    int j = 3;

    {
        System.out.println("");
        System.out.println("子类第一个构造代码块");
    }

    {
        System.out.println("子实例变量初始化后:" + "j=" + j);
        j = 5;
        System.out.println("子代码块初始化后:" + "j=" + j);
    }

    public Son() {
        j = 4;
        System.out.println("子构造函数初始化后:" + "j=" + j);
    }

    public static void main(String[] args) {
        Son son = new Son();
        System.out.println("子类getValue返回值:" + son.getValue());
    }

    @Override
    protected int getValue() {
        System.out.println("调用子类重写的getValue:j=" + j);
        return j;
    }
}
