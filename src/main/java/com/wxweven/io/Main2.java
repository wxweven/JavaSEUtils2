package com.wxweven.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
	private static String[] UNITS = {"KB","MB","GB","TB","PB","EB"};
	
	public static void main(String[] args) throws IOException {
		String input;//初始的输入字符串
		String res;//最后的结果字符串
		int count = 0;//保存单位的下标
		//预定义的单位数组
		System.out.println("please input:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// 读取一行信息
		input = reader.readLine();
		double num = Double.parseDouble(input.split(" ")[0]);//得到数字部分
		String unit = input.split(" ")[1];//得到单位部分
		
		/**
		 * 考虑边界：1：大于等于1024EB，直接输出
		 * 		  2：小于1KB，直接输出
		 */
		if( (num >= 1024 && unit.equals("EB")) || 
				((num < 1) && unit.equals("KB")) ) {
			
			String numStr = num + "";
			String zs = numStr.split("\\.")[0];//得到整数部分
			String xs = numStr.split("\\.")[1];//得到小数部分
			
			if(Integer.parseInt(xs) == 0) {//小数部分为0，则只保留整数
				res = zs + unit;
			
			} else {
				res = num + unit;
			}
		} else {
			//记录当前单位的下标
			for(int i=0; i<UNITS.length; i++) {
				if(unit.equals(UNITS[i]))
					count = i;
			}
			
			if(num >= 1 && num < 1024) {
				res = betweenNums(num,unit);
			} else if(num >= 1024) {
				res = largerThanNum(num, unit, count);
			} else {
				res = littleThanNum(num, unit, count);
			}
		}
		System.out.println(res);
	}
	
	/**
	 * 数字在[1,1024)，保留三位小数，直接输出
	 * @param num
	 * @param unit
	 * @return
	 */
	public static String betweenNums(double num, String unit) {
		/**
		 * 利用BigDecimal类来保留3位小数
		 */
		/*BigDecimal bg = new BigDecimal(num);
        num = bg.setScale(3, BigDecimal.ROUND_FLOOR).doubleValue();*/
		
		/**
		 * 利用乘以1000，向下取整，然后在除以1000来保留3位小数
		 */
		num = Math.floor(num * 1000);
		num /= 1000;
		
		String numStr = num + "";
		int len = 0;
		String zs = numStr.split("\\.")[0];//得到整数部分
		String xs = numStr.split("\\.")[1];//得到小数部分
		
		if(Integer.parseInt(xs) == 0) {//小数部分为0，则只保留整数
			return zs + unit;
		
		} else {
			len = xs.length();
			if(len == 1)
				unit = "00" + unit;
			else if(len == 2)
				unit = "0" + unit;
		
			return num + unit;
		}
	}
	
	/**
	 * 如果数字大于等于1024，那么就将数字除以1024，同时将单位升级
	 * 直到数字处于(1,1024]
	 * @param num
	 * @param unit
	 * @return
	 */
	public static String largerThanNum(double num, String unit, int count) {
		while(num >= 1024) {
			num /= 1024;//数字除以1024
			count++;//单位升级
		}
		//此时，num处于[1,1024),调用betweenNums()处理
		return betweenNums(num, UNITS[count]);
	}
	
	/**
	 * 如果数字小于1，那么就将数字乘以1024，同时将单位降级
	 * 直到数字处于[1,1024）
	 * @param num
	 * @param unit
	 * @return
	 */
	public static String littleThanNum(double num, String unit, int count) {
		while(num < 1) {
			num *= 1024;//数字除以1024
			count--;//单位升级
		}
		//此时，num处于[1,1024),调用betweenNums()处理
		return betweenNums(num, UNITS[count]);
	}
}
