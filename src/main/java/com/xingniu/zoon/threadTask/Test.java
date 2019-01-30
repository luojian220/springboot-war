package com.xingniu.zoon.threadTask;

/**
 * @author luojian
 * @version 1.0
 * @ClassName: Test
 * @Reason: TODO ADD REASON(可选)
 * @date: 2018年11月13日 15:24
 * @company:
 * @since JDK 1.8
 */
public class Test {

    public synchronized void test() {
        System.out.println(Thread.currentThread().getName() + "开始执行 test方法");
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
