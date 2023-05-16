package com.xiaosheng.juc.completableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaosheng
 * @date Created at 2023/4/23
 */
public class CompleteableFutureApiDemo3 {
    public static void main(String[] args) {
        /**
         * thenAccept: 消费型
         */
        CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenApply(f -> {
            System.out.println("f:" + f);
            return f + 2;
        }).thenApply(f -> {
            return f + 3;
        }).thenAccept(f -> {
            System.out.println("f:" + f);
        });

        /**
         * thenRun:任务a执行完执行任务b,并且b不需要a的结果
         * thenAccept:任务a执行完执行任务b,并且b需要a的结果,但是任务b无返回值
         * thenApply:任务a执行完执行任务b,并且b不需要a的结果,但是任务b有返回值
         */
        System.out.println(CompletableFuture.supplyAsync(() -> "result").thenRun(() -> {
        }).join());
    }
}
