package com.xiaosheng.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerAddDemo {

    public static void main(String[] args) throws InterruptedException {
        MyNumber myNumber = new MyNumber();
        Integer size = 50;

        CountDownLatch countDownLatch = new CountDownLatch(size);

        for (int i = 0; i < size; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 1000; j++) {
                        myNumber.addAndPlus();
                    }
                }finally {
                    countDownLatch.countDown();
                }

            },"线程"+i).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 这样计算结果不到50000， 因为计算没完成就看结果了
        System.out.println("total:"+myNumber.getInt());
    }
}

class MyNumber{
    private AtomicInteger atomicInteger = new AtomicInteger();
    public void addAndPlus(){
        atomicInteger.getAndIncrement();
    }

    public Integer getInt(){
        return atomicInteger.get();
    }
}

