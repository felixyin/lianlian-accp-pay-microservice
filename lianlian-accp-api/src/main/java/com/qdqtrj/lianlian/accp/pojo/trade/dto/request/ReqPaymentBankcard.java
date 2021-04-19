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
 * 3.2.3. 银行卡快捷支付 银行卡快捷支付接口，支持附加优惠券、余额组合支付；
 * 适用于如下几种场景：
 *   已开户绑定银行卡的个人用户支付
 *   未注册的匿名用户首次/历次支付
 * 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-bankcard
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "请求地址https://accpapi.lianlianpay.com/v1/txn/payment-bankcard")
public class ReqPaymentBankcard implements Serializable {
    private static final long serialVersionUID = 7766434104881772443L;
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
     * risk_item,风控参数,N,String,参见‘风控参数’
     */
    @ApiModelProperty("risk_item,风控参数,N,String,参见‘风控参数’")
    private RiskItem risk_item;

    private PayerInfo payerInfo;
    private BankCardInfo bankCardInfo;
    private List<PayMethod> payMethods;

    /**
     * 付款方信息
     */
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

    /**
     * 付款银行卡信息
     */
    @Data
    public static class BankCardInfo {

        /**
         * linked_acctno,绑定银行帐号,N,String,使用已绑定的银行卡进行支付，银
         */
        @ApiModelProperty("linked_acctno,绑定银行帐号,N,String,使用已绑定的银行卡进行支付，银")
        private String linked_acctno;
        /**
         * linked_agrtno,绑卡协议号,N,String,行卡号和协议号二者选其一提供
         */
        @ApiModelProperty("linked_agrtno,绑卡协议号,N,String,行卡号和协议号二者选其一提供")
        private String linked_agrtno;
        /**
         * linked_phone,银行预留手机号,N,String,匿名用户首次支付提供的银行账户
         */
        @ApiModelProperty("linked_phone,银行预留手机号,N,String,匿名用户首次支付提供的银行账户")
        private String linked_phone;
        /**
         * linked_acctname,银行账户名称,N,String,信息，历次支付提供绑定协议号或
         */
        @ApiModelProperty("linked_acctname,银行账户名称,N,String,信息，历次支付提供绑定协议号或")
        private String linked_acctname;
        /**
         * id_type,证件类型,N,String,者银行卡号
         */
        @ApiModelProperty("id_type,证件类型,N,String,者银行卡号")
        private String id_type;
        /**
         * id_no,证件号码,N,String
         */
        @ApiModelProperty("id_no,证件号码,N,String")
        private String id_no;
        /**
         * cvv2,信用卡cvv2,N,String,信用卡支付时必填
         */
        @ApiModelProperty("cvv2,信用卡cvv2,N,String,信用卡支付时必填")
        private String cvv2;
        /**
         * valid_thru,信用卡有效期,N,String,信用卡支付时必填,格式：MM/YY
         */
        @ApiModelProperty("valid_thru,信用卡有效期,N,String,信用卡支付时必填,格式：MM/YY")
        private String valid_thru;
    }

    /**
     * 付款方式信息
     */
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
