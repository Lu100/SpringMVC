package com.lu.threadpool.factory;

import com.lmax.disruptor.EventFactory;
import com.lu.threadpool.event.LongEvent;

/**
 * 事件工厂
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
