package com.java8.collections;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * java8 collections sort test
 * Created by wxweven on 2017/3/21.
 */
public class SortTest {
    private static Logger  LOGGER = LoggerFactory.getLogger(SortTest.class);
    private List<SortBean> beans  = new ArrayList<>();

    @Before
    public void setup() {
        IntStream.range(1, 3)
                 .forEach(i -> beans.add(new SortBean(i, "name" + i, i + 10)));
    }


    @Test
    public void testSortProperty() {
        LOGGER.debug("before sort, beans:{}", beans);
        beans.sort(Comparator.comparing(SortBean::getName)
                             .reversed() // 按name降序排序
                             .thenComparing(SortBean::getAge)); // 再按age升序排序

        LOGGER.debug("after sort, beans:{}", beans);
    }
}
