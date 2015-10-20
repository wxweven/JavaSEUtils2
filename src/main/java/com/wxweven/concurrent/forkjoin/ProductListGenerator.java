package com.wxweven.concurrent.forkjoin;

import java.util.ArrayList;
import java.util.List;

public class ProductListGenerator {

	public static List<Product> generate(int size) {
		List<Product> list = new ArrayList<Product>(size);

		for (int i = 0; i < size; i++) {
			Product product = new Product();
			product.setName("Product" + (i + 1));
			product.setPrice(10D);
			list.add(product);

		}

		return list;
	}
}
