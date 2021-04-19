package com.qdqtrj.lianlian.accp.util.accp;

import com.qdqtrj.lianlian.accp.config.AccpConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author accp
 */
@Slf4j
public class HttpRequestSimple {


    /**
     * @param url
     * @param body
     * @return
     */
    public String postSendHttp(String url, String body) throws IOException {
        if (url == null || "".equals(url)) {
            log.warn("request url is empty.");
            return null;
        }
        HttpClient httpClient = AccpPayHttpClient.getInstance();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "text/html;charset=UTF-8");
        BufferedReader br = null;
        try {
            StringEntity stringEntity = new StringEntity(body, AccpConstants.UTF_8);
            stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING, AccpConstants.UTF_8));
            // 设置请求主体
            post.setEntity(stringEntity);
            // 发起交易
            HttpResponse resp = httpClient.execute(post);
            int ret = resp.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK) {
                // 响应分析
                HttpEntity entity = resp.getEntity();

                br = new BufferedReader(new InputStreamReader(entity.getContent(), StandardCharsets.UTF_8));
                StringBuilder responseString = new StringBuilder();
                String result = br.readLine();
                while (result != null) {
                    responseString.append(result);
                    result = br.readLine();
                }

                return responseString.toString();
            }
            return null;
        } catch (Exception cte) {
            log.error("accp->发送https请求报错：{}", cte.getMessage());
            return null;
        } finally {
            if (null != br) {
                br.close();
            }
        }
    }

    /**
     * @param url
     * @return
     */
    public String getSendHttp(String url) throws IOException {
        if (url == null || "".equals(url)) {
            log.warn("request url is empty.");
            return null;
        }
        HttpClient httpClient = AccpPayHttpClient.getInstance();
        HttpGet get = new HttpGet(url);
        get.setHeader("Content-Type", "text/html;charset=UTF-8");
        BufferedReader br = null;
        try {
            // 发起交易
            HttpResponse resp = httpClient.execute(get);
            int ret = resp.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK) {
                // 响应分析
                HttpEntity entity = resp.getEntity();
                br = new BufferedReader(new InputStreamReader(entity.getContent()));
                StringBuilder responseString = new StringBuilder();
                String result = br.readLine();
                while (result != null) {
                    responseString.append(result);
                    result = br.readLine();
                }
                return responseString.toString();
            }
            return null;
        } catch (Exception cte) {
            log.error("accp->发送https请求报错：{}", cte.getMessage());
            return null;
        } finally {
            if (null != br) {
                br.close();
            }
        }
    }

    /**
     * @param params
     * @param url
     * @return
     */
    public String postPramaList(List<NameValuePair> params, String url) throws IOException {
        HttpClient httpClient = AccpPayHttpClient.getInstance();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=utf-8");
        BufferedReader br = null;
        try {
            @SuppressWarnings("deprecation")
            UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(params,
                    HTTP.UTF_8);
            // 设置请求参数
            post.setEntity(formEntiry);
            // 发起交易
            HttpResponse resp = httpClient.execute(post);
            int ret = resp.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK) {
                // 响应分析
                HttpEntity entity = resp.getEntity();
                br = new BufferedReader(new InputStreamReader(entity
                        .getContent(), StandardCharsets.UTF_8));
                StringBuilder responseString = new StringBuilder();
                String result = br.readLine();
                while (result != null) {
                    responseString.append(result);
                    result = br.readLine();
                }
                return responseString.toString();
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("accp->发送https请求报错：{}", e.getMessage());
            return null;
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

}
