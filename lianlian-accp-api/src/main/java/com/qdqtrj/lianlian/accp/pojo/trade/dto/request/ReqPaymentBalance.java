package com.qdqtrj.lianlian.accp.pojo.trade.dto.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.qdqtrj.lianlian.accp.pojo.RiskItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 3.2.2. 余额支付 用户账户余额支付接口，支持附加优惠券组合支付。 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-balance
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.2.2. 余额支付 用户账户余额支付接口，支持附加优惠券组合支付。 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-balance")
public class ReqPaymentBalance implements Serializable {
    private static final long serialVersionUID = 6749513090994094882L;
    /**
     * timestamp,时间戳,Y,Date,交易服务器时间戳,格式：yyyyMMddHHmms,有效期30分钟（包含服务器之间的时间差）
     */
    @NotNull()
    @JSONField(format = "yyyyMMddHHmmss")
    @ApiModelProperty("timestamp,时间戳,Y,Date,交易服务器时间戳,格式：yyyyMMddHHmms,有效期30分钟（包含服务器之间的时间差）")
    private Date timestamp = new Date();
    /**
     * oid_partner,商户号,Y,String,ACCP 系统分配给平台商户的唯一编号
     */
    @NotBlank()
    @ApiModelProperty(value = "oid_partner,商户号,Y,String,ACCP 系统分配给平台商户的唯一编号", required = true)
    private String oid_partner;
    /**
     * txn_seqno,商户交易流水号,Y,String(64),商户系统唯一交易流水号
     */
    @NotBlank()
    @Length(min = 1, max = 64)
    @ApiModelProperty(value = "txn_seqno,商户交易流水号,Y,String(64),商户系统唯一交易流水号", required = true)
    private String txn_seqno;
    /**
     * total_amount,订单总金额,Y,Number(8,2),订单总金额，单位为元，精确到小数点后两位
     */
    @NotNull
    @Digits(integer = 8, fraction = 2)
    @ApiModelProperty(value = "total_amount,订单总金额,Y,Number(8,2),订单总金额，单位为元，精确到小数点后两位", required = true)
    private BigDecimal total_amount;
    /**
     * coupon_amount,优惠金额,N,Number(8,2),优惠券金额，单位为元，精确到小数点后两位
     */
    @ApiModelProperty("coupon_amount,优惠金额,N,Number(8,2),优惠券金额，单位为元，精确到小数点后两位")
    private String coupon_amount;
    /**
     * risk_item,风控参数,N,String,参见‘风控参数’
     */
    @ApiModelProperty("risk_item,风控参数,N,String,参见‘风控参数’")
    private RiskItem risk_item;

    private PayerInfo payerInfo;

    @Data
    public static class PayerInfo {

        /**
         * user_id,商户用户唯一编号,Y,String,用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户
         */
        @NotBlank()
        @ApiModelProperty(value = "user_id,商户用户唯一编号,Y,String,用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户", required = true)
        private String user_id;
        /**
         * password,支付密码,N,String,支付密码
         */
        @ApiModelProperty("password,支付密码,N,String,支付密码")
        private String password;
        /**
         * random_key,密码随机因子key,N,String,密码随机因子 key，随机因子获取接口返回
         */
        @ApiModelProperty("random_key,密码随机因子key,N,String,密码随机因子 key，随机因子获取接口返回")
        private String random_key;
    }
}
