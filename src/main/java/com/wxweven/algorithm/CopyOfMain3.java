package com.wxweven.algorithm;

import java.util.Scanner;

import org.junit.Test;

public class CopyOfMain3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.next();
		sc.close();
		int len = line.length();

		if (len == 1) {
			System.out.println("YES");
			return;
		}

		String revStr = inverseStr(line.toCharArray());

		if (revStr.equals(line)) {
			System.out.println("YES");
			return;
		}

		// =======1.在str首部加个字符串
		if (line.substring(0, len - 1).equals(revStr.substring(1))) {
			System.out.println("YES");
			return;
		}

		// =======2.在str尾部加个字符串
		if (line.substring(1).equals(revStr.substring(0, len - 1))) {
			System.out.println("YES");
			return;
		}

		System.out.println("NO");

	}

	private static String inverseStr(char[] str) {
		int len = str.length;
		char[] revStr = new char[len];
		for (int i = 0; i < len; i++) {
			revStr[i] = str[len - i - 1];
		}

		return new String(revStr);
	}

	@Test
	public void testName() throws Exception {
		Scanner sc = new Scanner(System.in);
		String line = sc.next();
		sc.close();

		/**
		 * 判断给定的字符串是不是回文
		 */
		int left = 0;
		int right = line.length() - 1;
		while (left < right) {
			if (line.charAt(left) == line.charAt(right)) {
				left++;
				right--;
			} else {
				System.out.println("No");
				return;
			}
		}

		System.out.println("Yes");
	}

}
