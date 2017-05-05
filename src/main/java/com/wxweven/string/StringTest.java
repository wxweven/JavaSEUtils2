package com.wxweven.string;

public class StringTest {

    public static void main(String[] args) {
         String a = "hello";
        String b = "hello2";
        String e = "hello" + 2;
        String c = a + 2;
        final String d = a + 2;
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);

        System.out.println(b == c);
        System.out.println(d == b);

        System.out.println(b == e);

//        Integer age = Integer.valueOf(10);
        Integer age = null;
        String str = null;




//        System.out.println(String.valueOf(age));
//        System.out.println(Integer.valueOf(str));

    }
}
