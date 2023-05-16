package com.xiaosheng.juc.completableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author xiaosheng
 * @date Created at 2023/4/23
 */
public class CompleteableFutureApiDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> completeable = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {

            }
            return "abc";
        });
//        System.out.println(completeable.get());
//        System.out.println(completeable.get(2L, TimeUnit.SECONDS));

        /**
         * 没有计算完的情况下，给一个替代结果
         * 立即获取结果不阻塞，
         * - 计算完：返回计算完成后的结果
         * - 没计算完，返回设定的valueIfAbsent值
         */
//        System.out.println(completeable.getNow("xxx"));

        /**
         * 当调用CompletableFuture.get()被阻塞的是否，complete方法就是结束阻塞并get()获取设置的complete里面的值
         */
        System.out.println((completeable.complete("complete") + "\t" + completeable.join()));
    }
}
