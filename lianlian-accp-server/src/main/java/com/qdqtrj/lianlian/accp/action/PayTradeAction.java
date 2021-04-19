package com.qdqtrj.lianlian.accp.action;

import com.qdqtrj.lianlian.accp.api.IaccpTradePayClient;
import com.qdqtrj.lianlian.accp.api.impl.AccpTradePayClientImpl;
import com.qdqtrj.lianlian.accp.config.AccpConstants;
import com.qdqtrj.lianlian.accp.config.AccpPayConfig;
import com.qdqtrj.lianlian.accp.exception.AccpPayException;
import com.qdqtrj.lianlian.accp.exception.AccpReqParamException;
import com.qdqtrj.lianlian.accp.pojo.AccpPayRecordModel;
import com.qdqtrj.lianlian.accp.pojo.GenRiskItemHelper;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.request.*;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.response.*;
import com.qdqtrj.lianlian.accp.service.AsyncMongoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * 包括所有 doc/《ACCP产品商户接口说明书-交易类（API）V1.4-标准版.pdf》 接口
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Slf4j
@RestController
@RequestMapping("/payTradeAction")
@Api(value = "PayTradeAction", tags = "支付微服务《交易类》API接口")
public class PayTradeAction {

    /**
     * accp交易类接口
     */
    @Resource(type = AccpTradePayClientImpl.class)
    private IaccpTradePayClient tradeClient;

    /**
     * accp支付配置
     */
    @Resource
    private AccpPayConfig payConfig;

    /**
     * web端口
     */
    @Value("${server.port}")
    private String port;

    /**
     * 异步写库
     */
    @Resource
    private AsyncMongoService asyncMongoService;

    /**
     * 如果业务系统没有传递notifyurl，则启用本服务提供的
     *
     * @param thisNotifyUrl
     */
    private String getDefaultNotifyUrl(String thisNotifyUrl) {
        String notifyUrl = this.payConfig.getThisDomainServerUrl() + "/payNotifyAction/" + thisNotifyUrl + "/white";
        log.info("accp->异步通知url：{}", notifyUrl);
        return notifyUrl;
    }


