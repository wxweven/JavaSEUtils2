package com.java8.optional;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Optional 正确使用姿势
 * Created by wxweven
 * on 2017/5/19.
 */
public class OptionalTest {
    public static final int UNKNOWN_ID = 445;
    public static final int KNOWN_ID = 10;
    private static final String UNKNOWN_NAME = "未知用户名";

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionalTest.class);

    @Test
    public void testUserUN() {
        UserService userService = new UserService();
        Optional<User> user = userService.getUserById(UNKNOWN_ID);
        String userName = user.map(User::getName)
                              .map(String::toUpperCase)
                              .orElse(UNKNOWN_NAME);

        LOGGER.info("用户名：{}", userName);
    }

    @Test
    public void testUser() {
        UserService userService = new UserService();
        Optional<User> user = userService.getUserById(KNOWN_ID);
        String userName = user.map(User::getName)
                              .map(String::toUpperCase)
                              .orElse(UNKNOWN_NAME);

        LOGGER.info("用户名：{}", userName);
    }
}
