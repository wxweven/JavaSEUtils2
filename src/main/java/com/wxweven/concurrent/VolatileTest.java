package com.wxweven.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangxianwen
 * @version 1.0
 * @date 2016年8月25日 ©Copyright 2016 Baidu
 */
public class VolatileTest {

    private volatile int inc  = 0;
    private Lock         lock = new ReentrantLock();

    public void increase() {
        lock.lock();
        try {
            inc++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        test.increase();
                    }
                };
            }.start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println(test.inc);
    }
}
