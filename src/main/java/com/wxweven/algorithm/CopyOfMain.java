package com.wxweven.algorithm;

import java.util.Scanner;

/**
 * 编程题目
 * @author wxweven
 * @date 2016年9月26日
 * @version 1.0
 * @email wxweven@qq.com
 * @blog wxweven.com
 * @Copyright: Copyright (c) wxweven 2009 - 2016
 */
public class CopyOfMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String firstLine = sc.nextLine();
        int p = Integer.valueOf(firstLine.split(" ")[0]);// 篮筐个数
        int n = Integer.valueOf(firstLine.split(" ")[1]);// 篮球个数

        int[] bucket = new int[p];
        for (int i = 0; i < p; i++) {
            bucket[i] = 0;
        }

        int[] ballNumbers = new int[n];
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            ballNumbers[i] = num;
        }

        sc.close();

        int i = 0;
        for (; i < n; i++) {
            int index = ballNumbers[i] % p;
            if (bucket[index] == 1) {
                System.out.println(i + 1);
                return;
            }
            bucket[index] = 1;
        }

        System.out.println(-1);

    }
}