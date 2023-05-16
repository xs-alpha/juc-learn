package com.xiaosheng.juc.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author xiaosheng
 * @date Created at 2023/4/16
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 号同学离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        // 等待
        countDownLatch.await();
        System.out.println("班长锁门");
    }
}
