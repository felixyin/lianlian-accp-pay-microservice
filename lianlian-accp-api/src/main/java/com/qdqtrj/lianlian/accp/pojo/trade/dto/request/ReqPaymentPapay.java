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
 * 3.2.5. 委托代扣 微信、支付宝提交委托代扣，通过使用与用户签约获得的微信、支付宝代扣协议号，由商户后台调用该接口发起创单并支付，暂不支持组合支付。
 * 支持以下主支付方式：
 * 编码  描述,WECHAT_PAPAY  微信委托代扣,BALANCE  余额代扣/余额,ALIPAY_PAPAY  支付宝商户代扣
 *
 * @author yinbin3.2.5
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@SuppressWarnings("unused")
@ApiModel(description = "3.2.5. 委托代扣 微信、支付宝提交委托代扣，通过使用与用户签约获得的微信、支付宝代扣协议号，由商户后台调用该接口发起创单并支付，暂不支持组合支付。")
public class ReqPaymentPapay implements Serializable {
    private static final long serialVersionUID = 1238315669564311124L;
    /**
     * timestamp,时间戳,Y,Date,交易服务器时间戳,格式：yyyyMMddHHmmss,有效期30分钟（包含服务器之间的时间差）
     */
    @NotNull()
    @JSONField(format = "yyyyMMddHHmmss")
    @ApiModelProperty("timestamp,时间戳,Y,Date,交易服务器时间戳,格式：yyyyMMddHHmmss,有效期30分钟（包含服务器之间的时间差）")
    private Date timestamp = new Date();
    /**
     * oid_partner,商户号,Y,String,ACCP 系统分配给平台商户的唯一编号
     */
    @NotBlank()
    @ApiModelProperty(value = "oid_partner,商户号,Y,String,ACCP 系统分配给平台商户的唯一编号", required = true)
    private String oid_partner;
    /**
     * txn_type,交易类型,Y,String,用户充值：USER_TOPUP,普通消费：GENERAL_CONSUME,担保消费：SECURED_CONSUME
     */
    @NotBlank()
    @ApiModelProperty(value = "txn_type,交易类型,Y,String,用户充值：USER_TOPUP,普通消费：GENERAL_CONSUME,担保消费：SECURED_CONSUME", required = true)
    private String txn_type;
    /**
     * user_id,商户用户唯一编 N,String,交易类型非商户充值时必填号
     */
    @ApiModelProperty("user_id,商户用户唯一编 N,String,交易类型非商户充值时必填号")
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
     * appid,应用ID,N,String,开发者在微信或者支付宝开放平台申请的AppID,微信或支付宝代扣时必输
     */
    @ApiModelProperty("appid,应用ID,N,String,开发者在微信或者支付宝开放平台申请的AppID,微信或支付宝代扣时必输")
    private String appid;
    /**
     * client_ip,终端用户IP,Y,String,用户端IP
     */
    @NotBlank()
    @ApiModelProperty(value = "client_ip,终端用户IP,Y,String,用户端IP", required = true)
    private String client_ip;
    /**
     * risk_item,风控参数,N,String,参见‘风控参数’
     */
    @ApiModelProperty("risk_item,风控参数,N,String,参见‘风控参数’")
    private RiskItem risk_item;

