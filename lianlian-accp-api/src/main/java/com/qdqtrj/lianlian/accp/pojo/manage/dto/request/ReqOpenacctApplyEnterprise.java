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
 * 3.4. 企业用户开户 3.4.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-enterprise
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.4. 企业用户开户 3.4.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-enterprise")
public class ReqOpenacctApplyEnterprise implements Serializable {
    private static final long serialVersionUID = 7614849425606591283L;
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
     * user_id,商户用户唯一编 Y,String(64),用户在商户系统中的唯一编号，要求号该编号在商户系统能唯一标识用户
     */
    @ApiModelProperty(value = "user_id,商户用户唯一编 Y,String(64),用户在商户系统中的唯一编号，要求号该编号在商户系统能唯一标识用户", required = true)
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
     * open_sms_flag,开户成功短信通 N,String(1),开户成功短信通知通知,Y：发送,N：不发送,默认是N,注：：该功能需要商户合作银行支持，暂只满足富民银行
     */
    @ApiModelProperty("open_sms_flag,开户成功短信通 N,String(1),开户成功短信通知通知,Y：发送,N：不发送,默认是N,注：：该功能需要商户合作银行支持，暂只满足富民银行")
    private String open_sms_flag;
    /**
     * risk_item,风控参数,N,String,参见‘风控参数’
     */
    @ApiModelProperty("risk_item,风控参数,N,String,参见‘风控参数’")
    private RiskItem risk_item;
    /**
     * 开户基本信息 basicInfo
     */
    @ApiModelProperty("开户基本信息 basicInfo")
    private BasicInfo basicInfo;
    /**
     * 开户绑卡信息 linkedAcctInfo
     */
    @ApiModelProperty("开户绑卡信息 linkedAcctInfo")
    private LinkedAcctInfo linkedAcctInfo;
    /**
     * 企业法定代表人信息 legalreptInfo
     */
    @ApiModelProperty("企业法定代表人信息 legalreptInfo")
    private LegalreptInfo legalreptInfo;
    /**
     * 企业联系人信息 contactsInfo
     */
    @ApiModelProperty("企业联系人信息 contactsInfo")
    private ContactsInfo contactsInfo;
    /**
     * 企业经营信息 businessInfo
     */
    @ApiModelProperty("企业经营信息 businessInfo")
    private BusinessInfo businessInfo;
    /**
     * 企业基本户信息 basicAcctInfo
     */
    @ApiModelProperty("企业基本户信息 basicAcctInfo")
    private BasicAcctInfo basicAcctInfo;

    /**
     * 开户基本信息 basicInfo
     */
    @Data
    public static class BasicInfo {

        /**
         * reg_phone,绑定手机号码,Y,String(11),用户开户绑定手机号
         */
        @NotBlank()
        @Length(min = 1, max = 11)
        @ApiModelProperty(value = "reg_phone,绑定手机号码,Y,String(11),用户开户绑定手机号", required = true)
        private String reg_phone;
        /**
         * user_name,用户名称,Y,String(85),用户名称（企业用户则是企业名称，个人用户则是姓名）
         */
        @NotBlank()
        @Length(min = 1, max = 85)
        @ApiModelProperty(value = "user_name,用户名称,Y,String(85),用户名称（企业用户则是企业名称，个人用户则是姓名）", required = true)
        private String user_name;
        /**
         * id_type,证件类型,Y,String(32),统一社会信用代码证：UNIFIED_SOCIAL_CREDIT_CODE
         */
        @NotBlank()
        @Length(min = 1, max = 32)
        @ApiModelProperty(value = "id_type,证件类型,Y,String(32),统一社会信用代码证：UNIFIED_SOCIAL_CREDIT_CODE", required = true)
        private String id_type;
        /**
         * id_no,证件号码,Y,String(64),证件号码
         */
        @NotBlank()
        @Length(min = 1, max = 64)
        @ApiModelProperty(value = "id_no,证件号码,Y,String(64),证件号码", required = true)
        private String id_no;
        /**
         * id_exp,证件有效期,Y,String(8),证件到期日，格式：yyyyMMdd,长期有效则设置为：99991231
         */
        @NotBlank()
        @Length(min = 1, max = 8)
        @ApiModelProperty(value = "id_exp,证件有效期,Y,String(8),证件到期日，格式：yyyyMMdd,长期有效则设置为：99991231", required = true)
        private String id_exp;
        /**
         * address,地址,Y,String(85),企业地址
         */
        @NotBlank()
        @Length(min = 1, max = 85)
        @ApiModelProperty(value = "address,地址,Y,String(85),企业地址", required = true)
        private String address;
        /**
         * reg_email,注册邮箱,Y,String(32) xx
         */
        @NotBlank()
        @Length(min = 1, max = 32)
        @ApiModelProperty(value = "reg_email,注册邮箱,Y,String(32) xx", required = true)
        private String reg_email;
        /**
         * password,密码,Y,String xx
         */
        @NotBlank()
        @ApiModelProperty(value = "password,密码,Y,String xx", required = true)
        private String password;
        /**
         * random_key,密码随机因子,Y,String,xx
         */
        @NotBlank()
        @ApiModelProperty(value = "random_key,密码随机因子,Y,String,xx", required = true)
        private String random_key;

    }

