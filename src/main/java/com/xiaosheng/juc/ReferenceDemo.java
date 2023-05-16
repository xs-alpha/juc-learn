package com.xiaosheng.juc;

public class ReferenceDemo {
    public static void main(String[] args) {
        // 强引用 软引用 弱引用 虚引用
        /**
         * 1 虚引用必须和引用队列 (ReferenceQueue)联合使用虚引用需要iava.ang.ref.PhantomReference类来实现,顾名思义，就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收，它不能单独使用也不能通过它访问对象，虚引用必须和引用队列(ReferenceQueue)联合使用。
         * 2 PhantomReference的get方法总是返回null
         * 虚引用的主要作用是跟踪对象被垃圾回收的状态。仅仅是提供了一种确保对象被 finalize以后，做某些事情的通知机制。
         * PhantomReference的get方法总是返回null，因此无法访问对应的引用对象。
         * 3 处理监控通知使用
         * 换句话说，设置虑引用关联对象的唯一目的，就是在这个对象被收集器回收的时候收到一个系统通知或者后续添加进一步的处理，用来实现比finalize机制更灵活的回收操作
         */

        /**
         * 为什么源代码用弱引用?
         * 当function01方法执行完毕后，栈顺销毁强引用 tl 也就没有了。但此时线程的ThreadLocalMap里某个entry的key引用还指向这个对象
         * 若这个key引用是强引用，就会导致key指向的ThreadLocal对象及v指向的对象不能被gc回收，造成内存泄漏:
         * 若这个key引用是弱引用就大概率会减少内存泄漏的问题(还有一个key为nul的雷，第2个坑后面讲)。
         * 使用弱引用，就可以使ThreadLocal对象在方法执行完毕后顺利被回收且Entry的key引用指向为null。
         */

        /**
         * ThreadLocalMap使用ThreadLocal的弱引用作为key,
         * 如果一个ThreadLocal没有外部强引用引用他，那么系统gc的时候，这个ThreadLocal势必会被回收，这样一来，
         * ThreadLocalMap中就会出现kev为nul的Entv，就没有办法访问这些key为nul的Entrv的value，如果当前线程再迟迟不结束的话(比如正好用在线程池)，
         * 这些key为nul的Entry的value就会一直存在一条强引用链
         *
         * 虽然弱引用，保证了key推向的ThreadLoca对象能被及时回收，
         * 但是v指向的value对象是需要ThreadLocalMap调用get、set时发现key为nul时才会去回收整个entry、value，
         * 因此弱引用不能100%保证内存不泄露。我们要在不使用某个ThreadLocal对象后，手动调用remoev方法来删除它，
         * 其是在线程池中，不仅仅是内存泄露的问题，因为线程池中的线程是重复使用的，
         * 意味着这个线程的ThreadLocalMap对象也是重复使用的，如果我们不手动调用remove方法，那么后面的线程就有可能获取到上个线程遗留下来的value值，造成bug。
         */
    }
}
