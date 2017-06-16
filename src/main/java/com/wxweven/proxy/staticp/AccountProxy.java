package com.wxweven.proxy.staticp;

/**
 * 静态代理模式
 * 使用组合来完成
 *
 * @author even
 */
public class AccountProxy implements Account {

    private Account target;//保存一个Account对象

    /**
     * 覆盖默认的构造方法
     */
    public AccountProxy(Account account) {
        this.target = account;
    }

    @Override
    public void queryAccount() {
        System.out.println("事务处理之前，加点逻辑...");
        //调用委托的方法
        this.target.queryAccount();
        System.out.println("事务处理之后，加点逻辑...");
    }

    @Override
    public void updateAccount() {
        System.out.println("事务处理之前，加点逻辑...");
        //调用委托的方法
        this.target.updateAccount();
        System.out.println("事务处理之后，加点逻辑...");

    }
}
