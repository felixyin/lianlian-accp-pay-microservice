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
 * 3.13. 随机因子获取 3.13.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/get-random
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.13. 随机因子获取 3.13.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/get-random")
public class ReqGetRandom implements Serializable {
    public static final String ANDROID = "ANDROID";
    public static final String IOS = "IOS";
    public static final String H5 = "H5";
    public static final String PC = "PC";
    private static final long serialVersionUID = -1806805809260637104L;

    /**
     * timestamp,时间戳,Y,String(14),交易服务器时间戳格式：yyyyMMddHHmmss,有效期 30 分钟（包含服务器之间的时间差）
     */
    @NotNull()
    @JSONField(format = "yyyyMMddHHmmss")
    @ApiModelProperty("timestamp,时间戳,Y,String(14),交易服务器时间戳格式：yyyyMMddHHmmss,有效期 30 分钟（包含服务器之间的时间差）")
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
     * flag_chnl,应用渠道,Y,String(10),交易发起渠道 ANDROID IOS H5 PC
     */
    @NotBlank()
    @Length(min = 1, max = 10)
    @ApiModelProperty(value = "flag_chnl,应用渠道,Y,String(10),交易发起渠道 ANDROID IOS H5 PC", required = true)
    private String flag_chnl;
    /**
     * pkg_name,APP包名,N,String(256),flag_chnl为H5时，送商户一级域名,（支持ip）
     */
    @Length(min = 1, max = 256)
    @ApiModelProperty("pkg_name,APP包名,N,String(256),flag_chnl为H5时，送商户一级域名,（支持ip）")
    private String pkg_name;
    /**
     * app_name,APP应用名,N,String(256),flag_chnl为H5时，送商户一级域名,（支持IP）
     */
    @Length(min = 1, max = 256)
    @ApiModelProperty("app_name,APP应用名,N,String(256),flag_chnl为H5时，送商户一级域名,（支持IP）")
    private String app_name;
}
