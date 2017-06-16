package com.wxweven.beanutils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BeanUtilsCopyTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(BeanUtilsCopyTest.class);

    @Test
    public void test() throws Exception {
        try {
            List<BeanBO> bos = new ArrayList<>();
            BeanBO bo1 = new BeanBO();
            bo1.setId(10);
            bo1.setName("bo1");
            bo1.setPid(999L);

            BeanBO bo2 = new BeanBO();
            bo2.setId(10);
            bo2.setName("bo2");
            bo2.setPid(999L);

            bos.add(bo1);
            bos.add(bo2);

            Map<Integer, String> idNameMap = bos.stream()
                                                .collect(Collectors.toMap(BeanBO::getId, BeanBO::getName, (v1, v2) -> v1));
            int a = 1 / 0;
        } catch (Exception e) {
            LOGGER.debug("异常拉");
        }


        LOGGER.debug("{}", 111);


//        BeanVO vo = new BeanVO();
//        BeanUtils.copyProperties(bo, vo);
//        logger.debug(bo.toString());
//        logger.debug(vo.toString());

//        Integer a = Integer.valueOf(45);
//        Integer a = null;
//        int b = 45;
//        logger.debug("a==b:" + (a == b));
    }

    @Test
    public void test2() {
        List<BeanBO> bos = Collections.emptyList();

        Map<Integer, BeanBO> beanMap = bos.stream()
                                          .collect(Collectors.toMap(BeanBO::getId,
                                                  Function.identity(),
                                                  (v1, v2) -> v2)
                                          );

        LOGGER.debug("map:", beanMap);
    }

    public static void test3() {
        try {
            int i = 0;
            if (i <= 0) {
                LOGGER.debug("抛出异常啦...");
                throw new IndexOutOfBoundsException("越界");
            }
        } catch (Exception e) {
            LOGGER.debug("捕获到异常啦...", e);
        }
    }

    @Test
    public void test4() {
        test3();
        LOGGER.debug("没有异常...");
    }
}
