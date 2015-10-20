package com.wxweven.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main1 {
	
//	test input is :0x1a 0x2a 0xff 0x6a 0x4a 0x5a 0xff 0x6a 0x7a 0x6a 0xff 0x9a 0xad 0xaf 0xff 0xac
	public final static int N = 4;
	public static void main(String[] args) throws IOException {
		List<Integer> hexList = new ArrayList<Integer>();
		String[] res = new String[N];

		String input = "";//输入的字符串
		

		System.out.println("waiting for your input:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		input = reader.readLine();// 读取一行信息

		String[] hexStr = stringToHexArr(input.trim(), " ");//得到16进制字符串数组

		int count = 0;//用户计数
		for (int i=0; i< hexStr.length; i++) {
			if(hexStr[i].equalsIgnoreCase("ff"))//跳过0xff
				continue;
			
			hexList.add(Integer.valueOf(hexStr[i], 16));//将16进制字符串转化为整数
			
			/**
			 * 三个一组做异或运算，找回丢失的数字
			 */
			if ((i+1) % N == 0) {
				String tmp = Integer.toHexString(hexList.get(0) ^ hexList.get(1) ^ hexList.get(2));
				res[count] = "0x" + tmp;
				hexList.clear();
				count++;
			}
		}

		for (int i=0; i<N; i++) {
			System.out.print(res[i] + " ");
		}

	}

	/**
	 * 将输入的16进制字符串，转化为去除 "0x" 后的字符串数组
	 * @param str:需要被转化的字符串
	 * @param sp:字符串分隔符
	 * @return:去除 "0x" 后的字符串数组
	 */
	public static String[] stringToHexArr(String str, String sp) {
		String newStr = str.replaceAll("0x", "");
		String[] strs = newStr.split(sp);

		return strs;
	}
}
