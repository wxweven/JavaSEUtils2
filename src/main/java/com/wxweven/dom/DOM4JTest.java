package com.wxweven.dom;

import com.wxweven.poi.ExcelWriter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * DOM4J 解析demo
 * Created by wxweven on 2017/4/11.
 */
public class DOM4JTest {
    private static final Logger LOGGER     = LoggerFactory.getLogger(DOM4JTest.class);
    private static final String FILE_DIR   = "/Users/wxweven/Desktop/xmlfile/";
    private static final String INPUT_FILE = FILE_DIR + "3.xml";
    private static final String OUT_FILE   = FILE_DIR + "3.xlsx";

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
            Element enbElement = rootElement.element("eNB");
            Element measurementEle = enbElement.element("measurement");
            Element smrEle = measurementEle.element("smr");
            String header = smrEle.getText();
            LOGGER.debug("标题是：" + header);

            List<String> dataHead = Arrays.asList(header.split("\\s+"));
            LOGGER.debug("标题:" + dataHead);

            List<String> key = new ArrayList<>(32);
            for (int i = 0; i < dataHead.size(); i++) {
                key.add(i + "");
            }

            List<Map<String, String>> dataList = new ArrayList<>(30 * 1000);

            for (Iterator iterator = measurementEle.elementIterator(); iterator.hasNext();) {
                Element element = (Element) iterator.next();
                if ("object".equals(element.getName())) {
                    for (Iterator vIt = element.elementIterator(); vIt.hasNext();) {
                        Map<String, String> map = new HashMap<>(32);
                        Element vEle = (Element) vIt.next();
                        String v = vEle.getText();
                        List<String> vList = Arrays.asList(v.split("\\s+"));
                        for (int i = 0; i < vList.size(); i++) {
                            map.put(i + "", vList.get(i));
                        }
                        dataList.add(map);
                    }
                }
            }

            InputStream excelStream = ExcelWriter.getExcelStream(dataHead, key, dataList);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            IOUtils.copy(excelStream, baos);
            FileUtils.writeByteArrayToFile(new File(OUT_FILE), baos.toByteArray());
            excelStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
