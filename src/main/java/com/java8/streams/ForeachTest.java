package com.java8.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wxweven on 2017/5/16.
 */
public class ForeachTest {
    @Test
    public void testForeach() {
        List<String> names = Arrays.asList("Wxw", "Even", "wxw");

        System.out.println("before:" + names);

        names.forEach(String::toLowerCase);

        System.out.println("after foreach:" + names);

        List<String> names2 = names.stream()
                                   .map(String::toLowerCase)
//                                   .distinct()
                                   .collect(Collectors.toList());

        System.out.println(Collections.emptyList().size());

        System.out.println("after stream:" + names2);

    }
}
