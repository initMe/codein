package com.initMe.utils.common;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: jiqing
 * @Date: 2022/1/19 4:17 PM
 **/
public class Timeout {
    public static void main(String[] args) throws InterruptedException {
        //当前线程执行任务，等待
        CountDownLatch latch = new CountDownLatch(1);
        Runnable runnable = () -> {
            System.out.println("do something...");
            try {
                Thread.sleep(15000);
                latch.countDown(); //释放
                System.out.println("done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        //执行任务
        System.out.println("start");
        Thread thread = new Thread(runnable);
        thread.start();
        //设置5秒超时
        Timer timer = new HashedWheelTimer();
        timer.newTimeout(timeout -> {
            if (latch.getCount() > 0) {
                //超时
                System.out.println("time out, cancel task");
                latch.countDown();
                thread.interrupt();
            }
        }, 5, TimeUnit.SECONDS);
        latch.await();
        System.out.println("done");
    }
}
