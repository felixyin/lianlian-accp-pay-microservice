package com.qdqtrj.lianlian.accp.client;

import com.qdqtrj.lianlian.accp.client.impl.PayServerHystrix;
import com.qdqtrj.lianlian.accp.pojo.FeignErrorDecoder;
import com.qdqtrj.lianlian.accp.pojo.manage.dto.callback.*;
import com.qdqtrj.lianlian.accp.pojo.manage.dto.request.*;
import com.qdqtrj.lianlian.accp.pojo.manage.dto.response.*;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.callback.NotifyPaymentPapay;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.callback.NotifyQueryRefund;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.callback.NotifyWithdrawalCheck;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.request.*;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.response.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 包括所有 doc/《ACCP产品商户接口说明书-账户管理类（API）V1.4-标准版.pdf》 接口
 * 包括所有 doc/《ACCP产品商户接口说明书-交易类（API）V1.4-标准版.pdf》 接口
 * <p>
 * 业务系统 调用 lianlian-accp-server
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@FeignClient(value = "lianlian-accp-server", fallbackFactory = PayServerHystrix.class, configuration = {FeignErrorDecoder.class})
public interface IPayClient {


    /**
     * 3. 交易接口定义 3.1. 绑定手机验证码申请 3.1.1. 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/regphone-verifycode-apply
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_REGPHONE_VERIFYCODE_APPLY
     *
     * @param req ReqRegphoneVerifycodeApply实例
     * @return AjaxJson实例
     * @see ReqRegphoneVerifycodeApply
     * @see ResRegphoneVerifycodeApply
     */
    @PostMapping("/payManageAction/regphoneVerifycodeApply/white")
    ResRegphoneVerifycodeApply regphoneVerifycodeApply(@RequestBody ReqRegphoneVerifycodeApply req);

    /**
     * 3.2. 绑定手机验证码验证 3.2.1. 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/regphone-verifycode-verify
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_REGPHONE_VERIFYCODE_VERIFY
     *
     * @param req ReqRegphoneVerifycodeVerify实例
     * @return AjaxJson实例
     * @see ReqRegphoneVerifycodeVerify
     * @see ResRegphoneVerifycodeVerify
     */
    @PostMapping("/payManageAction/regphoneVerifycodeVerify/white")
    ResRegphoneVerifycodeVerify regphoneVerifycodeVerify(@RequestBody ReqRegphoneVerifycodeVerify req);

    /**
     * 3.3. 个人用户开户 3.3.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-individual
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_OPENACCT_APPLY_INDIVIDUAL
     *
     * @param req ReqOpenacctApplyIndividual实例
     * @return AjaxJson实例
     * @see ReqOpenacctApplyIndividual
     * @see ResOpenacctApplyIndividual
     */
    @PostMapping("/payManageAction/openacctApplyIndividual/white")
    ResOpenacctApplyIndividual openacctApplyIndividual(@RequestBody ReqOpenacctApplyIndividual req);

    /**
     * 3.3.2. 开户验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-verify-individual
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_OPENACCT_VERIFY_INDIVIDUAL
     *
     * @param req ReqOpenacctVerifyIndividual实例
     * @return AjaxJson实例
     * @see NotifyOpenacctVerifyIndividual
     * @see ReqOpenacctVerifyIndividual
     * @see ResOpenacctVerifyIndividual
     */
    @PostMapping("/payManageAction/openacctVerifyIndividual/white")
    ResOpenacctVerifyIndividual openacctVerifyIndividual(@RequestBody ReqOpenacctVerifyIndividual req);

    /**
     * 3.4. 企业用户开户 3.4.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-enterprise
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_OPENACCT_APPLY_ENTERPRISE
     *
     * @param req ReqOpenacctApplyEnterprise实例
     * @return AjaxJson实例
     * @see NotifyOpenacctApplyEnterprise
     * @see ReqOpenacctApplyEnterprise
     * @see ResOpenacctApplyEnterprise
     */
    @PostMapping("/payManageAction/openacctApplyEnterprise")
    ResOpenacctApplyEnterprise openacctApplyEnterprise(@RequestBody ReqOpenacctApplyEnterprise req);

