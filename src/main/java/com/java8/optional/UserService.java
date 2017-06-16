package com.java8.optional;

import java.util.Optional;

/**
 * Service服务类
 * Created by wxweven
 * on 2017/5/19.
 */
public class UserService {
    private UserDao userDao = new UserDao();

    public Optional<User> getUserById(Integer id) {
        User user = userDao.getById(id);
        return Optional.ofNullable(user);
    }
}
