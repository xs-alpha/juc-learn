package com.xiaosheng.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaosheng
 * @date Created at 2023/4/16
 */

class ShareResource {
    private Integer flag = 1;  //AA:1 BB:2 CC:3

    // 创建锁
    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(int loop) {
        lock.lock();
        try {
            // 判断
            while (flag != 1) {
                c1.await();
            }
            // 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i + "轮数：" + loop);
            }
            flag = 2;
            // 通知
            c2.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void print10(int loop) {
        lock.lock();
        try {
            // 判断
            while (flag != 2) {
                c2.await();
            }
            // 干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i + "轮数：" + loop);
            }
            flag = 3;
            // 通知
            c3.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void print15(int loop) {
        lock.lock();
        try {
            // 判断
            while (flag != 3) {
                c3.await();
            }
            // 干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i + "轮数：" + loop);
            }
            flag = 1;
            // 通知
            c1.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
}

public class ThreadDemo3 {
    public static void main(String[] args) {
        ShareResource share = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                share.print5(i);
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                share.print10(i);
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                share.print15(i);
            }
        }, "CC").start();
    }
}