    /**
     * 3.5. 个人用户新增绑卡 3.5.1. 绑卡申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/individual-bindcard-apply
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_INDIVIDUAL_BINDCARD_APPLY
     *
     * @param req ReqIndividualBindcardApply实例
     * @return AjaxJson实例
     * @see ReqIndividualBindcardApply
     * @see ResIndividualBindcardApply
     */
    @PostMapping("/payManageAction/individualBindcardApply/white")
    ResIndividualBindcardApply individualBindcardApply(@RequestBody ReqIndividualBindcardApply req);

    /**
     * 3.5.2. 绑卡验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/individual-bindcard-verify
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_INDIVIDUAL_BINDCARD_VERIFY
     *
     * @param req ReqIndividualBindcardVerify实例
     * @return AjaxJson实例
     * @see NotifyIndividualBindcardVerify
     * @see ReqIndividualBindcardVerify
     * @see ResIndividualBindcardVerify
     */
    @PostMapping("/payManageAction/individualBindcardVerify/white")
    ResIndividualBindcardVerify individualBindcardVerify(@RequestBody ReqIndividualBindcardVerify req);

    /**
     * 3.6. 企业用户更换绑定账号 3.6.1. 更换申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/enterprise-changecard-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_ENTERPRISE_CHANGECARD_APPLY
     *
     * @param req ReqEnterpriseChangecardApply实例
     * @return AjaxJson实例
     * @see NotifyEnterpriseChangecardApply
     * @see ReqEnterpriseChangecardApply
     * @see ResEnterpriseChangecardApply
     */
    @PostMapping("/payManageAction/enterpriseChangecardApply")
    ResEnterpriseChangecardApply enterpriseChangecardApply(@RequestBody ReqEnterpriseChangecardApply req);

    /**
     * 3.7. 个人用户解绑银行卡 3.7.1. 解绑 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/unlinkedacct-ind-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_UNLINKEDACCT_IND_APPLY
     *
     * @param req ReqUnlinkedacctIndApply实例
     * @return AjaxJson实例
     * @see NotifyUnlinkedacctIndApply
     * @see ReqUnlinkedacctIndApply
     * @see ResUnlinkedacctIndApply
     */
    @PostMapping("/payManageAction/unlinkedacctIndApply/white")
    ResUnlinkedacctIndApply unlinkedacctIndApply(@RequestBody ReqUnlinkedacctIndApply req);

    /**
     * 3.8. 个人用户修改预留手机号 3.8.1. 申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-linkedphone-apply
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_CHANGE_LINKEDPHONE_APPLY
     *
     * @param req ReqChangeLinkedphoneApply实例
     * @return AjaxJson实例
     * @see ReqChangeLinkedphoneApply
     * @see ResChangeLinkedphoneApply
     */
    @PostMapping("/payManageAction/changeLinkedphoneApply")
    ResChangeLinkedphoneApply changeLinkedphoneApply(@RequestBody ReqChangeLinkedphoneApply req);

    /**
     * 3.8.2. 验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-linkedphone-verify
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_CHANGE_LINKEDPHONE_VERIFY
     *
     * @param req ReqChangeLinkedphoneVerify实例
     * @return AjaxJson实例
     * @see NotifyChangeLinkedphoneVerify
     * @see ReqChangeLinkedphoneVerify
     * @see ResChangeLinkedphoneVerify
     */
    @PostMapping("/payManageAction/changeLinkedphoneVerify")
    ResChangeLinkedphoneVerify changeLinkedphoneVerify(@RequestBody ReqChangeLinkedphoneVerify req);

