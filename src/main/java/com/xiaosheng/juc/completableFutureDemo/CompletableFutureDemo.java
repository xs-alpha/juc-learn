package com.xiaosheng.juc.completableFutureDemo;

import java.util.EmptyStackException;
import java.util.concurrent.*;

/**
 * @author xiaosheng
 * @date Created at 2023/4/19
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 异步调用，没有返回值
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "completableFuture1");
        });
        System.out.println(voidCompletableFuture.get());

        // 异步调用，有返回值
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
//                int i = 1 / 0;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "completableFuture1");
            return 900;
        });
        // t是返回值，u是异常
        integerCompletableFuture.whenComplete((t, u) -> {
            System.out.println("---t:" + t);
            System.out.println("---u:" + u);
        }).get();

        //--------------------------

    }
}
