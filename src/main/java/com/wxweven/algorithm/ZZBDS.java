package com.wxweven.algorithm;

import java.util.Stack;

/**
 * 中缀表达式转后缀表达式
 * 
 * @author Administrator
 * 
 */
public class ZZBDS {

	/**
	 * 前提：假设表达式是合法的 中缀表达式算法 1.当前读入是操作数，直接输出
	 * 2.当前读入是操作符：如果栈顶元素优先级比当前读入明显低，则压栈，否则一直弹栈直到比当前低 3.读入是左括号，直接压栈
	 * 4.读入是右括号，一直弹栈并输出，直到弹出左括号 5.栈为空，则直接弹栈并输出
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String testStr = "a+b*c+(d*e+f)*g";

		Stack<opStr> stack = new Stack<opStr>();
		
		
		int len = testStr.length();

	}
	
	private static Stack<opStr> initStack(String str) {
		int len = str.length();
		for(int i=0; i<len; i++) {
			char ch = str.charAt(i);
		}
		return null;
	}

	private static enum opStr {
		PLUS('+',1),MINUS('-',1),MULTIPE('*',2),DIVIDE('/',2),LKH('(',3),RKH(')',3);
		
		public char getName() {
			return name;
		}

		public int getPriv() {
			return priv;
		}

		// 成员变量
		private char name;
		private int priv;

		private opStr(char name, int priv) {
			this.name = name;
			this.priv = priv;
		}
	}

}
