/**
 *
 */
package com.wxweven.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wxweven
 * @File: LockTest.java
 * @date 2014年7月12日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class LockTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        new LockTest().init();

    }

    private void init() {
        final Outputer outputer = new Outputer();

        // jdk1.7 and before
        //        new Thread(new Runnable() {
        //            @Override
        //            public void run() {
        //                while (true) {
        //                    try {
        //                        Thread.sleep(10);
        //                    } catch (InterruptedException e) {
        //                        e.printStackTrace();
        //                    }
        //                    outputer.output("aaaaaaaa");
        //                }
        //
        //            }
        //        }).start();

        // jdk1.8
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                outputer.output("aaaaaaaa");
            }

        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outputer.output("bbbbbbbb");
            }
        }).start();
    }

    class Outputer {
        Lock lock = new ReentrantLock();

        public void output(String name) {
            int len = name.length();
            lock.lock();
            try {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            } finally {
                lock.unlock();
            }
        }
    }
}
