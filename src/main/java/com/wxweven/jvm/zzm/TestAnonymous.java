package com.wxweven.jvm.zzm;

public class TestAnonymous {
    public static void main(String[] args) {
        new TestAnonymous().test(10, 20);
    }

    public void test(int a, int c) {
        int b = a + 10;
        c = a + 10;
        new Thread() {
            public void run() {
                System.out.println(a);
            }
        }.start();
    }
}