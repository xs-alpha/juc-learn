package com.xiaosheng.juc.completableFutureDemo;

import com.sun.media.sound.SoftTuning;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaosheng
 * @date Created at 2023/4/21
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> stringFutureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "come in-----");
            try {
                TimeUnit.SECONDS.sleep(5L);
                return "200";
            } catch (Exception e) {
                return "err";
            }
        });
        new Thread(stringFutureTask, "t").start();

        System.out.println("main线程去忙其他的了");
        System.out.println(stringFutureTask.get());
    }
}
