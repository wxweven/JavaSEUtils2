package com.wxweven.concurrent;

public class WorkerThread implements Runnable {
	private int workerNumber;

	public WorkerThread(int num) {
		workerNumber = num;
	}

	@Override
	public void run() {
		// The thread simply prints 1 to 5
		for (int i = 1; i <= 5; ++i) {
			System.out.printf("Worker %d: %d\n", workerNumber, i);
			try {
				// sleep for 0 to 0.5 second
				Thread.sleep((int) (Math.random() * 500));
			} catch (InterruptedException e) {
			}
		}
	}

}
