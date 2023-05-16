package com.xiaosheng.juc;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderApiDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();
        /**
         * 1 如果Cells表为空，尝试用CAS更新base字段，成功则退出;
         * 2如果Cells表为空，CAS更新base宁段失败，出现亮争，uncontended为true，调用ongAccumulate;
         * 3如果Cells表非空，但当前线程映射的槽为空，uncontended为true，调用longAccumulate:
         * 4如果Cells表非空，且前线程映射的槽非空，CAS更新Cell的值，成功则返回，否则uncontended设为false，调用longAccumulate。
         */

        longAdder.sum();
        System.out.println(longAdder.longValue());

        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> {
            return x + y;
        }, 0);
        longAccumulator.accumulate(1);
        longAccumulator.accumulate(1);
        longAccumulator.accumulate(1);

        System.out.println(longAccumulator.longValue());
    }
}
