package com.qdqtrj.lianlian.accp.pojo.trade.dto.callback;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 3.7.2. 退款结果查询 该接口提供发起提现申请后的订单查询，商户可以通过该接口主动查询提现申请订单状态， 完成下一步的业务逻辑。 请求地址https://accpapi.lianlianpay.com/v1/txn/query-refund
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
public class NotifyQueryRefund implements Serializable {
    private static final long serialVersionUID = -5450059843507538178L;
    /**
     * oid_partner,商户号,Y,String,ACCP 系统分配给平台商户的唯一编号
     */
    @NotBlank()
    private String oid_partner;
    /**
     * accounting_date,账务日期,N,String,ACCP系统交易账务日期，交易成功时返回，格式：yyyyMMdd
     */
    private String accounting_date;
    /**
     * accp_txno,ACCP系统退款单号,N,String,ACCP系统退款单号
     */
    private String accp_txno;
    /**
     * refund_seqno,退款订单号,Y,String(64),标识一次退款请求，商户系统需保证唯一
     */
    @NotBlank()
    @Length(min = 1, max = 64)
    private String refund_seqno;
    /**
     * chnl_txno,渠道退款单号,N,String,渠道退款单号
     */
    private String chnl_txno;
    /**
     * refund_amount,退款金额,N,Number(8,2),退款金额，单位为元，精确到小数点后两位
     */
    @Digits(integer = 8, fraction = 2)
    private BigDecimal refund_amount;
    /**
     * txn_status,退款交易状态,N,String, TRADE_SUCCESS：退款成功,TRADE_FAILURE：退款失败,退款结果以此为准，商户按此进行后续业务逻辑处理
     */
    private String txn_status;
    /**
     * 回调业务处理是否执行成功
     */
    private boolean bizSuccess;
}
