package com.wxweven.http;

import com.google.gson.Gson;
import com.wxweven.poi.ExcelWriter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wxweven
 * @date 2017/8/18
 */
public class HttpRequestDemo {
    private static final String BASE_URL = "https://octo.sankuai.com/data/performance";
    private static final String APPKEY = "com.sankuai.sjst.m.ordercrm";
    private static final String ENV = "prod";
    private static final String SOURCE = "server";
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestUtils.class);
    private static final Gson gson = new Gson();

    @Test
    public void test_get() throws Exception {
        String cookie = "_lxsdk=15b7f4cdc92c8-0e26a3434fcc7e-396f7807-1fa400-15b7f4cdc9283; _lxsdk_cuid=15d30b933f6c8-0135b434764f9e-30627808-13c680-15d30b933f6c8; _hc.v=7602c76a-e92f-6de4-b0b1-838726214efc.1501125769; _jzqa=1.1212334789522741200.1500962156.1500962156.1502262691.2; skmtutc=a68IMlw7/LRZWHa8GHXJAerarFRpsnrkqswj4lkVUaAVwqVoypuyZRpr1/HGJ0MR-U5xKpYgGoOfLyFjqw4VVwk9WI70=; __mta=50091801.1499774685810.1501469437893.1503389504981.21; ssoid=29c21ed8f5*04da0920f6c4434300bf5; _ga=GA1.2.616692771.1492489115; _gid=GA1.2.1673360543.1503292290; al=bgytsaoznaynorpsqlqqisaxpbaquila; u=1866231; uu=786124e0-2eff-11e7-ba6b-83c41608f28d; ai=1; JSESSIONID=1lhvww0awb8d31urrsjbk1hxay; _lxsdk_s=15e0ce16e05-3a0-c6e-d98%7C%7C11";
        String spanname = "CrmOrderThriftService.queryOrders";

        String startDate = "2017-07-11";
        int len = 40;
        List<Map<String, Object>> dataList = new ArrayList<>(len);

        for (int i = 0; i < len; i++) {
            // 1. 设置请求以及URL
            HttpGet httpGet = new HttpGet(BASE_URL);
            // 2. 封装并设置请求参数
            Map<String, String> params = new HashMap<>();
            params.put("appkey", APPKEY);
            params.put("env", ENV);
            params.put("source", SOURCE);
            List<NameValuePair> entities = params.keySet()
                                                 .stream()
                                                 .map(key -> new BasicNameValuePair(key, params.get(key)))
                                                 .collect(Collectors.toList());

            DateTime beginDate = DateTime.parse(startDate);
            String reqDay = beginDate.plusDays(i).toString("yyyy-MM-dd");
            entities.add(new BasicNameValuePair("day", reqDay));

            // 设置参数
            String str = EntityUtils.toString(new UrlEncodedFormEntity(entities, Consts.UTF_8));
            httpGet.setURI(new URI(String.join("?", httpGet.getURI().toString(), str)));

            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                httpGet.setHeader("Cookie", cookie);

                LOGGER.info("请求URL：{}", httpGet.getRequestLine());

                // 3. 发起请求并返回请求的响应
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    int statusCode = response.getStatusLine().getStatusCode();
                    // 打印响应状态
                    LOGGER.info("响应状态码：{}", statusCode);
                    if (statusCode == HttpStatus.SC_OK) {
                        // 获取响应对象
                        HttpEntity resEntity = response.getEntity();
                        if (resEntity != null) {

                            String resText = EntityUtils.toString(resEntity, Consts.UTF_8);
                            //Type oppDoneMsgMap = new TypeToken<Map<String, String>>() {}.getType();
                            //Map<String, String> map = gson.fromJson(resText, oppDoneMsgMap);

                            TPVOS tpvos = gson.fromJson(resText, TPVOS.class);
                            List<TPVO> tpvoList = tpvos.getData();
                            if (CollectionUtils.isEmpty(tpvoList)) {
                                LOGGER.warn("没有性能数据");
                            }

                            List<Map<String, Object>> mapList = tpvoList.stream()
                                                                        .filter(tpvo -> spanname.equalsIgnoreCase(tpvo.getSpanname()))
                                                                        .map(tpvo -> {
                                                                            Map<String, Object> map = new HashMap<>();
                                                                            map.put("date", reqDay);
                                                                            map.put("upper50", tpvo.getUpper50());
                                                                            map.put("upper90", tpvo.getUpper90());
                                                                            map.put("upper95", tpvo.getUpper95());
                                                                            map.put("upper99", tpvo.getUpper99());
                                                                            map.put("upper999", tpvo.getUpper999());
                                                                            return map;
                                                                        })
                                                                        .collect(Collectors.toList());
                            dataList.addAll(mapList);
                        }

                        // 消费响应
                        EntityUtils.consume(resEntity);
                    } else {
                        LOGGER.error("返回异常：statusCode:{}", statusCode);
                    }
                }

                List<String> dataHead = Arrays.asList("日期", "TP50", "TP90", "TP95", "TP99", "TP999");
                List<String> key = Arrays.asList("date", "upper50", "upper90", "upper95", "upper99", "upper999");

                InputStream excelStream = ExcelWriter.getExcelStream(dataHead, key, dataList);

                FileUtils.copyToFile(excelStream, new File("/Users/wxweven/Desktop/2.xlsx"));
            }
        }
    }
}
