package com.wxweven.javase;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.wxweven.data.User;

/**
 * @author wxweven
 * @version 1.0
 * @date 2016年4月19日
 * @email wxweven@qq.com
 * @website http://wxweven.com/
 * @Copyright: Copyright (c) wxweven 2016
 * @Description: beansort测试
 */
public class BeanSortTest {
    private static Logger logger = Logger.getLogger(BeanSortTest.class);

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

}
