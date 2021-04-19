package com.qdqtrj.lianlian.accp.pojo.manage.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 3.15. 用户信息查询 3.15.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-userinfo
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.15. 用户信息查询 3.15.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-userinfo")
public class ResQueryUserinfo implements Serializable {
    private static final long serialVersionUID = 33105946028245695L;
    /**
     * ret_code,交易结果代码,Y,String(4)
     */
    @NotBlank()
    @Length(min = 1, max = 4)
    @ApiModelProperty(value = "ret_code,交易结果代码,Y,String(4)", required = true)
    private String ret_code;
    /**
     * ret_msg,交易结果描述,Y,String(32)
     */
    @NotBlank()
    @Length(min = 1, max = 32)
    @ApiModelProperty(value = "ret_msg,交易结果描述,Y,String(32)", required = true)
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
     * oid_userno,ACCP系统用户编号,N,String(32),个人用户注册成功后的用户编号
     */
    @Length(min = 1, max = 32)
    @ApiModelProperty("oid_userno,ACCP系统用户编号,N,String(32),个人用户注册成功后的用户编号")
    private String oid_userno;
    /**
     * user_status,用户状态,Y,String(32),待激活：ACTIVATE_PENDING,待审核：CHECK_PENDING,审核通过，待打款验证：REMITTAN,CE_VALID_PENDING,正常：NORMAL,销户：CANCEL
     */
    @NotBlank()
    @Length(min = 1, max = 32)
    @ApiModelProperty(value = "user_status,用户状态,Y,String(32),待激活：ACTIVATE_PENDING,待审核：CHECK_PENDING,审核通过，待打款验证：REMITTAN,CE_VALID_PENDING,正常：NORMAL,销户：CANCEL", required = true)
    private String user_status;
    /**
     * remark,备注,N,String,审核不通过时，该字段内容是失败原因；
     */
    @ApiModelProperty("remark,备注,N,String,审核不通过时，该字段内容是失败原因；")
    private String remark;
    /**
     * bank_account,合作银行账号,N,String,ACCP合作银行账户,注：新网、通商、富民合作银行有效
     */
    @ApiModelProperty("bank_account,合作银行账号,N,String,ACCP合作银行账户,注：新网、通商、富民合作银行有效")
    private String bank_account;
}

