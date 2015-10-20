package com.wxweven.concurrent.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {
	private static final long serialVersionUID = -5109058961272089478L;

	private static final int SIZE = 1000;

	private List<Product> products;
	private int start;
	private int end;
	private double increment;

	public Task(List<Product> products, int start, int end, double increment) {
		super();
		this.products = products;
		this.start = start;
		this.end = end;
		this.increment = increment;
	}

	@Override
	protected void compute() {
		boolean canCompute = (end - start) < 10;

		if (canCompute) {
			updatePrices();
		} else {
			int middle = (end - start) >> 1;
			System.out.println("Pending tasks:" + getQueuedTaskCount());

			Task leftTask = new Task(products, start, middle+1, increment);
			Task rightTask = new Task(products, middle + 1, end, increment);

			invokeAll(leftTask, rightTask);

		}

	}

	private void updatePrices() {
		for (int i = start; i < end; i++) {
			Product product = products.get(i);
			product.setPrice(product.getPrice() * (1 + increment));
		}
	}

	public static void main(String[] args) {
		List<Product> products = ProductListGenerator.generate(SIZE);
		Task task = new Task(products, 0, SIZE, 0.20);

		ForkJoinPool fjPool = new ForkJoinPool();
		fjPool.execute(task);// 不需要结果，所以用execute方法就好了

		for (int i = 0; i < SIZE; i++) {
			Product product = products.get(i);
			if (product.getPrice() != 12) {
				System.out.println(product);
			}
		}

	}

}
