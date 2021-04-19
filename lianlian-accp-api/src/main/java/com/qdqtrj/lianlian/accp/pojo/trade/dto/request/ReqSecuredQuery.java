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
 * 3.5.3. 担保交易信息查询 针对担保交易的担保支付和担保交易确认多次操作，为商户提供某笔担保单的真实收款方金 额确认情况以及多次确认、退款操作的查询接口。 注：本接口只用于担保支付获取该交易相关确认单、退款单等信息与卖方确认金额信息，具 体担保支付交易、担保确认交易的结果，请参考【支付结果查询】 与 【担保确认结果查询】。 请求地址https://accpapi.lianlianpay.com/v1/txn/secured-query
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.5.3. 担保交易信息查询 针对担保交易的担保支付和担保交易确认多次操作，为商户提供某笔担保单的真实收款方金 额确认情况以及多次确认、退款操作的查询接口。 注：本接口只用于担保支付获取该交易相关确认单、退款单等信息与卖方确认金额信息，具 体担保支付交易、担保确认交易的结果，请参考【支付结果查询】 与 【担保确认结果查询】。 请求地址https://accpapi.lianlianpay.com/v1/txn/secured-query")
public class ReqSecuredQuery implements Serializable {
    private static final long serialVersionUID = -8224940508379899636L;
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
     * txn_seqno,商户交易流水号,N,String(64),原担保支付商户系统唯一交易
     */
    @Length(min = 1, max = 64)
    @ApiModelProperty("txn_seqno,商户交易流水号,N,String(64),原担保支付商户系统唯一交易")
    private String txn_seqno;
    /**
     * accp_txno,ACCP 系统交易单 N,String,流水号或原担保支付ACCP系号统交易单号二选一，建议优先使用ACCP系统交易单号
     */
    @ApiModelProperty("accp_txno,ACCP 系统交易单 N,String,流水号或原担保支付ACCP系号统交易单号二选一，建议优先使用ACCP系统交易单号")
    private String accp_txno;
}
