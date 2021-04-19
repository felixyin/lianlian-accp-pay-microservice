package com.qdqtrj.lianlian.accp.pojo.manage.dto.callback;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 3.5.2. 绑卡验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/individual-bindcard-verify
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
public class NotifyIndividualBindcardVerify implements Serializable {
    private static final long serialVersionUID = 307245267126707314L;
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
     * action,操作类型,Y,String(32),LINKEDACCT_IND：个人用户新增绑卡
     */
    @NotBlank()
    @Length(min = 1, max = 32)
    private String action;
    /**
     * result,操作结果,Y,String(32),SUCCESS
     */
    @NotBlank()
    @Length(min = 1, max = 32)
    private String result;
    /**
     * linked_agrtno,绑卡协议号,Y,String(32),绑卡协议号
     */
    @NotBlank()
    @Length(min = 1, max = 32)
    private String linked_agrtno;
    /**
     * 回调业务处理是否执行成功
     */
    private boolean bizSuccess;
}
