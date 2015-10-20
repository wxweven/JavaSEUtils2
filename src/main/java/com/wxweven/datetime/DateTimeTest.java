package com.wxweven.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeTest {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		System.out.println(date);
		
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.YEAR));
	}

}
