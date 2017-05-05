package com.wxweven.concurrent;

import java.util.concurrent.Callable;

public class CallableWorkerThread implements Callable<String> {
    private int workerNumber;

    CallableWorkerThread(int workerNumber) {
        this.workerNumber = workerNumber;
    }

    @Override
    public String call() throws Exception {
        // use call() instead of run()
        for (int i = 1; i <= 5; ++i) {
            // just print 1 to 5
            System.out.printf("Worker %d: %d\n", workerNumber, i);
            Thread.sleep((int) (Math.random() * 1000));
        }
        return "worker " + workerNumber;
    }
}