    /**
     * 开户绑卡信息 linkedAcctInfo
     */
    @Data
    public static class LinkedAcctInfo {
        /**
         * linked_acctno,绑定银行帐号,Y,String(32),企业用户绑定的银行帐号
         */
        @NotBlank()
        @Length(min = 1, max = 32)
        @ApiModelProperty(value = "linked_acctno,绑定银行帐号,Y,String(32),企业用户绑定的银行帐号", required = true)
        private String linked_acctno;
        /**
         * linked_bankcode,银行编码,Y,String(8)
         */
        @NotBlank()
        @Length(min = 1, max = 8)
        @ApiModelProperty(value = "linked_bankcode,银行编码,Y,String(8)", required = true)
        private String linked_bankcode;
        /**
         * linked_brbankname,对公账户开户行 N,String(85),企业用户绑定账户开户支行名称名
         */
        @ApiModelProperty("linked_brbankname,对公账户开户行 N,String(85),企业用户绑定账户开户支行名称名")
        private String linked_brbankname;
        /**
         * linked_brbankno,对公账户开户行 Y,String(12),企业用户开户行行号，企业用户必填号
         */
        @ApiModelProperty(value = "linked_brbankno,对公账户开户行 Y,String(12),企业用户开户行行号，企业用户必填号", required = true)
        private String linked_brbankno;
        /**
         * linked_acctname,对公账户名,Y,String(85),企业用户必填
         */
        @NotBlank()
        @Length(min = 1, max = 85)
        @ApiModelProperty(value = "linked_acctname,对公账户名,Y,String(85),企业用户必填", required = true)
        private String linked_acctname;
    }

    /**
     * 企业法定代表人信息 legalreptInfo
     */
    @Data
    public static class LegalreptInfo {


        /**
         * legalrept_name,法定代表人姓名,Y,String(85),法定代表人姓名
         */
        @NotBlank()
        @Length(min = 1, max = 85)
        @ApiModelProperty(value = "legalrept_name,法定代表人姓名,Y,String(85),法定代表人姓名", required = true)
        private String legalrept_name;
        /**
         * legalrept_phone,法定代表人手机 Y,String(11),法定代表人手机号号
         */
        @ApiModelProperty(value = "legalrept_phone,法定代表人手机 Y,String(11),法定代表人手机号号", required = true)
        private String legalrept_phone;
        /**
         * legalrept_idno,法定代表人身份 Y,String(64),法定代表人身份证号证号
         */
        @ApiModelProperty(value = "legalrept_idno,法定代表人身份 Y,String(64),法定代表人身份证号证号", required = true)
        private String legalrept_idno;
        /**
         * legalrept_idexp,法定代表人身份 Y,String(8),法定代表人身份证到期日，格式：yyyyMMdd,长期有效则设置为：99991231
         */
        @ApiModelProperty(value = "legalrept_idexp,法定代表人身份 Y,String(8),法定代表人身份证到期日，格式：yyyyMMdd,长期有效则设置为：99991231", required = true)
        private String legalrept_idexp;
    }

    /**
     * 企业联系人信息 contactsInfo
     */
    @Data
    public static class ContactsInfo {
        /**
         * contacts_name,联系人姓名,Y,String(85),联系人姓名
         */
        @NotBlank()
        @Length(min = 1, max = 85)
        @ApiModelProperty(value = "contacts_name,联系人姓名,Y,String(85),联系人姓名", required = true)
        private String contacts_name;
        /**
         * contacts_phone,联系人手机号,Y,String(11),联系人手机号
         */
        @NotBlank()
        @Length(min = 1, max = 11)
        @ApiModelProperty(value = "contacts_phone,联系人手机号,Y,String(11),联系人手机号", required = true)
        private String contacts_phone;
    }

    /**
     * 企业经营信息 businessInfo
     */
    @Data
    public static class BusinessInfo {

        /**
         * scale,企业规模,N,String(170),企业规模
         */
        @Length(min = 1, max = 170)
        @ApiModelProperty("scale,企业规模,N,String(170),企业规模")
        private String scale;
        /**
         * industry_code,行业代码,N,String(32),行业代码
         */
        @Length(min = 1, max = 32)
        @ApiModelProperty("industry_code,行业代码,N,String(32),行业代码")
        private String industry_code;
        /**
         * registered_capital,注册资本,N,String(32),注册资本，单位：万元
         */
        @Length(min = 1, max = 32)
        @ApiModelProperty("registered_capital,注册资本,N,String(32),注册资本，单位：万元")
        private String registered_capital;
        /**
         * business_scope,经营范围,N,String(170),经营范围
         */
        @Length(min = 1, max = 170)
        @ApiModelProperty("business_scope,经营范围,N,String(170),经营范围")
        private String business_scope;
    }

    /**
     * 企业基本户信息 basicAcctInfo
     */
    @Data
    public static class BasicAcctInfo {

        /**
         * basicacct_bankcode,基本户银行编码,Y,String(8),企业基本户银行编码
         */
        @NotBlank()
        @Length(min = 1, max = 8)
        @ApiModelProperty(value = "basicacct_bankcode,基本户银行编码,Y,String(8),企业基本户银行编码", required = true)
        private String basicacct_bankcode;
        /**
         * basicacct_no,基本户账号,Y,String(32),企业基本户帐号
         */
        @NotBlank()
        @Length(min = 1, max = 32)
        @ApiModelProperty(value = "basicacct_no,基本户账号,Y,String(32),企业基本户帐号", required = true)
        private String basicacct_no;
    }
}
