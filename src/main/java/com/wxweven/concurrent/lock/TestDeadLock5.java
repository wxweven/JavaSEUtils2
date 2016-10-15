package com.wxweven.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestDeadLock5 implements Runnable {
	private int				number;
	static ReentrantLock	lock1	= new ReentrantLock();
	static ReentrantLock	lock2	= new ReentrantLock();
	// 统计发生死锁的次数
	private static int		count;

	public TestDeadLock5(int number) {
		this.number = number;
	}

	@Override
	public void run() {
		if (number == 1) {
			// while (true) {
			if (lock1.tryLock()) {
				System.out.println(Thread.currentThread().getName() + "获得了lock1");
				try {
					TimeUnit.MILLISECONDS.sleep(1);
					try {
						if (lock2.tryLock()) {
							System.out.println(Thread.currentThread().getName() + "获得了lock2");
						}
					} finally {
						// 同时获得Lock1和lock2，没有发生死锁，任务完成，退出循环
						if (lock1.isHeldByCurrentThread() && lock2.isHeldByCurrentThread()) {
							System.out.println(Thread.currentThread().getName() + "执行完毕...");
							lock1.unlock();
							lock2.unlock();
							// break;
						} else {
							lock1.unlock();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// }
		} else {
			if (lock2.tryLock()) {
				System.out.println(Thread.currentThread().getName() + "获得了lock2");
				try {
					TimeUnit.MILLISECONDS.sleep(1);
					try {
						if (lock1.tryLock()) {
							System.out.println(Thread.currentThread().getName() + "获得了lock1");
						}
					} finally {
						// 同时获得Lock1和lock2，没有发生死锁，任务完成，退出循环
						if (lock1.isHeldByCurrentThread() && lock2.isHeldByCurrentThread()) {
							System.out.println(Thread.currentThread().getName() + "执行完毕...");
							lock1.unlock();
							lock2.unlock();
							// break;
						} else {
							lock2.unlock();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new TestDeadLock5(1), "线程1");
		Thread thread2 = new Thread(new TestDeadLock5(2), "线程2");
		thread1.start();
		thread2.start();
	}
}
