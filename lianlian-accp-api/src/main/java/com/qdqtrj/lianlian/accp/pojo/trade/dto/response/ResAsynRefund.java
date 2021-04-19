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
 * 3.7. 退款 3.7.1. 异步退款申请 该接口只支持普通消费交易、担保消费交易退款。 退款规则： 1.  每次发起退款只能唯一指定原消费交易一个收款方进行处理，分账类交易退款需要按照 收款方多次发起退款； 2.  支持全额或者部分退款； 3.  组合类消费交易，每次退款需要明确指定原付款方式对应的退款金额。 4.  异步退款申请在渠道真实退款结果获取之前状态为处理中，且该笔资金将被冻结 请求地址https://accpapi.lianlianpay.com/v1/txn/asyn-refund
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.7. 退款 3.7.1. 异步退款申请 该接口只支持普通消费交易、担保消费交易退款。 退款规则： 1.  每次发起退款只能唯一指定原消费交易一个收款方进行处理，分账类交易退款需要按照 收款方多次发起退款； 2.  支持全额或者部分退款； 3.  组合类消费交易，每次退款需要明确指定原付款方式对应的退款金额。 4.  异步退款申请在渠道真实退款结果获取之前状态为处理中，且该笔资金将被冻结 请求地址https://accpapi.lianlianpay.com/v1/txn/asyn-refund")
public class ResAsynRefund implements Serializable {
    private static final long serialVersionUID = -3792590031151256584L;
    /**
     * ret_code,交易结果代码,Y,String,交易返回码，0000表示受理成功，具体退款结果请通过退款结果查询接口获取
     */
    @NotBlank()
    @ApiModelProperty(value = "ret_code,交易结果代码,Y,String,交易返回码，0000表示受理成功，具体退款结果请通过退款结果查询接口获取", required = true)
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
     * accp_txno,ACCP系统交易单号,N,String,ACCP系统退款单号
     */
    @ApiModelProperty("accp_txno,ACCP系统交易单号,N,String,ACCP系统退款单号")
    private String accp_txno;
}

