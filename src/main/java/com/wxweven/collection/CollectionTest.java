package com.wxweven.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CollectionTest {

	public static void main(String[] args) {
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("a", new Student(1,"even"));
		map.put("b", new Student(2,"wxweven"));
		map.put("c", new Student(3,"wang"));
		List list1 = new ArrayList();
		list1.add("even");
		list1.add("wxweven");
		list1.add("wang");
		
		List list2 = new ArrayList();
		list2.add("even");
		list2.add("wxweven");
		list2.add("jia");
		
//		list1.removeAll(list2);
		/*System.out.println(list1);
		System.out.println(list2);*/
		
		Iterator it = list1.iterator();
		
		/*while(it.hasNext()) {
			Object obj = it.next();
			System.out.println(obj);
		}*/
		
		System.out.println(Map.class.getSuperclass());
		for(String key : map.keySet()) {
			Student stu = (Student) map.get(key);
			System.out.println(key + " --> " + stu.getId() + ", " + stu.getName());
		}
	}

}
