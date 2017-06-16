package com.wxweven.reflect;

import java.lang.reflect.Constructor;

public class ReflectTest {

    public static void main(String[] args) {


        /**
         * 通过一个对象获得完整的包名和类名
         */
        Demo demo = new Demo();
//		System.out.println("完整的包名:" + demo.getClass().getPackage().getMsg());
//		System.out.println("完整的类名:" + demo.getClass().getMsg());


        /**
         * 利用反射来获得类字节码
         */
        Class<?> demo1 = null;
        Class<?> demo2 = null;
        Class<?> demo3 = null;
        try {
            //利用反射，动态加载类，来获得类的字节码
            demo1 = Class.forName("com.wxweven.reflect.Demo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //利用对象的getClass()方法来获得类字节码
        demo2 = new Demo().getClass();
        //利用类名的class属性来获得类字节码
        demo3 = Demo.class;
        //利用反射来new Demo类的实例：根据构造方法来new
        try {
            Constructor cst = demo3.getConstructor(String.class);
            Demo reflectDemo = (Demo) cst.newInstance("你妹");
            System.out.println(reflectDemo);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //一下是一样的效果
        //System.out.println("类名称1   " + demo1.getMsg());
        //System.out.println("类名称2   " + demo2.getMsg());
        //System.out.println("类名称3   " + demo3.getMsg());
    }
}

class Demo {

    private String name;
    private int age;

    /**
     * 默认的构造方法
     */
    public Demo() {
    }

    /**
     * 只有name属性的构造方法
     *
     * @param name 用于初始化name属性
     */
    public Demo(String name) {
        this.name = name;
    }

    /**
     * 带name和age属性的构造方法
     *
     * @param name 用于初始化name属性
     * @param age  用于初始化age属性
     */
    public Demo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Demo [name=" + name + ", age=" + age + "]";
    }


}
