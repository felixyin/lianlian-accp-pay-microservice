package com.qdqtrj.lianlian.accp.api.request.impl;

import com.qdqtrj.lianlian.accp.api.request.AccpPayRequest;
import com.qdqtrj.lianlian.accp.api.request.AccpResponseResult;
import com.qdqtrj.lianlian.accp.config.AccpPayConfig;

/**
 * post 请求实现
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Deprecated
public final class AccpPostRequestImpl implements AccpPayRequest {
    @Override
    public AccpResponseResult doRequest(AccpPayConfig accpPayConfig, String apiPath, String jsonData) {
        return null;
    }
}
