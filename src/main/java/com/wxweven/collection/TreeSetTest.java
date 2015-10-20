package com.wxweven.collection;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest {

	public static void main(String[] args) {
		Random ran = new Random(); 
		int len = 1000000;
		/**
		 * 直接利用TreeSet的add方法来构建排序的Set
		 */
		long startTime = System.currentTimeMillis();
		Set<Integer> numSet = new TreeSet<Integer>();
		for(int i=0; i<len; i++) {
			numSet.add(ran.nextInt());
		}
		
		System.out.println("直接TreeSet耗时："+(System.currentTimeMillis()-startTime));
		System.out.println(numSet.size());
		
		long startTime2 = System.currentTimeMillis();
		Set<Integer> numSet2 = new HashSet<Integer>(len);
		for(int i=0; i<len; i++) {
			numSet2.add(ran.nextInt());
		}
		TreeSet<Integer> ts = new TreeSet<Integer>(numSet2);
		System.out.println("间接TreeSet耗时："+(System.currentTimeMillis()-startTime2));
		System.out.println(numSet2.size());
		
	}

}
