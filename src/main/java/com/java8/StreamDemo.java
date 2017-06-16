package com.java8;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream测试 Created by wxweven on 2017/3/19.
 */
public class StreamDemo {
    private static Logger LOGGER = LoggerFactory.getLogger(StreamDemo.class);

    public static void main(String[] args) {
        // distinct
        List<String> l = Stream.of("a","b","c","b")
                               .distinct()
                               .collect(Collectors.toList());
        LOGGER.debug(l.toString()); //[a, b, c]

        // filter
        List<Integer> integerList = IntStream.range(1, 10)
                                             .filter(i -> i % 2 == 0)
                                             .boxed()
                                             .collect(Collectors.toList());
        System.out.println(integerList); // [2, 4, 6, 8]


        long id = -199999;
        System.out.println(id < NumberUtils.LONG_ZERO);


    }
}