    /**
     * 商户订单信息 bean
     */
    @ApiModelProperty("商户订单信息 bean")
    private OrderInfo orderInfo;
    /**
     * 付款方信息 bean
     */
    @ApiModelProperty("付款方信息 bean")
    private PayerInfo payerInfo;
    /**
     * 收款方信息
     * txn_type,根据txn_type设置收款方信息规则,USER_TOPUP：用户充值不接受上送收款方，系统默认充值到user_id对应的用户账户,GENERAL_CONSUME：必须上送收款方，且不可更改,SECURED_CONSUME：支持创单时不指定担保收款方，在担保确认时指定,消费分账场景支持上送多收款方,收款总金额必须和订单总金额相等
     */
    @ApiModelProperty("收款方信息")
    private PayeeInfo payeeInfo;
    /**
     * wechatInfo,微信附加字段 N String
     */
    @ApiModelProperty("wechatInfo,微信附加字段 N String")
    private WechatInfo wechatInfo;
    /**
     * 支付宝附加字段(非必输)
     */
    @ApiModelProperty("支付宝附加字段(非必输)")
    private AlipayInfo alipayInfo;

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
         * goods_url,订单及商品展示 N,String,订单及商品展示地址
         */
        @ApiModelProperty("goods_url,订单及商品展示 N,String,订单及商品展示地址")
        private String goods_url;
    }

    /**
     * 付款方信息
     */
    public static class PayerInfo {

        /**
         * payer_type,付款方类型,Y,String,用户：USER
         */
        @NotBlank()
        @ApiModelProperty(value = "payer_type,付款方类型,Y,String,用户：USER", required = true)
        private String payer_type;
        /**
         * payer_id,付款方标识,Y,String,付款方为用户时设置user_id
         */
        @NotBlank()
        @ApiModelProperty(value = "payer_id,付款方标识,Y,String,付款方为用户时设置user_id", required = true)
        private String payer_id;
        /**
         * contract_id,委托代扣协议id,Y,String,渠道或账户加返回的代扣协议号。即账户加的pap_agree_no,支付宝的agreement_no，或微信的contract_id。该字段需要RSA加密传输
         */
        @NotBlank()
        @ApiModelProperty(value = "contract_id,委托代扣协议id,Y,String,渠道或账户加返回的代扣协议号。即账户加的pap_agree_no,支付宝的agreement_no，或微信的contract_id。该字段需要RSA加密传输", required = true)
        private String contract_id;
        /**
         * method,付款方式,Y,String,参见‘付款方式列表’
         */
        @NotBlank()
        @ApiModelProperty(value = "method,付款方式,Y,String,参见‘付款方式列表’", required = true)
        private String method;
        /**
         * amount,付款金额,Y,Number(8,2),付款方式对应的金额，单位为元，精确到小数点后两位,所有的付款方式金额相加必须和订单总金额一致
         */
        @NotNull()
        @Digits(integer = 8, fraction = 2)
        @ApiModelProperty(value = "amount,付款金额,Y,Number(8,2),付款方式对应的金额，单位为元，精确到小数点后两位,所有的付款方式金额相加必须和订单总金额一致", required = true)
        private BigDecimal amount;
    }

    /**
     * 收款方信息
     * txn_type,根据txn_type设置收款方信息规则,USER_TOPUP：用户充值不接受上送收款方，系统默认充值到user_id对应的用户账户,GENERAL_CONSUME：必须上送收款方，且不可更改,SECURED_CONSUME：支持创单时不指定担保收款方，在担保确认时指定,消费分账场景支持上送多收款方,收款总金额必须和订单总金额相等
     */
    public static class PayeeInfo {

        /**
         * payee_id,收款方id,Y,String,收款方标识，指定为用户或者平台商户的唯一id
         */
        @NotBlank()
        @ApiModelProperty(value = "payee_id,收款方id,Y,String,收款方标识，指定为用户或者平台商户的唯一id", required = true)
        private String payee_id;
        /**
         * payee_type,收款方类型,Y,String,用户：USER,平台商户：MERCHANT
         */
        @NotBlank()
        @ApiModelProperty(value = "payee_type,收款方类型,Y,String,用户：USER,平台商户：MERCHANT", required = true)
        private String payee_type;
        /**
         * payee_accttype,收款账户类型,N,String,交易类型为商户充值时必须指定充值,入账账户类型，参见‘账户类型列表’
         */
        @ApiModelProperty("payee_accttype,收款账户类型,N,String,交易类型为商户充值时必须指定充值,入账账户类型，参见‘账户类型列表’")
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

    /**
     * 微信附加字段 wechatInfo(非必输)
     */
    public static class WechatInfo {

        /**
         * body,商品描述,N,String(32),商品或支付单简要描述,注：微信付款码支付时为必输
         */
        @Length(min = 1, max = 32)
        @ApiModelProperty("body,商品描述,N,String(32),商品或支付单简要描述,注：微信付款码支付时为必输")
        private String body;
        /**
         * detail,商品详情,N,String(512)
         */
        @Length(min = 1, max = 512)
        @ApiModelProperty("detail,商品详情,N,String(512)")
        private String detail;
    }

    /**
     * 支付宝附加字段(非必输)
     */
    public static class AlipayInfo {
        /**
         * subject,订单标题,N,String（256）,订单的简单描述，注：支付宝条码支付时为必输
         */
        @ApiModelProperty("subject,订单标题,N,String（256）,订单的简单描述，注：支付宝条码支付时为必输")
        private String subject;
        /**
         * body,订单描述,N,String(128)
         */
        @Length(min = 1, max = 128)
        //        @ApiModelProperty("body,订单描述,N,String(128)")
        //    private String body;
        /**
         * seller_id,卖家ID,N,String(28),如果该值为空，则默认为商户签约账号对应的支付宝用户ID
         */
        @Length(min = 1, max = 28)
        @ApiModelProperty("seller_id,卖家ID,N,String(28),如果该值为空，则默认为商户签约账号对应的支付宝用户ID")
        private String seller_id;
        /**
         * extend_params,业务扩展字段,N,String,渠道扩展字段JSON串，若渠道为支付宝，则支持的键值对如下：sys_service_provider_id,hb_fq_num,hb_fq_seller_percent,industry_reflux_info,card_type
         */
        @ApiModelProperty("extend_params,业务扩展字段,N,String,渠道扩展字段JSON串，若渠道为支付宝，则支持的键值对如下：sys_service_provider_id,hb_fq_num,hb_fq_seller_percent,industry_reflux_info,card_type")
        private String extend_params;
    }
}
