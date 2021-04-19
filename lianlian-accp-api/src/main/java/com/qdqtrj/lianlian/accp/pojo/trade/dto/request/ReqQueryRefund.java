package com.qdqtrj.lianlian.accp.pojo.trade.dto.request;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * 3.7.2. 退款结果查询 该接口提供发起提现申请后的订单查询，商户可以通过该接口主动查询提现申请订单状态， 完成下一步的业务逻辑。 请求地址https://accpapi.lianlianpay.com/v1/txn/query-refund
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.7.2. 退款结果查询 该接口提供发起提现申请后的订单查询，商户可以通过该接口主动查询提现申请订单状态， 完成下一步的业务逻辑。 请求地址https://accpapi.lianlianpay.com/v1/txn/query-refund")
public class ReqQueryRefund implements Serializable {
    private static final long serialVersionUID = 7982269203044254263L;
    /**
     * timestamp,时间戳,Y,Date,交易服务器时间戳,格式：yyyyMMddHHmmss,有效期30分钟（包含服务器之间的时间差）
     */
    @NotNull()
    @JSONField(format = "yyyyMMddHHmmss")
    @ApiModelProperty("timestamp,时间戳,Y,Date,交易服务器时间戳,格式：yyyyMMddHHmmss,有效期30分钟（包含服务器之间的时间差）")
    private Date timestamp = new Date();
    /**
     * oid_partner,商户号,Y,String,ACCP系统分配给平台商户的唯一编号
     */
    @NotBlank()
    @ApiModelProperty(value = "oid_partner,商户号,Y,String,ACCP系统分配给平台商户的唯一编号", required = true)
    private String oid_partner;
    /**
     * refund_seqno,退款申请交易请求 N,String(64),二选一，建议优先使用ACCP系流水号
     */
    @ApiModelProperty("refund_seqno,退款申请交易请求 N,String(64),二选一，建议优先使用ACCP系流水号")
    private String refund_seqno;
    /**
     * accp_txno,ACCP 系统退款单 N,String 统退款单号
     */
    @ApiModelProperty("accp_txno,ACCP 系统退款单 N,String 统退款单号")
    private String accp_txno;
}
