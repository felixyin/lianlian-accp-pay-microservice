package com.qdqtrj.lianlian.accp.pojo.manage.dto.callback;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 3.3.2. 开户验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-verify-individual
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
public class NotifyOpenacctVerifyIndividual implements Serializable {
    private static final long serialVersionUID = -5907913416617951535L;
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
     * accp_txno,ACCP系统交易单号,Y,String(32),ACCP系统业务单号
     */
    @NotBlank()
    @Length(min = 1, max = 32)
    private String accp_txno;
    /**
     * oid_userno,ACCP系统用户编号,Y,String(32),用户注册成功后ACCP系统返回的用户编号
     */
    @NotBlank()
    @Length(min = 1, max = 32)
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
     * remark,备注,N,String,开户失败时，该字段内容是失败原因
     */
    private String remark;
    /**
     * bank_account,合作银行账号,N,String,ACCP合作银行账户,注：新网、通商、富民合作银行有效
     */
    private String bank_account;
    /**
     * 回调业务处理是否执行成功
     */
    private boolean bizSuccess;
}
