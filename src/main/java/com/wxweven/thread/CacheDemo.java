/**
 * 
 */
package com.wxweven.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wxweven
 * @File: CacheDemo.java
 * @date 2014年7月12日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class CacheDemo {
	private Map<String, Object> cacheData = new HashMap<>();
	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	public static void main(String[] args) {

	}

	/**
	 * 获取数据，可以允许多个线程同时操作
	 * 
	 * @param key
	 * @return
	 */
	public Object getData(String key) {
		rwl.readLock().lock();// 加上读锁
		Object value = null;

		try {
			value = cacheData.get(key);// 先从缓存map里面找
			// 如果缓存里面没有，就去数据库里面取数据，并把取回的数据放入缓存
			if (value == null) {
				rwl.readLock().unlock();//先释放读锁
				rwl.writeLock().lock();//然后再加上写锁，因为设置值要加写锁，而加写锁需要先释放读锁（1）
				try {
					/**
					 * 下面这一行的判断很关键
					 * 再次判断value是否为空，确保在多线程并发时，没有重复去数据库取数据
					 * 因为（1）处的加写锁，
					 * 假设有两个线程来加写锁，第一个线程加上写锁后，第二个线程一直等待，等第一个线程取完数据，释放写锁后，第二个线程加上了写锁，
					 * 这个时候如果不判断value是否为空，那么第二个线程就会直接去数据库里面取数据，
					 * 而实际上，value的值已经被第一个线程从数据库里面取得了，第二个线程就没有必要再去取数据了
					 */
					if (value == null) {//再次判断value是否为空，确保在多线程并发时，没有重复去数据库取数据
						value = "从数据库取回的数据";// 实际代码是去数据库里面取数据
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					rwl.writeLock().unlock();// 最后无论如何都要释放读锁
				}
				rwl.readLock().lock();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rwl.readLock().unlock();// 最后无论如何都要释放读锁
		}

		return value;
	}

}
