package com.wxweven.dom;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * DOM4J 解析demo
 * Created by wxweven on 2017/4/11.
 */
public class SitemapDom {
    private static final Logger LOGGER = LoggerFactory.getLogger(SitemapDom.class);
    private static final String FILE_DIR = "/Users/wxweven/gitwork/hexo/public/";
    private static final String INPUT_FILE = FILE_DIR + "baidusitemap.xml";
    private static final String OUT_FILE = "/Users/wxweven/Desktop/common/urls.txt";

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 解析books.xml文件
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File(INPUT_FILE));
            Element rootElement = document.getRootElement();

            LOGGER.info("rootElement: {}", rootElement.getName());

            List<String> locList = new ArrayList<>(100);
            File outputFile = new File(OUT_FILE);

            // 便利 rootElement 下的子节点
            for (Iterator iterator = rootElement.elementIterator(); iterator.hasNext(); ) {
                Element element = (Element) iterator.next();
                if ("url".equalsIgnoreCase(element.getName())) {
                    // 便利 url 节点下面的子节点
                    for (Iterator innerIterator = element.elementIterator(); innerIterator.hasNext(); ) {
                        Element innerElement = (Element) innerIterator.next();
                        if ("loc".equalsIgnoreCase(innerElement.getName())) {
                            String loc = innerElement.getTextTrim();
                            LOGGER.info("loc: {}", loc);
                            locList.add(loc);
                        }
                    }
                }
            }

            LOGGER.info("成功添加 {} 条URL", locList.size());
            FileUtils.writeLines(outputFile, locList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
