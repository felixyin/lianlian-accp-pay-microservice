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
 * 3.3. 个人用户开户 3.3.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-individual
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.3. 个人用户开户 3.3.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-individual")
public class ReqOpenacctApplyIndividual implements Serializable {
    private static final long serialVersionUID = 3286835448089347296L;
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
     * user_id,商户用户唯一编 Y,String(64),用户在商户系统中的唯一编号，要号求该编号在商户系统能唯一标识用户
     */
    @ApiModelProperty(value = "user_id,商户用户唯一编 Y,String(64),用户在商户系统中的唯一编号，要号求该编号在商户系统能唯一标识用户", required = true)
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
     * open_sms_flag,开户成功短信通 N,String(1),开户成功短信通知通知,Y：发送,N：不发送,默认是N,注：该功能需要商户合作银行支持，暂只满足富民银行
     */
    @ApiModelProperty("open_sms_flag,开户成功短信通 N,String(1),开户成功短信通知通知,Y：发送,N：不发送,默认是N,注：该功能需要商户合作银行支持，暂只满足富民银行")
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
     * 开户基本信息 basicInfo
     */
    @Data
    public static class BasicInfo {

        /**
         * reg_phone,绑定手机号,Y,String(11),用户开户绑定手机号
         */
        @NotBlank()
        @Length(min = 1, max = 11)
        @ApiModelProperty(value = "reg_phone,绑定手机号,Y,String(11),用户开户绑定手机号", required = true)
        private String reg_phone;
        /**
         * user_name,用户名称,Y,String(85),用户名称
         */
        @NotBlank()
        @Length(min = 1, max = 85)
        @ApiModelProperty(value = "user_name,用户名称,Y,String(85),用户名称", required = true)
        private String user_name;
        /**
         * id_type,证件类型,Y,String(32),身份证：ID_CARD
         */
        @NotBlank()
        @Length(min = 1, max = 32)
        @ApiModelProperty(value = "id_type,证件类型,Y,String(32),身份证：ID_CARD", required = true)
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
         * id_authority,发证机关,N,String(64),证件签发机关
         */
        @Length(min = 1, max = 64)
        @ApiModelProperty("id_authority,发证机关,N,String(64),证件签发机关")
        private String id_authority;
        /**
         * address,地址,N,String(85),用户住址
         */
        @Length(min = 1, max = 85)
        @ApiModelProperty("address,地址,N,String(85),用户住址")
        private String address;
        /**
         * occupation,职业,Y,String(85),职业
         */
        @NotBlank()
        @Length(min = 1, max = 85)
        @ApiModelProperty(value = "occupation,职业,Y,String(85),职业", required = true)
        private String occupation;
    }

    /**
     * 开户绑卡信息 linkedAcctInfo
     */
    @Data
    public static class LinkedAcctInfo {

        /**
         * linked_acctno,绑定银行帐号,Y,String(32),个人用户绑定的银行卡号
         */
        @NotBlank()
        @Length(min = 1, max = 32)
        @ApiModelProperty(value = "linked_acctno,绑定银行帐号,Y,String(32),个人用户绑定的银行卡号", required = true)
        private String linked_acctno;
        /**
         * linked_phone,银行预留手机号,Y,String(11)
         */
        @NotBlank()
        @Length(min = 1, max = 11)
        @ApiModelProperty(value = "linked_phone,银行预留手机号,Y,String(11)", required = true)
        private String linked_phone;
    }
}
