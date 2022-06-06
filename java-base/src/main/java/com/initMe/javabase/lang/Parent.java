package com.initMe.javabase.lang;

/**
 * @Description: 父类
 * @Author: jiqing
 * @Date: 2022/5/25 10:22 AM
 **/
public class Parent {
    static int fj = 88;

    static {
        System.out.println("父类第一个静态代码块");
    }

    static {
        System.out.println("父类静态代码块" + "fj=" + fj);
    }

    int i = 1;

    {
        i = 99;  // 代码块可以赋值其后定义的实例变量，但不能访问其后定义的实例变量
        System.out.println("父类第一个构造代码块");
    }

    {
        System.out.println("父实例变量初始化后:" + "i=" + i);
        i = 2;
        System.out.println("父代码块初始化后:" + "i=" + i);
    }

    public Parent() {
        i = getValue();// 父类调用子类的重写getValue()方法，而子类的变量j为0(因为jvm仅对内存空间进行过初始化)
        System.out.println("父构造函数初始化后:" + "i=" + i);
    }

    protected int getValue() {
        System.out.println("父类的getValue:i=" + i);
        return i;
    }
}
