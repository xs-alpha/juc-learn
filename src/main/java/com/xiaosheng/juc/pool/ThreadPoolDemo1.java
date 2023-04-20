package com.xiaosheng.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaosheng
 * @date Created at 2023/4/17
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        // 一池5线程
//        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        // 一池一线程
//        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();

        ExecutorService threadPool1 = Executors.newCachedThreadPool();

        try {
            // 10个顾客
            for (int i = 1; i <= 100; i++) {
                threadPool1.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        } catch (Exception e) {

        } finally {
            threadPool1.shutdown();
        }
    }
}
