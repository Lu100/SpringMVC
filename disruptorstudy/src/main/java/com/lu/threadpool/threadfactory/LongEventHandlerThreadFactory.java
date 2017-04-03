package com.lu.threadpool.threadfactory;

import java.util.Random;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Administrator on 2017/3/30.
 */
public class LongEventHandlerThreadFactory implements ThreadFactory {

    private static volatile LongEventHandlerThreadFactory factory = new LongEventHandlerThreadFactory();

    private long counter = 1;

    private LongEventHandlerThreadFactory() {
    }

    public static LongEventHandlerThreadFactory getInstance() {
        return factory;
    }

    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String name = String.format("消费线程【%d】", pid());
        String message = String.format("[%s]已经被创建(已经创建了%d条线程)", name, counter++);
        System.out.println(message);
        thread.setName(name);
        return thread;
    }

    private Random random = new Random();

    private int pid() {
        return random.nextInt(2 << 8);
    }
}
