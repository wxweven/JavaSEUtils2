/**
 * 
 */
package com.wxweven.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * @author wxweven
 * @date 2014年7月15日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class BlockingThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Semaphore semaphore = new Semaphore(1);
		final SynchronousQueue<String> queue = new SynchronousQueue<>();

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {

					try {
						semaphore.acquire();
						String input = queue.take();
						String output = TestDo.doSome(input);
						System.out.println(Thread.currentThread().getName() + ":" + output);

					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						semaphore.release();// 就算抛异常，也要把资源给老子让出来，让别的线程继续跑
					}

				}
			}).start();
		}

		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		for (int i = 0; i < 10; i++) {
			String input = "" + i;
			try {
				queue.put(input);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class TestDo {
		private static int count = 0;

		/**
		 * @param input
		 * @return
		 * @throws InterruptedException
		 */
		public static String doSome(String input) throws InterruptedException {
			count++;

			if (count == 5) {
				System.out.println("-----要抛异常了！！！--------");
				throw new InterruptedException();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			String output = input + ":" + (System.currentTimeMillis() / 1000);

			return output;
		}

	}

}
