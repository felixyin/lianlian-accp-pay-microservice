package com.qdqtrj.lianlian.accp.pojo.manage.dto.callback;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 3.4. 企业用户开户 3.4.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-enterprise
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
public class NotifyOpenacctApplyEnterprise implements Serializable {
    private static final long serialVersionUID = 6258601051372858586L;
    /**
     * oid_partner,商户号,Y,String(18),ACCP 系统分配给平台商户的唯一编号
     */
    @NotBlank()
    @Length(min = 1, max = 18)
    private String oid_partner;
    /**
     * user_id,商户用户唯一编号,Y,String(64),用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户
     */
    @NotBlank()
    @Length(min = 1, max = 64)
    private String user_id;
    /**
     * txn_seqno,商户唯一订单号,Y,String(64),商户系统唯一交易流水号
     */
    @NotBlank()
    @Length(min = 1, max = 64)
    private String txn_seqno;
    /**
     * accp_txno,ACCP 系统交易单 Y,String(32),ACCP系统业务单号号
     */
    private String accp_txno;
    /**
     * oid_userno,ACCP系统用户编 Y,String(32),用户注册成功后 ACCP 系统返回的号用户编号
     */
    private String oid_userno;
    /**
     * user_status,用户状态,Y,String(32),参见‘用户状态列表’
     */
    @NotBlank()
    @Length(min = 1, max = 32)
    private String user_status;
    /**
     * linked_agrtno,绑卡协议号,N,String(32),绑卡协议号
     */
    @Length(min = 1, max = 32)
    private String linked_agrtno;
    /**
     * remark,备注,N,String,审核不通过时，该字段内容是失败原因；
     */
    private String remark;
    /**
     * bank_account,合作银行账号,N,String,ACCP合作银行账户,注：新网、通商、富民合作银行有效 企业用户绑卡打款信息
     */
    private String bank_account;

    /**
     * 企业用户绑卡打款信息 remittanceValidInfo(企业用户绑卡需要打款验证时返回)
     */
    private RemittanceValidInfo remittanceValidInfo;
    /**
     * 回调业务处理是否执行成功
     */
    private boolean bizSuccess;

    /**
     * 企业用户绑卡打款信息 remittanceValidInfo(企业用户绑卡需要打款验证时返回)
     */
    @Data
    public static class RemittanceValidInfo {

        /**
         * payee_acctno,收款帐号,N,String(32),收款帐号
         */
        @Length(min = 1, max = 32)
        private String payee_acctno;
        /**
         * payee_name,收款账户名称,N,String(85),收款账户名称
         */
        @Length(min = 1, max = 85)
        private String payee_name;
        /**
         * payee_amt,打款金额,N,Number(8,2),打款金额，单位：元
         */
        @Digits(integer = 8, fraction = 2)
        private BigDecimal payee_amt;
        /**
         * payee_bankcode,打款开户行行号,N,String(8),打款开户行行号
         */
        @Length(min = 1, max = 8)
        private String payee_bankcode;
        /**
         * payee_bankname,打款开户行行名,N,String(85),打款开户行行名
         */
        @Length(min = 1, max = 85)
        private String payee_bankname;
        /**
         * payee_deadline,打款截止日期,N,String(8),打款截止日期
         */
        @Length(min = 1, max = 8)
        private String payee_deadline;
    }

}
