package com.wxweven.proxy.staticp;

/**
 * 测试静态的代理
 * @author even
 *
 */
public class AccountTest {
	
	public static void main(String[] args) {
		Account account = new AccountImpl();
		AccountProxy accountProxy = new AccountProxy(account);
		
		//通过代理来访问业务逻辑方法
		accountProxy.queryAccount();
		accountProxy.updateAccount();
	}
}
