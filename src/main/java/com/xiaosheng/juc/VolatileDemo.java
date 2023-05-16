package com.xiaosheng.juc;

public class VolatileDemo {
    public static void main(String[] args) {
        /**
         * 当第一个操作为volatile读时，不论第二个操作是什么，都不能重排序。这个操作保证了volatile读之后的操作不会被重排到volatile读之前。
         * 当第二个操作为volatile写时，不论第一个操作是什么，都不能重排序。这个操作保证了volatile写之前的操作不会被重排到volatile写之后
         * 当第一个操作为volatile写时，第二个操作为volatile读时，不能重排。
         */

        /**
         * 写屏障
         * 在每个 volatile 写操作的前面插入一个 StoreStore 屏障,可以保证在volatile写之前，其前面的所有普通写操作都已经刷新到主内存中。
         * 在每个 volatile 写操作的后面插入一个 StoreLoad 屏障,作用是避免volatile写与后面可能有的volatile读/写操作重排序
         */

        /**
         * 使用volatile修饰共享变量，就可以达到上面的效果，被volatile修改的变量有以下特点:
         * 1.线程中读取的时候，每次读取都会去主内存中读取共享变量最新的值，然后将其复制到工作内存
         * 2.线程中修改了工作内存中变量的副本，修改之后会立即刷新到主内存
         */
        /**
         * 运算结果并不依赖变量的当前值，或者能够确保只有单一的线程修改变量的值
         * 变量不需要与其他的状态变量共同参与不变约束
         */

        /**
         * 使用:当婆远多于写，结合使用内部锁和volatile 变量来减少同步的开销
         * 理由:利用volatile保证读欧操作的可见性:利用synchronized保证复合操作的原子性
         */

    }
    public class Counter{
        private volatile int value;
        public int getValue(){
            return value; //利用volatile保证读取操作的可见性
        }

        public synchronized int increment(){
            return value++; //利用synchronized保证复合操作的原子性
        }
    }
    /**
     * volatile 写之前的操作，都禁止重排序到 volatile 之后
     * volatile 读之后的操作，都禁止重排序到 volatile 之前
     * volatile 写之后 volatile 读，禁止重排序
     */
}
