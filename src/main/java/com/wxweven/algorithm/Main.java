package com.wxweven.algorithm;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int carNums = 0;

		String firstLine = sc.nextLine();
		int n = Integer.valueOf(firstLine.split(" ")[0]);
		int m = Integer.valueOf(firstLine.split(" ")[1]);

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		sc.close();

		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			if(sum == 0){
				carNums++;
			}
			
			sum += arr[i];
			
			if (sum > m) {
				sum = 0;
				i--;
			} else if (sum == m) {
				sum = 0;
			} 
		}
		
		System.out.println(carNums);

	}
}