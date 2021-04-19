package com.qdqtrj.lianlian.accp.pojo.trade.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * 3.5.3. 担保交易信息查询 针对担保交易的担保支付和担保交易确认多次操作，为商户提供某笔担保单的真实收款方金 额确认情况以及多次确认、退款操作的查询接口。 注：本接口只用于担保支付获取该交易相关确认单、退款单等信息与卖方确认金额信息，具 体担保支付交易、担保确认交易的结果，请参考【支付结果查询】 与 【担保确认结果查询】。 请求地址https://accpapi.lianlianpay.com/v1/txn/secured-query
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.5.3. 担保交易信息查询 针对担保交易的担保支付和担保交易确认多次操作，为商户提供某笔担保单的真实收款方金 额确认情况以及多次确认、退款操作的查询接口。 注：本接口只用于担保支付获取该交易相关确认单、退款单等信息与卖方确认金额信息，具 体担保支付交易、担保确认交易的结果，请参考【支付结果查询】 与 【担保确认结果查询】。 请求地址https://accpapi.lianlianpay.com/v1/txn/secured-query")
public class ResSecuredQuery implements Serializable {
    private static final long serialVersionUID = -2860998394018792011L;
    /**
     * ret_code,交易结果代码,Y,String,查询交易返回码,此字段为本次查询交易结果标识
     */
    @NotBlank()
    @ApiModelProperty(value = "ret_code,交易结果代码,Y,String,查询交易返回码,此字段为本次查询交易结果标识", required = true)
    private String ret_code;
    /**
     * ret_msg,交易结果描述,Y,String,查询交易返回描述
     */
    @NotBlank()
    @ApiModelProperty(value = "ret_msg,交易结果描述,Y,String,查询交易返回描述", required = true)
    private String ret_msg;
    /**
     * oid_partner,商户号,N,String,ACCP 系统分配给平台商户的唯一编号
     */
    @ApiModelProperty("oid_partner,商户号,N,String,ACCP 系统分配给平台商户的唯一编号")
    private String oid_partner;
    /**
     * accp_txno,ACCP 系统交易单 N,String,原担保支付ACCP系统交易单号号
     */
    @ApiModelProperty("accp_txno,ACCP 系统交易单 N,String,原担保支付ACCP系统交易单号号")
    private String accp_txno;

