package com.xiaosheng.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock(){

        Thread thread = Thread.currentThread();
        System.out.println("lock"+thread.currentThread().getName()+"进来了");
        while (!atomicReference.compareAndSet(null, thread)){

        }
    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        System.out.println("unlock"+thread.currentThread().getName()+"进来了");
//        while (!atomicReference.compareAndSet(thread, null)){
//
//        }
        atomicReference.compareAndSet(thread, null);

    }

    public static void main(String[] args) {
        SpinLockDemo lockDemo = new SpinLockDemo();


        new Thread(()->{
            lockDemo.lock();

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lockDemo.unlock();
        },"a").start();

        try {
            TimeUnit.MICROSECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            lockDemo.lock();

            lockDemo.unlock();
        },"b").start();
    }
}
