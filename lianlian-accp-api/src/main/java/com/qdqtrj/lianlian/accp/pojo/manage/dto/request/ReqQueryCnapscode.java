package com.qdqtrj.lianlian.accp.pojo.manage.dto.request;

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
 * 3.19. 大额行号查询 3.19.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-cnapscode
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.19. 大额行号查询 3.19.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-cnapscode")
public class ReqQueryCnapscode implements Serializable {
    private static final long serialVersionUID = -9151977624540084174L;
    /**
     * timestamp,时间戳,Y,String(14),交易服务器时间戳,格式：yyyyMMddHHmmss,有效期30分钟,包含服务器之间的时间差
     */
    @NotNull()
    @JSONField(format = "yyyyMMddHHmmss")
    @ApiModelProperty("timestamp,时间戳,Y,String(14),交易服务器时间戳,格式：yyyyMMddHHmmss,有效期30分钟,包含服务器之间的时间差")
    private Date timestamp = new Date();
    /**
     * oid_partner,商户号,Y,String(18),ACCP 系统分配给平台商户的唯一编号
     */
    @NotBlank()
    @Length(min = 1, max = 18)
    @ApiModelProperty(value = "oid_partner,商户号,Y,String(18),ACCP 系统分配给平台商户的唯一编号", required = true)
    private String oid_partner;
    /**
     * bank_code,银行编码,Y,String(8)
     */
    @NotBlank()
    @Length(min = 1, max = 8)
    @ApiModelProperty(value = "bank_code,银行编码,Y,String(8)", required = true)
    private String bank_code;
    /**
     * brabank_name,支行关键字,Y,String
     */
    @NotBlank()
    @ApiModelProperty(value = "brabank_name,支行关键字,Y,String", required = true)
    private String brabank_name;
    /**
     * city_code,开户行所在市编码,Y,String(6)
     */
    @NotBlank()
    @Length(min = 1, max = 6)
    @ApiModelProperty(value = "city_code,开户行所在市编码,Y,String(6)", required = true)
    private String city_code;
}