    /**
     * 商户订单信息 orderInfo
     */
    @ApiModelProperty("商户订单信息 orderInfo")
    private OrderInfo orderInfo;
    /**
     * 付款方信息(组合支付场景返回付款方信息数组)payerInfo
     */
    @ApiModelProperty("付款方信息(组合支付场景返回付款方信息数组)payerInfo")
    private PayerInfo payerInfo;
    /**
     * 收款方信息(交易分账场景返回收款方信息数组)payeeInfo
     */
    @ApiModelProperty("收款方信息(交易分账场景返回收款方信息数组)payeeInfo")
    private List<PayeeInfo> payeeInfo;
    /**
     * 担保支付订单 payInfo
     */
    @ApiModelProperty("担保支付订单 payInfo")
    private PayInfo payInfo;
    /**
     * 担保确认订单 acceptInfo
     */
    @ApiModelProperty("担保确认订单 acceptInfo")
    private AcceptInfo acceptInfo;
    /**
     * 担保退款订单 refundInfo
     */
    @ApiModelProperty("担保退款订单 refundInfo")
    private RefundInfo refundInfo;

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
         * total_amount,订单总金额,N,Number(8,2),订单总金额，单位为元，精确到小数点后两位
         */
        @Digits(integer = 8, fraction = 2)
        @ApiModelProperty("total_amount,订单总金额,N,Number(8,2),订单总金额，单位为元，精确到小数点后两位")
        private BigDecimal total_amount;
        /**
         * secure_status,担保订单状态,N,String,WAIT_BUYER_PAY：等待付款,WAIT_BUYER_CONFIRM：等待确认,CONFIRMING：确认中,CONFIRM_SUCCESS：确认完成,TRADE_CLOSED：交易过期关闭,REFUND_SUCCESS：退款完成,担保交易状态以该字段为准
         */
        @ApiModelProperty("secure_status,担保订单状态,N,String,WAIT_BUYER_PAY：等待付款,WAIT_BUYER_CONFIRM：等待确认,CONFIRMING：确认中,CONFIRM_SUCCESS：确认完成,TRADE_CLOSED：交易过期关闭,REFUND_SUCCESS：退款完成,担保交易状态以该字段为准")
        private String secure_status;
    }

    /**
     * 付款方信息(组合支付场景返回付款方信息数组)payerInfo
     */
    @Data
    public static class PayerInfo {
        /**
         * payer_type,付款方类型,N,String,用户：USER,平台商户：MERCHANT
         */
        @ApiModelProperty("payer_type,付款方类型,N,String,用户：USER,平台商户：MERCHANT")
        private String payer_type;
        /**
         * payer_id,付款方标识,N,String,付款方标识，user_id或者商户号
         */
        @ApiModelProperty("payer_id,付款方标识,N,String,付款方标识，user_id或者商户号")
        private String payer_id;
        /**
         * method,付款方式,N,String,参见‘付款方式列表’
         */
        @ApiModelProperty("method,付款方式,N,String,参见‘付款方式列表’")
        private String method;
        /**
         * amount,付款金额,N,Number(8,2),付款方式对应的金额，单位为元，精确到小数点后两位
         */
        @Digits(integer = 8, fraction = 2)
        @ApiModelProperty("amount,付款金额,N,Number(8,2),付款方式对应的金额，单位为元，精确到小数点后两位")
        private BigDecimal amount;
        /**
         * receive_amount,已收退款金额,N,Number(8,2),付款方式对应的已收退款金额，单位为元，精确到小数点后两位
         */
        @ApiModelProperty("receive_amount,已收退款金额,N,Number(8,2),付款方式对应的已收退款金额，单位为元，精确到小数点后两位")
        private String receive_amount;
    }

    /**
     * 收款方信息(交易分账场景返回收款方信息数组)payeeInfo
     */
    @Data
    public static class PayeeInfo {

        /**
         * payee_type,收款方类型,N,String,用户：USER,平台商户：MERCHANT
         */
        @ApiModelProperty("payee_type,收款方类型,N,String,用户：USER,平台商户：MERCHANT")
        private String payee_type;
        /**
         * payee_id,收款方标识,N,String,收款方标识，user_id或者商户号
         */
        @ApiModelProperty("payee_id,收款方标识,N,String,收款方标识，user_id或者商户号")
        private String payee_id;
        /**
         * amount,收款金额,N,Number(8,2),收款金额，单位为元，精确到小数点后两位
         */
        @Digits(integer = 8, fraction = 2)
        @ApiModelProperty("amount,收款金额,N,Number(8,2),收款金额，单位为元，精确到小数点后两位")
        private BigDecimal amount;
        /**
         * accept_amount,已确认金额,N,Number(8,2),已确认金额，单位为元，精确到小数点后两位
         */
        @ApiModelProperty("accept_amount,已确认金额,N,Number(8,2),已确认金额，单位为元，精确到小数点后两位")
        private String accept_amount;
        /**
         * refund_amount,已退金额,N,Number(8,2),已退金额，单位为元，精确到小数点后两位
         */
        @Digits(integer = 8, fraction = 2)
        @ApiModelProperty("refund_amount,已退金额,N,Number(8,2),已退金额，单位为元，精确到小数点后两位")
        private BigDecimal refund_amount;
    }

    /**
     * 担保支付订单 payInfo
     */
    @Data
    public static class PayInfo {

        /**
         * accp_txno,ACCP 系统交易单 N,String,ACCP系统交易单号
         */
        @ApiModelProperty("accp_txno,ACCP 系统交易单 N,String,ACCP系统交易单号")
        private String accp_txno;
        /**
         * txn_seqno,商户交易流水号,N,String(64),商户系统唯一交易流水号
         */
        @Length(min = 1, max = 64)
        @ApiModelProperty("txn_seqno,商户交易流水号,N,String(64),商户系统唯一交易流水号")
        private String txn_seqno;
    }

    /**
     * 担保确认订单 acceptInfo
     */
    @Data
    public static class AcceptInfo {

        /**
         * accp_txno,ACCP 系统交易单 N,String,ACCP系统交易单号
         */
        @ApiModelProperty("accp_txno,ACCP 系统交易单 N,String,ACCP系统交易单号")
        private String accp_txno;
        /**
         * txn_seqno,商户交易流水号,N,String(64),商户系统唯一交易流水号
         */
        @Length(min = 1, max = 64)
        @ApiModelProperty("txn_seqno,商户交易流水号,N,String(64),商户系统唯一交易流水号")
        private String txn_seqno;
    }

    /**
     * 担保退款订单 refundInfo
     */
    @Data
    public static class RefundInfo {

        /**
         * refund_seqno,退款订单号,Y,String(64),标识一次退款请求，商户系统需保证唯一
         */
        @NotBlank()
        @Length(min = 1, max = 64)
        @ApiModelProperty(value = "refund_seqno,退款订单号,Y,String(64),标识一次退款请求，商户系统需保证唯一", required = true)
        private String refund_seqno;
        /**
         * accp_txno,ACCP 系统退款单 N,String,ACCP系统退款单号
         */
        @ApiModelProperty("accp_txno,ACCP 系统退款单 N,String,ACCP系统退款单号")
        private String accp_txno;
    }
}

