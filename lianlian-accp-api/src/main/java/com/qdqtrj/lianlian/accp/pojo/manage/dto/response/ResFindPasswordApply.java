package com.qdqtrj.lianlian.accp.pojo.manage.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 3.12. 找回密码 3.12.1.  申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/find-password-apply
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.12. 找回密码 3.12.1.  申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/find-password-apply")
public class ResFindPasswordApply implements Serializable {
    private static final long serialVersionUID = 7124945741630324849L;
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
     * oid_partner,商户号,Y,String(18),账户+分配给商户的唯一编号
     */
    @NotBlank()
    @Length(min = 1, max = 18)
    @ApiModelProperty(value = "oid_partner,商户号,Y,String(18),账户+分配给商户的唯一编号", required = true)
    private String oid_partner;
    /**
     * user_id,商户用户唯一编号,Y,String(64),用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户
     */
    @NotBlank()
    @Length(min = 1, max = 64)
    @ApiModelProperty(value = "user_id,商户用户唯一编号,Y,String(64),用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户", required = true)
    private String user_id;
    /**
     * token,授权令牌,N,String(32)
     */
    @Length(min = 1, max = 32)
    @ApiModelProperty("token,授权令牌,N,String(32)")
    private String token;
    /**
     * reg_phone,接收短验手机号,N,String(11),手机号脱敏处理，举例：183****5737
     */
    @Length(min = 1, max = 11)
    @ApiModelProperty("reg_phone,接收短验手机号,N,String(11),手机号脱敏处理，举例：183****5737")
    private String reg_phone;
}

