package com.wxweven.collection.map;

public class TreeMapTest {

	public static void main(String[] args) {
		Student s1 = new Student("even");
		s1.addScore("语文", 90);
		s1.addScore("数学", 100);
		s1.addScore("英语", 98);
		s1.addScore("计算机", 100);
		
		Student s2 = new Student("even");
		s2.addScore("语文", 90);
		s2.addScore("数学", 100);
		s2.addScore("英语", 23);
		s2.addScore("计算机", 100);
		
		System.out.println();
		if(s1.compareTo(s2) > 0) {
			System.out.println("s1大于s2");
		} else if(s1.compareTo(s2) < 0) {
			System.out.println("s1小于s2");
		} else {
			System.out.println("s1等于s2");
		}
	}

}
