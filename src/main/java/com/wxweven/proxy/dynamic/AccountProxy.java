package com.wxweven.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AccountProxy implements InvocationHandler {
    private Object target;// 需要被动态代理的对象
    private IAdvice advice;// advice对象，用于动态过滤代理

    /**
     * 绑定委托对象和过滤对象，并返回一个代理类
     *
     * @param obj    委托对象
     * @param advice 过滤对象
     * @return
     */
    public Object bind(Object obj, IAdvice advice) {
        this.target = obj;
        this.advice = advice;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        /**
         * 在方法前加点处理
         * 在方法后也会加点处理
         * 但是这个处理方法不能写死
         * 所以， 由传过来的IAdvice对象来决定
         */
        advice.beforeMethod("事务开始");
        result = method.invoke(target, args);// 执行委托对象的方法
        advice.afterMethod("事务结束");
        return result;
    }
}
