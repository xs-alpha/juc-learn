package com.xiaosheng.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportParkDemo {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(Thread.currentThread().getName() + "come in");
            LockSupport.park();
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " 唤醒");

        }, "t");
        t.start();

        new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" 发通知");
                LockSupport.unpark(t);
            LockSupport.unpark(t);
        },"t2").start();



        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
