package com.wxweven.math;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数值运算工具类，提供精确的浮点数运算，包括加减乘除和四舍五入
 * @author wxweven
 * @date 2017年3月7日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog http://wxweven.win
 * @Copyright: Copyright (c) wxweven 2009 - 2017
 */
public class Arithmetic {
    private static Logger    logger        = LoggerFactory.getLogger(Arithmetic.class);

    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 4;

    // 这个类不能实例化
    private Arithmetic() {
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @return 两个参数的积
     */
    public static double add(Number v1, Number v2) {
        return add(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的加法运算。由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @param scale
     *            表示表示需要精确到小数点以后几位。
     * @return 两个参数的和
     */
    public static double add(Number v1, Number v2, int scale) {

        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero!");
        }

        if (v1 == null || v2 == null) {
            logger.error("Data error! Either v1 or v2 is null!");
            return 0.0000;
        }

        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return Arithmetic.round(b1.add(b2).doubleValue(), scale);
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @return 两个参数的积
     */
    public static double sub(Number v1, Number v2) {
        return sub(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的减法运算。由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @param scale
     *            表示表示需要精确到小数点以后几位。
     * @return 两个参数的差
     */
    public static double sub(Number v1, Number v2, int scale) {

        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero!");
        }

        if (v1 == null || v2 == null) {
            logger.error("Data error! Either v1 or v2 is null!");
            return 0.0000;
        }

        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return Arithmetic.round(b1.subtract(b2).doubleValue(), scale);
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @return 两个参数的积
     */
    public static double mul(Number v1, Number v2) {
        return mul(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的乘法运算。由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @param scale
     *            表示表示需要精确到小数点以后几位。
     * @return 两个参数的积
     */
    public static double mul(Number v1, Number v2, int scale) {

        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero!");
        }

        if (v1 == null || v2 == null) {
            logger.error("Data error! Either v1 or v2 is null!");
            return 0.0000;
        }

        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return Arithmetic.round(b1.multiply(b2).doubleValue(), scale);
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后4位，以后的数字四舍五入。
     *
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @return 两个参数的商
     */
    public static double div(Number v1, Number v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(Number v1, Number v2, int scale) {

        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero!");
        }

        if (v1 == null || v2 == null) {
            logger.error("Data error! Either v1 or v2 is null!");
            return 0.0000;
        }

        BigDecimal zero = new BigDecimal(0.0);
        BigDecimal b2 = new BigDecimal(v2.toString());

        if (zero.compareTo(b2) == 0) {
            logger.error("Data error! The value of divisor(v2) is zero!");
            return 0.0000;
        }

        BigDecimal b1 = new BigDecimal(v1.toString());
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v
     *            需要四舍五入的数字
     * @param scale
     *            小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(Number v, int scale) {

        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        if (v == null) {
            logger.error("Data error! The param is null!");
            return 0.0000;
        }

        BigDecimal b = new BigDecimal(v.toString());
        BigDecimal one = new BigDecimal(1);
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}