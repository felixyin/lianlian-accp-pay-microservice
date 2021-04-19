package com.qdqtrj.lianlian.accp.pojo.manage.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


/**
 * 3.19. 大额行号查询 3.19.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-cnapscode
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.19. 大额行号查询 3.19.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-cnapscode")
public class ResQueryCnapscode implements Serializable {
    private static final long serialVersionUID = 7261120955151033241L;
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
     * bank_code,所属银行编码,Y,String(8),银行编码
     */
    @NotBlank()
    @Length(min = 1, max = 8)
    @ApiModelProperty(value = "bank_code,所属银行编码,Y,String(8),银行编码", required = true)
    private String bank_code;
    /**
     * card_list,行号列表
     */
    @ApiModelProperty("card_list,行号列表")
    private List<Card> card_list;

    /**
     * card_list,行号列表
     */
    @Data
    public static class Card {

        /**
         * cnaps_code,大额行号,N,String(12),大额行号
         */
        @Length(min = 1, max = 12)
        @ApiModelProperty("cnaps_code,大额行号,N,String(12),大额行号")
        private String cnaps_code;
        /**
         * brabank_name,开户支行名称,N,String,支行名称
         */
        @ApiModelProperty("brabank_name,开户支行名称,N,String,支行名称")
        private String brabank_name;
    }
}

