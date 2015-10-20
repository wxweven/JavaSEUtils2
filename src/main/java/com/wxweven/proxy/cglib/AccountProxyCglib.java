package com.wxweven.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class AccountProxyCglib implements MethodInterceptor {

	private Object target;
	
	public Object getInstance(Object obj) {
		this.target = obj;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		 // 回调方法  
        enhancer.setCallback(this);  
        // 创建代理对象  
        return enhancer.create();  
		
	}
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy mProxy) throws Throwable {
		this.beforeInvoke("事务开始");
		//调用委托对象的方法
		mProxy.invoke(this.target, args);
		
		this.afterInvoke("事务结束");
		return null;
	}
	
	private void beforeInvoke(String str) {
		System.out.println("\n");  
		System.out.println("---------------------");  
        System.out.println(str);  
	}
	
	private void afterInvoke(String str) {
		 System.out.println(str);  
	     System.out.println("---------------------");  
	     System.out.println("\n");  
	}

}
