package com.wxweven.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算List中所有整数的和测试类
 * @author 飞雪无情
 * @since 2010-7-12
 */
public class CountListIntegerSumMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		int threadCounts = 10;//采用的线程数
		//生成的List数据
		for (int i = 1; i <= 10000; i++) {
			list.add(i);
		}
		
		long startTime = System.currentTimeMillis();
		
		CountListIntegerSum countListIntegerSum=new CountListIntegerSum(list,threadCounts);
		long sum=countListIntegerSum.getIntegerSum();
		System.out.println("多线程时间："+(System.currentTimeMillis() - startTime));
		System.out.println("List中所有整数的和为:"+sum);
		
//		long sum = 0;
//		for(int i : list) {
//			sum += i;
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		System.out.println("单线程时间："+(System.currentTimeMillis() - startTime));
//		System.out.println("List中所有整数的和为:"+sum);
	}

}