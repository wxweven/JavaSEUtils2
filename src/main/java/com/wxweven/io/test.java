package com.wxweven.io;

public class test {

	public static void main(String[] args) {
		
		int[] i = {1,2,3,4,5};
		for(int i2 : i) {
			System.out.println(i2);
		}
		System.out.println(str2num("fdjskljf8980io"));
	}

	public static int str2num(String str) {
		str = str.trim();
		String str2 = "";
		if (str != null && !"".equals(str)) {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
					str2 += str.charAt(i);
				}
			}
		}
		
		if(str2.length() != 0)
			return Integer.parseInt(str2);
		
		return 0;
	}
}
