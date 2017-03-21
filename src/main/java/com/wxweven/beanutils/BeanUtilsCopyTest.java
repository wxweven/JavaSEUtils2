package com.wxweven.beanutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class BeanUtilsCopyTest {
    private static Logger logger = LoggerFactory.getLogger(BeanUtilsCopyTest.class);

    public static void main(String[] args) {
        BeanBO bo = new BeanBO();
        bo.setId(10);
        bo.setName("bo");
        bo.setPid(999L);

        BeanVO vo = new BeanVO();
        BeanUtils.copyProperties(vo, bo);
        logger.debug(bo.toString());
        logger.debug(vo.toString());
    }
}
