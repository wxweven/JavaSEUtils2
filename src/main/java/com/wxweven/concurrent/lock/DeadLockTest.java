package com.wxweven.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁测试
 *
 * @author wxweven
 * @date 2016年8月27日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2016
 */
public class DeadLockTest implements Runnable {
	private static ReentrantLock	lock1	= new ReentrantLock();
	private static ReentrantLock	lock2	= new ReentrantLock();
	private int						number;

	public DeadLockTest(int number) {
		this.setNumber(number);
	}

	@Override
	public void run() {
		try {
			if (number == 1) {
				// lock1.lock();// 这种锁不可以被中断
				lock1.lockInterruptibly();// 加上可被中断的锁，线程被中断时，可以释放
				System.out.println(Thread.currentThread().getName() + " 得到了lock1的锁...");

				Thread.sleep(500);

				// lock2.lock();
				lock2.lockInterruptibly();// 加上可被中断的锁

				System.out.println(Thread.currentThread().getName() + " 得到了lock2的锁...");

			} else {
				// lock2.lock();
				lock2.lockInterruptibly();
				System.out.println(Thread.currentThread().getName() + " 得到了lock2的锁...");

				Thread.sleep(500);
				// lock1.lock();
				lock1.lockInterruptibly();
				System.out.println(Thread.currentThread().getName() + " 得到了lock1的锁...");

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (lock1.isHeldByCurrentThread()) {
				lock1.unlock();
			}

			if (lock2.isHeldByCurrentThread()) {
				lock2.unlock();
			}

			System.out.println(Thread.currentThread().getName() + " 线程退出！");
			// lock1.unlock();
			// lock2.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new DeadLockTest(1), "线程1");
		Thread thread2 = new Thread(new DeadLockTest(2), "线程2");
		thread1.start();
		thread2.start();

		Thread.sleep(1000);

		//		thread2.interrupt();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
