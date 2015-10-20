package com.wxweven.rmi;
/**
 * @author wxweven
 * @date 2014年7月29日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description: 任务接口
 */

public interface Task<T> {
	/**
	 * 任务接口的核心方法，实现类需要实现具体的execute()方法
	 * @return
	 * 这个方法的真正执行者，是服务端的JVM
	 * 而发起者是客户端
	 * 客户端发起RMI请求，把具体的执行交给服务端
	 * 服务端执行后，返回结果给客户端
	 */
	T execute();
}