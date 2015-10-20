package com.wxweven.collection.javabeansort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxweven
 * @date 2015年9月29日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description: User bean
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
