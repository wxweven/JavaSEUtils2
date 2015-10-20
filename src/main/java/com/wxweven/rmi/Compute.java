package com.wxweven.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author wxweven
 * @date 2014年7月29日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:远程对象接口
 */
public interface Compute extends Remote {
	/**
	 * 定义远程对象需要提供的服务接口
	 * 
	 * @param t
	 *            参数 ，代表需要被执行的任务接口
	 * @return
	 * @throws RemoteException
	 *             必须抛出 RemoteException异常
	 */
	<T> T executeTask(Task<T> t) throws RemoteException;
}