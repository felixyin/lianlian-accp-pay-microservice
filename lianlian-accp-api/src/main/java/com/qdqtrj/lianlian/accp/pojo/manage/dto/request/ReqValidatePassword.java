package com.qdqtrj.lianlian.accp.pojo.manage.dto.request;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * 3.21. 支付密码校验 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/validate-password
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.21. 支付密码校验 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/validate-password")
public class ReqValidatePassword implements Serializable {
    private static final long serialVersionUID = -5730509092072783732L;
    /**
     * timestamp,时间戳,Y,Date,交易服务器时间戳,格式：yyyyMMddHHmmss,有效期30分钟,包含服务器之间的时间差
     */
    @NotNull()
    @JSONField(format = "yyyyMMddHHmmss")
    @ApiModelProperty("timestamp,时间戳,Y,Date,交易服务器时间戳,格式：yyyyMMddHHmmss,有效期30分钟,包含服务器之间的时间差")
    private Date timestamp = new Date();
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
     * random_key,密码随机因子key,Y,String,通过3.13随机因子获取接口获取
     */
    @NotBlank()
    @ApiModelProperty(value = "random_key,密码随机因子key,Y,String,通过3.13随机因子获取接口获取", required = true)
    private String random_key;
    /**
     * password,密码,Y,String,密码
     */
    @NotBlank()
    @ApiModelProperty(value = "password,密码,Y,String,密码", required = true)
    private String password;
}
