package com.xiaosheng.juc.completableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaosheng
 * @date Created at 2023/4/21
 */
public class CompletableFutureDemo2 {
    public static void main(String[] args) throws InterruptedException {
        // 调用出错也会自动调用某个对象的方法
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "----come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {

            }
            System.out.println("一秒钟后出结果" + result);
            return result;
        }).whenComplete((v, e) -> {
            if (null == e) {
                System.out.println("计算完成");
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println("error----->");
            return null;
        });
        System.out.println(Thread.currentThread().getName() + "在忙其他");

        TimeUnit.SECONDS.sleep(2);
    }
}
