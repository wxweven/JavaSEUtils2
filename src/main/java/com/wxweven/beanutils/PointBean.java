package com.wxweven.beanutils;

/**
 * 测试用的普通Bean
 *
 * @author wxweven
 * @date 2017年3月7日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog http://wxweven.win
 * @Copyright: Copyright (c) wxweven 2009 - 2017
 */
public class PointBean {

    private int x;// 横坐标
    private int y;// 纵坐标

    public PointBean(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "PointBean [x=" + x + ", y=" + y + "]";
    }
}