    /**
     * 3.9. 修改绑定手机 3.9.1. 申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-regphone-apply
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_CHANGE_REGPHONE_APPLY
     *
     * @param req ReqChangeRegphoneApply实例
     * @return AjaxJson实例
     * @see ReqChangeRegphoneApply
     * @see ResChangeRegphoneApply
     */
    @PostMapping("/payManageAction/changeRegphoneApply")
    ResChangeRegphoneApply changeRegphoneApply(@RequestBody ReqChangeRegphoneApply req);

    /**
     * 3.9.2. 验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-regphone-verify
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_CHANGE_REGPHONE_VERIFY
     *
     * @param req ReqChangeRegphoneVerify实例
     * @return AjaxJson实例
     * @see NotifyChangeRegphoneVerify
     * @see ReqChangeRegphoneVerify
     * @see ResChangeRegphoneVerify
     */
    @PostMapping("/payManageAction/changeRegphoneVerify")
    ResChangeRegphoneVerify changeRegphoneVerify(@RequestBody ReqChangeRegphoneVerify req);

    /**
     * 3.10. 销户 3.10.1.  销户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/cancel-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_CANCEL_APPLY
     *
     * @param req ReqCancelApply实例
     * @return AjaxJson实例
     * @see NotifyCancelApply
     * @see ReqCancelApply
     * @see ResCancelApply
     */
    @PostMapping("/payManageAction/cancelApply")
    ResCancelApply cancelApply(@RequestBody ReqCancelApply req);

    /**
     * 3.11. 重置密码 3.11.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-password
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_CHANGE_PASSWORD
     *
     * @param req ReqChangePassword实例
     * @return AjaxJson实例
     * @see ReqChangePassword
     * @see ResChangePassword
     */
    @PostMapping("/payManageAction/changePassword")
    ResChangePassword changePassword(@RequestBody ReqChangePassword req);

    /**
     * 3.12. 找回密码 3.12.1.  申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/find-password-apply
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_FIND_PASSWORD_APPLY
     *
     * @param req ReqFindPasswordApply实例
     * @return AjaxJson实例
     * @see ReqFindPasswordApply
     * @see ResFindPasswordApply
     */
    @PostMapping("/payManageAction/findPasswordApply")
    ResFindPasswordApply findPasswordApply(@RequestBody ReqFindPasswordApply req);

    /**
     * 3.12.2.  验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/find-password-verify
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_FIND_PASSWORD_VERIFY
     *
     * @param req ReqFindPasswordVerify实例
     * @return AjaxJson实例
     * @see ReqFindPasswordVerify
     * @see ResFindPasswordVerify
     */
    @PostMapping("/payManageAction/findPasswordVerify")
    ResFindPasswordVerify findPasswordVerify(@RequestBody ReqFindPasswordVerify req);

    /**
     * 3.13. 随机因子获取 3.13.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/get-random
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_GET_RANDOM
     *
     * @param req ReqGetRandom实例
     * @return AjaxJson实例
     * @see ReqGetRandom
     * @see ResGetRandom
     */
    @PostMapping("/payManageAction/getRandom/white")
    ResGetRandom getRandom(@RequestBody ReqGetRandom req);

    /**
     * 3.14. 绑卡信息查询 3.14.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-linkedacct
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_QUERY_LINKEDACCT
     *
     * @param req ReqQueryLinkedacct实例
     * @return AjaxJson实例
     * @see ReqQueryLinkedacct
     * @see ResQueryLinkedacct
     */
    @PostMapping("/payManageAction/queryLinkedacct")
    ResQueryLinkedacct queryLinkedacct(@RequestBody ReqQueryLinkedacct req);

    /**
     * 3.15. 用户信息查询 3.15.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-userinfo
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_QUERY_USERINFO
     *
     * @param req ReqQueryUserinfo实例
     * @return AjaxJson实例
     * @see ReqQueryUserinfo
     * @see ResQueryUserinfo
     */
    @PostMapping("/payManageAction/queryUserinfo")
    ResQueryUserinfo queryUserinfo(@RequestBody ReqQueryUserinfo req);

