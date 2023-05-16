package com.xiaosheng.juc.lock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author xiaosheng
 * @date Created at 2023/4/16
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
//        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        for (int i = 0; i < 30000; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 6));
                System.out.println(list);
            }, i + "线程").start();
        }
    }
}
