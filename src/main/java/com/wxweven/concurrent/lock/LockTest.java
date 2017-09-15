package com.wxweven.concurrent.lock;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wxweven
 * @date 2017/8/3
 */
public class LockTest {
    private int number;

    private Lock lock = new ReentrantLock(true);

    public void increment() {
        lock.lock();
        number++;
        lock.unlock();
    }

    public int getNumber() {
        return number;
    }

    @Test
    public void test_concurrentWrite() throws InterruptedException {
        LockTest lockTest = new LockTest();
        Thread thread1 = new WriteThread(lockTest, "thread1");
        Thread thread2 = new WriteThread(lockTest, "thread2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(lockTest.getNumber());
    }

    private static class WriteThread extends Thread {
        private LockTest lockTest;

        public WriteThread(LockTest lockTest, String name) {
            super(name);
            this.lockTest = lockTest;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1; i++) {
                lockTest.increment();
            }
        }
    }
}
