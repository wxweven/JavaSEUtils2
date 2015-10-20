package com.wxweven.thread.threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author wxweven
 * @date 2015年10月4日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2015
 */
public class ThreadLocal<T> {
	private Map<Thread, T> map = Collections.synchronizedMap(new HashMap<Thread, T>());

	/**
	 * 取得线程局部变量的值
	 * 
	 * @return 线程局部变量的值
	 */
	public T get() {
		Thread currenThread = Thread.currentThread();
		T temp = map.get(currenThread);// 取得线程局部变量的值
		if (temp == null && !map.containsKey(currenThread)) {
			temp = initialValue();
			map.put(currenThread, temp);
		}
		return temp;
	}

	/**
	 * 设置当前线程局部变量的值
	 * 
	 * @param value
	 *            局部变量需设的值
	 */
	public void set(T value) {
		map.put(Thread.currentThread(), value);
	}

	/**
	 * 将线程局部变量的值删除
	 */
	public void remove() {
		map.remove(Thread.currentThread());
	}

	/**
	 * 线程局部变量初始值
	 * 
	 * @return 初始值为null
	 */
	public T initialValue() {
		return null;
	}
}
