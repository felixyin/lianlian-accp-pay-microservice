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
 * 3.5. 担保 担保交易支持两类场景： 1.  在支付交易统一创单时指定担保收款方信息，担保确认时支持全额及部分金额多次确认； 对于创单时指定的担保收款方不支持确认时修改； 2.  在支付交易统一创单时不指定担保收款方信息，担保交易确认时动态指定收款方并进行 交易确认，资金从担保平台商户账户转入担保收款方账户。 3.5.1. 担保交易确认 请求地址https://accpapi.lianlianpay.com/v1/txn/secured-confirm
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.5. 担保 担保交易支持两类场景： 1.  在支付交易统一创单时指定担保收款方信息，担保确认时支持全额及部分金额多次确认； 对于创单时指定的担保收款方不支持确认时修改； 2.  在支付交易统一创单时不指定担保收款方信息，担保交易确认时动态指定收款方并进行 交易确认，资金从担保平台商户账户转入担保收款方账户。 3.5.1. 担保交易确认 请求地址https://accpapi.lianlianpay.com/v1/txn/secured-confirm")
public class ResSecuredConfirm implements Serializable {
    private static final long serialVersionUID = -5839915555009276110L;
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
     * total_amount,确认总金额,Y,Number(8,2),本次确认总金额，单位为元，精确到小数点后两位
     */
    @NotNull
    @Digits(integer = 8, fraction = 2)
    @ApiModelProperty(value = "total_amount,确认总金额,Y,Number(8,2),本次确认总金额，单位为元，精确到小数点后两位", required = true)
    private BigDecimal total_amount;
    /**
     * accp_txno,ACCP系统交易单号,N,String,ACCP系统交易单号
     */
    @ApiModelProperty("accp_txno,ACCP系统交易单号,N,String,ACCP系统交易单号")
    private String accp_txno;
}

