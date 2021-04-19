package com.qdqtrj.lianlian.accp.pojo.trade.dto.callback;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 3.3.2. 提现确认 请求地址https://accpapi.lianlianpay.com/v1/txn/withdrawal-check
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
public class NotifyWithdrawalCheck implements Serializable {
    private static final long serialVersionUID = -2236792242752317185L;
    /**
     * oid_partner,商户号,Y,String,ACCP 系统分配给平台商户的唯一编号
     */
    @NotBlank()
    private String oid_partner;
    /**
     * user_id,商户用户唯一编号,N,String,用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户
     */
    private String user_id;
    /**
     * accounting_date,账务日期,N,String,ACCP系统交易账务日期，交易成功时返回，格式：yyyyMMdd
     */
    private String accounting_date;
    /**
     * finish_time,支付完成时间,N,String,支付完成时间,格式：yyyyMMddHHmmss
     */
    private String finish_time;
    /**
     * accp_txno,ACCP系统交易单号,Y,String,ACCP系统交易单号
     */
    @NotBlank()
    private String accp_txno;
    /**
     * chnl_txno,渠道交易单号,N,String,渠道交易单号
     */
    private String chnl_txno;
    /**
     * txn_status,提现交易状态,Y,String, TRADE_SUCCESS：成功,TRADE_FAILURE：交易失败,TRADE_CANCEL：退汇,提现最终结果以此为准，商户按该字段值进行后续业务逻辑处理
     */
    @NotBlank()
    private String txn_status;
    /**
     * failure_reason,提现失败原因,N,String,提现失败原因
     */
    private String failure_reason;
    /**
     * chnl_reason,渠道原始原因,N,String,提现失败渠道原始原因
     */
    private String chnl_reason;
    /**
     * bankcode,银行编码,N,String,提现收款银行编码
     */
    private String bankcode;
    /**
     * 付款方信息 payerInfo
     */
    private PayerInfo payerInfo;
    /**
     * 商户订单信息 orderInfo
     */
    private OrderInfo orderInfo;
    /**
     * 回调业务处理是否执行成功
     */
    private boolean bizSuccess;

    /**
     * 付款方信息 payerInfo
     */
    @Data
    public static class PayerInfo {

        /**
         * payer_type,付款方类型,Y,String,用户：USER,平台商户：MERCHANT
         */
        @NotBlank()
        private String payer_type;
        /**
         * payer_id,付款方标识,Y,String,付款方为用户时设置user_id,付款方为商户时设置平台商户号
         */
        @NotBlank()
        private String payer_id;
    }

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
        private String txn_seqno;
        /**
         * txn_time,商户交易时间,Y,String(14),商户系统交易时间,格式：yyyyMMddHHmmss
         */
        @NotBlank()
        @Length(min = 1, max = 14)
        private String txn_time;
        /**
         * total_amount,订单总金额,Y,Number(8,2),提现总金额，单位为元，精确到小数点后两位
         */
        @NotNull()
        @Digits(integer = 8, fraction = 2)
        private BigDecimal total_amount;
        /**
         * fee_amount,手续费金额,N,Number(8,2),手续费金额，单位为元，精确到小数点后两位。会自动收取到商户的自有资金账户。不允许超过订单总金额的20%
         */
        @Digits(integer = 8, fraction = 2)
        private BigDecimal fee_amount;
        /**
         * order_info,订单信息,N,String,订单信息
         */
        private String order_info;
    }
}
