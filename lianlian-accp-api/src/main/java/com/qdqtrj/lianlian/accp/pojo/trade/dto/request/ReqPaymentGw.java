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
import java.util.List;


/**
 * 3.2.4. 网关类支付 微信、支付宝、网银类支付申请，通过该接口唤起相应渠道的支付界面，用户确认并支付，支持附加优惠券、余额组合支付。
 * 支持以下主支付方式：==== 编码  描述,EBANK_DEBIT_CARD  网银借记卡,EBANK_CREDIT_CARD  网银信用卡,EBANK_B2B  企业网银,WECHAT_APP
 * 支付宝扫码,ALIPAY_APP  支付宝APP,ALIPAY_H5  支付宝H5,ALIPAY_WEB  支付宝WEB,ALIPAY_WXA  支付宝小程序,ALIPAY_NATIVE_ICBC
 * 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-gw
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data

@ApiModel(description = "请求地址https://accpapi.lianlianpay.com/v1/txn/payment-gw")
public class ReqPaymentGw implements Serializable {
    private static final long serialVersionUID = -9036304060969457877L;
    /**
     * timestamp,时间戳,Y,Date,交易服务器时间戳,格式：yyyyMMddHHmmss,有效期 30 分钟（包含服务器之间的时间差）
     */
    @NotNull()
    @JSONField(format = "yyyyMMddHHmmss")
    @ApiModelProperty("timestamp,时间戳,Y,Date,交易服务器时间戳,格式：yyyyMMddHHmmss,有效期 30 分钟（包含服务器之间的时间差）")
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
    @NotNull()
    @Digits(integer = 8, fraction = 2)
    @ApiModelProperty(value = "total_amount,订单总金额,Y,Number(8,2),订单总金额，单位为元，精确到小数点后两位", required = true)
    private BigDecimal total_amount;
    /**
     * risk_item,风控参数,N,String,参见‘风控参数’
     */
    @ApiModelProperty("risk_item,风控参数,N,String,参见‘风控参数’")
    private RiskItem risk_item;
    /**
     * appid,应用ID,N,String,开发者在微信或者支付宝开放平台申请的AppID
     */
    @ApiModelProperty("appid,应用ID,N,String,开发者在微信或者支付宝开放平台申请的AppID")
    private String appid;
    /**
     * openid,渠道用户标识,N,String,微信公众号支付时必传，此参数为微信用户在商户对应 appid下的唯一标识或支付宝买家的支付宝唯一用户号（2088开头的16位纯数字）,获取方式可参考官方文档说明
     */
    @ApiModelProperty("openid,渠道用户标识,N,String,微信公众号支付时必传，此参数为微信用户在商户对应 appid下的唯一标识或支付宝买家的支付宝唯一用户号（2088开头的16位纯数字）,获取方式可参考官方文档说明")
    private String openid;
    /**
     * bankcode,银行编码,N,String,付款方式为网银类时可指定
     */
    @ApiModelProperty("bankcode,银行编码,N,String,付款方式为网银类时可指定")
    private String bankcode;
    /**
     * device_info,设备标识,N,String,自定义参数，可以为终端设备号
     */
    @ApiModelProperty("device_info,设备标识,N,String,自定义参数，可以为终端设备号")
    private String device_info;
    /**
     * client_ip,终端用户IP,Y,String,支持IPV4和IPV6两种格式的IP地址。
     */
    @NotBlank()
    @ApiModelProperty(value = "client_ip,终端用户IP,Y,String,支持IPV4和IPV6两种格式的IP地址。", required = true)
    private String client_ip;
    /**
     * device_version,版本标识,N,String,自定义参数，可以为支付宝的版本号
     */
    @ApiModelProperty("device_version,版本标识,N,String,自定义参数，可以为支付宝的版本号")
    private String device_version;
    /**
     * limit_card_type,限制卡类型,N,String,限制某种卡类型支付权限, 支付宝和微信支付适用，若限制多种类型以“,”分隔（暂时只支持限制信用卡支付）。credit：限制使用信用卡支付
     */
    @ApiModelProperty("limit_card_type,限制卡类型,N,String,限制某种卡类型支付权限, 支付宝和微信支付适用，若限制多种类型以“,”分隔（暂时只支持限制信用卡支付）。credit：限制使用信用卡支付")
    private String limit_card_type;
    /**
     * extend_params,业务扩展字段,N,String,渠道扩展字段JSON串，若渠道为支付宝，则支持的键值对如下：sys_service_provider_id,hb_fq_num,hb_fq_seller_percent,industry_reflux_info,card_type
     */
    @ApiModelProperty("extend_params,业务扩展字段,N,String,渠道扩展字段JSON串，若渠道为支付宝，则支持的键值对如下：sys_service_provider_id,hb_fq_num,hb_fq_seller_percent,industry_reflux_info,card_type")
    private String extend_params;

    private PayerInfo payerInfo;
    private List<PayMethod> payMethods;

    /**
     * 付款方信息
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
         * password,支付密码,N,String,支付密码
         */

        @ApiModelProperty("password,支付密码,N,String,支付密码")
        private String password;
        /**
         * random_key,密码随机因子 N,String,密码随机因子key，随机因子获取接key口返回
         */

        @ApiModelProperty("random_key,密码随机因子 N,String,密码随机因子key，随机因子获取接key口返回")
        private String random_key;
    }

    @Data
    public static class PayMethod {

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
}
