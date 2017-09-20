package com.java8.completablefuture;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * CompletableFuture demo
 *
 * @author wxweven
 * @date 2017-09-20
 */
public class CompletableFutureTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureTest.class);

    /**
     * 模拟耗时任务
     *
     * @return
     */
    private static int getMoreData() {
        LOGGER.info("begin to start compute...");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("end to start compute. passed: {} seconds", (System.currentTimeMillis() - start) / 1000);
        return 8888;
    }

    /**
     * jdk1.5 中的 future 模式
     *
     * @throws Exception
     */
    @Test
    public void test_future() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future<Integer> f = es.submit(() -> {
            // 长时间的异步计算
            Thread.sleep(5000);
            // 然后返回结果
            return 100;
        });

        LOGGER.info("main thread continues to do sth else...");
        Integer result = f.get();
        LOGGER.info("waiting for result...");
        LOGGER.info("result is: {}", result);
    }

    /**
     * 计算结果完成时的处理
     *
     * @throws Exception
     */
    public void test_whenComplete() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(CompletableFutureTest::getMoreData);

        Future<Integer> f = future.whenComplete((v, e) -> {
            LOGGER.info("v: {}", v);
            LOGGER.info("e: {}", e);
        });

        LOGGER.info("main thread continues to do sth else...");

        LOGGER.info("result is: {}", f.get());
        System.in.read();
    }

    /**
     * 计算结果处理完成后转化
     *
     * @throws Exception
     */
    @Test
    public void test_conversion() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(CompletableFutureTest::getMoreData);

        // 将上一步的future执行完毕的结果作转化
        CompletableFuture<String> f = future.thenApplyAsync(i -> i * 10)
                                            .thenApplyAsync(Object::toString);

        LOGGER.info("result is: {}", f.get());
    }

    /**
     * handle, 计算结果完成时做响应的处理，兼有 whenComplete 和转化两个功能
     *
     * @throws Exception
     */
    @Test
    public void test_handle() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(CompletableFutureTest::getMoreData);

        // 处理上一步的future执行完毕的结果，包括转化
        CompletableFuture<String> handleResult = future.handleAsync((i, e) -> {
            int res = i * 10;
            return String.valueOf(res);
        });

        LOGGER.info("result is: {}", handleResult.get());
    }

    /**
     * 纯消费(执行Action)，没有返回值
     *
     * @throws Exception
     */
    @Test
    public void test_action() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(CompletableFutureTest::getMoreData);
        // 执行完异步任务后，打印结果，没有返回值
        CompletableFuture<Void> f = future.thenAcceptAsync(System.out::println);
        System.out.println(f.get());
    }

    /**
     * thenAcceptBoth,
     * 当两个CompletionStage都正常完成计算的时候，就会执行提供的action，它用来组合另外一个异步的结果
     *
     * @throws Exception
     */
    @Test
    public void test_thenAcceptBoth() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(CompletableFutureTest::getMoreData);
        CompletableFuture<Void> f = future.thenAcceptBoth(CompletableFuture.completedFuture(10), (x, y) -> System.out.println(x * y));
        System.out.println(f.get());
    }

    /**
     * thenRun,
     * Runnable并不使用CompletableFuture计算的结果，
     * 因此先前的CompletableFuture计算的结果被忽略了,这个方法返回CompletableFuture<Void>类型的对象
     *
     * @throws Exception
     */
    @Test
    public void test_thenRun() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Void> f = future.thenRun(() -> System.out.println("finished"));
        System.out.println(f.get());
    }

}