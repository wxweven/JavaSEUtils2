package com.wxweven.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main11 {
	
	static String input;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("please input a num string:");
		input = br.readLine();
		
		Stack<Integer> stack0 = new Stack<Integer>();
		Stack<Integer> stack1 = new Stack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		
		int i0 = 0;
		int i1 = 0;
		int i2 = 0;
		
		for(int i=0; i<input.length(); i++) {
			
			char tmp = input.charAt(i);
			switch(tmp) {
			case '0':
				i0++;
				if(i < (input.length()-1) && nextChar(i) != '0') {
					stack0.push(i0);
					i0 = 0;
				} 
				if(i == (input.length() - 1)) 
					stack0.push(i0);
				break;
			case '1':
				i1++;
				if(i < (input.length()-1) && nextChar(i) != '1') {
					stack1.push(i1);
					i1 = 0;
				}
				if(i == (input.length() - 1)) 
					stack1.push(i1);
				break;
			case '2':
				i2++;
				if(i < (input.length()-1) && nextChar(i) != '2') {
					stack2.push(i2);
					i2 = 0;
				}
				if(i == (input.length() - 1)) 
					stack2.push(i2);
				break;
			}
		}
		
		int max_0 = 0;
		int max_1 = 0;
		int max_2 = 0;
		
		for(int i : stack0) {
			if(i > max_0){
				max_0 = stack0.peek();
			}
		}
		
		for(int i : stack1) {
			if(i > max_1){
				max_1 = stack1.peek();
			}
		}
		
		for(int i : stack2) {
			if(i > max_2){
				max_2 = stack2.peek();
			}
		}
		
//		System.out.println(max_0 + "-" + max_1 + "-" + max_2);
		int max = (max_0 > max_1)? max_0 : max_1;
		max = (max > max_2) ? max : max_2;
		
		if(max == max_0)
			System.out.println(max + " " + 0);
		if(max == max_1)
			System.out.println(max + " " + 1);
		if(max == max_2)
			System.out.println(max + " " + 2);
	}

	private static char nextChar(int i) {
		return input.charAt(i+1);
	}

}
