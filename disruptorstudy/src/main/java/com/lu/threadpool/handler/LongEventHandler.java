package com.lu.threadpool.handler;

import com.lmax.disruptor.EventHandler;
import com.lu.threadpool.event.LongEvent;

/**
 * 事件处理器
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        String message = String.format("Thread[%s]消费了: sequence=[%d] event.getValue()=[%d] 由[%s]创建的Event", Thread.currentThread().getName(), sequence, event.getValue(), event.getName());
        System.out.println(message);
        Thread.yield();
    }
}
