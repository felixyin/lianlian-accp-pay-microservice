package com.qdqtrj.lianlian.accp.action;

import com.qdqtrj.lianlian.accp.api.notify.AbstractAccpNotifyCallback;
import com.qdqtrj.lianlian.accp.api.notify.AccpNotifyHelper;
import com.qdqtrj.lianlian.accp.config.AccpConstants;
import com.qdqtrj.lianlian.accp.config.AccpPayConfig;
import com.qdqtrj.lianlian.accp.notify.INotifyBusinessServiceClient;
import com.qdqtrj.lianlian.accp.pojo.manage.dto.callback.*;
import com.qdqtrj.lianlian.accp.pojo.manage.dto.request.*;
import com.qdqtrj.lianlian.accp.pojo.manage.dto.response.*;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.callback.NotifyPaymentPapay;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.callback.NotifyQueryRefund;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.callback.NotifyWithdrawalCheck;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqPaymentPapay;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqQueryRefund;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqWithdrawalCheck;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResPaymentPapay;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResQueryRefund;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResWithdrawalCheck;
import com.qdqtrj.lianlian.accp.service.AsyncMongoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 本微服务提供的异步通知url
 * 业务系统可以自己传递notifyurl，替换掉本微服务提供的实现
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@RestController
@RequestMapping("/payNotifyAction")
@Api(value = "PayNotifyAction", tags = "连连ACCP回调《异步通知》接口参考")
public class PayNotifyAction {

    /**
     * 配置对象
     */
    @Resource
    private AccpPayConfig payConfig;

    /**
     * 业务服务
     */
    @Resource
    private INotifyBusinessServiceClient notifyClient;

    /**
     * 异步写库mongodb
     */
    @Resource
    private AsyncMongoService asyncMongoService;

    /**
     * 3.3.2. 开户验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-verify-individual
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IaccpManagePayClient.URL_OPENACCT_VERIFY_INDIVIDUAL
     *
     * @param headers Map<String, String>
     * @param body    String
     * @see NotifyOpenacctVerifyIndividual
     * @see ReqOpenacctVerifyIndividual
     * @see ResOpenacctVerifyIndividual
     */
    @ApiOperation(value = "3.3.2. 开户验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-verify-individual",
            response = String.class, responseContainer = "SUCCESS/FAILED")
    @ApiImplicitParam(value = "加密的报文", dataTypeClass = String.class)
    @PostMapping("openacctVerifyIndividual/white")
    public String openacctVerifyIndividual(@RequestHeader Map<String, String> headers, @RequestBody String body) {
        String signatureData = headers.get(AccpConstants.NOTIFY_SIGNATURE_DATA);
        return AccpNotifyHelper.doNotify(payConfig, signatureData, body, NotifyOpenacctVerifyIndividual.class, new AbstractAccpNotifyCallback<NotifyOpenacctVerifyIndividual>() {
            @Override
            protected boolean businessCode(NotifyOpenacctVerifyIndividual notifyBean) {
                boolean isBizSuccess = notifyClient.openacctVerifyIndividual(notifyBean);
                notifyBean.setBizSuccess(isBizSuccess);
                asyncMongoService.update(notifyBean);
                return isBizSuccess;
            }
        });
    }

    /**
     * 3.4. 企业用户开户 3.4.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-enterprise
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IaccpManagePayClient.URL_OPENACCT_APPLY_ENTERPRISE
     *
     * @param headers Map<String, String>
     * @param body    String
     * @see NotifyOpenacctApplyEnterprise
     * @see ReqOpenacctApplyEnterprise
     * @see ResOpenacctApplyEnterprise
     */
    @ApiOperation(value = "3.4. 企业用户开户 3.4.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-enterprise",
            response = String.class, responseContainer = "SUCCESS/FAILED")
    @ApiImplicitParam(value = "加密的报文", dataTypeClass = String.class)
    @PostMapping("openacctApplyEnterprise/white")
    public String openacctApplyEnterprise(@RequestHeader Map<String, String> headers, @RequestBody String body) {
        String signatureData = headers.get(AccpConstants.NOTIFY_SIGNATURE_DATA);
        return AccpNotifyHelper.doNotify(payConfig, signatureData, body, NotifyOpenacctApplyEnterprise.class, new AbstractAccpNotifyCallback<NotifyOpenacctApplyEnterprise>() {
            @Override
            protected boolean businessCode(NotifyOpenacctApplyEnterprise notifyBean) {
                boolean isBizSuccess = notifyClient.openacctApplyEnterprise(notifyBean);
                notifyBean.setBizSuccess(isBizSuccess);
                asyncMongoService.update(notifyBean);
                return isBizSuccess;
            }
        });
    }