    /**
     * 3.16. 账户信息查询 3.16.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-acctinfo
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_QUERY_ACCTINFO
     *
     * @param req ReqQueryAcctinfo实例
     * @return AjaxJson实例
     * @see ReqQueryAcctinfo
     * @see ResQueryAcctinfo
     */
    @PostMapping("/payManageAction/queryAcctinfo/white")
    ResQueryAcctinfo queryAcctinfo(@RequestBody ReqQueryAcctinfo req);

    /**
     * 3.17. 资金流水查询 3.17.1.  资金流水列表查询 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-acctserial
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_QUERY_ACCTSERIAL
     *
     * @param req ReqQueryAcctserial实例
     * @return AjaxJson实例
     * @see ReqQueryAcctserial
     * @see ResQueryAcctserial
     */
    @PostMapping("/payManageAction/queryAcctserial")
    ResQueryAcctserial queryAcctserial(@RequestBody ReqQueryAcctserial req);

    /**
     * 3.17.2.  资金流水详情查询 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-acctserialdetail
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_QUERY_ACCTSERIALDETAIL
     *
     * @param req ReqQueryAcctserialdetail实例
     * @return AjaxJson实例
     * @see ReqQueryAcctserialdetail
     * @see ResQueryAcctserialdetail
     */
    @PostMapping("/payManageAction/queryAcctserialdetail")
    ResQueryAcctserialdetail queryAcctserialdetail(@RequestBody ReqQueryAcctserialdetail req);

    /**
     * 3.18. 交易流水结果查询 3.18.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-txn
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_QUERY_TXN
     *
     * @param req ReqQueryTxn实例
     * @return AjaxJson实例
     * @see ReqQueryTxn
     * @see ResQueryTxn
     */
    @PostMapping("/payManageAction/queryTxn")
    ResQueryTxn queryTxn(@RequestBody ReqQueryTxn req);

    /**
     * 3.19. 大额行号查询 3.19.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-cnapscode
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_QUERY_CNAPSCODE
     *
     * @param req ReqQueryCnapscode实例
     * @return AjaxJson实例
     * @see ReqQueryCnapscode
     * @see ResQueryCnapscode
     */
    @PostMapping("/payManageAction/queryCnapscode")
    ResQueryCnapscode queryCnapscode(@RequestBody ReqQueryCnapscode req);

    /**
     * 3.20. 上传照片 该接口用于上传个人用户的身份证照片及企业用户的统一社会信用代码证照片和企业法人 身份证照片。 3.20.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/upload-photos
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_UPLOAD_PHOTOS
     *
     * @param req ReqUploadPhotos实例
     * @return AjaxJson实例
     * @see ReqUploadPhotos
     * @see ResUploadPhotos
     */
    @PostMapping("/payManageAction/uploadPhotos")
    ResUploadPhotos uploadPhotos(@RequestBody ReqUploadPhotos req);

    /**
     * 3.21. 支付密码校验 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/validate-password
     * 接口类型：非异步通知接口
     * 地址常量：IAccpManagePayClient.URL_VALIDATE_PASSWORD
     *
     * @param req ReqValidatePassword实例
     * @return AjaxJson实例
     * @see ReqValidatePassword
     * @see ResValidatePassword
     */
    @PostMapping("/payManageAction/validatePassword")
    ResValidatePassword validatePassword(@RequestBody ReqValidatePassword req);


    /**
     * 3.2. 充值/消费 3.2.1. 支付统一创单 商户在充值/消费交易模式一场景下使用，先通过该接口完成支付统一创单，后续根据业务 场景调用不同的支付接口完成付款。 请求地址https://accpapi.lianlianpay.com/v1/txn/tradecreate
     * 接口类型：非异步通知接口
     * 地址常量：IAccpTradePayClient.URL_TRADECREATE
     *
     * @param req ReqTradecreate实例
     * @return AjaxJson实例
     * @see ReqTradecreate
     * @see ResTradecreate
     */
    @PostMapping("/payTradeAction/tradecreate/white")
    ResTradecreate tradecreate(@RequestBody ReqTradecreate req);

