package com.wxweven.proxy.cglib;

import org.junit.Test;

public class AccountProxyCglibTest {

    @Test
    public void test() {
        //实例化cglib 的代理对象
        AccountProxyCglib cglibProxy = new AccountProxyCglib();

        //得到委托的代理
        Account cglibAccount = (Account) cglibProxy.getInstance(new Account());

        //利用委托的代理来操作
        cglibAccount.queryAccount();
        cglibAccount.updateAccount();
    }

}
