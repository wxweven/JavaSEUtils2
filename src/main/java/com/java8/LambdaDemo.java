package com.java8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java Lambda Demo
 * Created by wxweven on 2017/3/18.
 */
public class LambdaDemo {
    public static void main(String[] args){
        int res;
        Converter<String, Integer> converter = a -> {
            System.out.println("函数内部...");
            return Integer.valueOf(a);
        };

        res = converter.convert("456");
        System.out.println("value:" + res);

        Converter<String, Integer> converter2 = Integer::valueOf;
        res = converter2.convert("12");
        System.out.println("value:" + res);

        List<String> l = Stream.of("a","b","c","b")
                               .distinct()
                               .collect(Collectors.toList());
        System.out.println(l); //[a, b, c]

    }
}
