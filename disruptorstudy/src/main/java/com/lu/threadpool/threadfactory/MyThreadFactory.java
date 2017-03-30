package com.lu.threadpool.threadfactory;

import java.util.Random;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Administrator on 2017/3/30.
 */
public class MyThreadFactory implements ThreadFactory {

    private static volatile MyThreadFactory factory = new MyThreadFactory();

    private MyThreadFactory() {
    }

    public static MyThreadFactory getInstance() {
        return factory;
    }

    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String name = String.format("线程【%d】", pid());
        String message = String.format("[%s]已经被创建", name);
        System.out.println(message);
        thread.setName(name);
        return thread;
    }

    private Random random = new Random();

    private int pid() {
        return random.nextInt(2 << 8);
    }
}
