package com.wxweven.string;

public class StringTest {

	public static void main(String[] args) {
		final String a = "hello";
		String b = "hello2";
		String e = "hello" + 2;
		String c = a + 2;
		final String d = a + 2;
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);

		System.out.println(b == c);
		System.out.println(d == b);

		System.out.println(b ==e);

	}
}
