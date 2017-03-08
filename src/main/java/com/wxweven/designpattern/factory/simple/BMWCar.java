package com.wxweven.designpattern.factory.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 宝马车
 *
 * @author wxweven
 * @date 2017年3月8日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2017
 */
public class BMWCar implements ICar {
    private static Logger logger = LoggerFactory.getLogger(BMWCar.class);

    @Override
    public String getName() {
        return "尊贵宝马";
    }

    @Override
    public void drive() {
        logger.debug("宝马才是尊贵的象征...");
    }

}
