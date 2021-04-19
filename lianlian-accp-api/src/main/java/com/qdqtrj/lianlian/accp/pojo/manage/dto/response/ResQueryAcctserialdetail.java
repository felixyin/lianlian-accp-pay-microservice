package com.qdqtrj.lianlian.accp.pojo.manage.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 3.17.2.  资金流水详情查询 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-acctserialdetail
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.17.2.  资金流水详情查询 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-acctserialdetail")
public class ResQueryAcctserialdetail implements Serializable {
    private static final long serialVersionUID = -999452440534462737L;
    /**
     * ret_code,交易结果 Y,String(4) 代码
     */
    @ApiModelProperty(value = "ret_code,交易结果 Y,String(4) 代码", required = true)
    private String ret_code;
    /**
     * ret_msg,交易结果 Y,String(32) 描述
     */
    @ApiModelProperty(value = "ret_msg,交易结果 Y,String(32) 描述", required = true)
    private String ret_msg;
    /**
     * oid_partner,商户号,Y,String(18),ACCP系统分配给平台商户的唯一编号
     */
    @NotBlank()
    @Length(min = 1, max = 18)
    @ApiModelProperty(value = "oid_partner,商户号,Y,String(18),ACCP系统分配给平台商户的唯一编号", required = true)
    private String oid_partner;
    /**
     * date_acct,账务日期,N,String(8),交易账期,格式：yyyyMMdd
     */
    @Length(min = 1, max = 8)
    @ApiModelProperty("date_acct,账务日期,N,String(8),交易账期,格式：yyyyMMdd")
    private String date_acct;
    /**
     * oid_acctno,账户号,N,String(32),账户号
     */
    @Length(min = 1, max = 32)
    @ApiModelProperty("oid_acctno,账户号,N,String(32),账户号")
    private String oid_acctno;
    /**
     * jno_acct,资金流水 N,String(20),ACCP账务系统资金流水唯一标识号
     */
    @ApiModelProperty("jno_acct,资金流水 N,String(20),ACCP账务系统资金流水唯一标识号")
    private String jno_acct;
    /**
     * txn_seqno,交易流水 N,String,支付类、退款类、提现类业务交易每笔交号,易商户系统唯一交易流水号
     */
    @ApiModelProperty("txn_seqno,交易流水 N,String,支付类、退款类、提现类业务交易每笔交号,易商户系统唯一交易流水号")
    private String txn_seqno;
    /**
     * accp_txno,ACCP 系 N,String,该笔资金流水对应ACCP系统交易单号
     */
    @ApiModelProperty("accp_txno,ACCP 系 N,String,该笔资金流水对应ACCP系统交易单号")
    private String accp_txno;
    /**
     * txn_type,交易类型,N,String,用户充值：USER_TOPUP 商户充值：MCH_TOPUP 普通消费：GENERAL_CONSUME 担保消费：SECURED_CONSUME 手续费收取：SERVICE_FEE 内部代发：INNER_FUND_EXCHA NGE 外部代发：OUTER_FUND_EXCHA NGE 账户提现：ACCT_CASH_OUT 担保确认：SECURED_CONFIRM product_code,产品编码,N,String,消费API CONSUMPTION_API 消费页面 CONSUMPTION_WEB 充值API DEPOSIT_API 充值页面 DEPOSIT_WEB 普通代发(内部)API NORMAL_TRANSFER_ACCOUNT_API 普通代发(内部)WEB NORMAL_TRANSFER_ACCOUNT_WEB 普通代发(外部)API NORMAL_TRANSFER_CARD_API 普通代发(外部)WEB NORMAL_TRANSFER_ACCOUNT_WEB 垫资代发(内部)API FUNDS_TRANSFER_ACCOUNT_API 垫资代发(内部)WEB FUNDS_TRANSFER_ACCOUNT_WEB 垫资代发(外部)API FUNDS_TRANSFER_CARD_API 垫资代发(外部)WEB FUNDS_TRANSFER_CARD_WEB 普通提现API NORMAL_WITHDRAW_API 普通提现WEB NORMAL_WITHDRAW_WEB 垫资提现API FUNDS_WITHDRAW_API 垫资提现WEB FUNDS_WITHDRAW_WEB 内部调账 NTERNAL_ACCOUNT_TRANSFER 日终结转 DAILY_SETTLEMENT
     */
    @ApiModelProperty("txn_type,交易类型,N,String,用户充值：USER_TOPUP 商户充值：MCH_TOPUP 普通消费：GENERAL_CONSUME 担保消费：SECURED_CONSUME 手续费收取：SERVICE_FEE 内部代发：INNER_FUND_EXCHA NGE 外部代发：OUTER_FUND_EXCHA NGE 账户提现：ACCT_CASH_OUT 担保确认：SECURED_CONFIRM product_code,产品编码,N,String,消费API CONSUMPTION_API 消费页面 CONSUMPTION_WEB 充值API DEPOSIT_API 充值页面 DEPOSIT_WEB 普通代发(内部)API NORMAL_TRANSFER_ACCOUNT_API 普通代发(内部)WEB NORMAL_TRANSFER_ACCOUNT_WEB 普通代发(外部)API NORMAL_TRANSFER_CARD_API 普通代发(外部)WEB NORMAL_TRANSFER_ACCOUNT_WEB 垫资代发(内部)API FUNDS_TRANSFER_ACCOUNT_API 垫资代发(内部)WEB FUNDS_TRANSFER_ACCOUNT_WEB 垫资代发(外部)API FUNDS_TRANSFER_CARD_API 垫资代发(外部)WEB FUNDS_TRANSFER_CARD_WEB 普通提现API NORMAL_WITHDRAW_API 普通提现WEB NORMAL_WITHDRAW_WEB 垫资提现API FUNDS_WITHDRAW_API 垫资提现WEB FUNDS_WITHDRAW_WEB 内部调账 NTERNAL_ACCOUNT_TRANSFER 日终结转 DAILY_SETTLEMENT")
    private String txn_type;
    /**
     * other_acct,对手账号,N xx
     */
    @ApiModelProperty("other_acct,对手账号,N xx")
    private String other_acct;
    /**
     * other_acct_name,对手户名,N xx
     */
    @ApiModelProperty("other_acct_name,对手户名,N xx")
    private String other_acct_name;
    /**
     * txn_time,交易时间,N,String(14),账务系统交易时间格式：yyyyMMddHHmmss
     */
    @Length(min = 1, max = 14)
    @ApiModelProperty("txn_time,交易时间,N,String(14),账务系统交易时间格式：yyyyMMddHHmmss")
    private String txn_time;
    /**
     * flag_dc,出入账标 N,String(16),账户出入账标识识,DEBIT：出账 CREDIT：入账
     */
    @ApiModelProperty("flag_dc,出入账标 N,String(16),账户出入账标识识,DEBIT：出账 CREDIT：入账")
    private String flag_dc;
    /**
     * amt,出入账金 N,String,单位元额
     */
    @ApiModelProperty("amt,出入账金 N,String,单位元额")
    private String amt;
    /**
     * amt_bal,交易后余 N,String,单位元额
     */
    @ApiModelProperty("amt_bal,交易后余 N,String,单位元额")
    private String amt_bal;
    /**
     * memo,备注,N,String,资金流水备注
     */
    @ApiModelProperty("memo,备注,N,String,资金流水备注")
    private String memo;
}

