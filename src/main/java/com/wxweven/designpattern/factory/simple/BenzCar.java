package com.wxweven.designpattern.factory.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 奔驰车
 *
 * @author wxweven
 * @date 2017年3月8日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2017
 */
public class BenzCar implements ICar {
    private static Logger logger = LoggerFactory.getLogger(BenzCar.class);


    @Override
    public String getName() {
        return "极速奔驰";
    }

    @Override
    public void drive() {
        logger.debug("奔驰跑的飞快...");
    }

}
