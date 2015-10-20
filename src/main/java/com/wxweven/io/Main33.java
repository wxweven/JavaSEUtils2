package com.wxweven.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;

public class Main33 {

	public static void main(String[] args) throws IOException {
		// [5,7]&&(6,7)||(1,3)7
		//[1,2]&&(3,4)||(5,9]&&[6,9)8
		String input;// 初始的输入字符串
		// 预定义的单位数组
		System.out.println("please input:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		// 读取一行信息
		input = reader.readLine();
		int inputLen = input.length();// 获得输入字符串的长度
		char last = input.charAt(inputLen - 1);

		Stack<Character> stack1 = new Stack<Character>();// 存储'[,(,),]'
		LinkedList<Character> stack2 = new LinkedList<Character>();// 存储'|,&'
		Stack<Character> snum = new Stack<Character>();// 存储数字
		LinkedList<Character> sresult = new LinkedList<Character>();// 存储实时运算结果

		for (int i = 0; i < inputLen - 1; i++) {
			char tmp = input.charAt(i);//当前下标字符
			
			if (tmp == '[' || tmp == '(') {
				stack1.push(tmp);
			
			} else if (tmp <= 57 && tmp >= 48) {// 数字
				snum.push(tmp);// 数字压栈
			} else if (tmp == '|') {
				if (stack2.isEmpty() || stack2.getLast() != '|')
					stack2.add(tmp);
			} else if (tmp == '&') {
				if (stack2.isEmpty() || stack2.getLast() != '&')
					stack2.add(tmp);
			} else if (tmp == ',') {
				continue;
			} else if (tmp == ')' || tmp == ']') {
				char leftSecKh = stack1.pop();// 左区间的括号
				char rightSecNum = snum.pop();// 右区间数字
				char leftSecNum = snum.pop();// 左区间数字
				if (leftSecKh == '(' && tmp == ')')
					if (last > leftSecNum && last < rightSecNum)
						sresult.add('1');
					else
						sresult.add('0');
				else if (leftSecKh == '(' && tmp == ']')
					if (last > leftSecNum && last <= rightSecNum)
						sresult.add('1');
					else
						sresult.add('0');
				else if (leftSecKh == '[' && tmp == ')')
					if (last >= leftSecNum && last < rightSecNum)
						sresult.add('1');
					else
						sresult.add('0');
				else if (leftSecKh == '[' && tmp == ']')
					if (last >= leftSecNum && last <= rightSecNum)
						sresult.add('1');
					else
						sresult.add('0');
			}
		}

		/*for (char x : sresult) {
			System.out.println(x);
		}

		System.out.println("--------");

		for (char x : stack2) {
			System.out.println(x);
		}*/

		Stack<Integer> numStack = new Stack<Integer>();//数字栈
		Stack<Character> oprStack = new Stack<Character>();//操作符栈

		while (!stack2.isEmpty() && !sresult.isEmpty()) {
			int curNum = sresult.remove() - 48;//获取数字队列的队首元素,转化为对应的数字，（存的是ANSI码，需要减去48）
			char curOpr = stack2.remove();//获取操作符队列的队首元素，并删除
			numStack.push(curNum);//数字直接压到数字栈
			
			if (oprStack.isEmpty())//若操作符栈为空，则直接放到操作符栈顶
				oprStack.push(curOpr);
			
			else if (cmpStr(curOpr, oprStack.peek()) > 0)//若当前操作符的运算优先级比栈顶的高，则直接压入栈顶
				oprStack.push(curOpr);
			
			else {//弹出数字栈顶的两个数字并弹出操作符栈顶的运算符进行运算，把运算结果压入数字栈顶，重复直到当前运算符被压入操作符栈顶
				while ( (oprStack.isEmpty() == false) && (cmpStr(curOpr, oprStack.peek()) < 0) ) {
					//1.弹出数字栈顶的两个数字
					int num1 = numStack.pop();
					int num2 = numStack.pop();
					//2.弹出操作符栈顶的运算符
					char oprChar = oprStack.pop();
				
					//3.运算
					int res = 0;
					if (oprChar == '&')
						res = num1 & num2;
					else
					    res = num1 | num2;
					
					//4.运算结果压入数字栈顶
					numStack.push(res);
					//5.重复
				}
				//6.当前运算符压入操作符栈顶
				oprStack.push(curOpr);
			}
		}
		
		//将临时结果队列中剩余的的数字压到数字栈
		numStack.push( ((int)sresult.remove()) - 48 );
		
		/*System.out.println("数字栈---------");
		for (int x : numStack) {
			System.out.println(x);
		}
		System.out.println("字符串栈---------");
		for (char x : oprStack) {
			System.out.println(x);
		}*/

		while (oprStack.isEmpty() == false) {
			int i1 = numStack.pop();
			int i2 = numStack.pop();
			char c = oprStack.pop();

			int res = 0;
			if (c == '&')
				res = i1 & i2;
			else
				res = i1 | i2;
			numStack.push(res);
		}
		
		//最后的结果
		int finalResult = numStack.peek();
		System.out.println(finalResult);

	}

	static int cmpStr(char ch1, char ch2) {
		int res = 0;
		if (ch1 == '&' && ch2 == '|')
			res = 1;
		
		else
			res = -1;
		
		return res;
	}
}
