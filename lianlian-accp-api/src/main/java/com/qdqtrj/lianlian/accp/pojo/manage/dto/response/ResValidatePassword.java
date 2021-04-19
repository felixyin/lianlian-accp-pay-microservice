package com.qdqtrj.lianlian.accp.pojo.manage.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 3.21. 支付密码校验 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/validate-password
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.21. 支付密码校验 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/validate-password")
public class ResValidatePassword implements Serializable {
    private static final long serialVersionUID = -608163790174391350L;
    /**
     * ret_code,交易结果代码,Y,String,交易返回码,0000:验证成功
     */
    @NotBlank()
    @ApiModelProperty(value = "ret_code,交易结果代码,Y,String,交易返回码,0000:验证成功", required = true)
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
}

