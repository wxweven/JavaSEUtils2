package com.wxweven.test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		List<String> version = new ArrayList<String>();
		
		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			String line = sc.next();
			
			if(Integer.valueOf(line.split("\\.")[1]) % 2 == 0 ){
				version.add(line);
			}
		}
		
		sc.close();
		
		if(version.size() > 0){
			Collections.sort(version);
			System.out.println(version.get(version.size() -1));
			return;
		}
		
		
		System.out.println("no stable available");
		

		
	}

}
