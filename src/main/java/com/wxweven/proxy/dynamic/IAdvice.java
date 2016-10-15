/**
 *
 */
package com.wxweven.proxy.dynamic;

/**
 * @author wxweven
 * @date 2014年7月26日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public interface IAdvice {
	void beforeMethod(String str);

	void afterMethod(String str);
}