    /**
     * 3.2.2. 余额支付 用户账户余额支付接口，支持附加优惠券组合支付。 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-balance
     * 接口类型：非异步通知接口
     * 地址常量：IAccpTradePayClient.URL_PAYMENT_BALANCE
     *
     * @param req ReqPaymentBalance实例
     * @return AjaxJson实例
     * @see ReqPaymentBalance
     * @see ResPaymentBalance
     */
    @PostMapping("/payTradeAction/paymentBalance/white")
    ResPaymentBalance paymentBalance(@RequestBody ReqPaymentBalance req);

    /**
     * 3.2.3. 银行卡快捷支付 银行卡快捷支付接口，支持附加优惠券、余额组合支付；适用于如下几种场景：   已开户绑定银行卡的个人用户支付   未注册的匿名用户首次/历次支付 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-bankcard
     * 接口类型：非异步通知接口
     * 地址常量：IAccpTradePayClient.URL_PAYMENT_BANKCARD
     *
     * @param req ReqPaymentBankcard实例
     * @return AjaxJson实例
     * @see ReqPaymentBankcard
     * @see ResPaymentBankcard
     */
    @PostMapping("/payTradeAction/paymentBankcard")
    ResPaymentBankcard paymentBankcard(@RequestBody ReqPaymentBankcard req);

    /**
     * 3.2.4. 网关类支付 微信、支付宝、网银类支付申请，通过该接口唤起相应渠道的支付界面，用户确认并支付，支持附加优惠券、余额组合支付。支持以下主支付方式：==== 编码  描述,EBANK_DEBIT_CARD  网银借记卡,EBANK_CREDIT_CARD  网银信用卡,EBANK_B2B  企业网银,WECHAT_APP  微信APP,WECHAT_JSAPI  微信公众号,WECHAT_NATIVE  微信扫码,WECHAT_H5  微信H5,WECHAT_WXA  微信小程序,ALIPAY_NATIVE  支付宝扫码,ALIPAY_APP  支付宝APP,ALIPAY_H5  支付宝H5,ALIPAY_WEB  支付宝WEB,ALIPAY_WXA  支付宝小程序,ALIPAY_NATIVE_ICBC  支付宝扫码-工银e支付,WECHAT_NATIVE_ICBC  微信扫码-工银e支付 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-gw
     * 接口类型：非异步通知接口
     * 地址常量：IAccpTradePayClient.URL_PAYMENT_GW
     *
     * @param req ReqPaymentGw实例
     * @return AjaxJson实例
     * @see ReqPaymentGw
     * @see ResPaymentGw
     */
    @PostMapping("/payTradeAction/paymentGw/white")
    ResPaymentGw paymentGw(@RequestBody ReqPaymentGw req);

    /**
     * 3.2.5. 委托代扣 微信、支付宝提交委托代扣，通过使用与用户签约获得的微信、支付宝代扣协议号，由商户后台调用该接口发起创单并支付，暂不支持组合支付。 支持以下主支付方式： 编码  描述,WECHAT_PAPAY  微信委托代扣,BALANCE  余额代扣/余额,ALIPAY_PAPAY  支付宝商户代扣 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-papay
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpTradePayClient.URL_PAYMENT_PAPAY
     *
     * @param req ReqPaymentPapay实例
     * @return AjaxJson实例
     * @see NotifyPaymentPapay
     * @see ReqPaymentPapay
     * @see ResPaymentPapay
     */
    @PostMapping("/payTradeAction/paymentPapay")
    ResPaymentPapay paymentPapay(@RequestBody ReqPaymentPapay req);

