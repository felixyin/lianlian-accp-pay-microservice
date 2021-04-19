package com.qdqtrj.lianlian.accp.api;

import com.alibaba.fastjson.JSON;
import com.qdqtrj.lianlian.accp.api.request.AccpPayRequest;
import com.qdqtrj.lianlian.accp.api.request.AccpResponseResult;
import com.qdqtrj.lianlian.accp.api.request.impl.AccpSignRequestImpl;
import com.qdqtrj.lianlian.accp.config.AccpPayConfig;
import com.qdqtrj.lianlian.accp.exception.AccpPayException;
import com.qdqtrj.lianlian.accp.exception.AccpReqParamException;
import com.qdqtrj.lianlian.accp.util.accp.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 通用accp请求封装
 * 为什么不直接用这个方法，而是再次封装ImanagePayClient/ItradePayClient接口，为了：进一步减少使用者负担，减少不必要的bug发生
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Slf4j
public final class AccpApiHelper {

    private AccpApiHelper() {
    }

    /**
     * 通用请求封装
     *
     * @param accpPayConfig 支付参数
     * @param reqUrl        请求地址
     * @param reqParam      请求报文对象
     * @param returnClass   返回报文对象的class类型
     * @param <R>           请求报文对象范型
     * @param <S>           返回报文对象范型
     * @return
     * @throws AccpReqParamException
     * @throws AccpPayException
     */
    public static <R, S> S doCommonRequest(AccpPayConfig accpPayConfig, String reqUrl, R reqParam, Class<S> returnClass)
            throws AccpReqParamException, AccpPayException {

        String reqParamClassName = reqParam.getClass().getName();
        log.info("accp->请求报文json对应类型为：{}，返回报文对应类型为：{}", reqParamClassName, returnClass.getName());

        // 对person进行校验然后拿到结果（显然使用时默认的校验器）   会保留下校验失败的消息
        Map<String, StringBuffer> validateResult = ValidatorUtils.validate(reqParam);
        if (null != validateResult) {

            // 考虑支付信息保密问题,这里并没有输出原错误值
            String vResult = validateResult.toString();
            log.info("accp->请求参数对象校验失败，校验结果：{}", vResult);

            throw new AccpReqParamException(reqParamClassName + vResult);
        }

        String jsonData = JSON.toJSONString(reqParam);

        // 单例 accp签名请求实现对象
        AccpPayRequest accpPayRequest = AccpSignRequestImpl.getInstance();

        // 请求包ca签名，通过https发送到 accp 的 reqUrl 接口，返回数据 ca 验签后解包到 java bean
        AccpResponseResult accpResponseResult = accpPayRequest.doRequest(accpPayConfig, reqUrl, jsonData);

        String responseJson = accpResponseResult.getResponseJson();

        return JSON.parseObject(responseJson, returnClass);
    }

}
