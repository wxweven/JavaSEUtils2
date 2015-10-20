/**
 * 
 */
package com.wxweven.thread;

/**
 * @author wxweven
 * @File: MultiThreadShare.java
 * @date 2014年7月11日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class MultiThreadShare {
	public static void main(String[] args) {
		final ShareData shareData = new ShareData();
		System.out.println("before:" + shareData.getCount());
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++)
					shareData.increment();

			}

		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 5; i++)
					shareData.decrement();

			}

		}).start();
	}

}

class ShareData {
	private int count = 100;

	public synchronized void increment() {
		count++;
		System.out.println("increment:" + getCount());
	}

	public synchronized void decrement() {
		count--;
		System.out.println("decrement:" + getCount());
	}  

	public int getCount() {
		return count;
	}

}
