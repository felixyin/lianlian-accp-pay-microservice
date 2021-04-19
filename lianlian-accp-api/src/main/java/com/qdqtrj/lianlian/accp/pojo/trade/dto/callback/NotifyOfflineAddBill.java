package com.qdqtrj.lianlian.accp.pojo.trade.dto.callback;

import lombok.Data;

import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 3.8. 线下加款
 * 3.8.1. 线下加款成功通知 请求地址
 * 请求地址 recharge_url 需要在接入时报备，该地址只作为加款成功来账通知结果地址。
 * 商户系统必须具备接收重复通知处理的能力，按照通知的唯一单号 accp_txno 进行幂等性 控制，
 * 若未进行处理，由于重复通知导致的重复入账等资金风险由商户自行承担。
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
public class NotifyOfflineAddBill implements Serializable {
    private static final long serialVersionUID = -3723434136154549212L;
    /**
     * oid_partner  商户号  Y  String  ACCP系统分配给平台商户的唯一编号
     */
    private String oid_partner;

    /**
     * accp_txno  ACCP 系统订 Y  String  ACCP系统加款订单号,同一笔线下加款的多次通知的accp_txno  不变，商户需根据该字段进行幂等性控制
     */
    private String accp_txno;

    /**
     * chnl_txno  渠道订单号  Y  String  网银明细流水号
     */
    private String chnl_txno;

    /**
     * chnl_time  渠道交易时间  Y  String  格式：yyyyMMddHHmmss
     */
    private Date chnl_time;

    /**
     * amount  交易金额  Y  Number(8,2)  交易金额 单位元，保留两位小数
     */
    @Digits(integer = 8, fraction = 2)
    private BigDecimal amount;

    /**
     * payer_acctno  付款方账号  Y  String  付款方账号
     */
    private String payer_acctno;

    /**
     * payer_acctname  付款方账户名  Y  String  付款方账户名
     */
    private String payer_acctname;

    /**
     * payer_ virtualno  付款方虚拟卡  N  String  虚拟卡加款模式返回
     */
    private String payer_virtualno;

    /**
     * payer_virtualna  付款方虚拟卡 N  String  虚拟卡加款模式返回名称
     */
    private String getPayer_virtualname;

    /**
     * payee_id  收款方标识  Y  String  收款方为用户时取值user_id收款方为商户时取值平台商户号
     */
    private String payee_id;

    /**
     * payee_accttype  收款方账户类 Y  String  收款方账户类型,参见‘账户类型列表’
     */
    private String payee_accttype;

    /**
     * payee_acctno  收款方银行账 Y  String  收款方银行账号
     */
    private String payee_acctno;

    /**
     * payee_acctname  收款方账户名 Y  String  收款方账户名称
     */
    private String payee_acctname;

    /**
     * postscript  交易附言  N  String  交易附言
     */
    private String postscript;
    /**
     * 回调业务处理是否执行成功
     */
    private boolean bizSuccess;
}
