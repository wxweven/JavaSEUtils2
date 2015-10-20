package com.wxweven.thread;
/**
 * 
 * @author wxweven
 * @File: ThreadCommunication.java
 * @date 2014年7月11日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class ThreadCommunication {

	public static void main(String[] args) {
		new ThreadCommunication().init();
	}

	public void init() {
		final Business business = new Business();
		// 启动子线程
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int loop = 0; loop < 50; loop++)
					business.sub(loop);
			}
		}).start();

		// 主线程代码
		for (int loop = 0; loop < 50; loop++)
			business.mainth(loop);
	}

	class Business {
		/**
		 * 标志位：标志轮到哪个线程执行 true : 子线程执行 false : main线程执行
		 */
		boolean isSub = true;

		/**
		 * 定义子线程
		 * 
		 * @param loop
		 */
		public synchronized void sub(int loop) {
			// 如果轮到Main线程执行，自己就阻塞
			while (!isSub) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// 轮到自己运行
			for (int i = 0; i < 10; i++) {
				System.out.println("sub process: loop of " + (loop + 1) + ",sequence of " + (i + 1));
			}

			isSub = false;// 自己执行完毕后，将标志位置为false，表示轮到main线程执行
			this.notify();// 同时唤醒阻塞的main线程

		}

		/**
		 * 定义主线程
		 * 
		 * @param loop
		 */
		public synchronized void mainth(int loop) {
			// 如果是轮到子线程执行，就阻塞自己
			while (isSub) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// 轮到自己运行
			for (int i = 0; i < 100; i++) {
				System.out.println("main process: loop of " + (loop + 1) + ",sequence of " + (i + 1));
			}

			isSub = true;// 主线程执行完毕后，将标志位置为true，表示轮到子线程执行
			this.notify();// 同时，唤醒阻塞的子线程
		}

	}

}
