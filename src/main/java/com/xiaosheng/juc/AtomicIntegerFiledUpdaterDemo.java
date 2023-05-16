package com.xiaosheng.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

//public class AtomicIntegerFiledUpdaterDemo {
//    public static void main(String[] args) throws InterruptedException {
//
//        BankAccount bankAccount = new BankAccount();
//        CountDownLatch countDownLatch = new CountDownLatch(10);
//
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//                for (int j = 0; j < 1000; j++) {
//                    bankAccount.add();
//                }
//                countDownLatch.countDown();
//            },"i").start();
//        }
//        countDownLatch.await();
//
//        System.out.println(bankAccount.value);
//    }
//}

//class BankAccount{
//    public String name = "bbc";
//    public int value = 0;
////    public void add(){
////        value++;
////    }
//    public synchronized void add(){
//        value++;
//    }
//}
class BankAccount{
    public String name = "bbc";
    // 更新字段必须用public volatile 修饰
    public volatile int value = 0;
    AtomicIntegerFieldUpdater fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class,"value");

    public void add(){
        value++;
    }

    public void addMoney(BankAccount bankAccount){
        // 这里注意，不能直接传value
        fieldUpdater.getAndIncrement(bankAccount);
    }
}
public class AtomicIntegerFiledUpdaterDemo {
    public static void main(String[] args) throws InterruptedException {

        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
//                    bankAccount.add();
                    bankAccount.addMoney(bankAccount);
                }
                countDownLatch.countDown();
            },"i").start();
        }
        countDownLatch.await();

        System.out.println(bankAccount.value);
    }
}