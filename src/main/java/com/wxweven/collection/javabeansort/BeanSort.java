package com.wxweven.collection.javabeansort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;

public class BeanSort {
	public static void main(String[] args) {

		List<User> users = User.getUserList(2);
		User user = new User();
		user.setId(10);
		user.setMarks(50);
		users.add(user);

		users = sort(users, "name", "desc");

		System.out.println(users);
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> sort(List<T> list, String orderField, String orderDirection) {
		// 创建针对某个属性的升序比较
		Comparator<T> comparator = new BeanComparator<T>(orderField, new NullComparator(false));

		if ("desc".equalsIgnoreCase(orderDirection)) {
			// desc :降序排序
			comparator = new ReverseComparator(comparator);
		}

		// 开始排序
		Collections.sort(list, comparator);
		return list;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> sortMutil(List<T> list, Map<String, Boolean> orderMap) {
		// 创建多属性排序链
		ComparatorChain cc = new ComparatorChain();

		for (String key : orderMap.keySet()) {
			// 第一个参数是指定排序的属性，第二个参数指定降序升序,true:降序，false:升序
			cc.addComparator(new BeanComparator<T>(key), orderMap.get(key));
		}

		Collections.sort(list, cc);
		return list;
	}
}
