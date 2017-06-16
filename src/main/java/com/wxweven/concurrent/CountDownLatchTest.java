package com.wxweven.concurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * 下面的这个例子可以理解为 F1 赛车的维修过程， 只有 startSignal命令下达之后，维修工才开始干活，
 * 只有等所有工人（doneSignal）完成工作之后，赛车才能继续
 * <p>
 * <p>
 * CountDownLatch 适用于一组线程和另一个主线程之间的工作协作。
 * 一个主线程等待一组工作线程的任务完毕才继续它的执行是使用CountDownLatch 的主要场景
 *
 * @author wxweven
 * @version 1.0
 * @date 2016年8月28日
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2016
 */
public class CountDownLatchTest {

    private static final int WORKER_SIZE = 5; // 定义维修线程的数量
    private static CountDownLatch startSignal = new CountDownLatch(1); //发号施令的计数器
    private static CountDownLatch fixSignal = new CountDownLatch(WORKER_SIZE); // 维修线程计数器

    @Test
    public void test() throws InterruptedException {
        System.out.println("正在执行准备工作...");

        IntStream.range(0, WORKER_SIZE)
                 .forEach(id -> new Thread(new Worker()).start());

        System.out.println("开始修车吧...");

        startSignal.countDown();
        fixSignal.await();

        System.out.println("修车完成了，开始继续上路吧！！！");

    }

    private class Worker implements Runnable {

        @Override
        public void run() {
            try {
                startSignal.await(); // 阻塞在 startSignal上，即等待主线程发号开始维修的施令
                beginFix(); // 执行真正的修车方法
                fixSignal.countDown(); // 修车完成之后，将Signal减1，用于通知阻塞在fixSignal上的线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void beginFix() throws InterruptedException {
            System.out.println("工作线程 " + Thread.currentThread().getName() + " 在工作...");
            Thread.sleep(500);
        }
    }
}
