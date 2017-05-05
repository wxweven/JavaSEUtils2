package com.wxweven.concurrent;

import java.util.Random;

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
                Thread.sleep(new Random().nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> System.out.println("new thread start...")).start();

        Thread.currentThread().join();
    }
}
