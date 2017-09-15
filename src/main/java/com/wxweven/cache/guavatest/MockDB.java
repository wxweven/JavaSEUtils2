package com.wxweven.cache.guavatest;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟数据库
 *
 * @author wxweven
 * @date 2017/7/13
 */
public class MockDB {
    private static Map<String, String> mockPersistence = new HashMap<String, String>() {{
        this.put("github", "codedrinker");
    }};

    public static String loadFromPersistence(String key) {
        System.out.println("load key from persistence : " + key);
        return mockPersistence.get(key);
    }
}