/**
 * 
 */
package com.wxweven.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

/**
 * @author wxweven
 * @date 2014年7月13日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class SemaphoreEasyTest {

	public static void main(String[] args) {
		// 线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		// 只能5个线程同时访问
		final Semaphore semp = new Semaphore(5);
		// 模拟20个客户端访问
		for (int index = 0; index < 20; index++) {
			final int NO = index;
			Runnable run = new Runnable() {
				public void run() {
					try {
						// 获取许可
						semp.acquire();
						System.out.println("Accessing sequence: " + NO);
						Thread.sleep((long) (Math.random() * 10000));
						// 访问完后，释放
						semp.release();
						// availablePermits()指的是当前信号灯库中有多少个可以被使用
						System.out.println("-------释放后，可用信号量----------" + semp.availablePermits());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			exec.execute(run);
		}
		// 退出线程池
		exec.shutdown();
	}
}
