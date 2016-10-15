package com.wxweven.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 获取锁，超时放弃
 * @author wxweven
 * @date 2016年8月27日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2016
 */
public class TryLockWithTime implements Runnable {

	private static ReentrantLock lock = new ReentrantLock();

	@Override
	public void run() {
		try {
			if (lock.tryLock(2, TimeUnit.SECONDS)) {
				System.out.println(Thread.currentThread().getName() + "获取到了锁");
				// 在 5 秒钟之内获取到锁后 ，让线程休眠6秒
				Thread.sleep(6000);
				System.out.println(Thread.currentThread().getName() + "执行完毕");

			} else {
				System.out.println(Thread.currentThread().getName() + "获取锁失败");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
			System.out.println(Thread.currentThread().getName() + "退出");
		}
	}

	public static void main(String[] args) {
		Thread thread1 = new Thread(new TryLockWithTime(), "线程1");
		Thread thread2 = new Thread(new TryLockWithTime(), "线程2");

		thread1.start();
		thread2.start();
	}

}
