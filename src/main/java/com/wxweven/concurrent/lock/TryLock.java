package com.wxweven.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock 尝试获取锁
 *
 * @author wxweven
 * @version 1.0
 * @date 2016年8月27日
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2016
 */
public class TryLock implements Runnable {

    private static ReentrantLock lock1 = new ReentrantLock(true);
    private static ReentrantLock lock2 = new ReentrantLock(true);

    private int number;

    public TryLock(int number) {
        this.number = number;
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new TryLock(1), "线程01");
        Thread thread2 = new Thread(new TryLock(2), "线程02");
        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {
        if (number == 1) {
            while (true) {

                if (lock1.tryLock()) {
                    try {
                        if (lock2.tryLock()) {
                            //							System.out.println(Thread.currentThread().getMsg() + " 得到了lock2的锁...");
                        }
                    } finally {
                        if (lock1.isHeldByCurrentThread() && lock2.isHeldByCurrentThread()) {
                            System.out.println(Thread.currentThread().getName() + " 任务执行完毕...");
                            lock1.unlock();
                            lock2.unlock();
                            break;
                        } else {
                            lock1.unlock();
                        }
                    }
                }
            }
        } else {
            while (true) {
                if (lock2.tryLock()) {
                    //					System.out.println(Thread.currentThread().getMsg() + " 得到了lock2的锁...");
                    try {
                        if (lock1.tryLock()) {
                            //							System.out.println(Thread.currentThread().getMsg() + " 得到了lock1的锁...");
                        }
                    } finally {
                        if (lock1.isHeldByCurrentThread() && lock2.isHeldByCurrentThread()) {
                            System.out.println(Thread.currentThread().getName() + " 任务执行完毕...");
                            lock1.unlock();
                            lock2.unlock();
                            break;
                        } else {
                            lock2.unlock();
                        }
                    }
                }
            }
        }
    }
}
