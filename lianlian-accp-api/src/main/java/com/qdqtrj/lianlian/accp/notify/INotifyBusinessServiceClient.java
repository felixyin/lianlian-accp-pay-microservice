package com.qdqtrj.lianlian.accp.notify;

import com.qdqtrj.lianlian.accp.pojo.FeignErrorDecoder;
import com.qdqtrj.lianlian.accp.pojo.manage.dto.callback.*;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.callback.NotifyPaymentPapay;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.callback.NotifyQueryRefund;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.callback.NotifyWithdrawalCheck;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 支付异步回调feignclient
 *
 * @author yinbin
 */
@FeignClient(value = "notify-biz-server", fallbackFactory = NotifyBusinessServerHystrix.class, configuration = {FeignErrorDecoder.class})
public interface INotifyBusinessServiceClient {

    /**
     * 3.3. 个人用户开户 3.3.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-individual
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_OPENACCT_APPLY_INDIVIDUAL
     *
     * @param req NotifyOpenacctVerifyIndividual
     * @see NotifyOpenacctVerifyIndividual
     */
    @PostMapping("/order/notifybiz/openacctVerifyIndividual")
    boolean openacctVerifyIndividual(@RequestBody NotifyOpenacctVerifyIndividual req);

    /**
     * 3.4. 企业用户开户 3.4.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-enterprise
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_OPENACCT_APPLY_ENTERPRISE
     *
     * @param req NotifyOpenacctApplyEnterprise
     * @see NotifyOpenacctApplyEnterprise
     */
    @PostMapping("/order/notifybiz/openacctApplyEnterprise")
    boolean openacctApplyEnterprise(@RequestBody NotifyOpenacctApplyEnterprise req);

    /**
     * 3.5. 个人用户新增绑卡 3.5.1. 绑卡申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/individual-bindcard-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * <p>
     * 地址常量：IAccpManagePayClient.URL_INDIVIDUAL_BINDCARD_APPLY
     *
     * @param req NotifyIndividualBindcardVerify
     * @see NotifyIndividualBindcardVerify
     */
    @PostMapping("/order/notifybiz/individualBindcardVerify")
    boolean individualBindcardVerify(@RequestBody NotifyIndividualBindcardVerify req);

    /**
     * 3.6. 企业用户更换绑定账号 3.6.1. 更换申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/enterprise-changecard-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_ENTERPRISE_CHANGECARD_APPLY
     *
     * @param req NotifyEnterpriseChangecardApply
     * @see NotifyEnterpriseChangecardApply
     */
    @PostMapping("/order/notifybiz/enterpriseChangecardApply")
    boolean enterpriseChangecardApply(@RequestBody NotifyEnterpriseChangecardApply req);

    /**
     * 3.7. 个人用户解绑银行卡 3.7.1. 解绑 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/unlinkedacct-ind-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_UNLINKEDACCT_IND_APPLY
     *
     * @param req NotifyUnlinkedacctIndApply
     * @see NotifyUnlinkedacctIndApply
     */
    @PostMapping("/order/notifybiz/unlinkedacctIndApply")
    boolean unlinkedacctIndApply(@RequestBody NotifyUnlinkedacctIndApply req);

    /**
     * 3.8. 个人用户修改预留手机号 3.8.1. 申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-linkedphone-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_CHANGE_LINKEDPHONE_APPLY
     *
     * @param req NotifyChangeLinkedphoneVerify
     * @see NotifyChangeLinkedphoneVerify
     */
    @PostMapping("/order/notifybiz/changeLinkedphoneVerify")
    boolean changeLinkedphoneVerify(@RequestBody NotifyChangeLinkedphoneVerify req);

    /**
     * 3.9. 修改绑定手机 3.9.1. 申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-regphone-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_CHANGE_REGPHONE_APPLY
     *
     * @param req NotifyChangeRegphoneVerify
     * @see NotifyChangeRegphoneVerify
     */
    @PostMapping("/order/notifybiz/changeRegphoneVerify")
    boolean changeRegphoneVerify(@RequestBody NotifyChangeRegphoneVerify req);

    /**
     * 3.10. 销户 3.10.1.  销户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/cancel-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_CANCEL_APPLY
     *
     * @param req NotifyCancelApply
     * @see NotifyCancelApply
     */
    @PostMapping("/order/notifybiz/cancelApply")
    boolean cancelApply(@RequestBody NotifyCancelApply req);

    /**
     * 3.2.5. 委托代扣 微信、支付宝提交委托代扣，通过使用与用户签约获得的微信、支付宝代扣协议号，由商户后台调用该接口发起创单并支付，暂不支持组合支付。 支持以下主支付方式： 编码  描述,WECHAT_PAPAY  微信委托代扣,BALANCE  余额代扣/余额,ALIPAY_PAPAY  支付宝商户代扣 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-papay
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpTradePayClient.URL_PAYMENT_PAPAY
     *
     * @param req NotifyPaymentPapay
     * @see NotifyPaymentPapay
     */
    @PostMapping("/order/notifybiz/paymentPapay")
    boolean paymentPapay(@RequestBody NotifyPaymentPapay req);

    /**
     * 3.3. 提现 用户/平台商户将账户可用余额提现至开户绑定的银行账户。 3.3.1. 提现申请 请求地址https://accpapi.lianlianpay.com/v1/txn/withdrawal
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpTradePayClient.URL_WITHDRAWAL
     *
     * @param req NotifyWithdrawalCheck
     * @see NotifyWithdrawalCheck
     */
    @PostMapping("/order/notifybiz/withdrawalCheck")
    boolean withdrawalCheck(@RequestBody NotifyWithdrawalCheck req);

    /**
     * 3.7. 退款 3.7.1. 异步退款申请 该接口只支持普通消费交易、担保消费交易退款。 退款规则： 1.  每次发起退款只能唯一指定原消费交易一个收款方进行处理，分账类交易退款需要按照 收款方多次发起退款； 2.  支持全额或者部分退款； 3.  组合类消费交易，每次退款需要明确指定原付款方式对应的退款金额。 4.  异步退款申请在渠道真实退款结果获取之前状态为处理中，且该笔资金将被冻结 请求地址https://accpapi.lianlianpay.com/v1/txn/asyn-refund
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpTradePayClient.URL_ASYN_REFUND
     *
     * @see NotifyQueryRefund
     */
    @PostMapping("/order/notifybiz/queryRefund")
    boolean queryRefund(@RequestBody NotifyQueryRefund req);

}
