/**
 * 
 */
package com.wxweven.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wxweven
 * @date 2014年7月15日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class ConcurrentCollectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Collection<User> users = new CopyOnWriteArrayList<>();
		users.add(new User("张三", 28));
		users.add(new User("李四", 28));
		users.add(new User("王五", 28));

		Iterator<User> itr = users.iterator();
		int loop = 0;
		while (itr.hasNext()) {
			loop++;
			System.out.println("第 " + loop + " 次循环");
			User user = itr.next();
			if ("王五".equals(user.getName())) {
				users.remove(user);
			} else {
				System.out.println(user.getName());
			}
		}
		
	
	}

}
