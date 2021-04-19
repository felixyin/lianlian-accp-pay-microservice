package com.qdqtrj.lianlian.accp.api.request;

import com.qdqtrj.lianlian.accp.config.AccpPayConfig;
import com.qdqtrj.lianlian.accp.exception.AccpPayException;

/**
 * accp支付平台统一请求接口
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public interface AccpPayRequest {

    /**
     * 初步抽象封装accp所有接口，更深层次封装直接返回json对应Bean
     *
     * @param accpPayConfig
     * @param apiPath       accp支付平台某个接口的地址,不包括通用域名和通用的地址部分
     * @param jsonData      向accp支付平台发送的json报文（未加密过的，由相应的Bean序列化）
     * @return 返回请求报文json字符串
     */
    AccpResponseResult doRequest(AccpPayConfig accpPayConfig, String apiPath, String jsonData) throws AccpPayException;

}
