package com.wxweven.javase;

import com.wxweven.math.Arithmetic;
import org.apache.log4j.Logger;
import org.junit.Test;

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

    @Test
    public void testArithmetic() throws Exception {
        double val = Arithmetic.div(10.9, 0.0000, 4);
        double val2 = Arithmetic.round(19.08808, 4);
        logger.debug(val);
        logger.debug(val2);
    }

}
