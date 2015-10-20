/**
 * 
 */
package com.wxweven.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wxweven
 * @date 2014年7月14日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class CountDownLatchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();//线程池
		final CountDownLatch cdOrder = new CountDownLatch(1);//用于标记等待命令，这里为1
		final CountDownLatch cdAnwser = new CountDownLatch(3);//用于标记需要执行的命令数，这里为3

		for (int i = 0; i < 3; i++) {
			Runnable run = new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println("线程 " + Thread.currentThread().getName() + " 正在等待命令");
						/**
						 * 阻塞在这里，等待接受主线程的命令
						 * 当调用cdOrder的countDown()方法时，cdOrder 减1
						 * 当cdOrder变为0时，线程被唤醒，就可以接着往下执行了
						 */
						cdOrder.await();
						
						System.out.println("线程 " + Thread.currentThread().getName() + " 已接收命令");

						Thread.sleep((long) (Math.random() * 10000));

						System.out.println("线程 " + Thread.currentThread().getName() + " 回应命令处理结束");
						
						/**
						 * 一个处理结束后，就调用结果的countDown()
						 * cdAnwser初始值为3,3个线程都执行完后，cdAnwser变为0
						 * 阻塞在cdAnwser上的线程被唤醒，开始执行
						 */
						cdAnwser.countDown();

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			};

			service.execute(run);
		}

		try {
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println("线程" + Thread.currentThread().getName() + "即将发布命令");
			/**
			 * cdOrder减1，此时，cdOrder变为0
			 * 阻塞在cdOrder上的线程被唤醒
			 */
			cdOrder.countDown();
			
			System.out.println("线程" + Thread.currentThread().getName() + "已发布命令，正在等待结果");
			/**
			 * 主线程阻塞在cdAnwser上
			 * 等待cdAnwser变为0
			 */
			cdAnwser.await();
			
			System.out.println("线程" + Thread.currentThread().getName() + "已收到所有响应结果");
			System.out.println("程序运行结束");

		} catch (Exception e) {
			e.printStackTrace();
		}

		service.shutdown();
	}

}