    /**
     * 3.2. 充值/消费 3.2.1. 支付统一创单 商户在充值/消费交易模式一场景下使用，先通过该接口完成支付统一创单，后续根据业务 场景调用不同的支付接口完成付款。 请求地址https://accpapi.lianlianpay.com/v1/txn/tradecreate
     * 接口类型：异步通知接口
     * 地址常量：AccpConstants.URL_TRADECREATE
     *
     * @param req ReqTradecreate实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqTradecreate
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResTradecreate
     */
    @ApiOperation(value = "3.2. 充值/消费 3.2.1. 支付统一创单 商户在充值/消费交易模式一场景下使用，先通过该接口完成支付统一创单，" +
            "后续根据业务 场景调用不同的支付接口完成付款。 请求地址https://accpapi.lianlianpay.com/v1/txn/tradecreate",
            response = ResTradecreate.class)
    @ApiImplicitParam(dataTypeClass = ReqTradecreate.class)
    @PostMapping("tradecreate/white")
    public ResTradecreate tradecreate(@RequestBody ReqTradecreate req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isBlank(req.getNotify_url())) {
            req.setNotify_url(getDefaultNotifyUrl("paymentPapay"));
        }
        ResTradecreate res = this.tradeClient.tradecreate(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_TRADECREATE, "3.2.1. 充值/消费-支付统一创单 ", req, res, sourceIp));
        return res;
    }

    /**
     * 3.2.2. 余额支付 用户账户余额支付接口，支持附加优惠券组合支付。 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-balance
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_PAYMENT_BALANCE
     *
     * @param req ReqPaymentBalance实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqPaymentBalance
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResPaymentBalance
     */

    @ApiOperation(value = "3.2.2. 余额支付 用户账户余额支付接口，支持附加优惠券组合支付。" +
            " 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-balance", response = ResPaymentBalance.class)
    @ApiImplicitParam(dataTypeClass = ReqPaymentBalance.class)
    @PostMapping("paymentBalance/white")
    public ResPaymentBalance paymentBalance(@RequestBody ReqPaymentBalance req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isEmpty(req.getRisk_item())) {
            req.setRisk_item(GenRiskItemHelper.randomRiskItem());
        }
        ResPaymentBalance res = this.tradeClient.paymentBalance(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_PAYMENT_BALANCE, "3.2.2. 余额支付", req, res, sourceIp));
        return res;
    }

    /**
     * 3.2.3. 银行卡快捷支付 银行卡快捷支付接口，支持附加优惠券、余额组合支付；适用于如下几种场景：   已开户绑定银行卡的个人用户支付   未注册的匿名用户首次/历次支付 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-bankcard
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_PAYMENT_BANKCARD
     *
     * @param req ReqPaymentBankcard实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqPaymentBankcard
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResPaymentBankcard
     */

    @ApiOperation(value = " 3.2.3. 银行卡快捷支付 银行卡快捷支付接口，支持附加优惠券、余额组合支付；适用于如下几种场景： " +
            "  已开户绑定银行卡的个人用户支付 " +
            "  未注册的匿名用户首次/历次支付 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-bankcard",
            response = ResPaymentBankcard.class)
    @ApiImplicitParam(dataTypeClass = ReqPaymentBankcard.class)
    @PostMapping("paymentBankcard")
    public ResPaymentBankcard paymentBankcard(@RequestBody ReqPaymentBankcard req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isEmpty(req.getRisk_item())) {
            req.setRisk_item(GenRiskItemHelper.randomRiskItem());
        }
        ResPaymentBankcard res = this.tradeClient.paymentBankcard(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_PAYMENT_BANKCARD, "3.2.3. 银行卡快捷支付", req, res, sourceIp));
        return res;
    }

    /**
     * 3.2.4. 网关类支付 微信、支付宝、网银类支付申请，通过该接口唤起相应渠道的支付界面，用户确认并支付，支持附加优惠券、余额组合支付。支持以下主支付方式：==== 编码  描述,EBANK_DEBIT_CARD  网银借记卡,EBANK_CREDIT_CARD  网银信用卡,EBANK_B2B  企业网银,WECHAT_APP  微信APP,WECHAT_JSAPI  微信公众号,WECHAT_NATIVE  微信扫码,WECHAT_H5  微信H5,WECHAT_WXA  微信小程序,ALIPAY_NATIVE  支付宝扫码,ALIPAY_APP  支付宝APP,ALIPAY_H5  支付宝H5,ALIPAY_WEB  支付宝WEB,ALIPAY_WXA  支付宝小程序,ALIPAY_NATIVE_ICBC  支付宝扫码-工银e支付,WECHAT_NATIVE_ICBC  微信扫码-工银e支付 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-gw
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_PAYMENT_GW
     *
     * @param req ReqPaymentGw实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqPaymentGw
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResPaymentGw
     */

    @ApiOperation(value = "3.2.4. 网关类支付 微信、支付宝、网银类支付申请，通过该接口唤起相应渠道的支付界面，用户确认并支付，" +
            "支持附加优惠券、余额组合支付。支持以下主支付方式：==== 编码  描述,EBANK_DEBIT_CARD  网银借记卡,EBANK_CREDIT_CARD  " +
            "网银信用卡,EBANK_B2B  企业网银,WECHAT_APP  微信APP,WECHAT_JSAPI  微信公众号,WECHAT_NATIVE  微信扫码,WECHAT_H5  " +
            "微信H5,WECHAT_WXA  微信小程序,ALIPAY_NATIVE  支付宝扫码,ALIPAY_APP  支付宝APP,ALIPAY_H5  支付宝H5,ALIPAY_WEB  " +
            "支付宝WEB,ALIPAY_WXA  支付宝小程序,ALIPAY_NATIVE_ICBC  支付宝扫码-工银e支付,WECHAT_NATIVE_ICBC  微信扫码-工银e支付 " +
            "请求地址https://accpapi.lianlianpay.com/v1/txn/payment-gw",
            response = ResPaymentGw.class)
    @ApiImplicitParam(dataTypeClass = ReqPaymentGw.class)
    @PostMapping("paymentGw/white")
    public ResPaymentGw paymentGw(@RequestBody ReqPaymentGw req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isEmpty(req.getRisk_item())) {
            req.setRisk_item(GenRiskItemHelper.randomRiskItem());
        }
        ResPaymentGw res = this.tradeClient.paymentGw(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_PAYMENT_GW, "3.2.4. 网关类支付", req, res, sourceIp));
        return res;
    }

    /**
     * 3.2.5. 委托代扣 微信、支付宝提交委托代扣，通过使用与用户签约获得的微信、支付宝代扣协议号，由商户后台调用该接口发起创单并支付，暂不支持组合支付。 支持以下主支付方式： 编码  描述,WECHAT_PAPAY  微信委托代扣,BALANCE  余额代扣/余额,ALIPAY_PAPAY  支付宝商户代扣 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-papay 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：AccpConstants.URL_PAYMENT_PAPAY
     *
     * @param req ReqPaymentPapay实例
     * @return ResponseEntity实例
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.callback.NotifyPaymentPapay
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqPaymentPapay
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResPaymentPapay
     */
    @ApiOperation(value = "3.2.5. 委托代扣 微信、支付宝提交委托代扣，通过使用与用户签约获得的微信、支付宝代扣协议号，" +
            "由商户后台调用该接口发起创单并支付，暂不支持组合支付。 " +
            "支持以下主支付方式： 编码  描述,WECHAT_PAPAY  微信委托代扣,BALANCE  余额代扣/余额,ALIPAY_PAPAY  支付宝商户代扣 " +
            "请求地址https://accpapi.lianlianpay.com/v1/txn/payment-papay " +
            "接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；" +
            "否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。 ",
            response = ResPaymentPapay.class)
    @ApiImplicitParam(dataTypeClass = ReqPaymentPapay.class)
    @PostMapping("paymentPapay")
    public ResPaymentPapay paymentPapay(@RequestBody ReqPaymentPapay req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isBlank(req.getNotify_url())) {
            req.setNotify_url(getDefaultNotifyUrl("paymentPapay"));
        }
        if (isEmpty(req.getRisk_item())) {
            req.setRisk_item(GenRiskItemHelper.randomRiskItem());
        }
        ResPaymentPapay res = this.tradeClient.paymentPapay(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_PAYMENT_PAPAY, "3.2.5. 委托代扣", req, res, sourceIp));
        return res;
    }

    /**
     * 3.2.8. 支付结果查询 该接口提供所有消费/充值场景下的支付订单查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。需要调用查询接口的情况：商户后台、网络、服务器等出现异常，商户最终未接收到支付结果通知；调用支付接口后，返回系统错误或者未知交易、处理中交易状态情况。 请求地址https://accpapi.lianlianpay.com/v1/txn/query-payment
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_QUERY_PAYMENT
     *
     * @param req ReqQueryPayment实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqQueryPayment
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResQueryPayment
     */
    @ApiOperation(value = " 3.2.8. 支付结果查询 该接口提供所有消费/充值场景下的支付订单查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。" +
            "需要调用查询接口的情况：商户后台、网络、服务器等出现异常，商户最终未接收到支付结果通知；调用支付接口后，返回系统错误或者未知交易、处理中交易状态情况。 " +
            "请求地址https://accpapi.lianlianpay.com/v1/txn/query-payment",
            response = ResQueryPayment.class)
    @ApiImplicitParam(dataTypeClass = ReqQueryPayment.class)
    @PostMapping("queryPayment")
    public ResQueryPayment queryPayment(@RequestBody ReqQueryPayment req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResQueryPayment res = this.tradeClient.queryPayment(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_QUERY_PAYMENT, "3.2.8. 支付结果查询", req, res, sourceIp));
        return res;
    }

    /**
     * 3.3. 提现 用户/平台商户将账户可用余额提现至开户绑定的银行账户。 3.3.1. 提现申请 请求地址https://accpapi.lianlianpay.com/v1/txn/withdrawal
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：AccpConstants.URL_WITHDRAWAL
     *
     * @param req ReqWithdrawal实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqWithdrawal
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResWithdrawal
     */
    @ApiOperation(value = "3.3. 提现 用户/平台商户将账户可用余额提现至开户绑定的银行账户。 " +
            "3.3.1. 提现申请 请求地址https://accpapi.lianlianpay.com/v1/txn/withdrawal",
            response = ResWithdrawal.class)
    @ApiImplicitParam(dataTypeClass = ReqWithdrawal.class)
    @PostMapping("withdrawal")
    public ResWithdrawal withdrawal(@RequestBody ReqWithdrawal req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isBlank(req.getNotify_url())) {
            req.setNotify_url(getDefaultNotifyUrl("withdrawalCheck"));
        }
        if (isEmpty(req.getRisk_item())) {
            req.setRisk_item(GenRiskItemHelper.randomRiskItem());
        }
        ResWithdrawal res = this.tradeClient.withdrawal(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_WITHDRAWAL, "3.3.1. 提现申请", req, res, sourceIp));
        return res;
    }

    /**
     * 3.3.2. 提现确认 请求地址https://accpapi.lianlianpay.com/v1/txn/withdrawal-check
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_WITHDRAWAL_CHECK
     *
     * @param req ReqWithdrawalCheck实例
     * @return ResponseEntity实例
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.callback.NotifyWithdrawalCheck
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqWithdrawalCheck
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResWithdrawalCheck
     */
    @ApiOperation(value = "3.3.2. 提现确认 请求地址https://accpapi.lianlianpay.com/v1/txn/withdrawal-check",
            response = ResWithdrawalCheck.class)
    @ApiImplicitParam(dataTypeClass = ReqWithdrawalCheck.class)
    @PostMapping("withdrawalCheck")
    public ResWithdrawalCheck withdrawalCheck(@RequestBody ReqWithdrawalCheck req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResWithdrawalCheck res = this.tradeClient.withdrawalCheck(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_WITHDRAWAL_CHECK, "3.3.2. 提现确认", req, res, sourceIp));
        return res;
    }

    /**
     * 3.3.4. 提现结果查询 该接口提供所有提现场景下的订单查询，包括提现及代发到银行账户；商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。需要调用查询接口的情况：商户后台、网络、服务器等出现异常，商户最终未接收到提现结果通知；调用提现及代发申请接口后，返回系统错误或者未知交易、处理中交易状态情况。 请求地址https://accpapi.lianlianpay.com/v1/txn/query-withdrawal
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_QUERY_WITHDRAWAL
     *
     * @param req ReqQueryWithdrawal实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqQueryWithdrawal
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResQueryWithdrawal
     */
    @ApiOperation(value = "3.3.4. 提现结果查询 该接口提供所有提现场景下的订单查询，包括提现及代发到银行账户；" +
            "商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。" +
            "需要调用查询接口的情况：商户后台、网络、服务器等出现异常，商户最终未接收到提现结果通知；" +
            "调用提现及代发申请接口后，返回系统错误或者未知交易、处理中交易状态情况。 " +
            "请求地址https://accpapi.lianlianpay.com/v1/txn/query-withdrawal",
            response = ResQueryWithdrawal.class)
    @ApiImplicitParam(dataTypeClass = ReqQueryWithdrawal.class)
    @PostMapping("queryWithdrawal")
    public ResQueryWithdrawal queryWithdrawal(@RequestBody ReqQueryWithdrawal req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResQueryWithdrawal res = this.tradeClient.queryWithdrawal(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_QUERY_WITHDRAWAL, "3.3.4. 提现结果查询", req, res, sourceIp));
        return res;
    }

    /**
     * 3.4. 代发 支持内部代发和外部代发两种场景交易。 内部代发：用户/平台商户A将账户可用余额代发至用户/平台商户B可用余额账户，业务场 景为佣金代发、红包奖励代发、供应商货款付款等。 外部代发：用户/平台商户A将账户可用余额代发至用户/平台商户B实体银行账户；业务场 景为佣金代发、红包奖励代发、供应商货款付款等。 3.4.1. 代发申请 请求地址https://accpapi.lianlianpay.com/v1/txn/transfer
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_TRANSFER
     *
     * @param req ReqTransfer实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqTransfer
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResTransfer
     */
    @ApiOperation(value = "3.4. 代发 支持内部代发和外部代发两种场景交易。 " +
            "内部代发：用户/平台商户A将账户可用余额代发至用户/平台商户B可用余额账户，业务场 景为佣金代发、红包奖励代发、供应商货款付款等。 " +
            "外部代发：用户/平台商户A将账户可用余额代发至用户/平台商户B实体银行账户；业务场景为佣金代发、红包奖励代发、供应商货款付款等。 " +
            "3.4.1. 代发申请 请求地址https://accpapi.lianlianpay.com/v1/txn/transfer",
            response = ResTransfer.class)
    @ApiImplicitParam(dataTypeClass = ReqTransfer.class)
    @PostMapping("transfer")
    public ResTransfer transfer(@RequestBody ReqTransfer req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isEmpty(req.getRisk_item())) {
            req.setRisk_item(GenRiskItemHelper.randomRiskItem());
        }
        ResTransfer res = this.tradeClient.transfer(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_TRANSFER, "3.4. 代发", req, res, sourceIp));
        return res;
    }

    /**
     * 3.5. 担保 担保交易支持两类场景： 1.  在支付交易统一创单时指定担保收款方信息，担保确认时支持全额及部分金额多次确认； 对于创单时指定的担保收款方不支持确认时修改； 2.  在支付交易统一创单时不指定担保收款方信息，担保交易确认时动态指定收款方并进行 交易确认，资金从担保平台商户账户转入担保收款方账户。 3.5.1. 担保交易确认 请求地址https://accpapi.lianlianpay.com/v1/txn/secured-confirm
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_SECURED_CONFIRM
     *
     * @param req ReqSecuredConfirm实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqSecuredConfirm
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResSecuredConfirm
     */
    @ApiOperation(value = "3.5. 担保 担保交易支持两类场景： " +
            "1.  在支付交易统一创单时指定担保收款方信息，担保确认时支持全额及部分金额多次确认； 对于创单时指定的担保收款方不支持确认时修改； " +
            "2.  在支付交易统一创单时不指定担保收款方信息，担保交易确认时动态指定收款方并进行 交易确认，资金从担保平台商户账户转入担保收款方账户。 " +
            "3.5.1. 担保交易确认 请求地址https://accpapi.lianlianpay.com/v1/txn/secured-confirm",
            response = ResSecuredConfirm.class)
    @ApiImplicitParam(dataTypeClass = ReqSecuredConfirm.class)
    @PostMapping("securedConfirm")
    public ResSecuredConfirm securedConfirm(@RequestBody ReqSecuredConfirm req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResSecuredConfirm res = this.tradeClient.securedConfirm(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_SECURED_CONFIRM, "3.5.1. 担保交易确认", req, res, sourceIp));
        return res;
    }

    /**
     * 3.5.3. 担保交易信息查询 针对担保交易的担保支付和担保交易确认多次操作，为商户提供某笔担保单的真实收款方金 额确认情况以及多次确认、退款操作的查询接口。 注：本接口只用于担保支付获取该交易相关确认单、退款单等信息与卖方确认金额信息，具 体担保支付交易、担保确认交易的结果，请参考【支付结果查询】 与 【担保确认结果查询】。 请求地址https://accpapi.lianlianpay.com/v1/txn/secured-query
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_SECURED_QUERY
     *
     * @param req ReqSecuredQuery实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqSecuredQuery
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResSecuredQuery
     */
    @ApiOperation(value = "3.5.3. 担保交易信息查询 针对担保交易的担保支付和担保交易确认多次操作，为商户提供某笔担保单的真实收款方金额确认情况以及多次确认、退款操作的查询接口。 " +
            "注：本接口只用于担保支付获取该交易相关确认单、退款单等信息与卖方确认金额信息，具体担保支付交易、担保确认交易的结果，请参考【支付结果查询】 与 【担保确认结果查询】。 " +
            "请求地址https://accpapi.lianlianpay.com/v1/txn/secured-query",
            response = ResSecuredQuery.class)
    @ApiImplicitParam(dataTypeClass = ReqSecuredQuery.class)
    @PostMapping("securedQuery")
    public ResSecuredQuery securedQuery(@RequestBody ReqSecuredQuery req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResSecuredQuery res = this.tradeClient.securedQuery(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_SECURED_QUERY, "3.5.3. 担保交易信息查询", req, res, sourceIp));
        return res;
    }

    /**
     * 3.6. 交易二次短信验证 对于充值、消费、提现、代发类交易需要二次进行付款方短信验证时，根据申请接口的交易 返回码进行判断，统一调用该接口完成交易验证。 请求地址https://accpapi.lianlianpay.com/v1/txn/validation-sms
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_VALIDATION_SMS
     *
     * @param req ReqValidationSms实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqValidationSms
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResValidationSms
     */
    @ApiOperation(value = "3.6. 交易二次短信验证 对于充值、消费、提现、代发类交易需要二次进行付款方短信验证时，根据申请接口的交易 返回码进行判断，统一调用该接口完成交易验证。 " +
            "请求地址https://accpapi.lianlianpay.com/v1/txn/validation-sms", response = ResValidationSms.class)
    @ApiImplicitParam(dataTypeClass = ReqValidationSms.class)
    @PostMapping("validationSms/white")
    public ResValidationSms validationSms(@RequestBody ReqValidationSms req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResValidationSms res = this.tradeClient.validationSms(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_VALIDATION_SMS, "3.6. 交易二次短信验证", req, res, sourceIp));
        return res;
    }

    /**
     * 3.7. 退款 3.7.1. 异步退款申请 该接口只支持普通消费交易、担保消费交易退款。 退款规则： 1.  每次发起退款只能唯一指定原消费交易一个收款方进行处理，分账类交易退款需要按照 收款方多次发起退款； 2.  支持全额或者部分退款； 3.  组合类消费交易，每次退款需要明确指定原付款方式对应的退款金额。 4.  异步退款申请在渠道真实退款结果获取之前状态为处理中，且该笔资金将被冻结 请求地址https://accpapi.lianlianpay.com/v1/txn/asyn-refund
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：AccpConstants.URL_ASYN_REFUND
     *
     * @param req ReqAsynRefund实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqAsynRefund
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResAsynRefund
     */
    @ApiOperation(value = "3.7. 退款 3.7.1. 异步退款申请 该接口只支持普通消费交易、担保消费交易退款。 退款规则： " +
            "1.  每次发起退款只能唯一指定原消费交易一个收款方进行处理，分账类交易退款需要按照 收款方多次发起退款； " +
            "2.  支持全额或者部分退款； " +
            "3.  组合类消费交易，每次退款需要明确指定原付款方式对应的退款金额。 " +
            "4.  异步退款申请在渠道真实退款结果获取之前状态为处理中，且该笔资金将被冻结 " +
            "请求地址https://accpapi.lianlianpay.com/v1/txn/asyn-refund", response = ResAsynRefund.class)
    @ApiImplicitParam(dataTypeClass = ReqAsynRefund.class)
    @PostMapping("asynRefund")
    public ResAsynRefund asynRefund(@RequestBody ReqAsynRefund req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isBlank(req.getNotify_url())) {
            req.setNotify_url(getDefaultNotifyUrl("asynRefund"));
        }
        ResAsynRefund res = this.tradeClient.asynRefund(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_ASYN_REFUND, "3.7.1. 异步退款申请", req, res, sourceIp));
        return res;
    }

    /**
     * 3.7.2. 退款结果查询 该接口提供发起提现申请后的订单查询，商户可以通过该接口主动查询提现申请订单状态， 完成下一步的业务逻辑。 请求地址https://accpapi.lianlianpay.com/v1/txn/query-refund
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_QUERY_REFUND
     *
     * @param req ReqQueryRefund实例
     * @return ResponseEntity实例
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.callback.NotifyQueryRefund
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.request.ReqQueryRefund
     * @see com.qdqtrj.lianlian.accp.pojo.trade.dto.response.ResQueryRefund
     */
    @ApiOperation(value = "3.7.2. 退款结果查询 该接口提供发起提现申请后的订单查询，商户可以通过该接口主动查询提现申请订单状态， 完成下一步的业务逻辑。 " +
            "请求地址https://accpapi.lianlianpay.com/v1/txn/query-refund",
            response = ResQueryRefund.class)
    @ApiImplicitParam(dataTypeClass = ReqQueryRefund.class)
    @PostMapping("queryRefund")
    public ResQueryRefund queryRefund(@RequestBody ReqQueryRefund req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResQueryRefund res = this.tradeClient.queryRefund(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_QUERY_REFUND, "3.7.2. 退款结果查询", req, res, sourceIp));
        return res;
    }

}