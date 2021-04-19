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
 * 3.2.8. 支付结果查询 该接口提供所有消费/充值场景下的支付订单查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。需要调用查询接口的情况：商户后台、网络、服务器等出现异常，商户最终未接收到支付结果通知；调用支付接口后，返回系统错误或者未知交易、处理中交易状态情况。 请求地址https://accpapi.lianlianpay.com/v1/txn/query-payment
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@SuppressWarnings("unused")
@ApiModel(description = "3.2.8. 支付结果查询 该接口提供所有消费/充值场景下的支付订单查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。需要调用查询接口的情况：商户后台、网络、服务器等出现异常，商户最终未接收到支付结果通知；调用支付接口后，返回系统错误或者未知交易、处理中交易状态情况。 请求地址https://accpapi.lianlianpay.com/v1/txn/query-payment")
public class ResQueryPayment implements Serializable {
    private static final long serialVersionUID = -7517162609894270352L;
    /**
     * ret_code,交易结果代码,Y,String,查询交易返回码,该字段为本次查询交易结果标识，非原支付交易结果标识，支付是否成功需要根据 txn_status 字段来判断
     */
    @NotBlank()
    @ApiModelProperty(value = "ret_code,交易结果代码,Y,String,查询交易返回码,该字段为本次查询交易结果标识，非原支付交易结果标识，支付是否成功需要根据 txn_status 字段来判断", required = true)
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
     * txn_type,交易类型,N,String,用户充值：USER_TOPUP,商户充值：MCH_TOPUP,普通消费：GENERAL_CONSUME,担保消费：SECURED_CONSUME,担保确认：SECURED_CONFIRM,内部代发：INNER_FUND_EXCHANGE,外部代发：OUTER_FUND_EXCHANGE
     */
    @ApiModelProperty("txn_type,交易类型,N,String,用户充值：USER_TOPUP,商户充值：MCH_TOPUP,普通消费：GENERAL_CONSUME,担保消费：SECURED_CONSUME,担保确认：SECURED_CONFIRM,内部代发：INNER_FUND_EXCHANGE,外部代发：OUTER_FUND_EXCHANGE")
    private String txn_type;
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
     * accp_txno,ACCP 系统交易单 N,String,ACCP系统交易单号号
     */
    @ApiModelProperty("accp_txno,ACCP 系统交易单 N,String,ACCP系统交易单号号")
    private String accp_txno;
    /**
     * chnl_txno,渠道交易单号,N,String,渠道交易单号
     */
    @ApiModelProperty("chnl_txno,渠道交易单号,N,String,渠道交易单号")
    private String chnl_txno;
    /**
     * txn_status,支付交易状态,N,String, TRADE_WAIT_PAY：等待付款（处理中）,TRADE_SUCCESS：成功,TRADE_CLOSE：交易关闭,TRADE_CANCEL：交易撤销,支付交易状态以此为准，商户必须依据该字段值进行后续业务逻辑处理
     */
    @ApiModelProperty("txn_status,支付交易状态,N,String, TRADE_WAIT_PAY：等待付款（处理中）,TRADE_SUCCESS：成功,TRADE_CLOSE：交易关闭,TRADE_CANCEL：交易撤销,支付交易状态以此为准，商户必须依据该字段值进行后续业务逻辑处理")
    private String txn_status;

    /**
     * 商户订单信息 orderInfo
     */
    @ApiModelProperty("商户订单信息 orderInfo")
    private OrderInfo orderInfo;
    /**
     * 付款方信息(组合支付场景返回付款方信息数组)payerInfo
     */
    @ApiModelProperty("付款方信息(组合支付场景返回付款方信息数组)payerInfo")
    private List<PayerInfo> payerInfo;
    /**
     * 收款方信息(交易分账场景返回收款方信息数组)payeeInfo
     */
    @ApiModelProperty("收款方信息(交易分账场景返回收款方信息数组)payeeInfo")
    private List<PayeeInfo> payeeInfo;

    /**
     * 商户订单信息 orderInfo
     */
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
         * order_info,订单信息,N,String,订单信息
         */
        @ApiModelProperty("order_info,订单信息,N,String,订单信息")
        private String order_info;
    }

    /**
     * 付款方信息(组合支付场景返回付款方信息数组)payerInfo
     */
    @Data
    public static class PayerInfo {

        /**
         * payer_type,付款方类型,N,String,用户：USER,平台商户：MERCHANT
         */
        @ApiModelProperty(value = "payer_type,付款方类型,N,String,用户：USER,平台商户：MERCHANT", required = true)
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
    }

    /**
     * 收款方信息(交易分账场景返回收款方信息数组)payeeInfo
     */
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
    }
}