    /**
     * 3.5.2. 绑卡验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/individual-bindcard-verify
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IaccpManagePayClient.URL_INDIVIDUAL_BINDCARD_VERIFY
     *
     * @param headers Map<String, String>
     * @param body    String
     * @see NotifyIndividualBindcardVerify
     * @see ReqIndividualBindcardVerify
     * @see ResIndividualBindcardVerify
     */
    @ApiOperation(value = "3.5.2. 绑卡验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/individual-bindcard-verify",
            response = String.class, responseContainer = "SUCCESS/FAILED")
    @ApiImplicitParam(value = "加密的报文", dataTypeClass = String.class)
    @PostMapping("individualBindcardVerify/white")
    public String individualBindcardVerify(@RequestHeader Map<String, String> headers, @RequestBody String body) {
        String signatureData = headers.get(AccpConstants.NOTIFY_SIGNATURE_DATA);
        return AccpNotifyHelper.doNotify(payConfig, signatureData, body, NotifyIndividualBindcardVerify.class, new AbstractAccpNotifyCallback<NotifyIndividualBindcardVerify>() {
            @Override
            protected boolean businessCode(NotifyIndividualBindcardVerify notifyBean) {
                boolean isBizSuccess = notifyClient.individualBindcardVerify(notifyBean);
                notifyBean.setBizSuccess(isBizSuccess);
                asyncMongoService.update(notifyBean);
                return isBizSuccess;
            }
        });
    }

    /**
     * 3.6. 企业用户更换绑定账号 3.6.1. 更换申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/enterprise-changecard-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IaccpManagePayClient.URL_ENTERPRISE_CHANGECARD_APPLY
     *
     * @param headers Map<String, String>
     * @param body    String
     * @see NotifyEnterpriseChangecardApply
     * @see ReqEnterpriseChangecardApply
     * @see ResEnterpriseChangecardApply
     */
    @ApiOperation(value = "3.6. 企业用户更换绑定账号 3.6.1. 更换申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/enterprise-changecard-apply",
            response = String.class, responseContainer = "SUCCESS/FAILED")
    @ApiImplicitParam(value = "加密的报文", dataTypeClass = String.class)
    @PostMapping("enterpriseChangecardApply/white")
    public String enterpriseChangecardApply(@RequestHeader Map<String, String> headers, @RequestBody String body) {
        String signatureData = headers.get(AccpConstants.NOTIFY_SIGNATURE_DATA);
        return AccpNotifyHelper.doNotify(payConfig, signatureData, body, NotifyEnterpriseChangecardApply.class, new AbstractAccpNotifyCallback<NotifyEnterpriseChangecardApply>() {
            @Override
            protected boolean businessCode(NotifyEnterpriseChangecardApply notifyBean) {
                boolean isBizSuccess = notifyClient.enterpriseChangecardApply(notifyBean);
                notifyBean.setBizSuccess(isBizSuccess);
                asyncMongoService.update(notifyBean);
                return isBizSuccess;
            }
        });
    }

    /**
     * 3.7. 个人用户解绑银行卡 3.7.1. 解绑 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/unlinkedacct-ind-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IaccpManagePayClient.URL_UNLINKEDACCT_IND_APPLY
     *
     * @param headers Map<String, String>
     * @param body    String
     * @see NotifyUnlinkedacctIndApply
     * @see ReqUnlinkedacctIndApply
     * @see ResUnlinkedacctIndApply
     */
    @ApiOperation(value = "3.7. 个人用户解绑银行卡 3.7.1. 解绑 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/unlinkedacct-ind-apply",
            response = String.class, responseContainer = "SUCCESS/FAILED")
    @ApiImplicitParam(value = "加密的报文", dataTypeClass = String.class)
    @PostMapping("unlinkedacctIndApply/white")
    public String unlinkedacctIndApply(@RequestHeader Map<String, String> headers, @RequestBody String body) {
        String signatureData = headers.get(AccpConstants.NOTIFY_SIGNATURE_DATA);
        return AccpNotifyHelper.doNotify(payConfig, signatureData, body, NotifyUnlinkedacctIndApply.class, new AbstractAccpNotifyCallback<NotifyUnlinkedacctIndApply>() {
            @Override
            protected boolean businessCode(NotifyUnlinkedacctIndApply notifyBean) {
                boolean isBizSuccess = notifyClient.unlinkedacctIndApply(notifyBean);
                asyncMongoService.update(notifyBean);
                return isBizSuccess;
            }
        });
    }

