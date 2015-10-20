package com.wxweven.enums;

public class EnumTest {

	public static void main(String[] args) {
		WeekDay wd = WeekDay.TUS;
		System.out.println(wd);
		
		for(WeekDay wds : WeekDay.values()) {
			System.out.println(wds);
		}
	}
	
	
	
	public enum WeekDay {
		SUN(0,"星期天"),
		MON(1,"星期一"),
		TUS(2,"星期二"),
		WED(3,"星期三"),
		THU(4,"星期四"),
		FRI(5,"星期五"),
		SAT(6,"星期六");
		
		private int index;
		private String name;
		
		private WeekDay(int index, String name) {
			this.index = index;
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public String toString() {
			return "今天是"+this.getName()+",本周的第 "+this.getIndex()+" 天";
		}
		
		
	}

}
