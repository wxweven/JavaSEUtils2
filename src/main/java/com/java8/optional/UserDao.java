package com.java8.optional;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * UserDao
 * Created by wxweven
 * on 2017/5/19.
 */
public class UserDao {
    private Map<Integer, User> users = new HashMap<>(100);

    public void initUsers() {
        IntStream.range(1, 100)
                 .forEach(id -> users.put(id, new User(id, "name" + id, "address" + id)));
    }

    public User getById(int id) {
        initUsers();
        return users.get(id);
    }
}
