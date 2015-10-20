package com.wxweven.designpattern.decorator;
/**
 * @author wxweven
 * @date 2015年9月30日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description: 科学装饰者
 */
public class Science implements GirlDecorator {

	private Girl girl;

	public Science(Girl girl) {
		this.girl = girl;
	}

	@Override
	public void getDiscription() {
		this.girl.getDiscription();
		this.learn();

	}

	private void learn() {
		System.out.println("科学家");

	}

}
