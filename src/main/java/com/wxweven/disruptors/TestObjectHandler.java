package com.wxweven.disruptors;

import com.lmax.disruptor.EventHandler;

public class TestObjectHandler implements EventHandler<TestObject> {

    @Override
    public void onEvent(TestObject event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(event.getValue());
    }
}