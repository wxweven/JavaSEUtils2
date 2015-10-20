package com.wxweven.concurrent;

/**
 * 
 * @author wxweven
 * @date 2014年9月10日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description: Java 版 消费者和生产者
 */
public class ConsumerProducer {

	private String message; // 消息内容
	private boolean hasMesg; // 标识符，缓冲区是否有内容

	public static void main(String[] args) {
		final ConsumerProducer cp = new ConsumerProducer();

		// 定义生产者线程
		Thread producerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Producer thread started...");
				for (int i = 1; i <= 3; ++i) {
					cp.produceMsg("msg" + i);
				}
			}
		});

		// 定义生产者线程
		Thread consumerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Consumer thread started...");
				for (int i = 1; i <= 10; ++i) {
					cp.consumeMsg();
				}
			}
		});

		producerThread.start();
		consumerThread.start();
	}

	/** 生产者 */
	public synchronized void produceMsg(String msg) {
		while (hasMesg) {
			// 缓冲区是满的，生产者 wait
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 缓冲区有空间，生产者开始生产
		this.message = msg;
		System.out.println(msg + " Put @ " + System.nanoTime());

		hasMesg = true;// 置标志位为true，表示缓冲区满

		notify();// 唤醒其他线程，这里是唤醒消费者，开始从缓冲区取数据
	}

	/** 消费者 */
	public synchronized String consumeMsg() {
		String getMsg = "";
		while (!hasMesg) {
			// 缓冲区是空的，消费者wait
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 缓冲区有数据，消费者开始工作
		getMsg = this.message;
		System.out.println(getMsg + " Get @" + System.nanoTime());
		hasMesg = false;// 置标志位为false，表示缓冲区空

		notify();// 唤醒其他线程，这里是唤醒生产者，开始写数据到缓冲区

		return getMsg;
	}
}
