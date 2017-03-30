package com.lu.threadpool.handler;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.lmax.disruptor.EventHandler;
import com.lu.threadpool.event.LongEvent;

import java.util.List;

/**
 * 事件处理器
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    private static final List<Long> longs = Lists.newArrayList();
    private static final Multimap<String, Long> STRING_LONG_MULTIMAP = LinkedListMultimap.create();

    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
//        String message = String.format("Thread[%s]消费了: sequence=[%d] event.getValue()=[%d] 由[%s]创建的Event", Thread.currentThread().getName(), sequence, event.getValue(), event.getName());
//        System.out.println(message);
//        Thread.yield();
        STRING_LONG_MULTIMAP.put(event.getName(), event.getValue());
        longs.add(sequence);
    }

    public List<Long> getLongs() {
        return longs;
    }

    public static Multimap<String, Long> getStringLongMultimap() {
        return STRING_LONG_MULTIMAP;
    }
}
