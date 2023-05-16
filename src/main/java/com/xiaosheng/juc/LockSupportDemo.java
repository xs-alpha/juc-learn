package com.xiaosheng.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupportDemo {
    public static void main(String[] args) {
        /**
         * Condition和await使用的条件：
         * 1.线程必须先持有并获得缩，必须在锁块（synchronized或lock中）
         * 2.必须先等待再唤醒才能唤醒
         */
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"come in");
                condition.await();
                System.out.println(Thread.currentThread().getName()+" 唤醒");
            }catch (Exception e){

            }finally {
                lock.unlock();
            }
        },"t").start();

        new Thread(()->{
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName()+" 发通知");
            }catch (Exception e){

            }finally {
                lock.unlock();
            }
        },"t2").start();



        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
