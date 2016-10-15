package com.wxweven.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * CyclicBarrier 用于一组或几组线程，比如一组线程需要在一个时间点上达成一致，例如同时开始一个工作。
 * 另外，CyclicBarrier的循环特性和构造函数所接受的 Runnable 参数也是 CountDownLatch 所不具备的
 *
 * 假若有若干个线程都要进行写数据操作，并且只有所有线程都完成写数据操作之后，这些线程才能继续做后面的事情
 *
 * @author wxweven
 * @date 2016年8月28日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2016
 */
public class CylicBarrierTest {

    private static int    WORKER_SIZE   = 5;
    private int[]         numbers       = new int[WORKER_SIZE];
    private int           sum           = 0;
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(WORKER_SIZE, new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < WORKER_SIZE; i++) {
                sum += numbers[i];
            }

            System.out.println(Thread.currentThread().getName() + "最后汇总的结果是：" + sum);
        }
    });

    public static void main(String[] args) {

        CylicBarrierTest test = new CylicBarrierTest();

        System.out.println("正在执行准备工作...");

        for (int i = 0; i < WORKER_SIZE; i++) {
            new Thread(test.new Worker(i), "线程" + i).start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CylicBarrier重用...");
        for (int i = 0; i < WORKER_SIZE; i++) {
            new Thread(test.new Worker(i), "线程" + i).start();
        }
    }

    class Worker implements Runnable {
        private int index;

        public Worker(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始计算...");

            try {
                Thread.sleep(2000);
                numbers[index] = index;
                System.out.println(Thread.currentThread().getName() + "完成计算...");
                cyclicBarrier.await(2, TimeUnit.SECONDS);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

            System.out.println("所有线程都已完成计算，开始汇总...");

        }
    }
}
