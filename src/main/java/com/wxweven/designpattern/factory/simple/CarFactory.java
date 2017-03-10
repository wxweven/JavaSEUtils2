package com.wxweven.designpattern.factory.simple;

/**
 * 简单工厂模式
 *
 * @author wxweven
 * @date 2017年3月8日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2017
 */
public class CarFactory {
    private static ICar car = null;

    public static ICar getCar(CarEnum carEnum) {
        switch (carEnum) {
        case BENZ:
            car = new BenzCar();
            break;
        case BWM:
            car = new BMWCar();
            break;
        case LANDROVER:
            car = new LandRoverCar();
            break;
        default:
            break;
        }

        return car;
    }
}
