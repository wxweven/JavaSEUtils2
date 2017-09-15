package com.wxweven.cache.guavatest;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * CacheLoader demo
 *
 * @author wxweven
 * @date 2017/7/13
 */
public class GuavaLoadingCache {

    public static void main(String[] args) {
        LoadingCache<String, Optional<String>> loadingCache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .removalListener(notification -> System.out.println("cache expired, remove key : " + notification.getKey()))
                .build(new CacheLoader<String, Optional<String>>() {
                    @Override
                    public Optional<String> load(String key) throws Exception {
                        return Optional.ofNullable(MockDB.loadFromPersistence(key));
                    }
                });

        try {
            System.out.println("load from cache once : " + loadingCache.get("github").orElse("Nothing"));
            Thread.sleep(2000);
            System.out.println("load from cache twice : " + loadingCache.get("github").orElse("Nothing"));
            Thread.sleep(2000);
            System.out.println("load from cache third : " + loadingCache.get("github").orElse("Nothing"));
            Thread.sleep(2000);
            System.out.println("load not exist key from cache : " + loadingCache.get("email").orElse("Nothing"));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}