package com.wxweven.jvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author wxweven
 * @date 2017/8/26
 */
public class JvmArgsTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(JvmArgsTest.class);

    public static void main(String[] args) {
        LOGGER.info("程序参数：{}", Arrays.asList(args));
    }
}
