/**
 * 
 */
package com.wxweven.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
public class Kongzhongwang3 extends Thread {
	public TestDo testDo;
	private String key;
	private String value;

	public Kongzhongwang3(String key, String key2, String value) {
		this.testDo = TestDo.getInstance();

		this.key = key + key2;
		this.value = value;
	}

	public static void main(String[] args) {
		Kongzhongwang3 a = new Kongzhongwang3("1", "", "1");
		Kongzhongwang3 b = new Kongzhongwang3("1", "", "2");
		Kongzhongwang3 c = new Kongzhongwang3("3", "", "3");
		Kongzhongwang3 d = new Kongzhongwang3("4", "", "4");
		Kongzhongwang3 e = new Kongzhongwang3("4", "", "5");

		a.start();
		b.start();
		c.start();
		d.start();
		e.start();
	}

	@Override
	public void run() {
		testDo.doSome(key, value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kongzhongwang3 other = (Kongzhongwang3) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
}

class TestDo {
	private static TestDo instance = new TestDo();

	private TestDo() {
	}

	private Set<String> set = new HashSet<String>();
	private Integer flag = 0;

	/**
	 * @param key
	 * @param value
	 */
	public void doSome2(String key, String value) {
		if (set.add(key))
			flag++;

		/**
		 * Thread flag lock a 1 1 b 1 1 c 2 2 d 3 3
		 */
		synchronized (flag) {
			// synchronized (key) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(key + ":" + value + "," + (System.currentTimeMillis() / 1000));
		}
	}

	/**
	 * 这个是张孝祥老师的解决版本，也比较佩服，扩展性比我的更好
	 * 
	 * @param key
	 * @param value
	 */
//	private ArrayList<String> keys = new ArrayList<>();
	private CopyOnWriteArrayList<String> keys = new CopyOnWriteArrayList<>();
	public void doSome(String key, String value) {
		String obj = key;
		if (!keys.contains(obj)) {
			keys.add(obj);
		} else {
			for (String str : keys) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (str.equals(obj))
					obj = str;
			}
		}

		synchronized (obj) {
			// synchronized (key) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(key + ":" + value + "," + (System.currentTimeMillis() / 1000));
		}
	}

	public static TestDo getInstance() {
		return instance;
	}
}
