package com.lu.threadpool.producer;

import com.lmax.disruptor.RingBuffer;
import com.lu.threadpool.event.LongEvent;

/**
 * 事件的生产者
 */
public class LongEventProducer implements Runnable {

    private RingBuffer<LongEvent> buffer;

    public LongEventProducer(RingBuffer<LongEvent> buffer) {
        this.buffer = buffer;
    }

    private int currentValue = 0;

    private static final long MAXIMUM = 1 << 4;

    //持续推送数据
    public void run() {
        String name = Thread.currentThread().getName();
        while (currentValue < MAXIMUM) {
            long sequence = buffer.next();
            try {
                LongEvent event = buffer.get(sequence);
                event.setValue(++currentValue);
                event.setName(name);
                Thread.yield();
            } finally {
                //将事件推送到缓冲区
                buffer.publish(sequence);
            }
        }
        System.err.println(name + "执行完毕!");
    }
}
