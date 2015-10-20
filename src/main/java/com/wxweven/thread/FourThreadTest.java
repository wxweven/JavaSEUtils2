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
public class FourThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final BlockingQueue<String> queue = new ArrayBlockingQueue<>(4);

		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							String log = queue.take();
							parseLog(log);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}

		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		for (int i = 0; i < 16; i++) {
			final String log = "" + (i + 1);
			try {
				// Thread.sleep(1000);
				queue.put(log);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static public void parseLog(String log) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(log + ":" + (System.currentTimeMillis() / 1000));
	}

}
