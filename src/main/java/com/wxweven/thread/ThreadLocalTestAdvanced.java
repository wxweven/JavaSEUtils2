package com.wxweven.thread;

import java.util.Random;

/**
 * @author wxweven
 * @File: ThreadLocalTestAdvanced.java
 * @date 2014年7月11日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:线程内数据共享，两个线程间，相互独立
 */
public class ThreadLocalTestAdvanced {
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
					MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();// 得到当前线程的单例
					// 设置当前线程上的单例对象的属性值
					myData.setAge(data);// 设置age值
					myData.setName("name" + data);// 设置name值

					new A().get();
					new B().get();
				}
			}).start();
		}

	}

	static class A {
		public void get() {
			MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();// 得到当前线程的单例
			// 获得并打印当前线程上的单例对象的属性
			System.out.println("A from " + Thread.currentThread().getName() + " get myData:" + myData.getAge() + ","
					+ myData.getName());
		}

	}

	static class B {
		public void get() {
			MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();// 得到当前线程的单例
			// 获得并打印当前线程上的单例对象的属性
			System.out.println("B from " + Thread.currentThread().getName() + " get myData:" + myData.getAge() + ","
					+ myData.getName());
		}

	}
}

/**
 * 与当前线程绑定的数据对象
 */
class MyThreadScopeData {
	private String name;
	private int age;

	private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();// 与当前线程绑定

	// 私有的构造方法，用于创建线程内的单例模式
	private MyThreadScopeData() {
	}

	/**
	 * 得到当前线程的单例
	 * 
	 * @return
	 */
	public static MyThreadScopeData getThreadInstance() {
		MyThreadScopeData instance = map.get();
		if (instance == null) {
			instance = new MyThreadScopeData();
			map.set(instance);
		}

		return instance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}