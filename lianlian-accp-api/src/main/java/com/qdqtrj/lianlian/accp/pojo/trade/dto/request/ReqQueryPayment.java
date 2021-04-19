package com.qdqtrj.lianlian.accp.pojo.trade.dto.request;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * 3.2.8. 支付结果查询 该接口提供所有消费/充值场景下的支付订单查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。
 * 需要调用查询接口的情况：商户后台、网络、服务器等出现异常，商户最终未接收到支付结果通知；调用支付接口后，返回系统错误或者未知交易、处理中交易状态情况。
 * 请求地址https://accpapi.lianlianpay.com/v1/txn/query-payment
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.2.8. 支付结果查询 该接口提供所有消费/充值场景下的支付订单查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。请求地址https://accpapi.lianlianpay.com/v1/txn/query-payment")
public class ReqQueryPayment implements Serializable {
    private static final long serialVersionUID = -3421731650068643328L;
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
     * txn_seqno,商户交易流水号,N,String(64),二选一，建议优先使用ACCP系
     */
    @Length(min = 1, max = 64)
    @ApiModelProperty("txn_seqno,商户交易流水号,N,String(64),二选一，建议优先使用ACCP系")
    private String txn_seqno;
    /**
     * accp_txno,ACCP 系统交易单 N,String,统交易单号号
     */
    @ApiModelProperty("accp_txno,ACCP 系统交易单 N,String,统交易单号号")
    private String accp_txno;
}
