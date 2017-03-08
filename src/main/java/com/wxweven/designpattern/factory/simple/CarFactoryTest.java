package com.wxweven.designpattern.factory.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 简单工厂模式测试
 *
 * @author wxweven
 * @date 2017年3月8日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2017
 */
public class CarFactoryTest {
    private static Logger logger = LoggerFactory.getLogger(CarFactoryTest.class);

    public static void main(String[] args) {
        ICar myCar = CarFactory.getCar("benz");
        logger.debug("我的车：" + myCar.getName());
        myCar.drive();

        logger.debug("今天高兴，换量车开吧...");
        myCar = CarFactory.getCar("bmw");
        logger.debug("我的车：" + myCar.getName());
        myCar.drive();

        logger.debug("今天要爬山，换量越野车吧...");
        myCar = CarFactory.getCar("landRover");
        logger.debug("我的车：" + myCar.getName());
        myCar.drive();
    }
}
