package com.qdqtrj.lianlian.accp.exception;

/**
 * Accp支付平台异步通知异常
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public class AccpNotifyException extends Exception {

    private static final long serialVersionUID = 7948056218122147023L;

    public AccpNotifyException() {
        super();
    }

    public AccpNotifyException(String str) {
        super("Accp支付平台异步通知异常, " + str);
    }
}
