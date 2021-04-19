package com.qdqtrj.lianlian.accp.exception;

/**
 * Accp支付平台请求报文验证异常
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public class AccpReqParamException extends Exception {

    private static final long serialVersionUID = -5637861355243467669L;

    public AccpReqParamException() {
        super();
    }

    public AccpReqParamException(String str) {
        super("Accp支付平台请求报文验证异常, " + str);
    }
}
