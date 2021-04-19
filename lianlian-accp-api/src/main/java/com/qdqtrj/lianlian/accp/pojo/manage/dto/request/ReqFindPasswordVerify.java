package com.qdqtrj.lianlian.accp.pojo.manage.dto.request;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * 3.12.2.  验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/find-password-verify
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.12.2.  验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/find-password-verify")
public class ReqFindPasswordVerify implements Serializable {
    private static final long serialVersionUID = -2711662182433155439L;
    /**
     * timestamp,时间戳,Y,String(14),交易服务器时间戳,格式：yyyyMMddHHmmss,有效期30分钟,包含服务器之间的时间差
     */
    @NotNull()
    @JSONField(format = "yyyyMMddHHmmss")
    @ApiModelProperty("timestamp,时间戳,Y,String(14),交易服务器时间戳,格式：yyyyMMddHHmmss,有效期30分钟,包含服务器之间的时间差")
    private Date timestamp = new Date();
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
     * token,授权令牌,Y,String(32)
     */
    @NotBlank()
    @Length(min = 1, max = 32)
    @ApiModelProperty(value = "token,授权令牌,Y,String(32)", required = true)
    private String token;
    /**
     * verify_code,短信验证码,Y,String(20)
     */
    @NotBlank()
    @Length(min = 1, max = 20)
    @ApiModelProperty(value = "verify_code,短信验证码,Y,String(20)", required = true)
    private String verify_code;
    /**
     * password,新密码,Y,String,设置的新密码
     */
    @NotBlank()
    @ApiModelProperty(value = "password,新密码,Y,String,设置的新密码", required = true)
    private String password;
    /**
     * random_key,新密码随机因子key,Y,String
     */
    @NotBlank()
    @ApiModelProperty(value = "random_key,新密码随机因子key,Y,String", required = true)
    private String random_key;
}
