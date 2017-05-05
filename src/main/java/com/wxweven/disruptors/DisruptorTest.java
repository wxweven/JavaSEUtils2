package com.wxweven.disruptors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Simple RingBuffer Demo
 * Created by wxweven on 2017/4/22.
 */
public class DisruptorTest {
    private static final int RING_BUFFERSIZE = 1 << 10;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ExecutorService executors = Executors.newCachedThreadPool();
        //创建一个disruptor，指定ringbuffer的size和处理数据的factory
        Disruptor<TestObject> disruptor = new Disruptor<>(
                new TestObjectFactory(),
                RING_BUFFERSIZE,
                executors,
                ProducerType.SINGLE,
                new YieldingWaitStrategy()
        );

        //disruptor里面设置一个处理方式
        disruptor.handleEventsWith(new TestObjectHandler());

        RingBuffer<TestObject> ringBuffer = disruptor.start();
        for (long i = 0; i < 1000; i++) {
            //下一个可以用的序列号
            long seq = ringBuffer.next();
            try {
                //这个序列号的slot 放入数据
                TestObject valueEvent = ringBuffer.get(seq);
                valueEvent.setValue(i);
            } finally {
                //发布通知，并且这一步一定要放在finally中，因为调用了ringBuffer.next(),就一定要发布，否则会导致disruptor状态的错乱
                ringBuffer.publish(seq);
            }
        }
        disruptor.shutdown();
        executors.shutdown();

        System.out.println(System.currentTimeMillis() - startTime);
    }
}
