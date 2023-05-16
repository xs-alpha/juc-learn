package com.xiaosheng.juc.completableFutureDemo;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaosheng
 * @date Created at 2023/4/23
 */
public class CompletableFutureApiDemo4 {
    public static void main(String[] args) {
        CompletableFuture<String> playa = CompletableFuture.supplyAsync(() -> {
            System.out.println("a come in");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {

            }
            return "a";
        });

        CompletableFuture<String> playb = CompletableFuture.supplyAsync(() -> {
            System.out.println("b come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {

            }
            return "b";
        });
        CompletableFuture<String> result = playa.applyToEither(playb, f -> {
            return f + "in win";
        });

        System.out.println(Thread.currentThread().getName() + "\t" + "---" + result.join());
    }
}
