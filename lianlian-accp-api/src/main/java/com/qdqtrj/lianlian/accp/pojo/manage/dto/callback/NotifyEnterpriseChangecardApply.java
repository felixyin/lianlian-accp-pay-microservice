package com.qdqtrj.lianlian.accp.pojo.manage.dto.callback;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 3.6. 企业用户更换绑定账号 3.6.1. 更换申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/enterprise-changecard-apply
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
public class NotifyEnterpriseChangecardApply implements Serializable {
    private static final long serialVersionUID = -8606495196284809055L;
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
     * action,操作类型,Y,String(32),LINKEDACCT_CHANGE_ENPR：企业更换绑定银行账号,REMITTANCE_VALID_PENDING：待打款验证
     */
    @NotBlank()
    @Length(min = 1, max = 32)
    private String action;
    /**
     * result,操作结果,Y,String(32) SUCCESS：绑卡成功
     */
    @NotBlank()
    @Length(min = 1, max = 32)
    private String result;
    /**
     * linked_agrtno,绑卡协议号,N,String(32),绑卡协议号
     */
    @Length(min = 1, max = 32)
    private String linked_agrtno;
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
