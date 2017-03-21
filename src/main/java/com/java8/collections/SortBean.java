package com.java8.collections;

/**
 * SortBean
 * Created by wxweven on 2017/3/21.
 */
public class SortBean {
    private Integer id;
    private String  name;
    private int     age;

    public SortBean() {
    }

    public SortBean(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SortBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
