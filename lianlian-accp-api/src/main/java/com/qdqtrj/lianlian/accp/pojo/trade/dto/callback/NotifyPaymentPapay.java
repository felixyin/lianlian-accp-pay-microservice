package com.qdqtrj.lianlian.accp.pojo.trade.dto.callback;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * 3.2.5. 委托代扣 微信、支付宝提交委托代扣，通过使用与用户签约获得的微信、支付宝代扣协议号，由商户后台调用该接口发起创单并支付，暂不支持组合支付。 支持以下主支付方式： 编码  描述,WECHAT_PAPAY  微信委托代扣,BALANCE  余额代扣/余额,ALIPAY_PAPAY  支付宝商户代扣 请求地址https://accpapi.lianlianpay.com/v1/txn/payment-papay
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
public class NotifyPaymentPapay implements Serializable {
    private static final long serialVersionUID = -5749990067885896577L;
    /**
     * oid_partner,商户号,Y,String,ACCP系统分配给平台商户的唯一编号
     */
    @NotBlank()
    private String oid_partner;
    /**
     * txn_type,交易类型,Y,String,用户充值：USER_TOPUP,商户充值：MCH_TOPUP,普通消费：GENERAL_CONSUME,担保消费：SECURED_CONSUME,内部代发：INNER_FUND_EXCHANGE
     */
    @NotBlank()
    private String txn_type;
    /**
     * accounting_date,账务日期,Y,String,ACCP系统交易账务日期，交易成功时返回，格式：yyyyMMdd
     */
    @NotBlank()
    private String accounting_date;
    /**
     * finish_time,支付完成时间,Y,String,支付完成时间,格式：yyyyMMddHHmmss
     */
    @NotBlank()
    private String finish_time;
    /**
     * accp_txno,ACCP 系统交 Y,String,ACCP系统交易单号易单号
     */
    private String accp_txno;
    /**
     * chnl_txno,渠道交易单号,N,String,渠道交易单号
     */
    private String chnl_txno;
    /**
     * txn_status,支付交易状态,Y,String, TRADE_SUCCESS：交易成功,支付交易最终状态以此为准，商户按此进行后续业务逻辑处理
     */
    @NotBlank()
    private String txn_status;
    /**
     * bankcode,银行编码,N,String,付款银行编码
     */
    private String bankcode;
    /**
     * linked_agrtno,绑卡协议号,N,String,银行卡首次支付成功返回
     */
    private String linked_agrtno;

    /**
     * 商户订单信息 orderInfo
     */
    private OrderInfo orderInfo;
    /**
     * 付款方信息(组合支付场景返回付款方信息数组)payerInfo
     */
    private List<PayerInfo> payerInfo;
    /**
     * 收款方信息(交易分账场景返回收款方信息数组)payeeInfo
     */
    private List<PayeeInfo> payeeInfo;
    /**
     * 回调业务处理是否执行成功
     */
    private boolean bizSuccess;

    /**
     * 商户订单信息 orderInfo
     */
    @Data
    public static class OrderInfo {

        /**
         * txn_seqno,商户交易流水 Y,String(64),商户系统唯一交易流水号号
         */
        private String txn_seqno;
        /**
         * txn_time,商户交易时间,Y,String(14),商户系统交易时间,格式：yyyyMMddHHmmss
         */
        @NotBlank()
        @Length(min = 1, max = 14)
        private String txn_time;
        /**
         * total_amount,订单总金额,Y,Number(8,2),订单总金额，单位为元，精确到小数点后两位
         */
        @NotNull()
        @Digits(integer = 8, fraction = 2)
        private BigDecimal total_amount;
        /**
         * order_info,订单信息,N,String,订单信息
         */
        private String order_info;
    }

    /**
     * 付款方信息(组合支付场景返回付款方信息数组)payerInfo
     */
    @Data
    public static class PayerInfo {

        /**
         * payerInfo,付款方信息（组合支付场景返回付款方信息数组） N String
         */
        private String payerInfo;
        /**
         * payer_type,付款方类型,Y,String,用户：USER,平台商户：MERCHANT
         */
        @NotBlank()
        private String payer_type;
        /**
         * payer_id,付款方标识,Y,String,付款方标识，user_id或者商户号
         */
        @NotBlank()
        private String payer_id;
        /**
         * method,付款方式,Y,String,参见‘付款方式列表’
         */
        @NotBlank()
        private String method;
        /**
         * amount,付款金额,Y,Number(8,2),付款方式对应的金额，单位为元，精确到小数点后两位
         */
        @NotNull()
        @Digits(integer = 8, fraction = 2)
        private BigDecimal amount;
    }

    /**
     * 收款方信息(交易分账场景返回收款方信息数组)payeeInfo
     */
    @Data
    public static class PayeeInfo {
        /**
         * payee_type,收款方类型,Y,String,用户：USER,平台商户：MERCHANT
         */
        @NotBlank()
        private String payee_type;
        /**
         * payee_id,收款方标识,Y,String,收款方标识，user_id或者商户号
         */
        @NotBlank()
        private String payee_id;
        /**
         * amount,收款金额,Y,Number(8,2),收款金额，单位为元，精确到小数点后两位
         */
        @NotNull()
        @Digits(integer = 8, fraction = 2)
        private BigDecimal amount;
    }
}
