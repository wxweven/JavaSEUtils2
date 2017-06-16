/**
 *
 */
package com.wxweven.proxy.dynamic;

/**
 * @author wxweven
 * @version 1.0
 * @date 2014年7月26日
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class MyAdvice implements IAdvice {


    @Override
    public void beforeMethod(String str) {
        System.out.println("\n");
        System.out.println("---------------------");
        System.out.println(str);

    }

    @Override
    public void afterMethod(String str) {
        System.out.println(str);
        System.out.println("---------------------");
        System.out.println("\n");
    }

}
