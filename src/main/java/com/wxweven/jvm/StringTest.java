package com.wxweven.jvm;

import org.junit.Test;

/**
 * @author wxweven
 * @date 2017/9/2
 */
public class StringTest {

    @Test
    public void testIntern() {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);
    }
}
