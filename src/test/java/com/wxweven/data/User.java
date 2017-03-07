package com.wxweven.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author wxweven
 * @version 1.0
 * @date 2016年4月19日
 * @email wxweven@qq.com
 * @website http://wxweven.com/
 * @Copyright: Copyright (c) wxweven 2016
 * @Description: User Bean
 */
public class User {
	private int id;
	private String name;
	private int marks;

	/**
	 * 默认的构造函数
	 */
	public User() {
	}

	/**
	 * 属性构造函数
	 * 
	 * @param id
	 * @param name
	 * @param marks
	 */
	public User(int id, String name, int marks) {
		super();
		this.id = id;
		this.name = name;
		this.marks = marks;
	}

	public static List<User> getUserList(int num) {
		List<User> userList = new ArrayList<User>();
		for (int i = 1; i <= num; i++) {
			userList.add(new User(i, "user-" + i, i * 5));
		}

		return userList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", marks=" + marks + "]";
	}

}
