package com.wxweven.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author wxweven
 * @date 2014年7月29日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description: 远程服务对象：接受客户端的请求，并实际运行客户端的任务，返回结果给客户端
 */
public class ComputeServer {

	/**
	 * 启动RMI 注册服务并进行对象注册
	 */
	public static void main(String[] args) {
		try {
			// 也可以通过命令 ＄java_home/bin/rmiregistry 1099启动
			// 这里用这种方式避免了再打开一个DOS窗口
			// 而且用命令rmiregistry启动注册服务还必须事先用RMIC生成一个stub类为它所用

			// 1.启动RMI注册服务，指定端口为1099　（1099为默认端口）
			LocateRegistry.createRegistry(1099);

			// 2.创建远程对象的实例：父类引用指向子类实例（面向接口编程）
			Compute computeEngine = new ComputeEngine();

			// 3.把computeEngine注册到RMI注册服务器上，命名为Compute
			String name = "rmi://172.23.8.60:1099/Compute";
			Naming.rebind(name, computeEngine);

			// 注册到本机上
			// Naming.rebind("Compute",computeEngine);

			System.out.println("Compute Server is ready.");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

}
