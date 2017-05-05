package com.wxweven.LmaxDisruptorDemo;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.RingBuffer;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LongEventMain {
    public static void main(String[] args) throws Exception {
        Executor executor = Executors.newCachedThreadPool();

        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, executor);

        disruptor.handleEventsWith(
                (event, sequence, endOfBatch) -> System.out.println("Event: " + event)
        );

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);

            // using Lambda instead of EventTranslatorOneArg
            ringBuffer.publishEvent(
                    (event, sequence, buffer) -> event.set(buffer.getLong(0)),
                    bb
            );
            Thread.sleep(1000);
        }
    }
}