    /**
     * 3.8.2. 验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-linkedphone-verify
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IaccpManagePayClient.URL_CHANGE_LINKEDPHONE_VERIFY
     *
     * @param headers Map<String, String>
     * @param body    String
     * @see NotifyChangeLinkedphoneVerify
     * @see ReqChangeLinkedphoneVerify
     * @see ResChangeLinkedphoneVerify
     */
    @ApiOperation(value = "3.8.2. 验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-linkedphone-verify",
            response = String.class, responseContainer = "SUCCESS/FAILED")
    @ApiImplicitParam(value = "加密的报文", dataTypeClass = String.class)
    @PostMapping("changeLinkedphoneVerify/white")
    public String changeLinkedphoneVerify(@RequestHeader Map<String, String> headers, @RequestBody String body) {
        String signatureData = headers.get(AccpConstants.NOTIFY_SIGNATURE_DATA);
        return AccpNotifyHelper.doNotify(payConfig, signatureData, body, NotifyChangeLinkedphoneVerify.class, new AbstractAccpNotifyCallback<NotifyChangeLinkedphoneVerify>() {
            @Override
            protected boolean businessCode(NotifyChangeLinkedphoneVerify notifyBean) {
                boolean isBizSuccess = notifyClient.changeLinkedphoneVerify(notifyBean);
                notifyBean.setBizSuccess(isBizSuccess);
                asyncMongoService.update(notifyBean);
                return isBizSuccess;
            }
        });
    }

    /**
     * 3.9.2. 验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-regphone-verify
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IaccpManagePayClient.URL_CHANGE_REGPHONE_VERIFY
     *
     * @param headers Map<String, String>
     * @param body    String
     * @see NotifyChangeRegphoneVerify
     * @see ReqChangeRegphoneVerify
     * @see ResChangeRegphoneVerify
     */
    @ApiOperation(value = "3.9.2. 验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-regphone-verify",
            response = String.class, responseContainer = "SUCCESS/FAILED")
    @ApiImplicitParam(value = "加密的报文", dataTypeClass = String.class)
    @PostMapping("changeRegphoneVerify/white")
    public String changeRegphoneVerify(@RequestHeader Map<String, String> headers, @RequestBody String body) {
        String signatureData = headers.get(AccpConstants.NOTIFY_SIGNATURE_DATA);
        return AccpNotifyHelper.doNotify(payConfig, signatureData, body, NotifyChangeRegphoneVerify.class, new AbstractAccpNotifyCallback<NotifyChangeRegphoneVerify>() {
            @Override
            protected boolean businessCode(NotifyChangeRegphoneVerify notifyBean) {
                boolean isBizSuccess = notifyClient.changeRegphoneVerify(notifyBean);
                notifyBean.setBizSuccess(isBizSuccess);
                asyncMongoService.update(notifyBean);
                return isBizSuccess;
            }
        });
    }

    /**
     * 3.10. 销户 3.10.1.  销户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/cancel-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IaccpManagePayClient.URL_CANCEL_APPLY
     *
     * @param headers Map<String, String>
     * @param body    String
     * @see NotifyCancelApply
     * @see ReqCancelApply
     * @see ResCancelApply
     */
    @ApiOperation(value = "3.10. 销户 3.10.1.  销户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/cancel-apply",
            response = String.class, responseContainer = "SUCCESS/FAILED")
    @ApiImplicitParam(value = "加密的报文", dataTypeClass = String.class)
    @PostMapping("cancelApply/white")
    public String cancelApply(@RequestHeader Map<String, String> headers, @RequestBody String body) {
        String signatureData = headers.get(AccpConstants.NOTIFY_SIGNATURE_DATA);
        return AccpNotifyHelper.doNotify(payConfig, signatureData, body, NotifyCancelApply.class, new AbstractAccpNotifyCallback<NotifyCancelApply>() {
            @Override
            protected boolean businessCode(NotifyCancelApply notifyBean) {
                boolean isBizSuccess = notifyClient.cancelApply(notifyBean);
                notifyBean.setBizSuccess(isBizSuccess);
                asyncMongoService.update(notifyBean);
                return isBizSuccess;
            }
        });
    }

