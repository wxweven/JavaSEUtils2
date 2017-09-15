package com.wxweven.jvm.zzm;

import java.io.IOException;

public class BTraceTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        BTraceTest test = new BTraceTest();
        for (int i = 0; i < 15; i++) {
            Thread.sleep(2000);
            int a = (int) Math.round(Math.random() * 1000);
            int b = (int) Math.round(Math.random() * 1000);
            System.out.println(test.add(a, b));
        }
    }

    public int add(int a, int b) {
        return a + b;
    }
}

