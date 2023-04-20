package com.xiaosheng.juc.rwWrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xiaosheng
 * @date Created at 2023/4/17
 */
public class Demo1 {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        //写锁降级
        /*
         // 锁降级
         writeLock.lock();
         System.out.println("atguigu");
         // 获取读锁
         readLock.lock();
         System.out.println("读锁");
         // 释放写锁
         writeLock.unlock();
         // 释放读锁
         readLock.unlock();
         */

        // 读锁升级 -》失败
        readLock.lock();
        System.out.println("读锁");
        writeLock.lock();
        System.out.println("写锁");
    }
}
