package com.wxweven.beanutils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtilsTest {
    public static void main(String[] args) throws Exception {
        PointBean pb = new PointBean(5, 2);

        /**
         * 利用BeanUtils来设置字段的值
         */
        BeanUtils.setProperty(pb, "x", 12);
        BeanUtils.setProperty(pb, "y", 34);

        /**
         * 利用BeanUtils来获取字段的值
         */
        String propertyX = BeanUtils.getProperty(pb, "x");
        String propertyY = BeanUtils.getProperty(pb, "y");

        /**
         * pb这个bean的Map形式 key值包括属性，和 "class"
         */
        Map<String, String> pbMap = BeanUtils.describe(pb);

        /**
         * 利用BeanUtils来更改Map
         */
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "even");
        map.put("age", 25);
        BeanUtils.setProperty(map, "name", "wxweven");
        System.out.println(map);

        System.out.println(pbMap.get("x"));
        System.out.println(pbMap.get("y"));
    }
}
