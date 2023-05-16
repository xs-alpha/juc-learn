package com.xiaosheng.juc;

import org.openjdk.jol.info.ClassLayout;

public class JavaObjectHeader {
    public static void main(String[] args) {
//        System.out.println(VM.current().details());

        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println("-----------------------------");

        Cust cust= new Cust();
        System.out.println(ClassLayout.parseInstance(cust).toPrintable());
        System.out.println("-----------------------------");

        Customer customer = new Customer();
        System.out.println(ClassLayout.parseInstance(customer).toPrintable());
        System.out.println("-----------------------------");
    }
}

class Cust{
}

class Customer{
    int id;
    boolean flag= false;
}
