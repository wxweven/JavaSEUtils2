package com.wxweven.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 二分查找算法
 *
 * @author wxweven
 * @date 2017年3月7日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2017
 */
public class BinarySearch {
    private static Logger logger = LoggerFactory.getLogger(BinarySearch.class);

    public static void main(String[] args) {
        int[] arr = { 10, 55, 69, 78, 85 };
        int x = 101;
        int pos = binarySearch(arr, 0, arr.length - 1, x);

        String res = pos == -1 ? "未找到元素": "找到元素，下标为" + pos;
        logger.debug(res);
    }

    /**
     * 二分查找
     *
     * @param arr
     *            数组
     * @param start
     *            查找开始范围
     * @param end
     *            查找结束范围
     * @param value
     *            被查找的值
     * @return 返回被查找值在数组中的下标
     */
    public static int binarySearch(int[] arr, int start, int end, int value) {
        while (start <= end) {
            int middle = (start + end) / 2;
            int temp = arr[middle];

            if (value < temp) {
                end = middle - 1;
            } else if (value > temp) {
                start = middle + 1;
            } else {
                return middle;
            }
        }

        return -1;
    }
}
