package com.wxweven.concurrent;

import java.util.concurrent.*;

public class CallableThreadPoolTest {
	public static void main(String[] args) {
//		int numWorkers = Integer.parseInt(args[0]);
		int numWorkers = 5;

//		ExecutorService pool = Executors.newCachedThreadPool();
		ExecutorService pool = Executors.newFixedThreadPool(2);
		CallableWorkerThread workers[] = new CallableWorkerThread[numWorkers];
		Future[] futures = new Future[numWorkers];

		for (int i = 0; i < numWorkers; ++i) {
			workers[i] = new CallableWorkerThread(i + 1);
			futures[i] = pool.submit(workers[i]);
		}
		
		for (int i = 0; i < numWorkers; ++i) {
			try {
				System.out.println("----------------");
				System.out.println(futures[i].get() + " ended");
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} catch (ExecutionException ex) {
				ex.printStackTrace();
			}
		}
	}
}