package com.wxweven.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 下面的这个例子可以理解为 F1 赛车的维修过程， 只有 startSignal命令下达之后，维修工才开始干活，
 * 只有等所有工人（doneSignal）完成工作之后，赛车才能继续
 * 
 * @author wxweven
 * @date 2015年9月13日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class CountDownLatchTest {

	private static final int WORKERS = 5;// 维修线程的数量

	public static void main(String[] args) throws InterruptedException {

		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(WORKERS);

		// 启动维修线程
		for (int i = 0; i < WORKERS; i++) {
			Worker worker = new Worker(startSignal, doneSignal);
			new Thread(worker).start();
		}

		prepare();//

		startSignal.countDown();// 下达开始维修的命令
		doSomething();// 主线程可以干别的了
		doneSignal.await();// 等待修车完成
		go();// 车子修完后，可以继续跑了

	}

	private static void go() {
		System.out.println("车子继续跑...");

	}

	private static void doSomething() {
		System.out.println("干点别的...");
	}

	private static void prepare() {
		System.out.println("准备工作...");

	}

}

class Worker implements Runnable {

	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	@Override
	public void run() {
		try {
			startSignal.await();// 先阻塞工作线程，等待修车的命令下达
			doSomething();// 具体的修车方法
			doneSignal.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doSomething() {
		System.out.println("工作线程 " + Thread.currentThread().getName() + " 在工作...");

	}

}
