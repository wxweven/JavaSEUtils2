package com.wxweven.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Guava Cache 测试类
 *
 * @author wxweven
 * @date 2017/7/13
 */
public class GuavaCacheTest {
    private static CacheLoader<String, String> loader = new CacheLoader<String, String>() {
        @Override
        public String load(String key) {
            System.out.printf("loading cache：%s...\n", key);
            return key.toUpperCase();
        }
    };

    @Test
    public void testMaximumSize() {
        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder()
                            .maximumSize(3) // 限制Cache的最大容量为3
                            .removalListener(notification -> System.out.printf("key：%s removed \n", notification.getKey()))
                            .build(loader);

        System.out.printf("缓存大小key：%d\n", cache.size());
        System.out.println("---------------------");

        System.out.printf("第一次获取缓存key：%s，value：%s\n", "first", cache.getUnchecked("first"));
        System.out.printf("第二次获取缓存key：%s，value：%s\n", "first", cache.getUnchecked("first"));
        System.out.println("---------------------");

        System.out.printf("第一次获取缓存key：%s，value：%s\n", "second", cache.getUnchecked("second"));
        System.out.println("---------------------");

        System.out.printf("第一次获取缓存key：%s，value：%s\n", "third", cache.getUnchecked("third"));
        System.out.println("---------------------");

        System.out.printf("第一次获取缓存key：%s，value：%s\n", "forth", cache.getUnchecked("forth"));
        System.out.println("---------------------");

        System.out.printf("缓存大小key：%d\n", cache.size());
        System.out.println("---------------------");

        System.out.printf("再次获取缓存key：%s，value：%s\n", "first", cache.getIfPresent("first"));
    }

    @Test
    public void testMaximumWeight() {
        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder()
                            .maximumWeight(11) // 限定缓存的最大“重量为16”
                            // 自定义缓存的“重量”：字符串的长度
                            .weigher((Weigher<String, String>) (key, value) -> value.length())
                            .removalListener(notification -> System.out.printf("key：%s removed \n", notification.getKey()))
                            .build(loader);

        System.out.printf("缓存大小：%d\n", cache.size());
        System.out.println("---------------------");

        System.out.printf("第一次获取缓存key：%s，value：%s\n", "first", cache.getUnchecked("first"));
        System.out.printf("第二次获取缓存key：%s，value：%s\n", "first", cache.getUnchecked("first"));
        System.out.println("---------------------");

        System.out.printf("缓存大小：%d\n", cache.size());
        System.out.println("---------------------");

        System.out.printf("第一次获取缓存key：%s，value：%s\n", "second", cache.getUnchecked("second"));
        System.out.println("---------------------");

        System.out.printf("第一次获取缓存key：%s，value：%s\n", "third", cache.getUnchecked("third"));
        System.out.println("---------------------");

        System.out.printf("第一次获取缓存key：%s，value：%s\n", "forth", cache.getUnchecked("forth"));
        System.out.println("---------------------");

        System.out.printf("缓存大小：%d\n", cache.size());
        System.out.println("---------------------");

        System.out.printf("再次获取缓存key：%s，value：%s\n", "first", cache.getIfPresent("first"));
    }

    @Test
    public void testExpireAfterAccess() throws InterruptedException {
        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder()
                            // 指定缓存 2 秒内没有被访问（读/写）就过期
                            .expireAfterAccess(2, TimeUnit.SECONDS)
                            .build(loader);

        System.out.printf("缓存大小：%d\n", cache.size());
        System.out.println("---------------------");

        System.out.printf("第一次获取缓存key：%s，value：%s\n", "first", cache.getUnchecked("hello"));
        System.out.println("---------------------");

        Thread.sleep(3000);

        System.out.printf("第二次获取缓存key：%s，value：%s\n", "first", cache.getUnchecked("first"));
        System.out.println("---------------------");

        Thread.sleep(3000);

        System.out.printf("第一次获取缓存key：%s，value：%s\n", "first", cache.getUnchecked("test"));
        System.out.println("---------------------");

        System.out.printf("缓存大小：%d\n", cache.size());
    }

    @Test
    public void testExpireAfterWrite() throws InterruptedException {
        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder()
                            // 指定缓存 2 秒内没有写就过期
                            .expireAfterWrite(2, TimeUnit.SECONDS)
                            .build(loader);

        System.out.printf("缓存大小：%d\n", cache.size());
        System.out.println("---------------------");

        System.out.printf("第一次获取缓存key：%s，value：%s\n", "first", cache.getUnchecked("first"));
        System.out.println("---------------------");

        Thread.sleep(1500);

        System.out.printf("第二次获取缓存key：%s，value：%s\n", "first", cache.getUnchecked("first"));
        System.out.println("---------------------");

        Thread.sleep(1500);

        System.out.printf("缓存大小：%d\n", cache.size());
        System.out.println("---------------------");

        //cache.put("first", "first-new");
        System.out.printf("第三次获取缓存key：%s，value：%s\n", "first", cache.getUnchecked("first"));
        System.out.println("---------------------");

        Thread.sleep(1000);

        System.out.printf("第一次获取缓存key：%s，value：%s\n", "first", cache.getUnchecked("test"));
        System.out.println("---------------------");

        System.out.printf("缓存大小：%d\n", cache.size());
    }
}
