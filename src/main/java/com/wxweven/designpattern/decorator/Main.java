package com.wxweven.designpattern.decorator;

public class Main {

	public static void main(String[] args) {
		Girl americaGirl = new AmericaGirl();
		americaGirl.getDiscription();
		System.out.println();

		Girl chinaGirl = new ChinaGirl();
		chinaGirl.getDiscription();
		System.out.println();

		Science scienceGirl = new Science(americaGirl);
		scienceGirl.getDiscription();

		Art artGirl = new Art(chinaGirl);
		artGirl.getDiscription();
	}
}
