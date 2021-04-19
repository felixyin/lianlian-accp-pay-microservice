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
 * 3.3. 提现 用户/平台商户将账户可用余额提现至开户绑定的银行账户。 3.3.1. 提现申请 请求地址https://accpapi.lianlianpay.com/v1/txn/withdrawal
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.3. 提现 用户/平台商户将账户可用余额提现至开户绑定的银行账户。 3.3.1. 提现申请 请求地址https://accpapi.lianlianpay.com/v1/txn/withdrawal")
public class ReqWithdrawal implements Serializable {
    private static final long serialVersionUID = -1513639610732798581L;
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
     * notify_url,异步通知地址,N,String,交易结果异步通知接收地址，建议HTTPS协议
     */
    @ApiModelProperty("notify_url,异步通知地址,N,String,交易结果异步通知接收地址，建议HTTPS协议")
    private String notify_url;
    /**
     * pay_expire,订单有效期,N,Number,订单有效期，逾期将会关闭交易,单位：分钟，默认：3天,注：提现确认有效时间，超期将关单
     */
    @ApiModelProperty("pay_expire,订单有效期,N,Number,订单有效期，逾期将会关闭交易,单位：分钟，默认：3天,注：提现确认有效时间，超期将关单")
    private Integer pay_expire = 60;
    /**
     * risk_item,风控参数,N,String,参见‘风控参数’
     */
    @ApiModelProperty("risk_item,风控参数,N,String,参见‘风控参数’")
    private RiskItem risk_item;
    /**
     * linked_acctno,绑定银行帐号,N,String,个人用户可绑定多张银行卡，提现
     */
    @ApiModelProperty("linked_acctno,绑定银行帐号,N,String,个人用户可绑定多张银行卡，提现")
    private String linked_acctno;
    /**
     * linked_agrtno,绑卡协议号,N,String,时可指定具体的卡号或者对应的绑卡协议号，二者选其一；企业用户建议上传，若为空则以默认绑定提现银行账号为准。
     */
    @ApiModelProperty("linked_agrtno,绑卡协议号,N,String,时可指定具体的卡号或者对应的绑卡协议号，二者选其一；企业用户建议上传，若为空则以默认绑定提现银行账号为准。")
    private String linked_agrtno;
    /**
     * funds_flag,垫资标识,N,String,标识该笔提现交易是否支持平台商户垫资，适用于用户提现业务场景，默认：N,Y：支持垫资,N：不支持垫资
     */
    @ApiModelProperty("funds_flag,垫资标识,N,String,标识该笔提现交易是否支持平台商户垫资，适用于用户提现业务场景，默认：N,Y：支持垫资,N：不支持垫资")
    private String funds_flag;
    /**
     * check_flag,审核标识,N,String,标识该笔订单是否需要审核，默认：N,Y：需要提现确认,N：无需提现确认
     */
    @ApiModelProperty("check_flag,审核标识,N,String,标识该笔订单是否需要审核，默认：N,Y：需要提现确认,N：无需提现确认")
    private String check_flag;
    /**
     * pay_time_type,到账类型,N,String,参见‘到账类型列表’,默认：实时到账 todo
     */
    @ApiModelProperty("pay_time_type,到账类型,N,String,参见‘到账类型列表’,默认：实时到账 todo")
    private String pay_time_type;

    /**
     * 商户订单信息 orderInfo
     */
    @ApiModelProperty("商户订单信息 orderInfo")
    private OrderInfo orderInfo;
    /**
     * 付款方信息 payerInfo
     */
    @ApiModelProperty("付款方信息 payerInfo")
    private PayerInfo payerInfo;

    /**
     * 商户订单信息 orderInfo
     */
    @Data
    public static class OrderInfo {
        /**
         * txn_seqno,商户交易流水号,Y,String(64),商户系统唯一交易流水号
         */
        @NotBlank()
        @Length(min = 1, max = 64)
        @ApiModelProperty("txn_seqno,商户交易流水号,Y,String(64),商户系统唯一交易流水号")
        private String txn_seqno;
        /**
         * txn_time,商户交易时间,Y,String(14),商户系统交易时间,格式：yyyyMMddHHmmss
         */
        @NotBlank()
        @Length(min = 1, max = 14)
        @ApiModelProperty("txn_time,商户交易时间,Y,String(14),商户系统交易时间,格式：yyyyMMddHHmmss")
        private String txn_time;
        /**
         * total_amount,订单总金额,Y,Number(8,2),订单总金额，单位为元，精确到小数点后两位
         */
        @NotNull()
        @Digits(integer = 8, fraction = 2)
        @ApiModelProperty("total_amount,订单总金额,Y,Number(8,2),订单总金额，单位为元，精确到小数点后两位")
        private BigDecimal total_amount;
        /**
         * fee_amount,手续费金额,N,Number(8,2),手续费金额，单位为元，精确到小数点后两位。会自动收取到商户的自有资金账户。不允许超过订单总金额的20%
         */
        @Digits(integer = 8, fraction = 2)
        @ApiModelProperty("fee_amount,手续费金额,N,Number(8,2),手续费金额，单位为元，精确到小数点后两位。会自动收取到商户的自有资金账户。不允许超过订单总金额的20%")
        private BigDecimal fee_amount;
        /**
         * order_info,订单信息,N,String,订单信息，在查询API和支付通知中原样返回，可作为自定义参数使用
         */

        @ApiModelProperty("order_info,订单信息,N,String,订单信息，在查询API和支付通知中原样返回，可作为自定义参数使用")
        private String order_info;
        /**
         * postscript,交易附言,N,String,提现交易附言，单笔金额大于等于5w必须提供
         */

        @ApiModelProperty("postscript,交易附言,N,String,提现交易附言，单笔金额大于等于5w必须提供")
        private String postscript;
    }

    /**
     * 付款方信息 payerInfo
     */

    @Data
    public static class PayerInfo {

        /**
         * payer_type,付款方类型,Y,String,用户：USER,平台商户：MERCHANT
         */
        @NotBlank()
        @ApiModelProperty(value = "payer_type,付款方类型,Y,String,用户：USER,平台商户：MERCHANT", required = true)
        private String payer_type;
        /**
         * payer_id,付款方标识,Y,String,付款方为用户时设置user_id付款方为商户时设置平台商户号
         */
        @NotBlank()
        @ApiModelProperty(value = "payer_id,付款方标识,Y,String,付款方为用户时设置user_id付款方为商户时设置平台商户号", required = true)
        private String payer_id;
        /**
         * payer_accttype,付款方账户类型,N,String,付款方类型为商户时需要指定平台商户账户类型参见‘账户类型列表’
         */
        @ApiModelProperty("payer_accttype,付款方账户类型,N,String,付款方类型为商户时需要指定平台商户账户类型参见‘账户类型列表’")
        private String payer_accttype;
        /**
         * password,支付密码,N,String,支付密码，用户提现时必须
         */
        @ApiModelProperty("password,支付密码,N,String,支付密码，用户提现时必须")
        private String password;
        /**
         * random_key,密码随机因子key,N,String,密码随机因子 key，随机因子获取接口返回，用户提现时必须
         */
        @ApiModelProperty("random_key,密码随机因子key,N,String,密码随机因子 key，随机因子获取接口返回，用户提现时必须")
        private String random_key;
    }
}
