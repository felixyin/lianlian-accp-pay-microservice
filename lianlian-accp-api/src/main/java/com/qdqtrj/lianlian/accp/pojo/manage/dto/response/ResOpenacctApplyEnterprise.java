package com.qdqtrj.lianlian.accp.pojo.manage.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 3.4. 企业用户开户 3.4.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-enterprise
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.4. 企业用户开户 3.4.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-enterprise")
public class ResOpenacctApplyEnterprise implements Serializable {
    private static final long serialVersionUID = -8901744797605128282L;
    /**
     * ret_code,交易结果代码,Y,String(4),交易返回码
     */
    @NotBlank()
    @Length(min = 1, max = 4)
    @ApiModelProperty(value = "ret_code,交易结果代码,Y,String(4),交易返回码", required = true)
    private String ret_code;
    /**
     * ret_msg,交易结果描述,Y,String(32),交易返回描述
     */
    @NotBlank()
    @Length(min = 1, max = 32)
    @ApiModelProperty(value = "ret_msg,交易结果描述,Y,String(32),交易返回描述", required = true)
    private String ret_msg;
    /**
     * oid_partner,商户号,Y,String(18),ACCP 系统分配给平台商户的唯一编号
     */
    @NotBlank()
    @Length(min = 1, max = 18)
    @ApiModelProperty(value = "oid_partner,商户号,Y,String(18),ACCP 系统分配给平台商户的唯一编号", required = true)
    private String oid_partner;
    /**
     * user_id,商户用户唯一编号,Y,String(64),用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户
     */
    @NotBlank()
    @Length(min = 1, max = 64)
    @ApiModelProperty(value = "user_id,商户用户唯一编号,Y,String(64),用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户", required = true)
    private String user_id;
    /**
     * txn_seqno,交易流水号,Y,String(64),商户系统唯一交易流水号
     */
    @NotBlank()
    @Length(min = 1, max = 64)
    @ApiModelProperty(value = "txn_seqno,交易流水号,Y,String(64),商户系统唯一交易流水号", required = true)
    private String txn_seqno;
    /**
     * accp_txno,ACCP系统交易单号,N,String(32),ACCP系统业务单号
     */
    @Length(min = 1, max = 32)
    @ApiModelProperty("accp_txno,ACCP系统交易单号,N,String(32),ACCP系统业务单号")
    private String accp_txno;
    /**
     * oid_userno,ACCP系统用户编号,N,String(32),用户注册成功后ACCP系统返回的用户编号
     */
    @Length(min = 1, max = 32)
    @ApiModelProperty("oid_userno,ACCP系统用户编号,N,String(32),用户注册成功后ACCP系统返回的用户编号")
    private String oid_userno;
    /**
     * user_status,用户状态,N,String(32),参见‘用户状态列表’
     */
    @Length(min = 1, max = 32)
    @ApiModelProperty("* user_status,用户状态,N,String(32),参见‘用户状态列表’")
    private String user_status;
}

