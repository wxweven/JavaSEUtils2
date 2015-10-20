package com.wxweven.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;

public class Main3 {

	private static LinkedList<Character> charQueue = new LinkedList<Character>();// 存储'[,(,),]'
	private static LinkedList<Character> oprQueue = new LinkedList<Character>();// 存储'|,&'
	private static LinkedList<Integer> numQueue = new LinkedList<Integer>();// 存储数字
	private static LinkedList<Integer> resultQueue = new LinkedList<Integer>();// 存储实时运算结果
	
	private static Stack<Integer> numStack = new Stack<Integer>();//数字栈
	private static Stack<Character> oprStack = new Stack<Character>();//操作符栈
	
	public static void main(String[] args) throws IOException {
		// [5,7]&&(6,7)||(1,3)7
		//[1,2]&&(3,4)||(5,9]&&[6,9)8
		//[10,20]||[1,2]&&(3,4)||(5,9]&&[6,9)8
		System.out.println("please input:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String input = reader.readLine();//读取一行信息,存入初始的输入字符串
		int last = (int)input.charAt(input.length() - 1) - 48;//最后一个字符，作为判断的数字
		String expStr = input.substring(0,input.length() - 1);//对前面的n-1个字符来处理

		buildNumQueue(expStr);//提取字符串中的多个数字，组成数字队列
		buildCharAndKh(expStr);//提取字符串中的括号，用于表示开闭区间
		buildResultQueue(last);//根据区间和判断数字，得到临时结果
		compute();//计算最后字符串表达式的值，如 0||1&&1 的结果是 1
	}
	
	/**
	 * 计算字符串表达式的结果 
	 * 如 0||1&&1 的结果是 1
	 * 也是本题最关键的算法
	 */
	private static void compute() {
		while (!oprQueue.isEmpty() && !resultQueue.isEmpty()) {
			int curNum = resultQueue.remove();//弹出数字队列的队首元素（弹出：获取并删除）
			numStack.push(curNum);//数字直接压到数字栈
			
			char curOpr = oprQueue.remove();//弹出操作符队列的队首元素
			if (oprStack.isEmpty())//若操作符栈为空，则直接放到操作符栈顶
				oprStack.push(curOpr);
			
			else if (cmpStr(curOpr, oprStack.peek()) > 0)//若当前操作符的运算优先级比栈顶的高，则直接压入栈顶
				oprStack.push(curOpr);
			
			else {
				/**
				 * 弹出数字栈顶的两个数字并弹出操作符栈顶的运算符进行运算
				 * 把运算结果压入数字栈顶
				 * 重复直到当前运算符被压入操作符栈顶
				 */
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
		numStack.push( resultQueue.remove() );
		
		/**
		 * 处理剩余的数字和操作符，步骤类似上述的1-6
		 * 只不过，此时没有了操作符压栈
		 */
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
	
	/**
	 * 比较字符串的优先级： & 高于 |
	 * @param ch1
	 * @param ch2
	 * @return
	 */
	static int cmpStr(char ch1, char ch2) {
		int res = 0;
		if (ch1 == '&' && ch2 == '|')
			res = 1;
		else
			res = -1;
		
		return res;
	}
	
	/**
	 * 解析字符串，转化成int
	 * @param str
	 * @return
	 */
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

	/**
	 * 构建临时结果队列
	 * @param last
	 */
	private static void buildResultQueue(int last) {
		while(charQueue.isEmpty() == false) {//隊列不空
			int leftSecNum = numQueue.remove();// 左区间数字
			int rightSecNum = numQueue.remove();// 右区间数字
			
			char leftSecKh = charQueue.remove();// 左区间的括号
			char rightSecKh = charQueue.remove();// 右区间的括号
//			System.out.println("-------"+ last + ":" + leftSecKh+leftSecNum+rightSecNum+rightSecKh);
			
			if (leftSecKh == '(' && rightSecKh == ')')
				if (last > leftSecNum && last < rightSecNum)
					resultQueue.add(1);
				else
					resultQueue.add(0);
			else if (leftSecKh == '(' && rightSecKh == ']')
				if (last > leftSecNum && last <= rightSecNum)
					resultQueue.add(1);
				else
					resultQueue.add(0);
			else if (leftSecKh == '[' && rightSecKh == ')')
				if (last >= leftSecNum && last < rightSecNum)
					resultQueue.add(1);
				else
					resultQueue.add(0);
			else if (leftSecKh == '[' && rightSecKh == ']')
				if (last >= leftSecNum && last <= rightSecNum)
					resultQueue.add(1);
				else
					resultQueue.add(0);
		}
	}
	
	/**
	 * 根据字符串，解析出数字，并构建数字队列
	 * @param expStr
	 */
	private static void buildNumQueue(String expStr) {
		String[] numStr = expStr.split(",");
		for(String tmpStr : numStr) {
			if(tmpStr.indexOf("&&") != -1) {
				String[] str  = tmpStr.split("&&");
				for(String s : str) {
					if(!"".equals(s) && s != null)
						numQueue.add(str2num(s));
				}
			} else if(tmpStr.indexOf("||") != -1) {
				String[] str  = tmpStr.split("\\|\\|");
				for(String s : str) {
					if(!"".equals(s))
						numQueue.add(str2num(s));
				}
			} else {
				if(!"".equals(tmpStr))
					numQueue.add(str2num(tmpStr));
			}
		}
	}

	/**
	 * 构建(,),[,] 和 &&,||队列
	 * @param expStr
	 */
	private static void buildCharAndKh(String expStr) {
		for (int i = 0; i < expStr.length(); i++) {
			char tmp = expStr.charAt(i);//当前下标字符
			if(tmp == '(' || tmp == ')' || tmp == '[' || tmp == ']')
				charQueue.add(tmp);
			else if(tmp == '&' || tmp == '|'){
				oprQueue.add(tmp);
				i++;
			}
		}
	}
}