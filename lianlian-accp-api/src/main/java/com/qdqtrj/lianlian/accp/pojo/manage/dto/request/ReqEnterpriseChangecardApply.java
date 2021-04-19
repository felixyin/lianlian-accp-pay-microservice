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
 * 3.6. 企业用户更换绑定账号 3.6.1. 更换申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/enterprise-changecard-apply
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.6. 企业用户更换绑定账号 3.6.1. 更换申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/enterprise-changecard-apply")
public class ReqEnterpriseChangecardApply implements Serializable {
    private static final long serialVersionUID = -5206475330681433375L;
    /**
     * timestamp,时间戳,Y,String(14),交易服务器时间戳,格式：yyyyMMddHHmmss,有效期30分钟,包含服务器之间的时间差业务参数
     */
    @NotNull()
    @JSONField(format = "yyyyMMddHHmmss")
    @ApiModelProperty("timestamp,时间戳,Y,String(14),交易服务器时间戳,格式：yyyyMMddHHmmss,有效期30分钟,包含服务器之间的时间差业务参数")
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
     * linked_acctno,新绑定银行帐号,Y,String(32),企业用户绑定的对公账户号
     */
    @NotBlank()
    @Length(min = 1, max = 32)
    @ApiModelProperty(value = "linked_acctno,新绑定银行帐号,Y,String(32),企业用户绑定的对公账户号", required = true)
    private String linked_acctno;
    /**
     * linked_bankcode,新绑定银行编码,Y,String(8),企业用户必填
     */
    @NotBlank()
    @Length(min = 1, max = 8)
    @ApiModelProperty(value = "linked_bankcode,新绑定银行编码,Y,String(8),企业用户必填", required = true)
    private String linked_bankcode;
    /**
     * linked_brbankno,新绑定对公账户开 Y,String(12),企业用户开户行行号户行号
     */
    @ApiModelProperty(value = "linked_brbankno,新绑定对公账户开 Y,String(12),企业用户开户行行号户行号", required = true)
    private String linked_brbankno;
    /**
     * linked_acctname,对公账户名,Y,String(85),企业用户绑定的对公账户名
     */
    @NotBlank()
    @Length(min = 1, max = 85)
    @ApiModelProperty(value = "linked_acctname,对公账户名,Y,String(85),企业用户绑定的对公账户名", required = true)
    private String linked_acctname;
    /**
     * linked_brbankname,对公账户开户行名,N,String(85),企业用户绑定账户开户支行名称
     */
    @Length(min = 1, max = 85)
    @ApiModelProperty("linked_brbankname,对公账户开户行名,N,String(85),企业用户绑定账户开户支行名称")
    private String linked_brbankname;
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
