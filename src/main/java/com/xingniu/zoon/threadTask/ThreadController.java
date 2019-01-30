package com.xingniu.zoon.threadTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author luojian
 * @version 1.0
 * @ClassName: ThreadController
 * @Reason: TODO ADD REASON(可选)
 * @date: 2018年08月27日 09:48
 * @company:
 * @since JDK 1.8
 */
public class ThreadController {



    public static void main(String[] args) {

        //固定线程数
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        /*for (int i =0 ; i < 10 ; i ++ ) {
            ThreadOne threadOne = new ThreadOne();
            executorService.submit(threadOne);
        }*/

        /*锁竞争同一个对象资源时，才会出现锁等待的情况*/
        Test test = new Test();
        for (int i =0 ; i < 10 ; i ++ ) {
            /*Test test = new Test();*/
            executorService.submit(() -> test.test());
        }
    }


    private void test() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        /*上一个任务执行结束之后，开始计算间隔时间*/
        executorService.scheduleAtFixedRate(new ThreadOne(),10,5, TimeUnit.SECONDS);
        /*上一个任务执行开始之后，开始计算间隔时间*/
        executorService.scheduleWithFixedDelay(new ThreadOne(),10,5, TimeUnit.SECONDS);
    }
}