    /**
     * 3.2.8. 支付结果查询 该接口提供所有消费/充值场景下的支付订单查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。需要调用查询接口的情况：商户后台、网络、服务器等出现异常，商户最终未接收到支付结果通知；调用支付接口后，返回系统错误或者未知交易、处理中交易状态情况。 请求地址https://accpapi.lianlianpay.com/v1/txn/query-payment
     * 接口类型：非异步通知接口
     * 地址常量：IAccpTradePayClient.URL_QUERY_PAYMENT
     *
     * @param req ReqQueryPayment实例
     * @return AjaxJson实例
     * @see ReqQueryPayment
     * @see ResQueryPayment
     */
    @PostMapping("/payTradeAction/queryPayment")
    ResQueryPayment queryPayment(@RequestBody ReqQueryPayment req);

    /**
     * 3.3. 提现 用户/平台商户将账户可用余额提现至开户绑定的银行账户。 3.3.1. 提现申请 请求地址https://accpapi.lianlianpay.com/v1/txn/withdrawal
     * 接口类型：非异步通知接口
     * 地址常量：IAccpTradePayClient.URL_WITHDRAWAL
     *
     * @param req ReqWithdrawal实例
     * @return AjaxJson实例
     * @see ReqWithdrawal
     * @see ResWithdrawal
     */
    @PostMapping("/payTradeAction/withdrawal")
    ResWithdrawal withdrawal(@RequestBody ReqWithdrawal req);

    /**
     * 3.3.2. 提现确认 请求地址https://accpapi.lianlianpay.com/v1/txn/withdrawal-check
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpTradePayClient.URL_WITHDRAWAL_CHECK
     *
     * @param req ReqWithdrawalCheck实例
     * @return AjaxJson实例
     * @see NotifyWithdrawalCheck
     * @see ReqWithdrawalCheck
     * @see ResWithdrawalCheck
     */
    @PostMapping("/payTradeAction/withdrawalCheck")
    ResWithdrawalCheck withdrawalCheck(@RequestBody ReqWithdrawalCheck req);

    /**
     * 3.3.4. 提现结果查询 该接口提供所有提现场景下的订单查询，包括提现及代发到银行账户；商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。需要调用查询接口的情况：商户后台、网络、服务器等出现异常，商户最终未接收到提现结果通知；调用提现及代发申请接口后，返回系统错误或者未知交易、处理中交易状态情况。 请求地址https://accpapi.lianlianpay.com/v1/txn/query-withdrawal
     * 接口类型：非异步通知接口
     * 地址常量：IAccpTradePayClient.URL_QUERY_WITHDRAWAL
     *
     * @param req ReqQueryWithdrawal实例
     * @return AjaxJson实例
     * @see ReqQueryWithdrawal
     * @see ResQueryWithdrawal
     */
    @PostMapping("/payTradeAction/queryWithdrawal")
    ResQueryWithdrawal queryWithdrawal(@RequestBody ReqQueryWithdrawal req);

    /**
     * 3.4. 代发 支持内部代发和外部代发两种场景交易。 内部代发：用户/平台商户A将账户可用余额代发至用户/平台商户B可用余额账户，业务场 景为佣金代发、红包奖励代发、供应商货款付款等。 外部代发：用户/平台商户A将账户可用余额代发至用户/平台商户B实体银行账户；业务场 景为佣金代发、红包奖励代发、供应商货款付款等。 3.4.1. 代发申请 请求地址https://accpapi.lianlianpay.com/v1/txn/transfer
     * 接口类型：非异步通知接口
     * 地址常量：IAccpTradePayClient.URL_TRANSFER
     *
     * @param req ReqTransfer实例
     * @return AjaxJson实例
     * @see ReqTransfer
     * @see ResTransfer
     */
    @PostMapping("/payTradeAction/transfer")
    ResTransfer transfer(@RequestBody ReqTransfer req);

