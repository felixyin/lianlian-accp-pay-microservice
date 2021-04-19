package com.qdqtrj.lianlian.accp.api.request;

import lombok.Data;

/**
 * 调用Accp平台返回结果对象
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
public class AccpResponseResult {
    /**
     * 签名是否成功
     */
    private boolean checkSign;
    /**
     * 解密后的报文json字符串
     */
    private String responseJson;

    public AccpResponseResult(boolean checkSign, String responseJson) {
        this.checkSign = checkSign;
        this.responseJson = responseJson;
    }
}
