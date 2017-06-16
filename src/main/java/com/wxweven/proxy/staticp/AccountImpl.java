package com.wxweven.proxy.staticp;

/**
 * 定义一个账户实现类
 *
 * @author even
 */
public class AccountImpl implements Account {

    /**
     * 查询账户的方法
     * 简单起见，直接打印
     */
    @Override
    public void queryAccount() {
        System.out.println("查询账户");

    }

    /**
     * 修改账户的方法
     * 简单起见，直接打印
     */
    @Override
    public void updateAccount() {
        System.out.println("修改账户");

    }
}
