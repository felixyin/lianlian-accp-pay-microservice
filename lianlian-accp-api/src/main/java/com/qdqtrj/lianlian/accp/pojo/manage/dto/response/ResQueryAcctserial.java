package com.qdqtrj.lianlian.accp.pojo.manage.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


/**
 * 3.17. 资金流水查询 3.17.1.  资金流水列表查询 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-acctserial
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.17. 资金流水查询 3.17.1.  资金流水列表查询 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-acctserial")
public class ResQueryAcctserial implements Serializable {
    private static final long serialVersionUID = 3950790550641961032L;
    /**
     * ret_code,交易结果代码,Y,String(4)
     */
    @NotBlank()
    @Length(min = 1, max = 4)
    @ApiModelProperty(value = "ret_code,交易结果代码,Y,String(4)", required = true)
    private String ret_code;
    /**
     * ret_msg,交易结果描述,Y,String(32)
     */
    @NotBlank()
    @Length(min = 1, max = 32)
    @ApiModelProperty(value = "ret_msg,交易结果描述,Y,String(32)", required = true)
    private String ret_msg;
    /**
     * oid_partner,商户号,Y,String(18),ACCP系统分配给平台商户的唯一编号
     */
    @NotBlank()
    @Length(min = 1, max = 18)
    @ApiModelProperty(value = "oid_partner,商户号,Y,String(18),ACCP系统分配给平台商户的唯一编号", required = true)
    private String oid_partner;
    /**
     * user_id,商户用户唯一编 Y,String(64),用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户
     */
    @ApiModelProperty(value = "user_id,商户用户唯一编 Y,String(64),用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户", required = true)
    private String user_id;
    /**
     * page_no,当前页码,Y,Int,表示返回结果集第几页
     */
    @NotBlank()
    @ApiModelProperty(value = "page_no,当前页码,Y,Int,表示返回结果集第几页", required = true)
    private Integer page_no;
    /**
     * total_out_amt,出账总金额,Y,String,表示当前查询条件下的出账总金额，单位：元
     */
    @NotBlank()
    @ApiModelProperty(value = "total_out_amt,出账总金额,Y,String,表示当前查询条件下的出账总金额，单位：元", required = true)
    private String total_out_amt;
    /**
     * total_in_amt,入账总金额,Y,String,表示当前查询条件下的入账总金额，单位：元
     */
    @NotBlank()
    @ApiModelProperty(value = "total_in_amt,入账总金额,Y,String,表示当前查询条件下的入账总金额，单位：元", required = true)
    private String total_in_amt;
    /**
     * total_num,结果集总数,Y,Int,表示当前查询条件下的结果集数据总数
     */
    @NotBlank()
    @ApiModelProperty(value = "total_num,结果集总数,Y,Int,表示当前查询条件下的结果集数据总数", required = true)
    private Integer total_num;
    /**
     * total_page,结果集总页数,Y,Int,total_page=(total_num/page_size),向上取整
     */
    @NotBlank()
    @ApiModelProperty(value = "total_page,结果集总页数,Y,Int,total_page=(total_num/page_size),向上取整", required = true)
    private Integer total_page;

    /**
     * acctbal_list,资金流水列表
     */
    @ApiModelProperty("acctbal_list,资金流水列表")
    private List<Acctbal> acctbal_list;

    /**
     * acctbal_list,资金流水列表
     */

    @Data
    public static class Acctbal {

        /**
         * date_acct,账务日期,N,String(8),交易账期格式：yyyyMMdd
         */
        @Length(min = 1, max = 8)
        @ApiModelProperty("date_acct,账务日期,N,String(8),交易账期格式：yyyyMMdd")
        private String date_acct;
        /**
         * oid_acctno,账户号,N,String(32),账户号
         */
        @Length(min = 1, max = 32)
        @ApiModelProperty("oid_acctno,账户号,N,String(32),账户号")
        private String oid_acctno;
        /**
         * jno_acct,资金流水号,N,String(20),ACCP账务系统资金流水唯一标识
         */
        @Length(min = 1, max = 20)
        @ApiModelProperty("jno_acct,资金流水号,N,String(20),ACCP账务系统资金流水唯一标识")
        private String jno_acct;
        /**
         * accp_txno,ACCP系统交易单 N,String,该笔资金流水对应ACCP系统交易单号
         */
        @ApiModelProperty("accp_txno,ACCP系统交易单 N,String,该笔资金流水对应ACCP系统交易单号")
        private String accp_txno;
        /**
         * txn_type,交易类型,N,String,用户充值：USER_TOPUP,商户充值：MCH_TOPUP,普通消费：GENERAL_CONSUME,担保消费：SECURED_CONSUME,手续费收取：SERVICE_FEE,内部转账：INNER_FUND_EXCHA,外部转账：OUTER_FUND_EXCHA,账户提现：ACCT_CASH_OUT,担保确认：SECURED_CONFIRM
         */
        @ApiModelProperty("txn_type,交易类型,N,String,用户充值：USER_TOPUP,商户充值：MCH_TOPUP,普通消费：GENERAL_CONSUME,担保消费：SECURED_CONSUME,手续费收取：SERVICE_FEE,内部转账：INNER_FUND_EXCHA,外部转账：OUTER_FUND_EXCHA,账户提现：ACCT_CASH_OUT,担保确认：SECURED_CONFIRM")
        private String txn_type;
        /**
         * product_code,产品编码,N,String,消费API CONSUMPTION_API,消费页面 CONSUMPTION_WEB,充值API DEPOSIT_API,充值页面 DEPOSIT_WEB,普通代发(内部)API,NORMAL_TRANSFER_ACCOUNT_API,普通代发(内部)WEB,NORMAL_TRANSFER_ACCOUNT_WEB,普通代发(外部)API,NORMAL_TRANSFER_CARD_API,普通代发(外部)WEB,NORMAL_TRANSFER_ACCOUNT_WEB,垫资代发(内部)API,FUNDS_TRANSFER_ACCOUNT_API,垫资代发(内部)WEB,FUNDS_TRANSFER_ACCOUNT_WEB,垫资代发(外部)API,FUNDS_TRANSFER_CARD_API,垫资代发(外部)WEB,FUNDS_TRANSFER_CARD_WEB,普通提现API,NORMAL_WITHDRAW_API,普通提现WEB,NORMAL_WITHDRAW_WEB,垫资提现API FUNDS_WITHDRAW_API,垫资提现WEB,FUNDS_WITHDRAW_WEB,内部调账,NTERNAL_ACCOUNT_TRANSFER,日终结转 DAILY_SETTLEMENT
         */
        @ApiModelProperty("product_code,产品编码,N,String,消费API CONSUMPTION_API,消费页面 CONSUMPTION_WEB,充值API DEPOSIT_API,充值页面 DEPOSIT_WEB,普通代发(内部)API,NORMAL_TRANSFER_ACCOUNT_API,普通代发(内部)WEB,NORMAL_TRANSFER_ACCOUNT_WEB,普通代发(外部)API,NORMAL_TRANSFER_CARD_API,普通代发(外部)WEB,NORMAL_TRANSFER_ACCOUNT_WEB,垫资代发(内部)API,FUNDS_TRANSFER_ACCOUNT_API,垫资代发(内部)WEB,FUNDS_TRANSFER_ACCOUNT_WEB,垫资代发(外部)API,FUNDS_TRANSFER_CARD_API,垫资代发(外部)WEB,FUNDS_TRANSFER_CARD_WEB,普通提现API,NORMAL_WITHDRAW_API,普通提现WEB,NORMAL_WITHDRAW_WEB,垫资提现API FUNDS_WITHDRAW_API,垫资提现WEB,FUNDS_WITHDRAW_WEB,内部调账,NTERNAL_ACCOUNT_TRANSFER,日终结转 DAILY_SETTLEMENT")
        private String product_code;
        /**
         * txn_time,交易时间,N,String(14),账务系统交易时间格式：yyyyMMddHHmmss
         */
        @Length(min = 1, max = 14)
        @ApiModelProperty("txn_time,交易时间,N,String(14),账务系统交易时间格式：yyyyMMddHHmmss")
        private String txn_time;
        /**
         * flag_dc,出入账标识,N,String(16),账户出入账标识,DEBIT：出账,CREDIT：入账
         */
        @Length(min = 1, max = 16)
        @ApiModelProperty("flag_dc,出入账标识,N,String(16),账户出入账标识,DEBIT：出账,CREDIT：入账")
        private String flag_dc;
        /**
         * amt,出入账金额,N,String,单位元
         */
        @ApiModelProperty("amt,出入账金额,N,String,单位元")
        private String amt;
        /**
         * amt_bal,交易后余额,N,String,单位元
         */
        @ApiModelProperty("amt_bal,交易后余额,N,String,单位元")
        private String amt_bal;
        /**
         * memo,备注,N,String,资金流水备注
         */
        @ApiModelProperty("memo,备注,N,String,资金流水备注")
        private String memo;
    }
}

