package com.xiaosheng.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockLevelDown {
    public static void main(String[] args) {
        /**
         * 遵循获取写锁、获取读锁再释放写锁的次序，写锁能够降级成为读锁
         *
         * 写锁的降级，降级成为了读锁
         * 1.如果同一个线程持有了写锁，在没有释放写锁的情况下，它还可以继续获得读锁。这就是写锁的降级，降级成为了读锁。
         * 2.规则惯例，先获取写锁，然后获取读锁，再释放写锁的 次序。
         * 3.如果释放了写锁，那么就完全转换为读锁。
         */
//        // 锁升级
//        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
//        lock.writeLock().lock();
//        System.out.println("写锁");
//        lock.readLock().lock();
//        System.out.println("读锁");
//        lock.readLock().unlock();
//        lock.writeLock().unlock();

        //
        // 锁升级，错的
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();
        System.out.println("读锁");
        lock.writeLock().lock();
        System.out.println("写锁");
        lock.writeLock().unlock();
        lock.readLock().unlock();

        // 精简：锁降级：写-》读-》释放写锁
    }
}
