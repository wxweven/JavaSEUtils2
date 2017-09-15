package com.wxweven.cache.guavatest;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * CallableCache demo
 *
 * @author wxweven
 * @date 2017/7/13
 */
public class GuavaCallableCache {

    public static void main(String[] args) {
        final String key = "github";
        Cache<String, Optional<String>> cache = CacheBuilder.newBuilder()
                                                            .expireAfterWrite(3, TimeUnit.SECONDS)
                                                            .removalListener(notification ->
                                                                    System.out.println("cache expired, remove key : " + notification.getKey())
                                                            )
                                                            .build();
        try {
            Optional<String> optional;

            String cacheSting1 = cache.get(key, () -> Optional.ofNullable(MockDB.loadFromPersistence(key))).orElse("empty");
            System.out.println("load from cache once : " + cacheSting1);
            Thread.sleep(2000);


            System.out.println("load from cache twice : " + cache.get(key, () -> Optional.ofNullable(MockDB.loadFromPersistence(key))).orElse(null));
            Thread.sleep(2000);


            System.out.println("load from cache third : " + cache.get(key, () -> Optional.ofNullable(MockDB.loadFromPersistence(key))).orElse(null));
            Thread.sleep(2000);

            final String nullKey = "email";
            optional = cache.get(nullKey, () -> Optional.ofNullable(MockDB.loadFromPersistence(nullKey)));
            System.out.println("load not exist key from cache : " + optional.orElse(null));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}