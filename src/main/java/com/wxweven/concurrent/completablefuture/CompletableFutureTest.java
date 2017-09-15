package com.wxweven.concurrent.completablefuture;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureTest {
    public static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureTest.class);
    private static Random rand = new Random();

    public static void main2(String[] args) {
        CompletableFuture<Double> futurePrice = getPriceAsync();

        // do anything you want, 当前线程不被阻塞
        System.out.println(111);

        // 线程任务完成的话，执行回调函数，不阻塞后续操作
        futurePrice.whenComplete((aDouble, throwable) -> {
            System.out.println(aDouble);
            // do something else
        });

        System.out.println(222);
    }

    static CompletableFuture<Double> getPriceAsync() {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            futurePrice.complete(23.55);
        }).start();
        return futurePrice;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(() -> {
////            if (true) {
////                throw new RuntimeException("Something wrong");
////            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 23.5;
//        }, runnable -> new Thread(runnable).start());
//
//        futurePrice.whenComplete((aDouble, throwable) -> {
//            System.out.println(aDouble);
//        });


        for (int i = 0; i < 10; i++) {
            System.out.println((Integer) i);
        }
    }

    @Test
    public void test_whenComplete() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {

            // 长时间的异步计算
            LOGGER.info("开始计算...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("结束计算...");
            return rand.nextInt(1000);
        });


        LOGGER.info("等待计算结果...");

        Future<Integer> f = future.whenComplete((v, e) -> {
            LOGGER.info("v:{}", v);
            LOGGER.info("e:{}", e);
        });

        LOGGER.info("得到计算结果");
        System.in.read();
    }

    @Test
    public void test_future() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future<Integer> f = es.submit(() -> {
            // 长时间的异步计算
            LOGGER.info("开始计算...");
            Thread.sleep(5000);
            LOGGER.info("结束计算...");
            // 然后返回结果
            return 100;
        });

        LOGGER.info("等待计算结果...");
        Integer res = f.get();
        LOGGER.info("得到计算结果：{}", res);
    }

    @Test
    public void test_completableFuture() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // 长时间的异步计算
            LOGGER.info("开始计算...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            LOGGER.info("结束计算...");
            return 100;
        });
        LOGGER.info("等待计算结果...");
        Integer res = future.get();
        LOGGER.info("得到计算结果：{}", res);
    }

}