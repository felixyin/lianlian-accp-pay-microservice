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
 * 3.4. 代发 支持内部代发和外部代发两种场景交易。 内部代发：用户/平台商户A将账户可用余额代发至用户/平台商户B可用余额账户，业务场 景为佣金代发、红包奖励代发、供应商货款付款等。 外部代发：用户/平台商户A将账户可用余额代发至用户/平台商户B实体银行账户；业务场 景为佣金代发、红包奖励代发、供应商货款付款等。 3.4.1. 代发申请 请求地址https://accpapi.lianlianpay.com/v1/txn/transfer
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.4. 代发 支持内部代发和外部代发两种场景交易。 内部代发：用户/平台商户A将账户可用余额代发至用户/平台商户B可用余额账户，业务场 景为佣金代发、红包奖励代发、供应商货款付款等。 外部代发：用户/平台商户A将账户可用余额代发至用户/平台商户B实体银行账户；业务场 景为佣金代发、红包奖励代发、供应商货款付款等。 3.4.1. 代发申请 请求地址https://accpapi.lianlianpay.com/v1/txn/transfer")
public class ReqTransfer implements Serializable {
    private static final long serialVersionUID = -2877771942229922666L;
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
     * pay_expire,订单有效期,N,Number,订单有效期，逾期将会关闭交易,单位：分钟，默认：3天,注：超时关单对内部代发暂不生效；外部代发用于代发确认有效时间，超过订单时间系统进行关单，将无法确认
     */
    @ApiModelProperty("pay_expire,订单有效期,N,Number,订单有效期，逾期将会关闭交易,单位：分钟，默认：3天,注：超时关单对内部代发暂不生效；外部代发用于代发确认有效时间，超过订单时间系统进行关单，将无法确认")
    private Integer pay_expire = 60;
    /**
     * funds_flag,垫资标识,N,String,标识该笔代发交易是否支持平台商户垫资，适用于代发付款方为用户的业务场景，默认：N,Y：支持垫资,N：不支持垫资
     */
    @ApiModelProperty("funds_flag,垫资标识,N,String,标识该笔代发交易是否支持平台商户垫资，适用于代发付款方为用户的业务场景，默认：N,Y：支持垫资,N：不支持垫资")
    private String funds_flag;
    /**
     * check_flag,审核标识,N,String,标识该笔订单是否需要审核，默认：N,Y：需要代发确认,N：无需代发确认,注：内部代发（收款方为ACCP账户）暂不支持
     */
    @ApiModelProperty("check_flag,审核标识,N,String,标识该笔订单是否需要审核，默认：N,Y：需要代发确认,N：无需代发确认,注：内部代发（收款方为ACCP账户）暂不支持")
    private String check_flag;
    /**
     * pay_time_type,到账类型,N,String,只对收款方为非余额账户类的外部代发生效，到账类型参见‘到账类型列表’,默认：实时到账
     */
    @ApiModelProperty("pay_time_type,到账类型,N,String,只对收款方为非余额账户类的外部代发生效，到账类型参见‘到账类型列表’,默认：实时到账")
    private String pay_time_type;
    /**
     * risk_item,风控参数,N,String,参见‘风控参数’
     */
    @ApiModelProperty("risk_item,风控参数,N,String,参见‘风控参数’")
    private RiskItem risk_item;

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
     * 收款方信息 payeeInfo
     */
    @ApiModelProperty("收款方信息 payeeInfo")
    private PayeeInfo payeeInfo;

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
        @ApiModelProperty(value = "txn_seqno,商户交易流水号,Y,String(64),商户系统唯一交易流水号", required = true)
        private String txn_seqno;
        /**
         * txn_time,商户交易时间,Y,String(14),商户系统交易时间,格式：yyyyMMddHHmmss
         */
        @NotBlank()
        @Length(min = 1, max = 14)
        @ApiModelProperty(value = "txn_time,商户交易时间,Y,String(14),商户系统交易时间,格式：yyyyMMddHHmmss", required = true)
        private String txn_time;
        /**
         * total_amount,订单总金额,Y,Number(8,2),订单总金额，单位为元，精确到小数点后两位
         */
        @NotNull()
        @Digits(integer = 8, fraction = 2)
        @ApiModelProperty(value = "total_amount,订单总金额,Y,Number(8,2),订单总金额，单位为元，精确到小数点后两位", required = true)
        private BigDecimal total_amount;
        /**
         * fee_amount,手续费金额,N,Number(8,2),手续费金额，单位为元，精确到小数点后两位。会自动收取到商户的自有资金账户。不允许超过订单总金额的20%,注：收款方为“对私银行账户”或“对公银行账户”才有效
         */
        @Digits(integer = 8, fraction = 2)
        @ApiModelProperty("fee_amount,手续费金额,N,Number(8,2),手续费金额，单位为元，精确到小数点后两位。会自动收取到商户的自有资金账户。不允许超过订单总金额的20%,注：收款方为“对私银行账户”或“对公银行账户”才有效")
        private BigDecimal fee_amount;
        /**
         * txn_purpose,代发交易用途,Y,String,服务费,信息费,修理费,佣金支付,贷款支付,其他
         */
        @NotBlank()
        @ApiModelProperty(value = "txn_purpose,代发交易用途,Y,String,服务费,信息费,修理费,佣金支付,贷款支付,其他", required = true)
        private String txn_purpose;
        /**
         * order_info,订单信息,N,String,订单信息，在查询API和支付通知中原样返回，可作为自定义参数使用
         */
        @ApiModelProperty("order_info,订单信息,N,String,订单信息，在查询API和支付通知中原样返回，可作为自定义参数使用")
        private String order_info;
        /**
         * postscript,交易附言,N,String,交易附言 代发至银行账户单笔金额大于等于5w必须提供
         */
        @ApiModelProperty("postscript,交易附言,N,String,交易附言 代发至银行账户单笔金额大于等于5w必须提供")
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
         * payer_accttype,付款方账户类型,N,String,付款方为平台商户时必须提供,参见‘账户类型列表’
         */
        @ApiModelProperty("payer_accttype,付款方账户类型,N,String,付款方为平台商户时必须提供,参见‘账户类型列表’")
        private String payer_accttype;
        /**
         * password,支付密码,N,String,支付密码，非委托代发,付款方为用,户时必填
         */
        @ApiModelProperty("password,支付密码,N,String,支付密码，非委托代发,付款方为用,户时必填")
        private String password;
        /**
         * random_key,密码随机因子key,N,String,密码随机因子 key，随机因子获取,接口返回
         */
        @ApiModelProperty("random_key,密码随机因子key,N,String,密码随机因子 key，随机因子获取,接口返回")
        private String random_key;
        /**
         * pap_agree_no,委托代发协议号,N,String,账户加返回的代扣协议号，委托代发时必输该字段需要RSA加密传输
         */
        @ApiModelProperty("pap_agree_no,委托代发协议号,N,String,账户加返回的代扣协议号，委托代发时必输该字段需要RSA加密传输")
        private String pap_agree_no;
    }

    /**
     * 收款方信息 payeeInfo
     */
    @Data
    public static class PayeeInfo {


        /**
         * payee_type,收款方类型,Y,String,用户：USER,平台商户：MERCHANT,对私银行账户：BANKACCT_PRI,对公银行账户：BANKACCT_PUB
         */
        @NotBlank()
        @ApiModelProperty(value = "payee_type,收款方类型,Y,String,用户：USER,平台商户：MERCHANT,对私银行账户：BANKACCT_PRI,对公银行账户：BANKACCT_PUB", required = true)
        private String payee_type;
        /**
         * payee_id,收款方标识,N,String,收款方标识，指定为用户或者平台商户的唯一 id，收款方类型为用户或者商户时必须
         */
        @ApiModelProperty("payee_id,收款方标识,N,String,收款方标识，指定为用户或者平台商户的唯一 id，收款方类型为用户或者商户时必须")
        private String payee_id;
        /**
         * payee_accttype,收款方账户类型,N,String,收款方为平台商户时必传,参见‘账户类型列表’
         */
        @ApiModelProperty("payee_accttype,收款方账户类型,N,String,收款方为平台商户时必传,参见‘账户类型列表’")
        private String payee_accttype;
        /**
         * bank_acctno,银行帐号,N,String,银行帐号
         */
        @ApiModelProperty("bank_acctno,银行帐号,N,String,银行帐号")
        private String bank_acctno;
        /**
         * bank_code,银行编码,N,String,收款方类型为对公银行账户必须
         */
        @ApiModelProperty("bank_code,银行编码,N,String,收款方类型为对公银行账户必须")
        private String bank_code;
        /**
         * bank_acctname,户名,N,String,户名
         */
        @ApiModelProperty("bank_acctname,户名,N,String,户名")
        private String bank_acctname;
        /**
         * cnaps_code,大额行号,N,String,银行大额行号，收款方类型为对公银行账户必须
         */
        @ApiModelProperty("cnaps_code,大额行号,N,String,银行大额行号，收款方类型为对公银行账户必须")
        private String cnaps_code;
    }
}
