package com.lu.threadpool;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lu.threadpool.event.LongEvent;
import com.lu.threadpool.factory.LongEventFactory;
import com.lu.threadpool.handler.LongEventHandler;
import com.lu.threadpool.producer.LongEventProducer;
import com.lu.threadpool.thread.LongEventThreadTask;
import com.lu.threadpool.threadfactory.LongEventHandlerThreadFactory;
import com.lu.threadpool.threadfactory.LongEventProducerThreadFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/3/30.
 */
public class Application {
    //八线程创建生产事件
    private static final int THREAD_SIZE = 8;

    private static final int RING_BUFFER_SIZE = 2;

    public static void main(String[] args) {
        EventFactory<LongEvent> longEventEventFactory = new LongEventFactory();
        //八线程生产事件
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_SIZE, LongEventProducerThreadFactory.getInstance());

        Disruptor<LongEvent> disruptor = new Disruptor<>(longEventEventFactory, RING_BUFFER_SIZE, LongEventHandlerThreadFactory.getInstance(), ProducerType.MULTI, new SleepingWaitStrategy());

        LongEventHandler eventHandler = new LongEventHandler();

        disruptor.handleEventsWith(eventHandler);

        disruptor.start();

        for (int i = 0; i < THREAD_SIZE; i++) {
            executorService.execute(new LongEventProducer(disruptor.getRingBuffer()));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            LongEventThreadTask.Sleep(5000);
        }
        disruptor.shutdown();
        List<Long> longs = eventHandler.getLongs();

        for (int i = 0; i < longs.size() - 2; i++) {
            if (longs.get(i + 1) - longs.get(i) != 1) {
                String message = String.format("第[%d]个元素值为[%d]，下一个元素[%d]值为[%d]，出现了顺序错误", i, longs.get(i), i + 1, longs.get(i + 1));
                System.out.println(message);
            }
        }
    }

    private static final Random RANDOM = new Random();

    private static long random() {
        return RANDOM.nextLong();
    }
}
