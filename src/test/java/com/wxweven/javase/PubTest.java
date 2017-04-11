package com.wxweven.javase;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * beansort测试
 *
 * @author wxweven
 * @date 2017年3月7日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2017
 */
public class PubTest {
    private static Logger logger = Logger.getLogger(PubTest.class);
    private List list = new ArrayList();

    @Test
    public void testArithmetic() throws Exception {
//        double val = Arithmetic.div(10.9, 0.0000, 4);
//        double val2 = Arithmetic.round(19.08808, 4);
//        logger.debug(val);
//        logger.debug(val2);

        list.get(0);

        Long l1 = 10L;
        Integer l2 = null;

        Map<String, Boolean> map = new HashMap<>();
        boolean b = (map!=null ? map.get("test") : false);

        boolean res2  = Objects.equals(l1, l2);

        System.out.println(res2);



    }

}
