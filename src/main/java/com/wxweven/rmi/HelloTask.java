package com.wxweven.rmi;

import java.io.Serializable;

/**
 * @author wxweven
 * @date 2014年7月29日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:具体的任务类实现
 */
public class HelloTask implements Task<String>, Serializable {
	private static final long serialVersionUID = -7211135538401534714L;

	/**
	 * 重写任务接口，实现具体的要执行的方法体execute()
	 * 简单起见，这里只是打印一句话，并返回一个字符串，类似HelloWorld
	 */
	@Override
	public String execute() {
		/**
		 * 这个方法的真正执行者，是服务端的JVM
		 * 而发起者是客户端
		 * 客户端发起RMI请求，把具体的执行交给服务端
		 * 服务端执行后，返回结果给客户端
		 */
		System.out.println("begin HelloTask execute()...");
		return "HelloTask";
	}

}
