package com.xiaosheng.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicReferenceStampsDemo {

    private  static Book b = new Book(1, "boo");
    private static AtomicStampedReference<Book> atomic = new AtomicStampedReference<>(b, 1);

    private  static Book gb = new Book(2, "gb");

    public static void main(String[] args) {
        new Thread(()->{
            atomic.compareAndSet(b, gb, 1, 2);
            System.out.println(Thread.currentThread().getName()+":stamps"+atomic.getStamp());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomic.compareAndSet(gb,b, atomic.getStamp(), atomic.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+":stamps2:"+atomic.getStamp());
        },"a").start();

        new Thread(()->{
            atomic.compareAndSet(b, gb, atomic.getStamp(), atomic.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+":stamps3:"+atomic.getStamp());
        },"b").start();
    }
}

class Book{
    private Integer id;
    private String name;

    Book(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
