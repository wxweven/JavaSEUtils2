package com.wxweven.concurrent;

import java.util.concurrent.Semaphore;

/**
 * Semaphore可以控同时访问的线程个数，通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
 *
 * 假若一个工厂有5台机器，但是有8个工人，一台机器同时只能被一个工人使用，只有使用完了，其他工人才能继续使用。
 *
 * @author wxweven
 * @date 2016年8月28日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2016
 */
public class SemaphoreTest {
    private static int workerSize  = 8;                         // 工人数量
    private static int machineSize = 5;                         // 机器数量

    private Semaphore  semaphore   = new Semaphore(machineSize);

    public static void main(String[] args) {
        SemaphoreTest test = new SemaphoreTest();

        for (int i = 0; i < workerSize; i++) {
            new Thread(test.new Worker(i + 1)).start();
        }
    }

    class Worker implements Runnable {
        int num;

        public Worker(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人" + num + "开始工作，占用一台机器...");
                Thread.sleep(500);
                System.out.println("工人" + num + "结束工作，释放一台机器...");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
