package com.lu.threadpool.threadfactory;

import java.util.Random;
import java.util.concurrent.ThreadFactory;

/**
 * 事件工厂
 */
public class LongEventProducerThreadFactory implements ThreadFactory {
    private static volatile LongEventProducerThreadFactory factory = new LongEventProducerThreadFactory();

    private long counter = 1;

    private LongEventProducerThreadFactory() {
    }

    public static LongEventProducerThreadFactory getInstance() {
        return factory;
    }

    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String name = String.format("生产线程【%d】", pid());
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
