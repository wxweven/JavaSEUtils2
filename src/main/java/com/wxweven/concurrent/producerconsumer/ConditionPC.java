package com.wxweven.concurrent.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用 condition 来实现生产者消费者
 *
 * @author wxweven
 * @date 2016年8月27日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2016
 */
public class ConditionPC {
	private boolean		flag				= false;
	private Lock		lock				= new ReentrantLock();

	private Condition	producerCondition	= lock.newCondition();
	private Condition	consumerCondition	= lock.newCondition();

	public static void main(String[] args) {
		ConditionPC cpc = new ConditionPC();
		for (int i = 1; i <= 5; i++) {
			new Thread(cpc.new Consumer(), "消费者" + i).start();
		}

		for (int i = 1; i <= 5; i++) {
			new Thread(cpc.new Producer(), "生产者" + i).start();
		}
	}

	/**
	 * 判断是否还有产品
	 *
	 * @return
	 */
	public boolean hasProduct() {
		return flag;
	}

	/**
	 * 消费者线程
	 */
	class Consumer implements Runnable {

		@Override
		public void run() {
			// 消费者不停地消费
			while (true) {
				lock.lock();
				try {
					while (!hasProduct()) {
						// 没有产品，消费者阻塞
						consumerCondition.await();
						System.out.println(Thread.currentThread().getName() + "被唤醒了");
					}

					// 唤醒后，消费者继续消费
					// 模拟消费者行为
					Thread.sleep(300);
					flag = false;
					System.out.println(Thread.currentThread().getName() + ": 消费了一个产品");

					producerCondition.signal();// 唤醒生产者
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}

		}
	}

	/**
	 * 生产者线程
	 */
	class Producer implements Runnable {

		@Override
		public void run() {
			// 生产者不停地生产
			while (true) {
				lock.lock();
				try {
					while (hasProduct()) {
						// 有产品，生产者阻塞
						producerCondition.await();
						System.out.println(Thread.currentThread().getName() + "被唤醒了");
					}

					// 被唤醒后，生产者继续生产
					// 模拟生产者行为
					Thread.sleep(500);
					flag = true; // 标记有产品
					System.out.println(Thread.currentThread().getName() + ": 生产了一个产品");

					consumerCondition.signal();// 唤醒消费者
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}
	}

}
