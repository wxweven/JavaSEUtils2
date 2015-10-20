/**
 * 
 */
package com.wxweven.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wxweven
 * @date 2014年7月13日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class ThreeConditionCommunication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ThreeConditionCommunication().init();
	}

	public void init() {
		final int loop = 3;// 循环的次数
		final ThreadService threadService = new ThreadService();

		/**
		 * 启动第2个线程（子线程1）
		 */
		new Thread(new Runnable() {
 
			@Override
			public void run() {
				for (int i = 0; i < loop; i++)
					threadService.subThread1(i);

			}
		}).start();

		/**
		 * 启动第3个线程（子线程2）
		 */
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < loop; i++)
					threadService.subThread2(i);

			}
		}).start();

		// 启动主线程
		for (int i = 0; i < loop; i++)
			threadService.mainThread(i);
	}

	class ThreadService {
		
		Lock lock = new ReentrantLock();
		Condition con1 = lock.newCondition();// 第1个线程上的condition
		Condition con2 = lock.newCondition();// 第2个线程上的condition
		Condition con3 = lock.newCondition();// 第3个线程上的condition

		/**
		 * 标志位，标志轮到哪个线程执行 1 : 第1个线程执行 2 : 第2个线程执行 3 : 第3个线程执行
		 */
		int shouldBe = 1;

		/**
		 * 定义第1个线程（主线程）上的执行内容
		 */
		public void mainThread(int loop) {
			// 主线程开始执行，加锁
			lock.lock();

			// 下面所有的代码都用try catch finally 包围，以保证最后可以释放lock
			try {
				/**
				 * 轮循检查，是否轮到自己执行 shouldBe == 1 : 轮到自己执行 否则，就阻塞线程
				 */
				while (shouldBe != 1) {
					try {
						con1.await();// 线程1在con1上阻塞
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// 轮到自己执行
				for (int i = 0; i < 10; i++) {
					System.out.println("Thread : "+Thread.currentThread().getName() + ", loop : " + (loop + 1) + ", sequence : "
							+ (i + 1));
				}
				System.out.println();//打印一个空行

				// 第1个线程执行完毕后，轮到第2个线程执行
				shouldBe = 2;// 将标志位置为2
				con2.signal();// 唤醒con2上阻塞的线程
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();// 最后，无论如何都要释放锁
			}
		}

		/**
		 * 定义第2个线程（子线程1）上的执行内容
		 */
		public void subThread1(int loop) {
			// 主线程开始执行，加锁
			lock.lock();

			// 下面所有的代码都用try catch finally 包围，以保证最后可以释放lock
			try {
				/**
				 * 轮循检查，是否轮到自己执行 shouldBe == 2 : 轮到自己执行 否则，就阻塞线程
				 */
				while (shouldBe != 2) {
					try {
						con2.await();// 线程2在con2上阻塞
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// 轮到自己执行
				for (int i = 0; i < 5; i++) {
					System.out.println("Thread : "+Thread.currentThread().getName() + ", loop : " + (loop + 1) + ", sequence : "
							+ (i + 1));
				}
				System.out.println();//打印一个空行

				// 第2个线程执行完毕后，轮到第3个线程执行
				shouldBe = 3;// 将标志位置为3
				con3.signal();// 唤醒con2上阻塞的线程
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();// 最后，无论如何都要释放锁
			}
		}

		/**
		 * 定义第3个线程（子线程2）上的执行内容
		 */
		public void subThread2(int loop) {
			// 主线程开始执行，加锁
			lock.lock();

			// 下面所有的代码都用try catch finally 包围，以保证最后可以释放lock
			try {
				/**
				 * 轮循检查，是否轮到自己执行 shouldBe == 3 : 轮到自己执行 否则，就阻塞线程
				 */
				while (shouldBe != 3) {
					try {
						con3.await();// 线程3在con3上阻塞
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// 轮到自己执行
				for (int i = 0; i < 5; i++) {
					System.out.println("Thread : "+Thread.currentThread().getName() + ", loop : " + (loop + 1) + ", sequence : "
							+ (i + 1));
				}
				System.out.println();//打印一个空行

				// 第3个线程执行完毕后，轮到第1个线程执行
				shouldBe = 1;// 将标志位置为1
				con1.signal();// 唤醒con1上阻塞的线程
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();// 最后，无论如何都要释放锁
			}
		}
	}

}