    /**
     * 3.5. 担保 担保交易支持两类场景： 1.  在支付交易统一创单时指定担保收款方信息，担保确认时支持全额及部分金额多次确认； 对于创单时指定的担保收款方不支持确认时修改； 2.  在支付交易统一创单时不指定担保收款方信息，担保交易确认时动态指定收款方并进行 交易确认，资金从担保平台商户账户转入担保收款方账户。 3.5.1. 担保交易确认 请求地址https://accpapi.lianlianpay.com/v1/txn/secured-confirm
     * 接口类型：非异步通知接口
     * 地址常量：IAccpTradePayClient.URL_SECURED_CONFIRM
     *
     * @param req ReqSecuredConfirm实例
     * @return AjaxJson实例
     * @see ReqSecuredConfirm
     * @see ResSecuredConfirm
     */
    @PostMapping("/payTradeAction/securedConfirm")
    ResSecuredConfirm securedConfirm(@RequestBody ReqSecuredConfirm req);

    /**
     * 3.5.3. 担保交易信息查询 针对担保交易的担保支付和担保交易确认多次操作，为商户提供某笔担保单的真实收款方金 额确认情况以及多次确认、退款操作的查询接口。 注：本接口只用于担保支付获取该交易相关确认单、退款单等信息与卖方确认金额信息，具 体担保支付交易、担保确认交易的结果，请参考【支付结果查询】 与 【担保确认结果查询】。 请求地址https://accpapi.lianlianpay.com/v1/txn/secured-query
     * 接口类型：非异步通知接口
     * 地址常量：IAccpTradePayClient.URL_SECURED_QUERY
     *
     * @param req ReqSecuredQuery实例
     * @return AjaxJson实例
     * @see ReqSecuredQuery
     * @see ResSecuredQuery
     */
    @PostMapping("/payTradeAction/securedQuery")
    ResSecuredQuery securedQuery(@RequestBody ReqSecuredQuery req);

    /**
     * 3.6. 交易二次短信验证 对于充值、消费、提现、代发类交易需要二次进行付款方短信验证时，根据申请接口的交易 返回码进行判断，统一调用该接口完成交易验证。 请求地址https://accpapi.lianlianpay.com/v1/txn/validation-sms
     * 接口类型：非异步通知接口
     * 地址常量：IAccpTradePayClient.URL_VALIDATION_SMS
     *
     * @param req ReqValidationSms实例
     * @return AjaxJson实例
     * @see ReqValidationSms
     * @see ResValidationSms
     */
    @PostMapping("/payTradeAction/validationSms/white")
    ResValidationSms validationSms(@RequestBody ReqValidationSms req);

    /**
     * 3.7. 退款 3.7.1. 异步退款申请 该接口只支持普通消费交易、担保消费交易退款。 退款规则： 1.  每次发起退款只能唯一指定原消费交易一个收款方进行处理，分账类交易退款需要按照 收款方多次发起退款； 2.  支持全额或者部分退款； 3.  组合类消费交易，每次退款需要明确指定原付款方式对应的退款金额。 4.  异步退款申请在渠道真实退款结果获取之前状态为处理中，且该笔资金将被冻结 请求地址https://accpapi.lianlianpay.com/v1/txn/asyn-refund
     * 接口类型：非异步通知接口
     * 地址常量：IAccpTradePayClient.URL_ASYN_REFUND
     *
     * @param req ReqAsynRefund实例
     * @return AjaxJson实例
     * @see ReqAsynRefund
     * @see ResAsynRefund
     */
    @PostMapping("/payTradeAction/asynRefund")
    ResAsynRefund asynRefund(@RequestBody ReqAsynRefund req);

    /**
     * 3.7.2. 退款结果查询 该接口提供发起提现申请后的订单查询，商户可以通过该接口主动查询提现申请订单状态， 完成下一步的业务逻辑。 请求地址https://accpapi.lianlianpay.com/v1/txn/query-refund
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpTradePayClient.URL_QUERY_REFUND
     *
     * @param req ReqQueryRefund实例
     * @return AjaxJson实例
     * @see NotifyQueryRefund
     * @see ReqQueryRefund
     * @see ResQueryRefund
     */
    @PostMapping("/payTradeAction/queryRefund")
    ResQueryRefund queryRefund(@RequestBody ReqQueryRefund req);

}