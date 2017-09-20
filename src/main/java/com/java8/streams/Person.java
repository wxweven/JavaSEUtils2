package com.java8.streams;

/**
 * @author wxweven
 * @date 2017/9/15
 */
public class Person {
    private String name;
    private String job;
    private String city;

    public Person(String name, String job, String city) {
        this.name = name;
        this.job = job;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
