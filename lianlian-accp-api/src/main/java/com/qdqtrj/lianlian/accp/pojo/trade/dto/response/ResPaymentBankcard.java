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
 * 3.2.3. 银行卡快捷支付 银行卡快捷支付接口，支持附加优惠券、余额组合支付；适用于如下几种场景：   已开户绑定银行卡的个人用户支付   未注册的匿名用户首次/历次支付 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-bankcard
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.2.3. 银行卡快捷支付 银行卡快捷支付接口，支持附加优惠券、余额组合支付；适用于如下几种场景： \uF06C  已开户绑定银行卡的个人用户支付 \uF06C  未注册的匿名用户首次/历次支付 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-bankcard")
public class ResPaymentBankcard implements Serializable {
    private static final long serialVersionUID = -3600417004512514748L;
    /**
     * ret_code,交易结果代码,Y,String,交易返回码
     */
    @NotBlank()
    @ApiModelProperty(value = "ret_code,交易结果代码,Y,String,交易返回码", required = true)
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
     * accp_txno,ACCP 系统交易单 N,String,ACCP系统交易单号号
     */
    @ApiModelProperty("accp_txno,ACCP 系统交易单 N,String,ACCP系统交易单号号")
    private String accp_txno;
    /**
     * token,交易token,N,String,支付授权令牌，有效期：30分钟,当交易需要二次验证时，需要通过token调用3.6交易二次短信验证接口
     */
    @ApiModelProperty("token,交易token,N,String,支付授权令牌，有效期：30分钟,当交易需要二次验证时，需要通过token调用3.6交易二次短信验证接口")
    private String token;
    /**
     * linked_agrtno,绑卡协议号,N,String,银行卡首次支付返回
     */
    @ApiModelProperty("linked_agrtno,绑卡协议号,N,String,银行卡首次支付返回")
    private String linked_agrtno;
    /**
     * accounting_date,账务日期,N,String,ACCP系统交易账务日期，交易成功时返回，格式：yyyyMMdd
     */
    @ApiModelProperty("accounting_date,账务日期,N,String,ACCP系统交易账务日期，交易成功时返回，格式：yyyyMMdd")
    private String accounting_date;
    /**
     * finish_time,支付完成时间,N,String,支付完成时间,格式：yyyyMMddHHmmss
     */
    @ApiModelProperty("finish_time,支付完成时间,N,String,支付完成时间,格式：yyyyMMddHHmmss")
    private String finish_time;
}

