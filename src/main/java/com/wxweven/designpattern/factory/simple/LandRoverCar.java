package com.wxweven.designpattern.factory.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 路虎车
 *
 * @author wxweven
 * @date 2017年3月8日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2017
 */
public class LandRoverCar implements ICar {
    private static Logger logger = LoggerFactory.getLogger(LandRoverCar.class);

    @Override
    public String getName() {
        return "越野路虎";
    }

    @Override
    public void drive() {
        logger.debug("要越野，选路虎...");
    }

}
