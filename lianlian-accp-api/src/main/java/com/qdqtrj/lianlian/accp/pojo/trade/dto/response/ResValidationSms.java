package com.qdqtrj.lianlian.accp.pojo.trade.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 3.6. 交易二次短信验证 对于充值、消费、提现、代发类交易需要二次进行付款方短信验证时，根据申请接口的交易 返回码进行判断，统一调用该接口完成交易验证。 请求地址https://accpapi.lianlianpay.com/v1/txn/validation-sms
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.6. 交易二次短信验证 对于充值、消费、提现、代发类交易需要二次进行付款方短信验证时，根据申请接口的交易 返回码进行判断，统一调用该接口完成交易验证。 请求地址https://accpapi.lianlianpay.com/v1/txn/validation-sms")
public class ResValidationSms implements Serializable {
    private static final long serialVersionUID = -6013043144949741911L;
    /**
     * ret_code,交易结果代码,Y,String,交易返回码
     */
    @NotBlank()
    @ApiModelProperty(value = "ret_code,交易结果代码,Y,String,交易返回码", required = true)
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
     * user_id,商户用户唯一编号,Y,String,用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户
     */
    @NotBlank()
    @ApiModelProperty(value = "user_id,商户用户唯一编号,Y,String,用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户", required = true)
    private String user_id;
    /**
     * txn_seqno,交易流水号,Y,String,商户系统唯一交易流水号
     */
    @NotBlank()
    @ApiModelProperty(value = "txn_seqno,交易流水号,Y,String,商户系统唯一交易流水号", required = true)
    private String txn_seqno;
    /**
     * total_amount,订单总金额,Y,Number(8,2),订单总金额，单位为元，精确到小数点后两位
     */
    @NotNull
    @Digits(integer = 8, fraction = 2)
    @ApiModelProperty(value = "total_amount,订单总金额,Y,Number(8,2),订单总金额，单位为元，精确到小数点后两位", required = true)
    private BigDecimal total_amount;
    /**
     * accp_txno,ACCP 系统交易单 N,String,ACCP系统交易单号
     */
    @ApiModelProperty("accp_txno,ACCP 系统交易单 N,String,ACCP系统交易单号")
    private String accp_txno;
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
}

