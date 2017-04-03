package com.lu.threadpool;

import com.lu.threadpool.thread.LongEventThreadTask;
import com.lu.threadpool.threadfactory.LongEventHandlerThreadFactory;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池测试
 */
public class ThreadPoolTest {

    @Test
    public void testThreadPool() {
        //缓存型线程池
        ExecutorService executorService = Executors.newCachedThreadPool(LongEventHandlerThreadFactory.getInstance());
        //固定长度线程池
//        ExecutorService executorService = Executors.newFixedThreadPool(32, MyThreadFactory.getInstance());
        //单进程线程池
//        ExecutorService executorService = Executors.newSingleThreadExecutor(MyThreadFactory.getInstance());
        long start = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            executorService.execute(new LongEventThreadTask(UUID.randomUUID().toString().replaceAll("-", "").substring(16)));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            System.err.println("子线程还在执行中!");
            LongEventThreadTask.Sleep(1000);
        }
        System.out.println("执行完成!");
        long end = System.currentTimeMillis();
        System.err.println(String.format("一共执行了%d毫秒", end - start));
    }

}
