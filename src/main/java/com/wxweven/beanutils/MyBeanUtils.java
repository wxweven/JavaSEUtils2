package com.wxweven.beanutils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;

/**
 * 封装BeanUtils功能，包括按照属性排序等
 * @author wxweven
 * @date 2017年3月7日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog http://wxweven.win
 * @Copyright: Copyright (c) wxweven 2009 - 2017
 */
public class MyBeanUtils {

    /**
     * 将javabean list按照指定的字段排序
     *
     * @param list
     *            封装javabean的list
     * @param orderField
     *            排序字段
     * @param orderDirection
     *            排序方向
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> sort(List<T> list, String orderField, String orderDirection) {
        // 创建针对某个属性的升序比较
        Comparator<T> comparator = new BeanComparator<T>(orderField, new NullComparator(false));

        if ("desc".equalsIgnoreCase(orderDirection)) {
            // desc :降序排序
            comparator = new ReverseComparator(comparator);
        }

        // 开始排序
        Collections.sort(list, comparator);

        return list;
    }

    /**
     * 将javabean list按照指定的多个字段排序
     *
     * @param list
     *            封装javabean的list
     * @param orderMap
     *            封装排序字段以及方向的map排序字段
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> sortMutil(List<T> list, Map<String, Boolean> orderMap) {
        // 创建多属性排序链
        ComparatorChain cc = new ComparatorChain();

        for (String key : orderMap.keySet()) {
            // 第一个参数是指定排序的属性，第二个参数指定降序升序,true:降序，false:升序
            cc.addComparator(new BeanComparator<T>(key), orderMap.get(key));
        }

        Collections.sort(list, cc);

        return list;
    }

    @SuppressWarnings("rawtypes")
    public static List<String> getListColumn(List list, String column) {
        List<String> result = new ArrayList<String>();
        try {
            for (Object object : list) {
                String property = BeanUtils.getProperty(object, column);
                result.add(property);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Double> str2Double(List<String> strList) {
        List<Double> res = new ArrayList<Double>();

        for (String str : strList) {
            Double val = Double.valueOf(str);
            res.add(val);
        }

        return res;
    }
}