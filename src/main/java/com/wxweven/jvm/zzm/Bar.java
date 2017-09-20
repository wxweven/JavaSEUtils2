package com.wxweven.jvm.zzm;

import org.junit.Test;

public class Bar {
    static int b = 2;
    int a = 1;

    public static void main(String[] args) {
        new Bar().sum(3);
    }

    public int sum(int c) {
        return a + b + c;
    }

    @Test
    public void test() {
        boolean b = !(me());
        System.out.println(b);
    }

    private boolean me() {
        return false;
    }
}

