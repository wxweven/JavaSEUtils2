package com.wxweven.jvm;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

/**
 * @author wxweven
 * @date 2017/8/21
 */
public class ReferenceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceTest.class);


    @Test
    public void test_weakRef() {
        String abc = new String("abc");
        WeakReference<String> abcWeakRef = new WeakReference<>(abc);
        abc = null;
        LOGGER.info("before gc: {}", abcWeakRef.get());
        System.gc();

        if (abcWeakRef.get() == null) {
            LOGGER.info("abc对象已经被清除...");
        } else {
            LOGGER.info("abc 对象还存活");
            LOGGER.info("after gc: {}", abcWeakRef.get());
        }
    }

    @Test
    public void test_phantomRef() throws Exception {

        Method method = getClass().getMethod("boolTest", int.class, int.class, long.class);
        Object result = method.invoke(this, 10, 20, 45L);
        if (result instanceof Boolean) {
            LOGGER.info("bool");
        }
    }

    public boolean boolTest(int a, int b, long c) {
        long res = a + b + c;
        LOGGER.info("res:{}", res);
        return true;
    }
}
