package com.xiaosheng.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicReferenceFieldUpdaterDemo {
    public static void main(String[] args) {
        MyVar myVar = new MyVar();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    myVar.init(myVar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },i+"").start();
        }
    }
}

class MyVar{
    // 这里一定要用包装类，不然会报错
    public volatile Boolean  flag = Boolean.FALSE;
    AtomicReferenceFieldUpdater<MyVar, Boolean> referenceFieldUpdater
            = AtomicReferenceFieldUpdater.newUpdater(MyVar.class, Boolean.class, "flag");

    public void init(MyVar myVar) throws InterruptedException {
        // 这里没有用while， 有一个完成就可以了
        if (referenceFieldUpdater.compareAndSet(myVar, Boolean.FALSE,Boolean.TRUE)){
            System.out.println(Thread.currentThread().getName()+"初始化即将完成，剩余3s");
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName()+"初始化完成");
        }else{
            System.out.println(Thread.currentThread().getName()+"已经初始化完成le");
        }

    }
}
