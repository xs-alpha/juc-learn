package com.xiaosheng.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author xiaosheng
 * @date Created at 2023/4/16
 */
class MyRunable1 implements Runnable {

    @Override
    public void run() {

    }
}

class MyThread1 implements Callable {

    @Override
    public Integer call() throws Exception {
        return 200;
    }
}

public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(new MyRunable1(), "aa").start();
//        new Thread(new MyThread1(),"bb").start(); //error
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread1());

        FutureTask<Integer> integerFutureTask = new FutureTask<>(() -> {
            System.out.println("integerfuturetask -----");
            return 1024;
        });

//        while (!futureTask.isDone()){
//            System.out.println("waiting");
//        }
        new Thread(futureTask, "futuretask").start();
        new Thread(integerFutureTask, "integerFutureTask").start();
        System.out.println(futureTask.get());
        System.out.println(integerFutureTask.get());
    }
}
