package com.wxweven.designpattern.decorator;

/**
 * @author wxweven
 * @date 2015年9月30日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description: 艺术装饰者
 */
public class Art implements GirlDecorator {

	private Girl girl;

	public Art(Girl girl) {
		this.girl = girl;
	}

	@Override
	public void getDiscription() {
		this.girl.getDiscription();
		this.draw();

	}

	public void draw() {
		System.out.println("画得一手好画");
	}

}
