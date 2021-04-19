package com.qdqtrj.lianlian.accp.pojo.manage.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 3.18. 交易流水结果查询 3.18.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-txn
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.18. 交易流水结果查询 3.18.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-txn")
public class ResQueryTxn implements Serializable {
    private static final long serialVersionUID = 4820364495264123637L;
    /**
     * ret_code,交易结果代码,Y,String(4),交易返回码
     */
    @NotBlank()
    @Length(min = 1, max = 4)
    @ApiModelProperty(value = "ret_code,交易结果代码,Y,String(4),交易返回码", required = true)
    private String ret_code;
    /**
     * ret_msg,交易结果描述,Y,String(32),交易返回描述
     */
    @NotBlank()
    @Length(min = 1, max = 32)
    @ApiModelProperty(value = "ret_msg,交易结果描述,Y,String(32),交易返回描述", required = true)
    private String ret_msg;
    /**
     * oid_partner,商户号,Y,String(18),ACCP 系统分配给平台商户的唯一编号
     */
    @NotBlank()
    @Length(min = 1, max = 18)
    @ApiModelProperty(value = "oid_partner,商户号,Y,String(18),ACCP 系统分配给平台商户的唯一编号", required = true)
    private String oid_partner;
    /**
     * txn_seqno,交易流水号,Y,String(64),商户系统唯一交易流水号
     */
    @NotBlank()
    @Length(min = 1, max = 64)
    @ApiModelProperty(value = "txn_seqno,交易流水号,Y,String(64),商户系统唯一交易流水号", required = true)
    private String txn_seqno;
    /**
     * accp_txno,ACCP系统交易单号,N,String(32),ACCP系统业务单号
     */
    @Length(min = 1, max = 32)
    @ApiModelProperty("accp_txno,ACCP系统交易单号,N,String(32),ACCP系统业务单号")
    private String accp_txno;
    /**
     * busi_type,业务类型,N,String(32),业务类型
     */
    @Length(min = 1, max = 32)
    @ApiModelProperty("busi_type,业务类型,N,String(32),业务类型")
    private String busi_type;
    /**
     * status,状态,N,String(32),CREATE,SUCCESS
     */
    @Length(min = 1, max = 32)
    @ApiModelProperty("status,状态,N,String(32),CREATE,SUCCESS")
    private String status;
}

