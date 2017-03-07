package com.wxweven.beanutils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BeanUtils 测试类
 *
 * @author wxweven
 * @date 2017年3月7日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog http://wxweven.win
 * @Copyright: Copyright (c) wxweven 2009 - 2017
 */
public class BeanUtilsTest {
    private static Logger logger = LoggerFactory.getLogger(BeanUtilsTest.class);

    public static void main(String[] args) throws Exception {
        PointBean pb = new PointBean(5, 2);

        logger.debug("before:" + pb);

        /**
         * 利用BeanUtils来设置字段的值
         */
        BeanUtils.setProperty(pb, "x", 12);
        BeanUtils.setProperty(pb, "y", 34);
        logger.debug("after:" + pb);

        /**
         * 利用BeanUtils来获取字段的值
         */
        BeanUtils.getProperty(pb, "x");
        BeanUtils.getProperty(pb, "y");

        /**
         * pb这个bean的Map形式，其中的key包括属性，和 "class"
         */
        Map<String, String> pbMap = BeanUtils.describe(pb);

        /**
         * 利用BeanUtils来更改Map
         */
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "even");
        map.put("age", 25);
        logger.debug("Map befor:"+map);
        BeanUtils.setProperty(map, "name", "wxweven");
        logger.debug("Map after:"+map);

        logger.debug("pb.x->"+pbMap.get("x"));
        logger.debug("pb.y->"+pbMap.get("y"));
    }
}
