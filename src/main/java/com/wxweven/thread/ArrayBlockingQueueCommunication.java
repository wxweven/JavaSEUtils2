/**
 * 
 */
package com.wxweven.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author wxweven
 * @date 2014年7月15日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class ArrayBlockingQueueCommunication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		new ArrayBlockingQueueCommunication().init();
	}

	public void init() {
		final Business business = new Business();

		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					business.sub();
				}
			}).start();

			new Thread(new Runnable() {

				@Override
				public void run() {
					business.main();
				}
			}).start();
		}
	}

	class Business {
		BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
		BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);

		{
			System.out.println("初始时刻，让queue2先放一个数据");
			try {
				queue2.put(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void sub() {
			try {
				queue1.put(1);
				System.out.println("queue1放一个数据");
				Thread.sleep((long) (Math.random() * 1000));

				queue2.take();
				System.out.println("queue2取一个数据");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void main() {
			try {
				queue2.put(1);
				System.out.println("queue2放一个数据");
				Thread.sleep((long) (Math.random() * 1000));
				
				queue1.take();
				System.out.println("queue1取一个数据");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