    /**
     * 3.2.5. 委托代扣 微信、支付宝提交委托代扣，通过使用与用户签约获得的微信、支付宝代扣协议号，由商户后台调用该接口发起创单并支付，暂不支持组合支付。 支持以下主支付方式： 编码  描述,WECHAT_PAPAY  微信委托代扣,BALANCE  余额代扣/余额,ALIPAY_PAPAY  支付宝商户代扣 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-papay
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IaccpTradePayClient.URL_PAYMENT_PAPAY
     *
     * @param headers Map<String, String>
     * @param body    String
     * @see NotifyPaymentPapay
     * @see ReqPaymentPapay
     * @see ResPaymentPapay
     */
    @ApiOperation(value = "3.2.5. 委托代扣 微信、支付宝提交委托代扣，通过使用与用户签约获得的微信、支付宝代扣协议号，由商户后台调用该接口发起创单并支付，暂不支持组合支付。 " +
            "支持以下主支付方式： 编码  描述,WECHAT_PAPAY  微信委托代扣,BALANCE  余额代扣/余额,ALIPAY_PAPAY  支付宝商户代扣 " +
            "请求地址https://accpapi.lianlianpay.com/v1/txn/payment-papay",
            response = String.class, responseContainer = "SUCCESS/FAILED")
    @ApiImplicitParam(value = "加密的报文", dataTypeClass = String.class)
    @PostMapping("paymentPapay/white")
    public String paymentPapay(@RequestHeader Map<String, String> headers, @RequestBody String body) {
        String signatureData = headers.get(AccpConstants.NOTIFY_SIGNATURE_DATA);
        return AccpNotifyHelper.doNotify(payConfig, signatureData, body, NotifyPaymentPapay.class, new AbstractAccpNotifyCallback<NotifyPaymentPapay>() {
            @Override
            protected boolean businessCode(NotifyPaymentPapay notifyBean) {
                boolean isBizSuccess = notifyClient.paymentPapay(notifyBean);
                notifyBean.setBizSuccess(isBizSuccess);
                asyncMongoService.update(notifyBean);
                return isBizSuccess;
            }
        });
    }

    /**
     * 3.3.2. 提现确认 请求地址https://accpapi.lianlianpay.com/v1/txn/withdrawal-check
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IaccpTradePayClient.URL_WITHDRAWAL_CHECK
     *
     * @param headers Map<String, String>
     * @param body    String
     * @see NotifyWithdrawalCheck
     * @see ReqWithdrawalCheck
     * @see ResWithdrawalCheck
     */
    @ApiOperation(value = "3.3.2. 提现确认 请求地址https://accpapi.lianlianpay.com/v1/txn/withdrawal-check",
            response = String.class, responseContainer = "SUCCESS/FAILED")
    @ApiImplicitParam(value = "加密的报文", dataTypeClass = String.class)
    @PostMapping("withdrawalCheck/white")
    public String withdrawalCheck(@RequestHeader Map<String, String> headers, @RequestBody String body) {
        String signatureData = headers.get(AccpConstants.NOTIFY_SIGNATURE_DATA);
        return AccpNotifyHelper.doNotify(payConfig, signatureData, body, NotifyWithdrawalCheck.class, new AbstractAccpNotifyCallback<NotifyWithdrawalCheck>() {
            @Override
            protected boolean businessCode(NotifyWithdrawalCheck notifyBean) {
                boolean isBizSuccess = notifyClient.withdrawalCheck(notifyBean);
                notifyBean.setBizSuccess(isBizSuccess);
                asyncMongoService.update(notifyBean);
                return isBizSuccess;
            }
        });
    }

    /**
     * 3.7.2. 退款结果查询 该接口提供发起提现申请后的订单查询，商户可以通过该接口主动查询提现申请订单状态， 完成下一步的业务逻辑。 请求地址https://accpapi.lianlianpay.com/v1/txn/query-refund
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IaccpTradePayClient.URL_QUERY_REFUND
     *
     * @param headers Map<String, String>
     * @param body    String
     * @see NotifyQueryRefund
     * @see ReqQueryRefund
     * @see ResQueryRefund
     */
    @ApiOperation(value = "3.7.2. 退款结果查询 该接口提供发起提现申请后的订单查询，商户可以通过该接口主动查询提现申请订单状态， 完成下一步的业务逻辑。 " +
            "请求地址https://accpapi.lianlianpay.com/v1/txn/query-refund",
            response = String.class, responseContainer = "SUCCESS/FAILED")
    @ApiImplicitParam(value = "加密的报文", dataTypeClass = String.class)
    @PostMapping("queryRefund/white")
    public String queryRefund(@RequestHeader Map<String, String> headers, @RequestBody String body) {
        String signatureData = headers.get(AccpConstants.NOTIFY_SIGNATURE_DATA);
        return AccpNotifyHelper.doNotify(payConfig, signatureData, body, NotifyQueryRefund.class, new AbstractAccpNotifyCallback<NotifyQueryRefund>() {
            @Override
            protected boolean businessCode(NotifyQueryRefund notifyBean) {
                boolean isBizSuccess = notifyClient.queryRefund(notifyBean);
                notifyBean.setBizSuccess(isBizSuccess);
                asyncMongoService.update(notifyBean);
                return isBizSuccess;
            }
        });
    }


}
