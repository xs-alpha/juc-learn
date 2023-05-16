package com.xiaosheng.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaosheng
 * @date Created at 2023/4/15
 */


// 第一部：创建资源类, 定义属性和操作方法
class Ticket {

    private int number = 30;

    // 创建可重入锁
//    private final ReentrantLock lock = new ReentrantLock(true); // 公平锁
    private final ReentrantLock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出：" + (number--) + "剩下：" + number);
        }
        lock.unlock();
    }
}

public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "售票员1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "售票员2").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "售票员3").start();
    }
}
