package com.wxweven.jvm.zzm;

import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * @author wxweven
 * @date 2017/9/15
 */
public class MethodHandleTest {
    @Test
    public void test_methodCall() throws Throwable {
        String target = "abcd";

        String directCall = target.replace("a", "b");
        System.out.println("直接调用：" + directCall);


        MethodType methodType = MethodType.methodType(String.class, char.class, char.class);
        MethodHandle mh = MethodHandles.lookup().findVirtual(String.class, "replace", methodType);
        String methodHandleInvoke = (String) mh.invoke(target, 'a', 'b');
        System.out.println("MethodHandle调用:" + methodHandleInvoke);

        Method method = String.class.getMethod("replace", char.class, char.class);
        String reflectInvoke = (String) method.invoke(target, 'a', 'b');
        System.out.println("反射调用:" + reflectInvoke);

    }

    @Test
    public void test_methodCallPerformance() throws Throwable {
        String target = "abcd";
        long len = 10000 * 1000;

        long start = System.currentTimeMillis();

        for (int i = 0; i < len; i++) {
            target.replace("a", "b");
        }

        System.out.println("直接调用耗时：" + (System.currentTimeMillis() - start) + "ms");


        start = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            MethodType methodType = MethodType.methodType(String.class, char.class, char.class);
            MethodHandle mh = MethodHandles.lookup().findVirtual(String.class, "replace", methodType);
            mh.invoke(target, 'a', 'b');
        }
        System.out.println("MethodHandle调用：" + (System.currentTimeMillis() - start) + "ms");


        start = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            Method method = String.class.getMethod("replace", char.class, char.class);
            method.invoke(target, 'a', 'b');
        }
        System.out.println("反射调用耗时：" + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    public void test_methodCallPerformanceWithCache() throws Throwable {
        String target = "abcd";
        long len = 10000 * 1000;

        long start = System.currentTimeMillis();

        for (int i = 0; i < len; i++) {
            target.replace("a", "b");
        }

        System.out.println("直接调用耗时：" + (System.currentTimeMillis() - start) + "ms");


        MethodType methodType = MethodType.methodType(String.class, char.class, char.class);
        MethodHandle mh = MethodHandles.lookup().findVirtual(String.class, "replace", methodType);
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            mh.invoke(target, 'a', 'b');
        }
        System.out.println("MethodHandle调用：" + (System.currentTimeMillis() - start2) + "ms");


        Method method = String.class.getMethod("replace", char.class, char.class);
        long start3 = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            method.invoke(target, 'a', 'b');
        }
        System.out.println("反射调用耗时：" + (System.currentTimeMillis() - start3) + "ms");
    }

    /**
     * 有 JIT 优化热点代码，运行次数多了以后，下面几种调用基本耗时一致
     *
     * @throws Throwable
     */
    @Test
    public void test_methodCallPerformanceWithCacheWithJIT() throws Throwable {
        MethodHandleTest target = new MethodHandleTest();
        long len = 100000000;

        long start = System.currentTimeMillis();

        for (int i = 0; i < len; i++) {
            target.testCall(100);
        }
        System.out.println("直接调用耗时：" + (System.currentTimeMillis() - start) + "ms");

        MethodType methodType = MethodType.methodType(void.class, int.class);
        MethodHandle mh = MethodHandles.lookup()
                                       .findVirtual(MethodHandleTest.class, "testCall", methodType);
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            mh.invokeExact(target, 100);
        }
        System.out.println("MethodHandle调用：" + (System.currentTimeMillis() - start2) + "ms");


        Method method = MethodHandleTest.class.getMethod("testCall", int.class);
        long start3 = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            method.invoke(target, 100);
        }
        System.out.println("反射调用耗时：" + (System.currentTimeMillis() - start3) + "ms");
    }

    /**
     * 必须要禁用 JIT 优化才能体现出来几种方法调用的区别
     * -Djava.compiler=NONE
     *
     * @throws Throwable
     */
    @Test
    public void test_methodCallPerformanceWithCacheWithoutJIT() throws Throwable {
        MethodHandleTest target = new MethodHandleTest();
        long len = 10000000;

        long start = System.currentTimeMillis();

        for (int i = 0; i < len; i++) {
            target.testCall(100);
        }
        System.out.println("直接调用耗时：" + (System.currentTimeMillis() - start) + "ms");

        MethodType methodType = MethodType.methodType(void.class, int.class);
        MethodHandle mh = MethodHandles.lookup().findVirtual(MethodHandleTest.class, "testCall", methodType);
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            mh.invokeExact(target, 100);
        }
        System.out.println("MethodHandle调用：" + (System.currentTimeMillis() - start2) + "ms");


        Method method = MethodHandleTest.class.getMethod("testCall", int.class);
        long start3 = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            method.invoke(target, 100);
        }
        System.out.println("反射调用耗时：" + (System.currentTimeMillis() - start3) + "ms");
    }

    public void testCall(int len) {
        for (int i = 0; i < len; i++) {
        }
    }

}
