package com.wxweven.disruptors;

import com.lmax.disruptor.EventFactory;

/**
 * Created with IntelliJ IDEA.
 * Project: test-jar
 * Author: Kevin
 * Date: 16/5/23
 * Time: 下午4:02
 */
public class TestObjectFactory implements EventFactory<TestObject> {

    /**
     * 仅仅是返回slot中，需要放置的Object，方便new disruptor的时候 fill
     * /@see Sequencer#fill()
     *
     * @return
     */
    @Override
    public TestObject newInstance() {
        return new TestObject();
    }
}