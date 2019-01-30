package com.xingniu.zoon.threadTask;

/**
 * @author luojian
 * @version 1.0
 * @ClassName: ThreadOne
 * @Reason: TODO ADD REASON(可选)
 * @date: 2018年08月27日 09:45
 * @company:
 * @since JDK 1.8
 */
public class ThreadOne implements Runnable {


    //执行
    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + "开始执行");
        System.out.println(Thread.currentThread().getName() + "执行完成");
    }
}
