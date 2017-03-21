package com.wxweven.algorithm;


import org.junit.Assert;
import org.junit.Test;

/**
 * KMP算法，感谢CSDN的July大神
 *
 * @author wxweven
 * @date 2015年9月15日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog http://wxweven.win
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class KMPSearchTest {

    private int[] next;

    @Test
    public void testKMP() throws Exception {
        String str = "acdbdes";
        String pStr = "bded";

        next = new int[pStr.length()];
        getNext(pStr.toCharArray());
        int index = kmpSearch(str.toCharArray(), pStr.toCharArray());
        System.out.println(index);

    }

    private int kmpSearch(char[] str, char[] pStr) {
        Assert.assertNotNull(str);
        Assert.assertNotNull(pStr);

        int i = 0;
        int j = 0;
        int oLen = str.length;
        int pLen = pStr.length;

        while (i < oLen && j < pLen) {
            if (j == -1 || str[i] == pStr[j]) {
                ++i;
                ++j;

            } else {
                j = next[j];
            }
        }

        if (j == pLen) {
            return i - j;
        }

        return -1;
    }

    private void getNext(char[] pStr) {
        Assert.assertNotNull(pStr);
        int pLen = pStr.length;

        int k = -1;
        int j = 0;
        next[0] = -1;

        while (j < pLen - 1) {
            if (k == -1 || pStr[j] == pStr[k]) {
                ++j;
                ++k;
                if (pStr[j] != pStr[k]) {
                    next[j] = k;
                } else {
                    next[j] = next[k];
                }
            } else {
                k = next[k];
            }
        }


    }
}
