package com.wxweven.classloader;

public class ClassLoaderTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        /*
         * System.out.println(
         * ClassLoaderTest.class.getClassLoader().getClass().getName() );
         * System.out.println( System.class.getClassLoader() );
         * System.out.println("xxx"); ClassLoader loader =
         * ClassLoaderTest.class.getClassLoader(); while(loader != null){
         * System.out.println(loader.getClass().getName()); loader =
         * loader.getParent(); } System.out.println(loader);
         */
        // System.out.println(new ClassLoaderAttachment().toString());
        Class clazz = new MyClassLoader("evenlib").loadClass("cn.itcast.day2.ClassLoaderAttachment");
        Object d1 = clazz.newInstance();
        System.out.println(d1);
    }

}
