package com.wxweven.concurrent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Java 版 消费者和生产者
 *
 * @author wxweven
 * @date 2014年9月10日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2016
 */
public class ConsumerProducer {
    private static final int QUEUE_SIZE = 1;                  // 定义缓冲队列的长度
    private Queue<Integer>   queue      = new LinkedList<>(); // 缓冲队列

    private Lock             lock       = new ReentrantLock();
    private Condition        condition  = lock.newCondition();

    public static void main(String[] args) {
        ConsumerProducer consumerProducer = new ConsumerProducer();

        // 消费者线程
        // ConsumerWaitNotify consumer = consumerProducer.new
        // ConsumerWaitNotify();
        ConsumerCondition consumer = consumerProducer.new ConsumerCondition();
        consumer.start();

        // 生产者线程
        ProducerCondition producer = consumerProducer.new ProducerCondition();
        producer.start();

    }

    /**
     * condition 定义生产者线程
     */
    class ProducerCondition extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == QUEUE_SIZE) {
                        condition.await();
                    }

                    queue.add(1);// 队列不为满，继续生产
                    System.out.println("生产者生产了一条数据...");
                    condition.signal();// 唤醒消费者线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * condition 定义消费者线程
     */
    class ConsumerCondition extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        condition.await();
                    }

                    queue.poll();// 队列不为空，继续消费
                    System.out.println("消费者消费了一条数据...");
                    condition.signal();// 唤醒生产者线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * wait nofity 定义生产者线程
     */
    class ProducerWaitNotify extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == QUEUE_SIZE) {
                        // 队列为满，阻塞生产者线程
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    queue.add(1);// 队列不为满，继续生产
                    System.out.println("生产者生产了一条数据...");
                    queue.notify();// 唤醒消费者线程
                }
            }
        }
    }

    /**
     * wait nofity 定义消费者线程
     */
    class ConsumerWaitNotify extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        // 队列为空，阻塞消费者线程
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    queue.poll();// 队列不为空，继续消费
                    System.out.println("消费者消费了一条数据...");
                    queue.notify();// 唤醒生产者线程
                }
            }
        }
    }

}
