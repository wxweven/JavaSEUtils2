package com.wxweven.algorithm.sort;

import java.util.Random;
import java.util.Scanner;

/**
 * 快排demo
 *
 * @author wxweven
 * @date 2017年2月23日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2017
 */
public class QuickSortDemo {

    private static final int ARR_LEN = 10;
    private static int[]     arr     = new int[ARR_LEN];

    /**
     * 快速排序
     *
     * @param left
     * @param right
     */
    public static void quickSort(int left, int right) {
        if (left > right) {
            return;
        }

        int low = left;
        int high = right;
        int record = arr[left];

        while (low < high) {
            while (arr[high] >= record && low < high) {
                high--;
            }
            while (arr[low] <= record && low < high) {
                low++;
            }

            if (low < high) {
                int tmp = arr[low];
                arr[low] = arr[high];
                arr[high] = tmp;
            }
        }

        arr[left] = arr[low];
        arr[low] = record;

        quickSort(left, low - 1);
        quickSort(low + 1, right);
    }

    /**
     * 初始化数组
     */
    private static void init() {
        for (int i = 0; i < ARR_LEN; i++) {
            arr[i] = new Random().nextInt(100);
        }

    }

    /**
     * 打印数组
     */
    private static void printArr() {
        for (int i=0;i<ARR_LEN;i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        QuickSortDemo.init();
        // 从标准输入中初始化数组

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < ARR_LEN; i++) {
            arr[i] = scanner.nextInt();
        }

        scanner.close();

        System.out.println("before sort:");
        printArr();

        quickSort(0, ARR_LEN - 1);

        System.out.println("after sort:");
        printArr();
    }
}
