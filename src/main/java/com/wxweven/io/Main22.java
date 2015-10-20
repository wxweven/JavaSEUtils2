package com.wxweven.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main22 {

	private static String input1;
	private static String input2;
	private static int Num;

	public static void main(String[] args) throws IOException {
		System.out.println("input a num:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input1 = br.readLine();
		Num = Integer.parseInt(input1);

		while (Num <= 0 || Num > 1000 || (Num % 2 != 0)) {
			System.out.println("error num!! input again:");
			input1 = br.readLine();
			Num = Integer.parseInt(input1);
		}

		System.out.println("input a num string:");
		input2 = br.readLine();

		String[] numStrs = input2.split(",");

		String[] hexNums = new String[Num];
		int[] shibu = new int[Num];
		int[] xubu = new int[Num];

		for (int i = 0; i < Num; i++) {
			hexNums[i] = Integer.toHexString(Integer.parseInt(numStrs[i]));
			System.out.println("实数+虚数："+hexNums[i]);
			shibu[i] = converRes(hexStrs2Shibu(hexNums[i]));
			xubu[i] = converRes(hexStrs2Xubu(hexNums[i]));
			
			System.out.println(shibu[i] + "--"+xubu[i]);
			
		}
		
		int sumShibu = 0;
		int sumXubu = 0;
		
		for(int i=0; i<Num/2; i++) {
			sumShibu = converRes(sumShibu);
			sumShibu += converRes(converRes((shibu[2*i] * shibu[2*i+1])) - converRes((xubu[2*i] * xubu[2*i+1])));
			
			sumXubu = converRes(sumXubu);
			sumXubu += converRes(converRes((shibu[2*i] * xubu[2*i+1])) + converRes((xubu[2*i] * shibu[2*i+1])));
			System.out.println("shibu乘积：" + sumShibu);
			System.out.println("xubu乘积：" + sumXubu);
		}
		
		sumShibu = converRes(sumShibu)/Num;
		sumXubu = converRes(sumXubu)/Num;
		System.out.println(sumShibu);
		System.out.println(sumXubu);
		
		String xubuStr = xubu2hexStrs(sumXubu);
		int fushu = 0;
		if(sumShibu < 0) {
			fushu = 1;
			sumShibu = -sumShibu;
		}
			
		
		
		String finalRes = Integer.toHexString(sumShibu) + xubuStr;
		System.out.println(finalRes);
		System.out.println(Integer.parseInt(finalRes,16));
		
//		System.out.println("++++++++++"+Float.toHexString((float) 7.5));;
		
		
//		4：262149,393223,524297,655371
//		2：32768,524295
	}

	private static int hexStrs2Shibu(String str) {
		if (str.length() <= 4) {
			return 0;
		} else {
			String tmp = str.substring(0, str.length() - 4);
			return Integer.parseInt(tmp, 16);
		}
	}

	private static int hexStrs2Xubu(String str) {
		if (str.length() <= 4) {
			return Integer.parseInt(str, 16);
		} else {
			String tmp = str.substring(str.length() - 4);
			return Integer.parseInt(tmp, 16);
		}
	}
	
	private static String xubu2hexStrs(int num) {
		String tmp = Integer.toHexString(num);
		String prefix = "";
		for(int i=0; i<4-tmp.length(); i++ ) {
			prefix += "0";
		} 
		
		return prefix + tmp;
	}
	
	private static int converRes(int res) {
		if(res > 32767)
			return 32767;
		
		if(res < -32768)
			return -32768;
		
		return res;
	}

}
