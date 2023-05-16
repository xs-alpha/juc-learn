package com.xiaosheng.juc;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.concurrent.TimeUnit;

public class BiasedLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();

        TimeUnit.SECONDS.sleep(4);
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }
    }
}
