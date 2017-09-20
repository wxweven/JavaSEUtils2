package com.java8.completablefuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.lang.String.format;

public class CompletableFuturePerformance {

    private static int PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        System.out.println("Processors: " + PROCESSORS);

        Arrays.asList(-3, -1, 0, 1, 2, 4, 5, 10, 16, 17)
              .forEach(offset -> {
                  int jobNum = PROCESSORS + offset;
                  System.out.println(
                          format("When %s tasks => stream: %s, parallelStream: %s, future default: %s, future custom: %s",
                                  jobNum, testStream(jobNum), testParallelStream(jobNum),
                                  testCompletableFutureDefaultExecutor(jobNum), testCompletableFutureCustomExecutor(jobNum)));
              });

    }

    private static long testStream(int jobCount) {
        List<Supplier<Integer>> tasks = new ArrayList<>();
        IntStream.rangeClosed(1, jobCount)
                 .forEach(value -> tasks.add(CompletableFuturePerformance::getJob));

        long start = System.currentTimeMillis();
        int sum = tasks.stream()
                       .map(Supplier::get)
                       .mapToInt(Integer::intValue)
                       .sum();
        checkSum(sum, jobCount);
        return System.currentTimeMillis() - start;
    }

    private static long testParallelStream(int jobCount) {
        List<Supplier<Integer>> tasks = new ArrayList<>();
        IntStream.rangeClosed(1, jobCount)
                 .forEach(value -> tasks.add(CompletableFuturePerformance::getJob));

        long start = System.currentTimeMillis();
        int sum = tasks.parallelStream()
                       .map(Supplier::get)
                       .mapToInt(Integer::intValue)
                       .sum();
        checkSum(sum, jobCount);
        return System.currentTimeMillis() - start;
    }

    private static long testCompletableFutureDefaultExecutor(int jobCount) {
        List<CompletableFuture<Integer>> tasks = new ArrayList<>();
        IntStream.rangeClosed(1, jobCount)
                 .forEach(value -> tasks.add(
                         CompletableFuture.supplyAsync(CompletableFuturePerformance::getJob)
                 ));

        long start = System.currentTimeMillis();
        int sum = tasks.stream()
                       .map(CompletableFuture::join)
                       .mapToInt(Integer::intValue)
                       .sum();
        checkSum(sum, jobCount);
        return System.currentTimeMillis() - start;
    }

    private static long testCompletableFutureCustomExecutor(int jobCount) {
        Executor executor = new ForkJoinPool(20);

        List<CompletableFuture<Integer>> tasks = new ArrayList<>();
        IntStream.rangeClosed(1, jobCount)
                 .forEach(value -> tasks.add(
                         CompletableFuture.supplyAsync(CompletableFuturePerformance::getJob, executor)
                 ));

        long start = System.currentTimeMillis();
        int sum = tasks.stream()
                       .map(CompletableFuture::join)
                       .mapToInt(Integer::intValue)
                       .sum();
        checkSum(sum, jobCount);
        return System.currentTimeMillis() - start;
    }

    private static int getJob() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        return 50;
    }

    private static void checkSum(int sum, int jobNum) {
        if (sum != 50 * jobNum) {
            throw new AssertionError("Result Error");
        }
    }
}