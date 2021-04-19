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
 * 3.20. 上传照片 该接口用于上传个人用户的身份证照片及企业用户的统一社会信用代码证照片和企业法人 身份证照片。 3.20.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/upload-photos
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.20. 上传照片 该接口用于上传个人用户的身份证照片及企业用户的统一社会信用代码证照片和企业法人 身份证照片。 3.20.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/upload-photos")
public class ReqUploadPhotos implements Serializable {
    private static final long serialVersionUID = 8931787754546008928L;
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
     * id_emblem,身份证国徽面,Y,String,个人用户身份证国徽面照片；企业用户法人身份证国徽面照片；Base64 编码后传输，字符编码:UTF-8;最大4M;
     */
    @NotBlank()
    @ApiModelProperty(value = "id_emblem,身份证国徽面,Y,String,个人用户身份证国徽面照片；企业用户法人身份证国徽面照片；Base64 编码后传输，字符编码:UTF-8;最大4M;", required = true)
    private String id_emblem;
    /**
     * id_portrait,身份证人像面,Y,String,个人用户身份证人像面照片；企业用户法人身份证人像面照片；Base64 编码后传输，字符编码:UTF-8；最大4M;
     */
    @NotBlank()
    @ApiModelProperty(value = "id_portrait,身份证人像面,Y,String,个人用户身份证人像面照片；企业用户法人身份证人像面照片；Base64 编码后传输，字符编码:UTF-8；最大4M;", required = true)
    private String id_portrait;
    /**
     * id_filetype,身份证照片文件类 Y,String,身份证照片文件类型,支持bmp、png、jpeg、jpg、gif
     */
    @ApiModelProperty(value = "id_filetype,身份证照片文件类 Y,String,身份证照片文件类型,支持bmp、png、jpeg、jpg、gif", required = true)
    private String id_filetype;
    /**
     * unified_code,统一社会信用代码 N,String,企业用户统一社会信用代码证照证片；企业用户必填,Base64,编码后传输，字符编码:UTF-8；最大4M;
     */
    @ApiModelProperty("unified_code,统一社会信用代码 N,String,企业用户统一社会信用代码证照证片；企业用户必填,Base64,编码后传输，字符编码:UTF-8；最大4M;")
    private String unified_code;
    /**
     * unified_code_filetype,统一社会信用代码 N,String,统一社会信用代码证照片文件类型,证照片文件类型,支持bmp、png、jpeg、jpg、gif
     */
    @ApiModelProperty("unified_code_filetype,统一社会信用代码 N,String,统一社会信用代码证照片文件类型,证照片文件类型,支持bmp、png、jpeg、jpg、gif")
    private String unified_code_filetype;
}
