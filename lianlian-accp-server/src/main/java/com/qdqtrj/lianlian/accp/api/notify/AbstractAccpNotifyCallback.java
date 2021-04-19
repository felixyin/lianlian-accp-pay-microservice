package com.qdqtrj.lianlian.accp.api.notify;

import com.alibaba.fastjson.JSON;
import com.qdqtrj.lianlian.accp.config.AccpConstants;
import com.qdqtrj.lianlian.accp.config.AccpPayConfig;
import com.qdqtrj.lianlian.accp.exception.AccpPayException;
import com.qdqtrj.lianlian.accp.util.accp.SignatureUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

/**
 * notify and return url，预处理工具类
 * 减少回调非业务代码的编写
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Slf4j
public abstract class AbstractAccpNotifyCallback<T> {

    public static final String LOG_FLAG = "AccpNotifyCallback.pretreatment";

    private static String getBody(HttpServletRequest request) throws AccpPayException {
        try (BufferedReader br = request.getReader()) {
            String str;
            StringBuilder wholeStr = new StringBuilder();
            while ((str = br.readLine()) != null) {
                wholeStr.append(str);
            }
            return wholeStr.toString();
        } catch (IOException e) {
            throw new AccpPayException(e);
        }
    }

    /**
     * 通用回调预处理方法
     *
     * @param accpPayConfig
     * @param request       spring中获取的或者servlet容器传递的  request封装对象
     * @param notifyClass   异步通知javabean class
     * @return
     */
    public String pretreatment(AccpPayConfig accpPayConfig, HttpServletRequest request,
                               Class<T> notifyClass) throws AccpPayException {
        log.info("{}=>异步通知返回json对应类型为：{}", LOG_FLAG, notifyClass.getName());

        // 1. 获取返回数据
        String notifyJsonStr = getBody(request);
        log.debug("{}->异步通知，请求报文json：{}", LOG_FLAG, notifyJsonStr);

        // 2. 验签，失败抛出异常（属于配置错误)
        StringBuilder resSignData = new StringBuilder();
        Enumeration<String> headers = request.getHeaders("Signature-Data");
        while (headers.hasMoreElements()) {
            String s = headers.nextElement();
            resSignData.append(s);
        }
        log.debug("{}->异步通知，请求头签名数据：{}", LOG_FLAG, resSignData);

        boolean checkSign = SignatureUtils.getInstance()
                .checksign(accpPayConfig.getRsaNotifyPublicKey(), notifyJsonStr, resSignData.toString());
        log.debug("{}->异步通知，验签结果：{}", LOG_FLAG, checkSign);
        if (!checkSign) {
            // 验签失败
            return AccpConstants.FAILED;
        }

        // 3. 解析 json 到 java bean
        T notifyBean = JSON.parseObject(notifyJsonStr, notifyClass);
        log.info("{}->异步通知，请求报文json解析的bean：{}", LOG_FLAG, notifyBean.toString());
        try {

            // 4. 调用 notifyCallback 给业务代码传递 解析后的 java bean(需要将去冲做到业务方法中，去冲处理,队列缓冲)
            boolean isSuccess = businessCode(notifyBean);
            log.info("{}->异步通知，业务逻辑方法是否处理成功：{}", LOG_FLAG, isSuccess);

            // 5. 返回给accp成功或失败标识字符
            return isSuccess ? AccpConstants.SUCCESS : AccpConstants.FAILED;
        } catch (Exception e) {
            log.error("{}->异步通知，业务逻辑方法抛出异常：{}", LOG_FLAG, e.getMessage());
            return AccpConstants.FAILED;
        }
    }


    /**
     * @param accpPayConfig
     * @param signatureData
     * @param body
     * @param notifyClass
     * @return
     * @throws Exception
     */
    public String pretreatment(AccpPayConfig accpPayConfig, String signatureData, String body, Class<T> notifyClass) throws AccpPayException {
        log.info("{}=>异步通知返回json对应类型为：{}", LOG_FLAG, notifyClass.getName());

        // 1. 获取返回数据
        log.debug("{}->异步通知，请求报文json：{}", LOG_FLAG, body);

        // 2. 验签，失败抛出异常（属于配置错误)
        log.debug("{}->异步通知，请求头签名数据：{}", LOG_FLAG, signatureData);
        boolean checkSign = SignatureUtils.getInstance()
                .checksign(accpPayConfig.getRsaNotifyPublicKey(), body, signatureData);
        log.info("{}->异步通知，验签结果：{}", LOG_FLAG, checkSign);
        if (!checkSign) {
            // 验签失败
            return AccpConstants.FAILED;
        }

        // 3. 解析 json 到 java bean
        T notifyBean = JSON.parseObject(body, notifyClass);
        log.info("{}->异步通知，请求报文json解析的bean：{}", LOG_FLAG, notifyBean.toString());
        try {

            // 4. 调用 notifyCallback 给业务代码传递 解析后的 java bean(需要将去冲做到业务方法中，去冲处理,队列缓冲)
            boolean isSuccess = businessCode(notifyBean);
            log.info("{}->异步通知，业务逻辑方法是否处理成功：{}", LOG_FLAG, isSuccess);

            // 5. 返回给accp成功或失败标识字符
            return isSuccess ? AccpConstants.SUCCESS : AccpConstants.FAILED;
        } catch (Exception e) {
            log.error("{}->异步通知，业务逻辑方法抛出异常：{}", LOG_FLAG, e.getMessage());
            return AccpConstants.FAILED;
        }
    }

    /**
     * 你需要new 此抽象类的子类，实现业务逻辑的处理。
     * <p>
     * businessCode 前后做了一些通用处理：
     * 1. 验签
     * 2. 解码json
     * 3. 调用 你自己编写的业务方法
     * 4. 处理异常
     * 5. 返回accp结
     *
     * @param notifyBean
     */
    protected abstract boolean businessCode(T notifyBean);

}
