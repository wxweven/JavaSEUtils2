package com.java8.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * Optional 正确使用姿势
 * Created by wxweven
 * on 2017/5/19.
 */
public class OptionalTest {
    private static final String UNKNOWN_NAME = "未知用户名";

    @Test
    public void testUser() {
        UserService userService = new UserService();
        Optional<User> user = userService.getUserById(445);
        String userName = user.map(User::getAddress)
                              .map(String::toUpperCase)
                              .orElse(UNKNOWN_NAME);

        System.out.println(userName);

    }
}
