package com.wxweven.concurrent.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ForkJoinPool fjp = new ForkJoinPool();
		CountTask countTask = new CountTask(1, 4);

		Future<Integer> future = fjp.submit(countTask);//由于需要返回结果，所以提交到线程池执行，通过future异步的得到执行结果

		int sum = future.get();

		System.out.println(sum);
	}

}

class CountTask extends RecursiveTask<Integer> {
	private static final long serialVersionUID = 8798918095134127200L;

	private static final int THRESHOLD = 2;// 阈值

	private int start;// 左指针
	private int end;// 右指针

	public CountTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;

		// 如果任务足够小，就计算
		boolean canCompute = (this.end - this.start) <= THRESHOLD;

		if (canCompute) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			// 如果任务大于阈值，接着分割任务
			int middle = (this.end - this.start) >> 1;
			CountTask leftTask = new CountTask(this.start, middle);
			CountTask rightTask = new CountTask(middle + 1, this.end);

			// 执行子任务
			leftTask.fork();
			rightTask.fork();

			// 获得子任务的结果
			int leftSum = leftTask.join();
			int rightSum = rightTask.join();

			sum = leftSum + rightSum;
		}

		return sum;
	}

}