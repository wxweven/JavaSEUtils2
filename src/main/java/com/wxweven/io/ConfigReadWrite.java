package com.wxweven.io;

import java.io.IOException;
import java.util.Properties;

/**
 * @author wxweven
 * @version 1.0
 * @date 2014年10月31日
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:读取和设置配置文件
 */
public class ConfigReadWrite {

    public static final String CONFIG_PROPERTIES = "config.properties";
    private Properties props;

    /**
     * 默认的配置文件，config源码包下的config.properties文件
     */
    public ConfigReadWrite() {
        this.props = new Properties();
        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * config源码包目录下，指定文件名的配置文件
     */
    public ConfigReadWrite(String configFile) {
        this.props = new Properties();
        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream(configFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据key来获取对应的值val,去掉首尾的空格
     *
     * @param key
     * @return
     */
    public String getConfigVal(String key) {
        if (props.get(key) != null) {
            return props.get(key).toString().trim();
        } else {
            return "";
        }
    }

    /**
     * 设置配置文件中，对应key 的值为 val，成功返回设值前的值
     *
     * @param key
     * @param val
     * @return
     */
    public Object setConfigVal(String key, String val) {
        return props.setProperty(key, val);
    }
}
