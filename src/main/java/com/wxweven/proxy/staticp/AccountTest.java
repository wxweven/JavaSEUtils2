package com.wxweven.proxy.staticp;

import org.junit.Test;

/**
 * 测试静态的代理
 *
 * @author even
 */
public class AccountTest {

    @Test
    public void test() {
        Account account = new AccountImpl();
        AccountProxy accountProxy = new AccountProxy(account);

        //通过代理来访问业务逻辑方法
        accountProxy.queryAccount();
        accountProxy.updateAccount();
    }
}
