package com.qdqtrj.lianlian.accp.api.request.impl;

import com.qdqtrj.lianlian.accp.api.request.AccpPayRequest;
import com.qdqtrj.lianlian.accp.api.request.AccpResponseResult;
import com.qdqtrj.lianlian.accp.config.AccpConstants;
import com.qdqtrj.lianlian.accp.config.AccpPayConfig;
import com.qdqtrj.lianlian.accp.exception.AccpPayException;
import com.qdqtrj.lianlian.accp.util.accp.AccpPayHttpClient;
import com.qdqtrj.lianlian.accp.util.accp.SignatureUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Accp支付平台接口统一的请求封装:
 * 1. https实现
 * 2. ca证书签名加密请求报文的实现
 * 3. ca证书验证返回数据报文的实现
 * 不包括数据去冲和https异步回调的实现，因涉及业务逻辑，需接口使用者自己实现
 * 采用枚举单例模式
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Slf4j
public final class AccpSignRequestImpl implements AccpPayRequest {

    /**
     * 私有化构造函数
     */
    private AccpSignRequestImpl() {
    }

    /**
     * 对外暴露一个获取单例对象的静态方法
     */
    public static AccpSignRequestImpl getInstance() {
        return SingletonEnum.INSTANCE.getInstnce();
    }

    @Override
    public AccpResponseResult doRequest(
            AccpPayConfig accpPayConfig,
            String apiPath,
            String jsonData) throws AccpPayException {

        Integer reqTimeout = accpPayConfig.getReqTimeout();
        log.info("accp->超时时间：{}", reqTimeout);

        HttpClient httpClient = AccpPayHttpClient.getInstance(reqTimeout);
        log.info("accp->请求报文：{}", jsonData);

        HttpPost post = new HttpPost(accpPayConfig.getPayDomainServerUrl() + apiPath);
        log.info("accp->请求URL：{}", apiPath);

        post.setHeader("Content-Type", "application/text;charset=UTF-8");

        BufferedReader br = null;
        try {
            String signatureData = SignatureUtils.getInstance()
                    .sign(accpPayConfig.getRsaPrivateKey(), jsonData);
            log.info("accp->请求signatureData：{}", apiPath);

            // 设置请求参数
            post.addHeader(AccpConstants.SIGNATURE_TYPE_UPPER, AccpConstants.RSA);
            post.addHeader(AccpConstants.SIGNATURE_DATA_UPPER, signatureData);
            StringEntity formEntiry = new StringEntity(jsonData, AccpConstants.UTF_8);
            post.setEntity(formEntiry);

            // 发起交易
            HttpResponse resp = httpClient.execute(post);

            // 响应分析
            HttpEntity entity = resp.getEntity();
            br = new BufferedReader(new InputStreamReader(entity.getContent(), StandardCharsets.UTF_8));
            StringBuilder responseString = new StringBuilder();
            String result = br.readLine();
            while (result != null) {
                responseString.append(result);
                result = br.readLine();
            }

            // 直接获取返回的字符串
            String responseJson = responseString.toString();
            log.info("accp->返回报文：{}", responseJson);

            String resSignatureData = "";
            Header[] headers = resp.getAllHeaders();
            int i = 0;
            while (i < headers.length) {
                Header header = headers[i];
                log.debug("accp->header {} : {}", header.getName(), header.getValue());
                if (AccpConstants.SIGNATURE_DATA_UPPER.equals(header.getName())) {
                    resSignatureData = header.getValue();
                }
                i++;
            }

            boolean checkSign = SignatureUtils.getInstance()
                    .checksign(accpPayConfig.getRsaPublicKey(), responseJson, resSignatureData);
            log.info("accp->验证签名结果：{}", checkSign);

            return new AccpResponseResult(checkSign, responseJson);
        } catch (Exception cte) {
            log.info("accp->调用accp支付平台接口发生异常：{}", cte.getMessage());
            throw new AccpPayException(cte.getMessage(), cte);
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.info("accp->调用accp支付平台接口发生异常：{}", e.getMessage());
                }
            }
        }
    }

    /**
     * 定义一个静态枚举类
     */
    enum SingletonEnum {
        /**
         * 创建一个枚举对象，该对象天生为单例
         */
        INSTANCE;
        private final AccpSignRequestImpl impl;

        /**
         * 私有化枚举的构造函数
         */
        SingletonEnum() {
            impl = new AccpSignRequestImpl();
        }

        public AccpSignRequestImpl getInstnce() {
            return impl;
        }
    }

}
