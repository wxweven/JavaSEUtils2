package com.wxweven.disruptors;

/**
 * Created with IntelliJ IDEA. Project: test-jar Author: Kevin Date:
 * 16/5/23 Time: 下午3:47
 */
public class TestObject {

    public long value;

    public TestObject() {
    };

    public TestObject(long value) {
        this.value = value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

}