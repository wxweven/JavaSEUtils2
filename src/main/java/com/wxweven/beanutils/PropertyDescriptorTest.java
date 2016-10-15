package com.wxweven.beanutils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class PropertyDescriptorTest {
	private static Logger logger = Logger.getLogger(PropertyDescriptor.class);

	public static void main(String[] args) throws Exception {
		PointBean pb = new PointBean(3,3);
		String propertyName = "x";
		int setValue = 10;

		getProperty(pb,propertyName);
		setProperty(pb,propertyName, setValue);
		getProperty(pb,propertyName);

	}

	/**
	 * 用PropertyDescriptor来操作类属性
	 * @param pb
	 * @param propertyName
	 * @throws Exception
	 */
	private static void setProperty(PointBean pb, String propertyName,Object value) throws Exception {
		PropertyDescriptor pd = new PropertyDescriptor(propertyName, pb.getClass());
		Method writeProperty = pd.getWriteMethod();
		Object obj = writeProperty.invoke(pb,value);
		logger.debug("obj-->" + obj);

	}

	private static void getProperty(Object pb, String propertyName) throws Exception {
		PropertyDescriptor pd = new PropertyDescriptor(propertyName, pb.getClass());
		Method readProperty = pd.getReadMethod();
		Object obj = readProperty.invoke(pb);
		System.out.println(obj);
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		PropertyDescriptorTest.logger = logger;
	}
}
