package com.qdqtrj.lianlian.accp.pojo.manage.dto.request;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * 3.2. 绑定手机验证码验证 3.2.1. 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/regphone-verifycode-verify
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
public class ReqRegphoneVerifycodeVerify implements Serializable {
    private static final long serialVersionUID = -644464277408925779L;
    /**
     * timestamp,时间戳,Y,String(14),交易服务器时间戳,格式：yyyyMMddHHmmss,有效期30分钟,包含服务器之间的时间差
     */
    @NotNull()
    @JSONField(format = "yyyyMMddHHmmss")
    @ApiModelProperty("timestamp,时间戳,Y,String(14),交易服务器时间戳,格式：yyyyMMddHHmmss,有效期30分钟,包含服务器之间的时间差")
    private Date timestamp = new Date();
    /**
     * oid_partner,商户号,Y,String(18),ACCP 系统分配给平台商户的唯一 编号
     */
    @NotBlank()
    @Length(min = 1, max = 18)
    @ApiModelProperty(value = "oid_partner,商户号,Y,String(18),ACCP 系统分配给平台商户的唯一 编号", required = true)
    private String oid_partner;
    /**
     * user_id,商户用户唯一编号,Y,String(64),用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户
     */
    @NotBlank()
    @Length(min = 1, max = 64)
    @ApiModelProperty(value = "user_id,商户用户唯一编号,Y,String(64),用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户", required = true)
    private String user_id;
    /**
     * reg_phone,绑定手机号,Y,String(11),用户开户注册绑定手机号
     */
    @NotBlank()
    @Length(min = 1, max = 11)
    @ApiModelProperty(value = "reg_phone,绑定手机号,Y,String(11),用户开户注册绑定手机号", required = true)
    private String reg_phone;
    /**
     * verify_code,绑定手机号验证码,Y,String(20),通过调用 3.2 接口申请的注册验证码
     */
    @NotBlank()
    @Length(min = 1, max = 20)
    @ApiModelProperty(value = "verify_code,绑定手机号验证码,Y,String(20),通过调用 3.2 接口申请的注册验证码", required = true)
    private String verify_code;
}
