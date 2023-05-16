package com.xiaosheng.juc;

public class JITDemo {
    public static void main(String[] args) {
        Object objectLock = new Object();
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println("11111111");
            }
            synchronized (objectLock) {
                System.out.println("22222222");
            }
            synchronized (objectLock) {
                System.out.println("333333333");
            }
            synchronized (objectLock) {
                System.out.println("4444444444");
            }
            // 锁粗化，假如方法中首尾相接，前后相邻的都是同一个锁对象，
            // 那JIT编译器就会把这几个synchronized块合并成一个大块,加粗加大范围，
            // 一次申请锁使用即可，避免次次的申请和释放锁，帮升了性能
            synchronized (objectLock) {
                System.out.println("11111");
                System.out.println("222222");
                System.out.println("333333");
                System.out.println("444444");
            }

        }).start();
    }
}