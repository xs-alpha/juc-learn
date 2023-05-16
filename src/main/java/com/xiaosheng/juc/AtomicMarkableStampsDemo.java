package com.xiaosheng.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicMarkableStampsDemo {

    private  static Book2 b = new Book2(1, "boo");
    private static AtomicMarkableReference<Book2> atomic = new AtomicMarkableReference<>(b, false);

    private  static Book2 gb = new Book2(2, "gb");

    public static void main(String[] args) {
        boolean mark= atomic.isMarked();
        new Thread(()->{
            System.out.println("默认："+atomic.isMarked());
            atomic.compareAndSet(b, gb, mark, !mark);
            System.out.println(Thread.currentThread().getName()+":stamps"+atomic.isMarked());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":stamps2:"+atomic.isMarked());
            atomic.compareAndSet(gb,b,mark, !mark);
        },"a").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+":stamps3:"+atomic.isMarked());
            atomic.compareAndSet(b, gb, mark, !mark);
        },"b").start();
    }
}

class Book2{
    private Integer id;
    private String name;

    Book2(Integer id, String name) {
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
