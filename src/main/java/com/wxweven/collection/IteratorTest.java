package com.wxweven.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {

	public static void main(String[] args) {
		List<String> testList = new ArrayList<String>();
		
		testList.add("1111");
		testList.add("2222");
		testList.add("3333");
		
		Iterator<String> it = testList.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		for(String s : testList) {
			System.out.println(s);
		}
	}

}
