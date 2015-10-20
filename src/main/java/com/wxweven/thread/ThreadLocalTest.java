package com.wxweven.thread;

import java.util.Random;

/**
 * @author wxweven
 * @File: ThreadLocalTest.java
 * @date 2014年7月11日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:线程内数据共享，两个线程间，相互独立
 */
public class ThreadLocalTest {

	private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();//ThreadLocal，代表x与当前线程绑定

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() + " has put data:" + data);
					x.set(data);
					new A().get();
					new B().get();
				}
			}).start();
		}

	}

	static class A {
		public void get() {
			int data = x.get();
			System.out.println("A from " + Thread.currentThread().getName() + " get data:" + data);
		}

	}

	static class B {
		public void get() {
			int data = x.get();
			System.out.println("B from " + Thread.currentThread().getName() + " get data:" + data);
		}

	}

}
