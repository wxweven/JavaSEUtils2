package com.wxweven.javase;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.wxweven.beanutils.MyBeanUtils;
import com.wxweven.data.User;
import com.wxweven.math.Arithmetic;

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
    public void testBeanSort() throws Exception {
        List<User> users = User.getUserList(2);
        User user = new User();
        user.setId(10);
        user.setMarks(50);
        users.add(user);

        users = MyBeanUtils.sort(users, "name", "desc");

        logger.debug(users);
    }

    @Test
    public void testArithmetic() throws Exception {
        double val = Arithmetic.div(10.9, 0.0000, 4);
        double val2 = Arithmetic.round(19.08808, 4);
        logger.debug(val);
        logger.debug(val2);
    }

}
