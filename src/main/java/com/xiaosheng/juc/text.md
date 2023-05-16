ArrayList 线程不安全  
解决方案：

- Vector
- Collections
- CopyOnWriteArrayList

HashSet线程不安全
解决方案：

- CopyOnWriteSrraySet

HashMap 线程不安全
解决方案：

- ConcurrentHashMap

# 公平锁

公平锁：阳光普照，效率相对较低
非公平锁：线程饿死，效率高

# 死锁

两个或两个以上线程在执行过程中，因为争夺资源而造成的相互等待的现象，如果没有外力干涉，他们无法再继续执行。
产生原因：

- 系统资源不足
- 进程运行推进顺序不合适
- 资源分配不当

# 读锁发生死锁 共享锁

- 线程1修改的时候，需要等待线程2读取之后
- 线程2修改的时候，需要等待线程1读取之后

# 写锁 独占锁

- 线程1在操作第一条记录的时候，也操作第二条数据，但第二条数据在被线程2修改，只能等线程2释放
- 线程2在操作第二条记录的时候，也操作第一条数据，但第一条数据在被线程1修改，只能等线程1释放

# 独占锁

synchronized 和ReentrantLock都是独占锁，每次只能来一个操作

# 读写锁

ReentrantReadWriteLock读写锁
读读可以共享，提升性能，同时多人进行读操作

缺点：

- 造成锁饥饿，一直读，没有写，比如1个人下地铁，100个人上地铁
- 读时候不能写，只有读完成后才可以写，写操作时候可以读 （锁降级）

# 锁降级

获取写锁-》获取读锁-》释放写锁-》释放读锁
写锁可以降级为读锁，而读锁不能升级为写锁
就好比一个学渣抄学霸作业，学霸写完了学渣才能抄

# 线程池参数

int corePoolSize: 常驻线程数量
int maximumPoolSize: 最大线程数量
long keepAliveTime:  线程存活时间
TimeUnit unit:        线程存活时间
BlockingQueue<Runnable> workQueue:阻塞队列
ThreadFactory threadFactory: 线程工厂
RejectedExcutionHandler handler: 拒绝策略

# 线程池拒绝策略

AbortPolicy: 直接抛出RejectedExecutionException异常阻止系统正常运行
CallerRunsPolicy:“调用者运行“一种调节机制，该策略不会抛弃任务，也不跑出异常，而是将任务回退到调用者，从而降低新任务的流量
DiscardOldestPolicy:抛弃任务中等待最久的任务，然后把当前任务加入队列中，尝试再次提交当前任务
DiscardPolicy:该策略默默地丢弃无法处理的任务，不予任何处理，也不抛出异常，如果允许任务丢失，这是一种最好的策略

# 管程

Monitor(监视器) ,也就是平时所说的锁
执行线程就要求先成功持有管程，然后才能执行方法，最后当方法执行完成后释放管程，在方法执行期间，执行线程持有了管程，其他任何线程都无法再获取到同一个管程