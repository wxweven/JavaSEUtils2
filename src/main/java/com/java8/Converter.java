package com.java8;

/**
 * interface
 * Created by wxweven on 2017/3/18.
 */
@FunctionalInterface
public interface Converter<P, R> {
    R convert(P param);
}
