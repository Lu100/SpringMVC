package com.lu.threadpool;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lu.threadpool.event.LongEvent;
import com.lu.threadpool.factory.LongEventFactory;
import com.lu.threadpool.handler.LongEventHandler;
import com.lu.threadpool.producer.LongEventProducer;
import com.lu.threadpool.thread.LongEventThreadTask;
import com.lu.threadpool.threadfactory.MyThreadFactory;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/3/30.
 */
public class Application {
    //八线程创建生产事件
    private static final int THREAD_SIZE = 8;

    public static void main(String[] args) {
        EventFactory<LongEvent> longEventEventFactory = new LongEventFactory();
        //双线程消费事件
        ExecutorService executor1 = Executors.newFixedThreadPool(2,MyThreadFactory.getInstance());
        //八线程生产事件
        ExecutorService executor2 = Executors.newFixedThreadPool(THREAD_SIZE, MyThreadFactory.getInstance());
        int ringBufferSize = 1024*1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(longEventEventFactory, ringBufferSize, executor1, ProducerType.MULTI, new YieldingWaitStrategy());
        EventHandler<LongEvent> eventEventHandler1 = new LongEventHandler();
        EventHandler<LongEvent> eventEventHandler2 = new LongEventHandler();
        disruptor.handleEventsWith(eventEventHandler1,eventEventHandler2);
        disruptor.start();

        for (int i = 0; i < THREAD_SIZE; i++) {
            executor2.execute(new LongEventProducer(disruptor.getRingBuffer()));
        }
        executor2.shutdown();
        while (!executor2.isTerminated()) {
            LongEventThreadTask.Sleep(5000);
        }
        executor1.shutdown();
        disruptor.shutdown();

    }

    private static final Random RANDOM = new Random();

    private static long random() {
        return RANDOM.nextLong();
    }
}
