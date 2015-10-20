package com.wxweven.rmi;

import java.rmi.Naming;
import java.math.BigDecimal;

/**
 * 
 * @author wxweven
 * @date 2014年7月29日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:RMI的客户端，负责发起RMI请求，等待服务端的运行，接受服务端返回的结果
 */
public class ComputeClient {
	public static void main(String args[]) {

		try {
			System.out.println("starup client...");

			// 1.根据RMI地址及端口，获得远程对象实例
			String name = "rmi://172.23.8.60:1099/Compute";
			Compute comp = (Compute) Naming.lookup(name);

			System.out.println("-----begin PiTask...");

			// 2.创建任务实例
			Task<BigDecimal> piTask = new PiTask(10);

			// 3.发起RMI请求，将任务交给远程对象运行，并得到结果
			BigDecimal pi = comp.executeTask(piTask);

			System.out.println(pi);
			System.out.println("-----end PiTask...");

			// 通上面的PiTask
			System.out.println("-----begin HelloTask...");
			Task<String> helloTask = new HelloTask();
			String helloTaskString = comp.executeTask(helloTask);
			System.out.println(helloTaskString);
			System.out.println("-----end HelloTask...");

			System.out.println("client ends...");
		} catch (Exception e) {
			System.err.println("Compute exception:");
			e.printStackTrace();
		}
	}
}