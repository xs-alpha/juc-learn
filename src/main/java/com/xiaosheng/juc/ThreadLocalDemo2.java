package com.xiaosheng.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo2 {
    public static void main(String[] args) {
        MyData myData = new MyData();
        ExecutorService pool = Executors.newFixedThreadPool(3);

        try {
            for (int i = 0; i < 10; i++) {
                pool.submit(()->{
                    try {
                        Integer integer = myData.threadLocal.get();
                        myData.add();
                        Integer after = myData.threadLocal.get();
                        System.out.println("before:"+integer+"  after:"+after);
                    }finally {
                        myData.threadLocal.remove();
                    }
                },"i");
            }
        }catch (Exception e){

        }finally {
            pool.shutdown();

        }
    }
}

class MyData{
    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->0);
    public void add(){
        threadLocal.set(1+threadLocal.get());
    }
}