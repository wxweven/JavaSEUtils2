package com.wxweven.beanutils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyDescriptorTest {
    private static Logger logger = LoggerFactory.getLogger(PropertyDescriptorTest.class);

    public static void main(String[] args) throws Exception {

        PointBean pb = new PointBean(3, 3);
        String propertyName = "x";
        int setValue = 10;

        getProperty(pb, propertyName);
        logger.debug("before:" + pb.getX());

        setProperty(pb, propertyName, setValue);

        getProperty(pb, propertyName);
        logger.debug("before:" + pb.getX());
    }

    /**
     * 用PropertyDescriptor来操作类属性
     *
     * @param pb
     *            Bean实例
     * @param propertyName
     *            Bean属性
     * @param value
     *            Bean属性对应的值
     * @throws Exception
     */
    private static void setProperty(PointBean pb, String propertyName, Object value) throws Exception {
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, pb.getClass());
        Method writeProperty = pd.getWriteMethod();
        writeProperty.invoke(pb, value);
    }

    /**
     * 用PropertyDescriptor来操作类属性
     *
     * @param pb
     *            Bean实例
     * @param propertyName
     *            Bean属性
     * @throws Exception
     */
    private static void getProperty(Object pb, String propertyName) throws Exception {
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, pb.getClass());
        Method readProperty = pd.getReadMethod();
        readProperty.invoke(pb);
    }
}
