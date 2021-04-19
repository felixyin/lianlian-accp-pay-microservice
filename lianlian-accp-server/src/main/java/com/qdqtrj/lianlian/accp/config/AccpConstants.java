package com.qdqtrj.lianlian.accp.config;

/**
 * 支付常量类
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/22 11:08 上午
 */
public interface AccpConstants {

    /**
     * header请求头name，用于标识签名加密类型，默认值为：RSA
     */
    String SIGNATURE_TYPE_UPPER = "Signature-Type";

    /**
     * response 的 header头name，用于标识ca证书加密的请求报文
     */
    String SIGNATURE_DATA_UPPER = "Signature-Data";

    /**
     * 默认30超时时间
     */
    int DEFAULT_REQ_TIMEOUT_SECOND = 30;

    /**
     * 转为毫秒
     */
    int MS = 1000;

    /**
     * http端口
     */
    int HTTP_PORT = 80;

    /**
     * https端口
     */
    int HTTPS_PORT = 443;

    /**
     * 异步通知请求 header key
     */
    String NOTIFY_SIGNATURE_DATA = "signature-data";

    /**
     * 异步通知返回值：标志处理成功
     */
    String SUCCESS = "Success";

    /**
     * 异步通知返回值：标志处理失败
     */
    String FAILED = "Failed";

    String RESULT_KEY_BITS = "7B29C312DF7244B9926EC3C00C45F3FD";

    String CIPHER_KEY_BITS = "4C2791B992794969929DA47A6E4A96F3";

    /**
     * 加密类型
     */
    String RSA = "RSA";

    /**
     * 编码方式
     */
    String UTF_8 = "UTF-8";


    // ====================================================== 交易类接口地址
    String URL_TRADECREATE = "/v1/txn/tradecreate";
    String URL_PAYMENT_BALANCE = "/v1/txn/payment-balance";
    String URL_PAYMENT_BANKCARD = "/v1/txn/payment-bankcard";
    String URL_PAYMENT_GW = "/v1/txn/payment-gw";
    String URL_PAYMENT_PAPAY = "/v1/txn/payment-papay";
    String URL_QUERY_PAYMENT = "/v1/txn/query-payment";
    String URL_WITHDRAWAL = "/v1/txn/withdrawal";
    String URL_WITHDRAWAL_CHECK = "/v1/txn/withdrawal-check";
    String URL_QUERY_WITHDRAWAL = "/v1/txn/query-withdrawal";
    String URL_TRANSFER = "/v1/txn/transfer";
    String URL_SECURED_CONFIRM = "/v1/txn/secured-confirm";
    String URL_SECURED_QUERY = "/v1/txn/secured-query";
    String URL_VALIDATION_SMS = "/v1/txn/validation-sms";
    String URL_ASYN_REFUND = "/v1/txn/asyn-refund";
    String URL_QUERY_REFUND = "/v1/txn/query-refund";

    // ====================================================== 管理类接口地址
    String URL_REGPHONE_VERIFYCODE_APPLY = "/v1/acctmgr/regphone-verifycode-apply";
    String URL_REGPHONE_VERIFYCODE_VERIFY = "/v1/acctmgr/regphone-verifycode-verify";
    String URL_OPENACCT_APPLY_INDIVIDUAL = "/v1/acctmgr/openacct-apply-individual";
    String URL_OPENACCT_VERIFY_INDIVIDUAL = "/v1/acctmgr/openacct-verify-individual";
    String URL_OPENACCT_APPLY_ENTERPRISE = "/v1/acctmgr/openacct-apply-enterprise";
    String URL_INDIVIDUAL_BINDCARD_APPLY = "/v1/acctmgr/individual-bindcard-apply";
    String URL_INDIVIDUAL_BINDCARD_VERIFY = "/v1/acctmgr/individual-bindcard-verify";
    String URL_ENTERPRISE_CHANGECARD_APPLY = "/v1/acctmgr/enterprise-changecard-apply";
    String URL_UNLINKEDACCT_IND_APPLY = "/v1/acctmgr/unlinkedacct-ind-apply";
    String URL_CHANGE_LINKEDPHONE_APPLY = "/v1/acctmgr/change-linkedphone-apply";
    String URL_CHANGE_LINKEDPHONE_VERIFY = "/v1/acctmgr/change-linkedphone-verify";
    String URL_CHANGE_REGPHONE_APPLY = "/v1/acctmgr/change-regphone-apply";
    String URL_CHANGE_REGPHONE_VERIFY = "/v1/acctmgr/change-regphone-verify";
    String URL_CANCEL_APPLY = "/v1/acctmgr/cancel-apply";
    String URL_CHANGE_PASSWORD = "/v1/acctmgr/change-password";
    String URL_FIND_PASSWORD_APPLY = "/v1/acctmgr/find-password-apply";
    String URL_FIND_PASSWORD_VERIFY = "/v1/acctmgr/find-password-verify";
    String URL_GET_RANDOM = "/v1/acctmgr/get-random";
    String URL_QUERY_LINKEDACCT = "/v1/acctmgr/query-linkedacct";
    String URL_QUERY_USERINFO = "/v1/acctmgr/query-userinfo";
    String URL_QUERY_ACCTINFO = "/v1/acctmgr/query-acctinfo";
    String URL_QUERY_ACCTSERIAL = "/v1/acctmgr/query-acctserial";
    String URL_QUERY_ACCTSERIALDETAIL = "/v1/acctmgr/query-acctserialdetail";
    String URL_QUERY_TXN = "/v1/acctmgr/query-txn";
    String URL_QUERY_CNAPSCODE = "/v1/acctmgr/query-cnapscode";
    String URL_UPLOAD_PHOTOS = "/v1/acctmgr/upload-photos";
    String URL_VALIDATE_PASSWORD = "/v1/acctmgr/validate-password";
}
