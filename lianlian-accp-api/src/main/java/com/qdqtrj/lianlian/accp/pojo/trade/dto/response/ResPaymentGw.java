package com.qdqtrj.lianlian.accp.pojo.trade.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 3.2.4. 网关类支付 微信、支付宝、网银类支付申请，通过该接口唤起相应渠道的支付界面，用户确认并支付，支持附加优惠券、余额组合支付。支持以下主支付方式：==== 编码  描述,EBANK_DEBIT_CARD  网银借记卡,EBANK_CREDIT_CARD  网银信用卡,EBANK_B2B  企业网银,WECHAT_APP  微信APP,WECHAT_JSAPI  微信公众号,WECHAT_NATIVE  微信扫码,WECHAT_H5  微信H5,WECHAT_WXA  微信小程序,ALIPAY_NATIVE  支付宝扫码,ALIPAY_APP  支付宝APP,ALIPAY_H5  支付宝H5,ALIPAY_WEB  支付宝WEB,ALIPAY_WXA  支付宝小程序,ALIPAY_NATIVE_ICBC  支付宝扫码-工银e支付,WECHAT_NATIVE_ICBC  微信扫码-工银e支付 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-gw
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.2.4. 网关类支付 微信、支付宝、网银类支付申请，通过该接口唤起相应渠道的支付界面，用户确认并支付，支持附加优惠券、余额组合支付。支持以下主支付方式：==== 编码  描述,EBANK_DEBIT_CARD  网银借记卡,EBANK_CREDIT_CARD  网银信用卡,EBANK_B2B  企业网银,WECHAT_APP  微信APP,WECHAT_JSAPI  微信公众号,WECHAT_NATIVE  微信扫码,WECHAT_H5  微信H5,WECHAT_WXA  微信小程序,ALIPAY_NATIVE  支付宝扫码,ALIPAY_APP  支付宝APP,ALIPAY_H5  支付宝H5,ALIPAY_WEB  支付宝WEB,ALIPAY_WXA  支付宝小程序,ALIPAY_NATIVE_ICBC  支付宝扫码-工银e支付,WECHAT_NATIVE_ICBC  微信扫码-工银e支付 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-gw")
public class ResPaymentGw implements Serializable {
    private static final long serialVersionUID = -7228564605641851290L;
    /**
     * ret_code,交易结果代码,Y,String,交易返回码,0000表示交易申请成功，可进行,后续网关类支付，最终支付结果以支付结果异步通知接口为准
     */
    @NotBlank()
    @ApiModelProperty(value = "ret_code,交易结果代码,Y,String,交易返回码,0000表示交易申请成功，可进行,后续网关类支付，最终支付结果以支付结果异步通知接口为准", required = true)
    private String ret_code;
    /**
     * ret_msg,交易结果描述,Y,String,交易返回描述
     */
    @NotBlank()
    @ApiModelProperty(value = "ret_msg,交易结果描述,Y,String,交易返回描述", required = true)
    private String ret_msg;
    /**
     * oid_partner,商户号,Y,String,ACCP 系统分配给平台商户的唯一编号
     */
    @NotBlank()
    @ApiModelProperty(value = "oid_partner,商户号,Y,String,ACCP 系统分配给平台商户的唯一编号", required = true)
    private String oid_partner;
    /**
     * user_id,商户用户唯一编号,Y,String,用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户
     */
    @NotBlank()
    @ApiModelProperty(value = "user_id,商户用户唯一编号,Y,String,用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户", required = true)
    private String user_id;
    /**
     * txn_seqno,交易流水号,Y,String,商户系统唯一交易流水号
     */
    @NotBlank()
    @ApiModelProperty(value = "txn_seqno,交易流水号,Y,String,商户系统唯一交易流水号", required = true)
    private String txn_seqno;
    /**
     * total_amount,订单总金额,Y,Number(8,2),订单总金额，单位为元，精确到小数点后两位
     */
    @NotNull
    @Digits(integer = 8, fraction = 2)
    @ApiModelProperty(value = "total_amount,订单总金额,Y,Number(8,2),订单总金额，单位为元，精确到小数点后两位", required = true)
    private BigDecimal total_amount;
    /**
     * accp_txno,ACCP系统交易单号,N,String,ACCP系统交易单号
     */
    @ApiModelProperty("accp_txno,ACCP系统交易单号,N,String,ACCP系统交易单号")
    private String accp_txno;
    /**
     * token,交易token,N,String,支付授权令牌，有效期：30分钟,当交易需要二次验证时，需要通过token调用3.6交易二次短信验证接口
     */
    @ApiModelProperty("token,交易token,N,String,支付授权令牌，有效期：30分钟,当交易需要二次验证时，需要通过token调用3.6交易二次短信验证接口")
    private String token;
    /**
     * gateway_url,网关地址,N,String,网银支付方式适用，返回跳转网关地址，用户跳转到网关完成后续支付操作
     */
    @ApiModelProperty("gateway_url,网关地址,N,String,网银支付方式适用，返回跳转网关地址，用户跳转到网关完成后续支付操作")
    private String gateway_url;
    /**
     * payload,支付参数集合,N,String(Json),返回外部渠道的标准支付提交参数，微信/支付宝可参考官方文档
     */
    @ApiModelProperty("payload,支付参数集合,N,String(Json),返回外部渠道的标准支付提交参数，微信/支付宝可参考官方文档")
    private String payload;
}

