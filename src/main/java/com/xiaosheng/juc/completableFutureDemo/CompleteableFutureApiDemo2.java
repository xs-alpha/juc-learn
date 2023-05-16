package com.xiaosheng.juc.completableFutureDemo;

import com.sun.media.sound.SoftTuning;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaosheng
 * @date Created at 2023/4/23
 */
public class CompleteableFutureApiDemo2 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        /**
         * thenApply
         * 计算结果存在依赖, 这两个线程串行化
         * 由于存在依赖关系，当前步错，不走下一步
         */
//        CompletableFuture.supplyAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            }catch (Exception e){
//
//            }
//            return 1;
//        }, pool).thenApply(f->{
//            System.out.println("f:"+f);
//            return f+2;
//        }).whenComplete((v,e)->{
//            if (null == e){
//                System.out.println(v);
//            }
//        }).exceptionally(e->{
//            System.out.println("error"+e);
//            return null;
//        });
//
//        pool.shutdown();
//        System.out.println(Thread.currentThread().getName()+"在忙");
//
//
        ExecutorService pool2 = Executors.newFixedThreadPool(3);
        /**
         * thenApply
         * 计算结果存在依赖, 这两个线程串行化
         * 有异常也可以继续往下走,可以根据异常参数进行进一步处理
         */
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {

            }
            return 1;
        }, pool2).handle((f, e) -> {
            System.out.println("f:" + f);
            int i = 1 / 0;
            System.out.println("111");
            return f + 2;
        }).handle((f, e) -> {
            System.out.println("222");
            return f + 1;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println(v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println("error:" + e);
            return null;
        });

        pool2.shutdown();
        System.out.println(Thread.currentThread().getName() + "在忙");
    }
}
