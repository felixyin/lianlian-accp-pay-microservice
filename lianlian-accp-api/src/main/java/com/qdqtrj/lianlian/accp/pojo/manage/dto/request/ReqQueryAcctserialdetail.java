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
 * 3.17.2.  资金流水详情查询 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-acctserialdetail
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.17.2.  资金流水详情查询 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-acctserialdetail")
public class ReqQueryAcctserialdetail implements Serializable {
    private static final long serialVersionUID = 3459590398019663676L;
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
     * user_id,商户用户唯一编号,N,String(64),用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户;user_id不送，则查询商户资金流水信息
     */
    @Length(min = 1, max = 64)
    @ApiModelProperty("user_id,商户用户唯一编号,N,String(64),用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户;user_id不送，则查询商户资金流水信息")
    private String user_id;
    /**
     * user_type,用户类型,Y,String(32),INNERMERCHANT:商户,INNERUSER：个人用户,INNERCOMPANY：企业用户
     */
    @NotBlank()
    @Length(min = 1, max = 32)
    @ApiModelProperty(value = "user_type,用户类型,Y,String(32),INNERMERCHANT:商户,INNERUSER：个人用户,INNERCOMPANY：企业用户", required = true)
    private String user_type;
    /**
     * jno_acct,资金流水号,Y,String(20),ACCP账务系统资金流水唯一标识
     */
    @NotBlank()
    @Length(min = 1, max = 20)
    @ApiModelProperty(value = "jno_acct,资金流水号,Y,String(20),ACCP账务系统资金流水唯一标识", required = true)
    private String jno_acct;
}
