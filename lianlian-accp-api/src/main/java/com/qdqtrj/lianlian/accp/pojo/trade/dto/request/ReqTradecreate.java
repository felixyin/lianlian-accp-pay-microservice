package com.qdqtrj.lianlian.accp.pojo.trade.dto.request;

import com.alibaba.fastjson.annotation.JSONField;
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
import java.util.List;


/**
 * 3.2. 充值/消费 3.2.1. 支付统一创单 商户在充值/消费交易模式一场景下使用，先通过该接口完成支付统一创单，后续根据业务 场景调用不同的支付接口完成付款。 请求地址https://accpapi.lianlianpay.com/v1/txn/tradecreate
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.2. 充值/消费 3.2.1. 支付统一创单 商户在充值/消费交易模式一场景下使用，先通过该接口完成支付统一创单，后续根据业务 场景调用不同的支付接口完成付款。 请求地址https://accpapi.lianlianpay.com/v1/txn/tradecreate")
public class ReqTradecreate implements Serializable {
    private static final long serialVersionUID = -3399100738516450770L;
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
     * txn_type,交易类型,Y,String,用户充值：USER_TOPUP,商户充值：MCH_TOPUP,普通消费：GENERAL_CONSUME,担保消费：SECURED_CONSUME
     */
    @NotBlank()
    @ApiModelProperty(value = "txn_type,交易类型,Y,String,用户充值：USER_TOPUP,商户充值：MCH_TOPUP,普通消费：GENERAL_CONSUME,担保消费：SECURED_CONSUME", required = true)
    private String txn_type;
    /**
     * user_id,商户用户唯一编号,N,String,交易类型非商户充值时必填
     */
    @ApiModelProperty("user_id,商户用户唯一编号,N,String,交易类型非商户充值时必填")
    private String user_id;
    /**
     * user_type,用户类型,N,String,用户类型，默认：注册用户,注册用户：REGISTERED,匿名用户：ANONYMOUS
     */
    @ApiModelProperty("user_type,用户类型,N,String,用户类型，默认：注册用户,注册用户：REGISTERED,匿名用户：ANONYMOUS")
    private String user_type;
    /**
     * notify_url,异步通知地址,N,String,交易结果异步通知接收地址，建议HTTPS协议
     */
    @ApiModelProperty("notify_url,异步通知地址,N,String,交易结果异步通知接收地址，建议HTTPS协议")
    private String notify_url;
    /**
     * return_url,交易返回地址,N,String,支付结果页面返回地址
     */
    @ApiModelProperty("return_url,交易返回地址,N,String,支付结果页面返回地址")
    private String return_url;
    /**
     * pay_expire,支付有效期,N,Number,支付有效期，逾期将会关闭交易,单位：分钟，默认：3天,建议：最短失效时间间隔大于5分钟
     */
    @ApiModelProperty("pay_expire,支付有效期,N,Number,支付有效期，逾期将会关闭交易,单位：分钟，默认：3天,建议：最短失效时间间隔大于5分钟")
    private Integer pay_expire = 60;

    private OrderInfo orderInfo;

    private List<PayeeInfo> payeeInfo;

    /**
     * 商户订单信息
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
         * order_info,订单信息,N,String,订单信息，在查询API和支付通知中原样返回，可作为自定义参数使用
         */
        @ApiModelProperty("order_info,订单信息,N,String,订单信息，在查询API和支付通知中原样返回，可作为自定义参数使用")
        private String order_info;
        /**
         * goods_name,商品描述信息,N,String,商品描述信息
         */
        @ApiModelProperty("goods_name,商品描述信息,N,String,商品描述信息")
        private String goods_name;
        /**
         * goods_url,订单及商品展示地 N,String,订单及商品展示地址址
         */
        @ApiModelProperty("goods_url,订单及商品展示地 N,String,订单及商品展示地址址")
        private String goods_url;
    }

    /**
     * 根据txn_type设置收款方信息规则,USER_TOPUP：用户充值不接受上送收款方，系统默认充值到user_id对应的用户账户,MCH_TOPUP：平台商户充值时必须指定收款账户类型，充值入账到指定类型账户,GENERAL_CONSUME：必须上送收款方，且不可更改,SECURED_CONSUME：支持创单时不指定担保收款方，在担保确认时指定;
     * 收款总金额必须和订单总金额相等;
     */
    @Data
    public static class PayeeInfo {
        /**
         * payee_id,收款方id,Y,String,收款方标识，指定为用户或者平台,商户的唯一id
         */
        @NotBlank()
        @ApiModelProperty(value = "payee_id,收款方id,Y,String,收款方标识，指定为用户或者平台,商户的唯一id", required = true)
        private String payee_id;
        /**
         * payee_type,收款方类型,Y,String,用户：USER,平台商户：MERCHANT
         */
        @NotBlank()
        @ApiModelProperty(value = "payee_type,收款方类型,Y,String,用户：USER,平台商户：MERCHANT", required = true)
        private String payee_type;
        /**
         * payee_accttype,收款账户类型,N,String,交易类型为商户充值时必须指定充值入账账户类型，参见‘账户类型列表’
         */
        @ApiModelProperty("payee_accttype,收款账户类型,N,String,交易类型为商户充值时必须指定充值入账账户类型，参见‘账户类型列表’")
        private String payee_accttype;
        /**
         * payee_amount,收款金额,Y,Number(8,2),收款金额，单位为元，精确到小数点后两位
         */
        @NotNull()
        @Digits(integer = 8, fraction = 2)
        @ApiModelProperty(value = "payee_amount,收款金额,Y,Number(8,2),收款金额，单位为元，精确到小数点后两位", required = true)
        private BigDecimal payee_amount;
        /**
         * payee_memo,收款备注,N,String,收款备注信息
         */
        @ApiModelProperty("payee_memo,收款备注,N,String,收款备注信息")
        private String payee_memo;
    }
}
