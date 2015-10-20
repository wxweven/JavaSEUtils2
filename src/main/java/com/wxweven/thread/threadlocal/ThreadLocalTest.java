package com.wxweven.thread.threadlocal;

/**
 * @Description:
 * @author wxweven
 * @date 2015年10月4日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2015
 */
public class ThreadLocalTest {

	// 通过覆写ThreadLocal中的initialValue方法为线程局部变量初始化之为0
	ThreadLocal<Integer> tlNum = new ThreadLocal<Integer>() {
		@Override
		public Integer initialValue() {
			return 0;
		};
	};

	/**
	 * 
	 * @return 下一个值
	 */
	public int getNextNum() {
		Integer num = tlNum.get();// 取得下一个值
		tlNum.set(num + 1);// 设置下一个值
		return num;// 返回取得的值
	}

	private static class TestThread implements Runnable {
		private ThreadLocalTest tlt;

		public TestThread(ThreadLocalTest tlt) {
			this.tlt = tlt;
		}

		public void run() {
			int n = 3;
			for (int i = 0; i < n; i++) {
				System.out.println("线程【" + Thread.currentThread().getName() + "】-线程变量值【" + tlt.getNextNum()
						+ "】");
			}
		}
	}

	public static void main(String[] args) {
		ThreadLocalTest tlt = new ThreadLocalTest();
		TestThread tt1 = new TestThread(tlt);
		TestThread tt2 = new TestThread(tlt);
		TestThread tt3 = new TestThread(tlt);
		Thread t1 = new Thread(tt1);
		Thread t2 = new Thread(tt2);
		Thread t3 = new Thread(tt3);
		t1.start();
		t2.start();
		t3.start();
	}
}
