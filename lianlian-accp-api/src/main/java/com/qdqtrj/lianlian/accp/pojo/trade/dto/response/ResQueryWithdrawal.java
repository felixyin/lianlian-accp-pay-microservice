package com.qdqtrj.lianlian.accp.pojo.trade.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 3.3.4. 提现结果查询 该接口提供所有提现场景下的订单查询，包括提现及代发到银行账户；商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。需要调用查询接口的情况：商户后台、网络、服务器等出现异常，商户最终未接收到提现结果通知；调用提现及代发申请接口后，返回系统错误或者未知交易、处理中交易状态情况。 请求地址https://accpapi.lianlianpay.com/v1/txn/query-withdrawal
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.3.4. 提现结果查询 该接口提供所有提现场景下的订单查询，包括提现及代发到银行账户；商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。需要调用查询接口的情况：商户后台、网络、服务器等出现异常，商户最终未接收到提现结果通知；调用提现及代发申请接口后，返回系统错误或者未知交易、处理中交易状态情况。 请求地址https://accpapi.lianlianpay.com/v1/txn/query-withdrawal")
public class ResQueryWithdrawal implements Serializable {
    private static final long serialVersionUID = -9132200662947317708L;
    /**
     * ret_code,交易结果代码,Y,String,交易返回码,该字段为本次查询交易结果标识，非原提现交易结果标识，提现是否成功需要根据txn_status字段判断
     */
    @NotBlank()
    @ApiModelProperty(value = "ret_code,交易结果代码,Y,String,交易返回码,该字段为本次查询交易结果标识，非原提现交易结果标识，提现是否成功需要根据txn_status字段判断", required = true)
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
     * accounting_date,账务日期,N,String,ACCP系统交易账务日期，交易成功时返回，格式：yyyyMMdd
     */
    @ApiModelProperty("accounting_date,账务日期,N,String,ACCP系统交易账务日期，交易成功时返回，格式：yyyyMMdd")
    private String accounting_date;
    /**
     * finish_time,支付完成时间,N,String,支付完成时间,格式：yyyyMMddHHmmss
     */
    @ApiModelProperty("finish_time,支付完成时间,N,String,支付完成时间,格式：yyyyMMddHHmmss")
    private String finish_time;
    /**
     * accp_txno,ACCP系统交易单号,N,String,ACCP系统交易单号
     */
    @ApiModelProperty("accp_txno,ACCP系统交易单号,N,String,ACCP系统交易单号")
    private String accp_txno;
    /**
     * chnl_txno,渠道交易单号,N,String,渠道交易单号
     */
    @ApiModelProperty("chnl_txno,渠道交易单号,N,String,渠道交易单号")
    private String chnl_txno;
    /**
     * txn_status,提现交易状态,N,String, TRADE_SUCCESS：成功,TRADE_FAILURE：交易失败,TRADE_CANCEL：退汇,TRADE_PREPAID：预付完成,交易最终状态以此为准，商户按该字段值进行后续业务逻辑处理
     */
    @ApiModelProperty("txn_status,提现交易状态,N,String, TRADE_SUCCESS：成功,TRADE_FAILURE：交易失败,TRADE_CANCEL：退汇,TRADE_PREPAID：预付完成,交易最终状态以此为准，商户按该字段值进行后续业务逻辑处理")
    private String txn_status;
    /**
     * failure_reason,提现失败原因,N,String,当txn_status为FAILURE或CANCEL时返回具体失败原因信息
     */
    @ApiModelProperty("failure_reason,提现失败原因,N,String,当txn_status为FAILURE或CANCEL时返回具体失败原因信息")
    private String failure_reason;
    /**
     * chnl_reason,渠道原始原因,N,String,提现失败渠道原始原因
     */
    @ApiModelProperty("chnl_reason,渠道原始原因,N,String,提现失败渠道原始原因")
    private String chnl_reason;

    /**
     * bankcode,银行编码,N,String,提现收款银行编码
     */
    @ApiModelProperty("bankcode,银行编码,N,String,提现收款银行编码")
    private String bankcode;

    /**
     * 付款方信息 payerInfo
     */
    @ApiModelProperty("付款方信息 payerInfo")
    private PayerInfo payerInfo;
    /**
     * 商户订单信息 orderInfo
     */
    @ApiModelProperty("商户订单信息 orderInfo")
    private OrderInfo orderInfo;

    /**
     * 付款方信息 payerInfo
     */
    @Data
    public static class PayerInfo {

        /**
         * payer_type,付款方类型,N,String,用户：USER,平台商户：MERCHANT
         */
        @ApiModelProperty("payer_type,付款方类型,N,String,用户：USER,平台商户：MERCHANT")
        private String payer_type;
        /**
         * payer_id,付款方标识,N,String,付款方为用户时设置user_id,付款方为商户时设置平台商户号
         */
        @ApiModelProperty("payer_id,付款方标识,N,String,付款方为用户时设置user_id,付款方为商户时设置平台商户号")
        private String payer_id;
    }

    /**
     * 商户订单信息 orderInfo
     */
    @Data
    public static class OrderInfo {

        /**
         * txn_seqno,商户交易流水号,N,String(64),商户系统唯一交易流水号
         */
        @Length(min = 1, max = 64)
        @ApiModelProperty("txn_seqno,商户交易流水号,N,String(64),商户系统唯一交易流水号")
        private String txn_seqno;
        /**
         * txn_time,商户交易时间,N,String(14),商户系统交易时间,格式：yyyyMMddHHmmss
         */
        @Length(min = 1, max = 14)
        @ApiModelProperty("txn_time,商户交易时间,N,String(14),商户系统交易时间,格式：yyyyMMddHHmmss")
        private String txn_time;
        /**
         * total_amount,订单总金额,N,Number(8,2),提现总金额，单位为元，精确到小数点后两位
         */
        @Digits(integer = 8, fraction = 2)
        @ApiModelProperty("total_amount,订单总金额,N,Number(8,2),提现总金额，单位为元，精确到小数点后两位")
        private BigDecimal total_amount;
        /**
         * fee_amount,手续费金额,N,Number(8,2),手续费金额，单位为元，精确到小数点后两位。会自动收取到商户的自有资金账户。不允许超过订单总金额的20%
         */
        @Digits(integer = 8, fraction = 2)
        @ApiModelProperty("fee_amount,手续费金额,N,Number(8,2),手续费金额，单位为元，精确到小数点后两位。会自动收取到商户的自有资金账户。不允许超过订单总金额的20%")
        private BigDecimal fee_amount;
        /**
         * order_info,订单信息,N,String,订单信息
         */
        @ApiModelProperty("order_info,订单信息,N,String,订单信息")
        private String order_info;
    }
}

