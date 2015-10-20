/**
 * 
 */
package com.wxweven.thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wxweven
 * @File: ReadWriteLockTest.java
 * @date 2014年7月12日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class ReadWriteLockTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final Queue3 q3 = new Queue3();
		for (int i = 0; i < 3; i++) {
			/**
			 * 启动一个读线程，循环读取q3对象
			 */
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						q3.get();
					}

				}
			}).start();

			/**
			 * 启动一个写线程，不停地写入q3对象
			 */
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						q3.put(new Random().nextInt(1000));
					}

				}
			}).start();
		}
	}

}

class Queue3 {
	private Object data = null;// 共享数据，同一时刻，只能有一个线程写该数据，但可以有多个线程读该数据

	ReadWriteLock rwl = new ReentrantReadWriteLock();// 读写锁

	/**
	 * 读方法，可以允许多个线程来读，加读锁
	 */
	public void get() {
		rwl.readLock().lock();// 读锁开始：可以允许其他线程来读，但不允许其他线程来写
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to read data!");
			Thread.sleep(100);
			System.out.println(Thread.currentThread().getName() + " has read data:" + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwl.readLock().unlock();// 无论如何，最后都要释放锁
		}
	}

	/**
	 * 写方法，排他锁，不允许其他线程来操作
	 * 
	 * @param data
	 */
	public void put(Object data) {
		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to write data!");
			Thread.sleep(100);
			this.data = data;
			System.out.println(Thread.currentThread().getName() + " has write data:" + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwl.writeLock().unlock();
		}
	}
}
