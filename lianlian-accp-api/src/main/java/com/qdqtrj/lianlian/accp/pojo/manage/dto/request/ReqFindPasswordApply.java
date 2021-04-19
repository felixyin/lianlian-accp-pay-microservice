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
 * 3.12. 找回密码 3.12.1.  申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/find-password-apply
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.12. 找回密码 3.12.1.  申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/find-password-apply")
public class ReqFindPasswordApply implements Serializable {
    private static final long serialVersionUID = -4256474212909699838L;
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
     * linked_acctno,绑定银行帐号,N,String(32),个人用户绑定的银行卡号、个人用户,必填
     */
    @Length(min = 1, max = 32)
    @ApiModelProperty("linked_acctno,绑定银行帐号,N,String(32),个人用户绑定的银行卡号、个人用户,必填")
    private String linked_acctno;
    /**
     * risk_item,风控参数,N,String,参见‘风控参数’
     */
    @ApiModelProperty("risk_item,风控参数,N,String,参见‘风控参数’")
    private RiskItem risk_item;
}
