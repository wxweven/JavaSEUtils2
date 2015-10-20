package com.wxweven.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class BasicThreadTest {

	private String name;

	public BasicThreadTest(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		
		new Timer().schedule(new MyTimerTask1(), 2000);

		while (true) {
			System.out.println(new Date().getSeconds());
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class MyTimerTask1 extends TimerTask {
		@Override
		public void run() {
			System.out.println("bombing!");
			new Timer().schedule(new MyTimerTask2(), 4000);
		}
	}
	
	static class MyTimerTask2 extends TimerTask {
		@Override
		public void run() {
			System.out.println("bombing!");
			new Timer().schedule(new MyTimerTask1(), 2000);
		}
	}

}
