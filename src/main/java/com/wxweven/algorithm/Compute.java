package com.wxweven.algorithm;

import java.util.Scanner;
import java.util.Stack;

public class Compute {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String line = sc.nextLine();
		sc.close();

		Stack<Integer> numberStack = new Stack<Integer>();// 数字栈
		Stack<Character> charStack = new Stack<Character>();// 操作符栈

		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);

			// =========如果当前字符是数字，直接入栈=========
			if (ch >= 48 && ch <= 57) {
				int number = ch - 48;
				numberStack.push(number);
			} else if (ch == '*' || ch == '/') {

				// ========如果当前字符是 * 或 /，将栈顶元素弹出，并与下一个数字计算，然后将结果入栈=============
				int ch1 = numberStack.pop();
				int ch2 = line.charAt(i + 1) - 48;
				
				i++;// 跳过下一个字符

				// 将结果入栈
				if (ch == '*') {
					numberStack.push(ch1 * ch2);
				} else if(ch == '/'){
					numberStack.push(ch1 / ch2);
				}
			} else if (ch == '+' || ch == '-') {
				// ========如果当前字符是 + 或 -，将操作符入栈=============
				charStack.push(ch);
			}
		}

		int j = 0;
		for(int i=0;i<charStack.size();i++){
			
			char oper = charStack.get(i);
			int op1 = numberStack.get(j);
			int op2 = numberStack.get(j+1);
			if (oper == '+') {
				numberStack.set(++j, op1+op2);
			} else if (oper == '-') {
				numberStack.set(++j, op1-op2);
			}
		}

		int res = numberStack.get(j);
		System.out.println(res);
	}

}
