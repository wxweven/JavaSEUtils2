package com.wxweven.http;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Http请求工具包，封装了Get、Post请求
 * Created by wxweven
 * on 2017/6/16.
 */
public class HttpRequestUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestUtils.class);

    public static void get(String url, Map<String, String> params) throws IOException, URISyntaxException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 1. 设置请求以及URL
            HttpGet httpGet = new HttpGet(url);

            // 2. 封装并设置请求参数
            List<NameValuePair> entities = params.keySet()
                                                 .stream()
                                                 .map(key -> new BasicNameValuePair(key, params.get(key)))
                                                 .collect(Collectors.toList());
            // 设置参数
            String str = EntityUtils.toString(new UrlEncodedFormEntity(entities, Consts.UTF_8));
            httpGet.setURI(new URI(String.join("?", httpGet.getURI().toString(), str)));

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
                        LOGGER.info("响应内容：{}", EntityUtils.toString(resEntity, Consts.UTF_8));
                    }

                    // 消费响应
                    EntityUtils.consume(resEntity);
                } else {
                    LOGGER.error("返回异常：statusCode:{}", statusCode);
                }
            }
        }
    }

    public static void post(String url, Map<String, String> params) throws IOException, URISyntaxException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 1. 设置请求以及URL
            HttpPost httpPost = new HttpPost(url);

            // 2. 封装并设置请求参数
            List<NameValuePair> entities = params.keySet()
                                                 .stream()
                                                 .map(key -> new BasicNameValuePair(key, params.get(key)))
                                                 .collect(Collectors.toList());
            httpPost.setEntity(new UrlEncodedFormEntity(entities, Consts.UTF_8));

            LOGGER.info("请求URL：{}", httpPost.getRequestLine());

            // 3. 发起请求并返回请求的响应
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                // 打印响应状态
                LOGGER.info("响应状态码：{}", statusCode);
                if (statusCode == HttpStatus.SC_OK) {
                    // 获取响应对象
                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        LOGGER.info("响应内容：{}", EntityUtils.toString(resEntity, Consts.UTF_8));
                    }

                    // 消费响应
                    EntityUtils.consume(resEntity);
                } else {
                    LOGGER.error("返回异常：statusCode:{}", statusCode);
                }
            }
        }
    }

    public static void postBody(String url, String json) throws IOException {
        //String url = "http://127.0.0.1:8099/login";
        //String json = "{\"login_name\": \"18800000000\",\"login_password\": \"123456\"}";
        //创建client
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            //创建post请求
            HttpPost httppost = new HttpPost(url);
            //json
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httppost.setEntity(entity);
            LOGGER.info("请求URL={}" + httppost.getRequestLine());
            //执行post请求
            try (CloseableHttpResponse response = httpclient.execute(httppost)) {
                LOGGER.info("----------------------------------------");
                //状态
                LOGGER.info("响应={}", response.getStatusLine());
                //响应实体
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    System.out.println(EntityUtils.toString(resEntity));
                }
                //关闭HttpEntity流
                EntityUtils.consume(resEntity);
            }
        }
    }

    public static void postFile(String url, File file, String param) throws IOException, URISyntaxException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 1. 设置post请求以及请求的URL
            HttpPost httpPost = new HttpPost(url);

            // 2. 把文件转换成流对象FileBody
            FileBody fileBody = new FileBody(file);

            // 3. 设置请求实体
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                                                         .setMode(HttpMultipartMode.BROWSER_COMPATIBLE) // 以浏览器兼容模式运行，防止文件名乱码。
                                                         .addPart("file", fileBody) // file 对应服务端类的同名属性<MultipartFile类型>
                                                         .addTextBody("param", param)// 其他参数
                                                         .setCharset(Consts.UTF_8)
                                                         .build();
            httpPost.setEntity(reqEntity);

            LOGGER.info("请求URL：{}", httpPost.getRequestLine());

            // 4. 发起请求 并返回请求的响应

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // 打印响应状态
                LOGGER.info("响应状态码：{}", response.getStatusLine().getStatusCode());
                // 获取响应对象
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    LOGGER.info("响应内容：{}", EntityUtils.toString(resEntity, Consts.UTF_8));
                }
                // 销毁
                EntityUtils.consume(resEntity);
            }
        }
    }
}
