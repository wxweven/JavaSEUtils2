package com.wxweven.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * @author wxweven
 * @date 2014年7月29日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description: 远程对象实现类，具体实现要提供的服务
 */
public class ComputeEngine extends UnicastRemoteObject implements Compute {
	private static final long serialVersionUID = -5095109301779014471L;

	/**
	 * 构造方法，必须有，而且要抛出RemoteException异常
	 * @throws RemoteException
	 */
	public ComputeEngine() throws RemoteException {
		super();
	}

	/**
	 * 实现具体的服务，这里执行请求任务Task t对象的execute()方法
	 */
	@Override
	public <T> T executeTask(Task<T> t) throws RemoteException {
		System.out.println("the server begins to serve...");
		return t.execute();
	}

	/*
	 * public static void main(String[] args) { if (System.getSecurityManager()
	 * == null) { System.setSecurityManager(new SecurityManager()); } try {
	 * String name = "Compute"; Compute engine = new ComputeEngine(); Compute
	 * stub = (Compute) UnicastRemoteObject.exportObject(engine, 0); Registry
	 * registry = LocateRegistry.getRegistry(); registry.rebind(name, stub);
	 * System.out.println("ComputeEngine bound"); } catch (RemoteException e) {
	 * System.err.println("ComputeEngine exception:"); e.printStackTrace(); } }
	 */
}