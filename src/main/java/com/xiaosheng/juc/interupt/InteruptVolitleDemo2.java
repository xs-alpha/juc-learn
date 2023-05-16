package com.xiaosheng.juc.interupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author xiaosheng
 * @date Created at 2023/4/26
 */
public class InteruptVolitleDemo2 {
    private static volatile boolean flag = false;
    private static AtomicBoolean flags = new AtomicBoolean();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("来玩呀----");
                if (flags.get()) {
                    System.out.println(Thread.currentThread().getName() + "程序终止------------");
                    break;
                }
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.NANOSECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            flags.set(true);
        }, "t2").start();
    }
}
