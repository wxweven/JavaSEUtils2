package com.java8.streams;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author wxweven
 * @date 2017/9/15
 */
public class StreamTest {
    @Test
    public void test_listDistinct() {
        List<String> oldList = Arrays.asList("a", "b", "a", "c");
        List<String> newList = oldList.stream().distinct().collect(Collectors.toList());
        System.out.println("oldList: " + oldList);
        System.out.println("newList: " + newList);
    }

    @Test
    public void test_listGroup() {
        List<Person> persons = listPersons();

        // 按照city来分组

        Map<String, Person> personMap = persons.stream()
                                               .collect(Collectors.toMap(
                                                       Person::getCity,
                                                       Function.identity(),
                                                       (v1, v2) -> v2)
                                               );
        System.out.println("按城市分组后：" + personMap);
    }

    private List<Person> listPersons() {
        List<Person> persons = new ArrayList<>();
        Person p = new Person("wxweven", "程序员", "huangshi");
        persons.add(p);

        p = new Person("even", "教师", "beijing");
        persons.add(p);

        p = new Person("meixiaoxi", "足球员", "basailuona");
        persons.add(p);

        p = new Person("meidaxi", "教师", "hangzhou");
        persons.add(p);

        return persons;
    }
}
