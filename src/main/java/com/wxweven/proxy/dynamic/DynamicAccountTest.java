package com.wxweven.proxy.dynamic;

import org.junit.Test;

public class DynamicAccountTest {

    @Test
    public void test() {
        //实例化代理对象
        AccountProxy proxy = new AccountProxy();

        //用代理绑定委托对象，并返回一个委托的代理对象
        Account accountProxy = (Account) proxy.bind(new AccountImpl(), new MyAdvice());

        //用返回的委托代理，调用委托的方法
        accountProxy.queryAccount();
        accountProxy.updateAccount();
    }
}
