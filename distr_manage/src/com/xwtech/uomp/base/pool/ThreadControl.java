package com.xwtech.uomp.base.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * 线程管理类
 *
 * @author zhulx
 */
public class ThreadControl {

    private static final Logger logger = Logger.getLogger("threadPoolControl");

    // 线程池维护线程的最少数量
    public static int corePoolSize = 5;
    // 线程池维护线程的最大数量
    public static int maximumPoolSize = 20;
    // 线程池维护线程所允许的空闲时间，单位MS，超时将会终止该线程
    public static int keepAliveTime = 1000;
    // 线程池队列大小
    public static int queueSize = 10;

    public static ThreadPoolExecutor newRetractedThreadPool() {
        return new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize),
                new RejectedExecutionHandler() {
                    public void rejectedExecution(Runnable r,
                                                  ThreadPoolExecutor executor) {
                        logger.info("线程池状态已满,"
                                + "线程池中计划被执行的任务总数：" + executor.getTaskCount() + ","
                                + "执行完毕的任务数：" + executor.getCompletedTaskCount() + ","
                                + "线程池中同时存在最大线程数：" + executor.getLargestPoolSize() + ","
                                + "当前正在执行的任务数：" + executor.getActiveCount() + ","
                                + "线程池中当前线程数：" + executor.getPoolSize()
                        );
                    }

                }
        );

    }
}
