package com.qdqtrj.lianlian.accp.pojo.manage.dto.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.qdqtrj.lianlian.accp.pojo.RiskItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * 3.9. 修改绑定手机 3.9.1. 申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-regphone-apply
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.9. 修改绑定手机 3.9.1. 申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-regphone-apply")
public class ReqChangeRegphoneApply implements Serializable {
    private static final long serialVersionUID = -8795801294599261553L;
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
     * txn_seqno,交易流水号,Y,String(64),商户系统唯一交易流水号
     */
    @NotBlank()
    @Length(min = 1, max = 64)
    @ApiModelProperty(value = "txn_seqno,交易流水号,Y,String(64),商户系统唯一交易流水号", required = true)
    private String txn_seqno;
    /**
     * txn_time,交易时间,Y,String(14),商户系统交易时间格式：yyyyMMddHHmmss
     */
    @NotBlank()
    @Length(min = 1, max = 14)
    @ApiModelProperty(value = "txn_time,交易时间,Y,String(14),商户系统交易时间格式：yyyyMMddHHmmss", required = true)
    private String txn_time;
    /**
     * notify_url,异步通知地址,Y,String(512),交易结果异步通知接收地址，建议HTTPS协议
     */
    @NotBlank()
    @Length(min = 1, max = 512)
    @ApiModelProperty(value = "notify_url,异步通知地址,Y,String(512),交易结果异步通知接收地址，建议HTTPS协议", required = true)
    private String notify_url;
    /**
     * risk_item,风控参数,N,String,参见‘风控参数’
     */
    @ApiModelProperty("risk_item,风控参数,N,String,参见‘风控参数’")
    private RiskItem risk_item;
    /**
     * reg_phone,绑定手机号,Y,String(11),绑定手机号
     */
    @NotBlank()
    @Length(min = 1, max = 11)
    @ApiModelProperty(value = "reg_phone,绑定手机号,Y,String(11),绑定手机号", required = true)
    private String reg_phone;
    /**
     * reg_phone_new,新绑定手机号码,Y,String(11),新绑定手机号
     */
    @NotBlank()
    @Length(min = 1, max = 11)
    @ApiModelProperty(value = "reg_phone_new,新绑定手机号码,Y,String(11),新绑定手机号", required = true)
    private String reg_phone_new;
    /**
     * password,密码,Y,String
     */
    @NotBlank()
    @ApiModelProperty(value = "password,密码,Y,String", required = true)
    private String password;
    /**
     * random_key,密码随机因子key,Y,String
     */
    @NotBlank()
    @ApiModelProperty(value = "random_key,密码随机因子key,Y,String", required = true)
    private String random_key;
}
