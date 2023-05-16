package com.xiaosheng.juc;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo {
    public static void main2(String[] args) throws InterruptedException {

        SaleTicket ticket = new SaleTicket();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                int size = new Random().nextInt(10);
                for (int j = 0; j < size; j++) {
                    ticket.saleHose();
                    ticket.saleHoseVolume();
                }
                System.out.println(Thread.currentThread().getName()+"号 迈出"+ticket.threadLocal.get());
            },""+i).start();
        }
        TimeUnit.SECONDS.sleep(1);

        System.out.println(Thread.currentThread().getName()+"迈出"+ ticket.count);
    }

    public static void main(String[] args) throws InterruptedException {

        SaleTicket ticket = new SaleTicket();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                int size = new Random().nextInt(10);
                try {
                    for (int j = 0; j < size; j++) {
                        ticket.saleHose();
                        ticket.saleHoseVolume();
                    }
                    System.out.println(Thread.currentThread().getName()+"号 迈出"+ticket.threadLocal.get());
                }finally {
                    // 一定要remove,线程池的场景下不remove会导致内存泄露
                    ticket.threadLocal.remove();
                }
            },""+i).start();
        }
        TimeUnit.SECONDS.sleep(1);

        System.out.println(Thread.currentThread().getName()+"迈出"+ ticket.count);
    }
}

class SaleTicket{
    int count = 0;
    public synchronized void saleHose(){
        count++;
    }

    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->0);
    public void saleHoseVolume(){
        threadLocal.set(1+ threadLocal.get());
    }
}
