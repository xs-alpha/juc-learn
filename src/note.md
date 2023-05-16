https://tangzhi.blog.csdn.net/article/details/109210095
https://www.yuque.com/liuyanntes/vx9leh/su3lu9

count.addAndGet(1);锁的重试次数
说明:如果是 count++操作，使用如下类实现: AtomicInteger count = new AtomicInteger0;
如果是JDK8，推荐使用 LongAdder 对象，比 AtomicLong 性能更好( 减少乐观锁重试次数）。

![](https://image.devilwst.top/imgs/2023/05/3ed30f257548ce19.png)
![](https://image.devilwst.top/imgs/2023/05/32bd08cf64c034bd.png)

偏向锁: MarkWord存储的是偏向的线程ID
轻量锁: MarkWord存储的是指向线程栈中Lock Record的指针;
重量锁:MarkWord存储的是指向堆中的monitor对象的指针

偏向锁的撤销()
偏向锁使用一种等到竞争出现才释放锁的机制，只有当其他线程竞争锁时，持有偏向锁的原来线程才会被撤销。
撤销需要等待全局安全点(该时间点上没有字节码正在执行)，同时检查持有偏向锁的线程是否还在执行:
1.第一个线程正在执行synchronized方法(处于同步块)，它还没有执行完，其它线程来抢夺，该偏向锁会被取消掉并出现锁升级。
此时轻量级锁由原持有偏向锁的线程持有，继续执行其同步代码，而正在竞争的线程会进入自旋等待获得该轻量级锁。
2.第一个线程执行完成synchronized方法(退出同步块)，则将对象头设置成无锁状态并撤销偏向锁，重新偏向。

当一个对象已经计算过identity hashcode，它就无法进入偏向锁状态，跳过偏向锁，直接升级轻量级锁
偏向锁过程中遇到一致性哈希计算请求立马撤销偏向模式，膨胀为重量级锁



git config --global http.https://github.com.proxy http://127.0.0.1:7890
