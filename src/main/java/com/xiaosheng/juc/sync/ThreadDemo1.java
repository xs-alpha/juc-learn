package com.xiaosheng.juc.sync;

/**
 * @author xiaosheng
 * @date Created at 2023/4/15
 */
class Share {
    private int number = 0;

    public synchronized void incr() throws InterruptedException {
        // 做判断
        if (number == 0) {
            // 干活
            number++;
        } else {
            // 干活
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + "::" + number);
        // 通知
        this.notifyAll();
    }

    public synchronized void decr() {
        // 判断
        if (number == 1) {
            // 干活
            number--;
        } else {
            // 干活
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName() + "::" + number);
        // 通知
        this.notifyAll();
    }
}

public class ThreadDemo1 {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "aa").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, "bb").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, "cc").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, "dd").start();
    }
}
