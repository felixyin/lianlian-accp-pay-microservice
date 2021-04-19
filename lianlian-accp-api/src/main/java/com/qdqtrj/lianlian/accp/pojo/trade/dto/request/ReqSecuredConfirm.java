package com.qdqtrj.lianlian.accp.pojo.trade.dto.request;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 3.5. 担保 担保交易支持两类场景： 1.  在支付交易统一创单时指定担保收款方信息，担保确认时支持全额及部分金额多次确认； 对于创单时指定的担保收款方不支持确认时修改； 2.  在支付交易统一创单时不指定担保收款方信息，担保交易确认时动态指定收款方并进行 交易确认，资金从担保平台商户账户转入担保收款方账户。 3.5.1. 担保交易确认 请求地址https://accpapi.lianlianpay.com/v1/txn/secured-confirm
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@ApiModel(description = "3.5. 担保 担保交易支持两类场景： 1.  在支付交易统一创单时指定担保收款方信息，担保确认时支持全额及部分金额多次确认； 对于创单时指定的担保收款方不支持确认时修改； 2.  在支付交易统一创单时不指定担保收款方信息，担保交易确认时动态指定收款方并进行 交易确认，资金从担保平台商户账户转入担保收款方账户。 3.5.1. 担保交易确认 请求地址https://accpapi.lianlianpay.com/v1/txn/secured-confirm")
public class ReqSecuredConfirm implements Serializable {
    private static final long serialVersionUID = 1426848732583408268L;
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
     * user_id,商户用户唯一编号,Y,String,用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户
     */
    @NotBlank()
    @ApiModelProperty(value = "user_id,商户用户唯一编号,Y,String,用户在商户系统中的唯一编号，要求该编号在商户系统能唯一标识用户", required = true)
    private String user_id;
    /**
     * confirm_mode,确认方式,Y,String,ALL：全订单金额一次性确认（创单时指定了收款方）,PART：订单金额部分多次确认
     */
    @NotBlank()
    @ApiModelProperty(value = "confirm_mode,确认方式,Y,String,ALL：全订单金额一次性确认（创单时指定了收款方）,PART：订单金额部分多次确认", required = true)
    private String confirm_mode;

    /**
     * 原商户订单信息 originalOrderInfo
     */
    @ApiModelProperty("原商户订单信息 originalOrderInfo")
    private OriginalOrderInfo originalOrderInfo;
    /**
     * 确认订单信息 confirmOrderInfo
     */
    @ApiModelProperty("确认订单信息 confirmOrderInfo")
    private ConfirmOrderInfo confirmOrderInfo;

    /**
     * 原商户订单信息 originalOrderInfo
     */
    @Data
    public static class OriginalOrderInfo {
        /**
         * txn_seqno,商户交易流水号,Y,String(64),原担保支付交易商户系统唯一交易流水号
         */
        @NotBlank()
        @Length(min = 1, max = 64)
        @ApiModelProperty(value = "txn_seqno,商户交易流水号,Y,String(64),原担保支付交易商户系统唯一交易流水号", required = true)
        private String txn_seqno;
        /**
         * total_amount,订单总金额,Y,Number(8,2),订单总金额，单位为元，精确到小数点后两位
         */
        @NotNull()
        @Digits(integer = 8, fraction = 2)
        @ApiModelProperty(value = "total_amount,订单总金额,Y,Number(8,2),订单总金额，单位为元，精确到小数点后两位", required = true)
        private BigDecimal total_amount;
    }

    /**
     * 确认订单信息 confirmOrderInfo
     */
    @Data
    public static class ConfirmOrderInfo {

        /**
         * confirm_seqno,确认订单号,Y,String(64),担保确认交易商户系统唯一交易流水号
         */
        @NotBlank()
        @Length(min = 1, max = 64)
        @ApiModelProperty(value = "confirm_seqno,确认订单号,Y,String(64),担保确认交易商户系统唯一交易流水号", required = true)
        private String confirm_seqno;
        /**
         * confirm_time,确认时间,Y,String(14),担保确认交易商户系统交易时间,格式：yyyyMMddHHmmss
         */
        @NotBlank()
        @Length(min = 1, max = 14)
        @ApiModelProperty(value = "confirm_time,确认时间,Y,String(14),担保确认交易商户系统交易时间,格式：yyyyMMddHHmmss", required = true)
        private String confirm_time;
        /**
         * confirm_amount,确认金额,Y,Number(8,2),本次确认总金额,确认收款方信息payeeInfo,确认方式为ALL时，不接受指定收款方信息,确认方式为PART时，若创单时未指定收款方则接受确认时重新指定；若创单时已指定了收款方则需要再次指定，ACCP系统后台进行验证一致性,支持一次性指定多个收款方进行确认
         */
        @NotNull
        @ApiModelProperty(value = "confirm_amount,确认金额,Y,Number(8,2),本次确认总金额,确认收款方信息payeeInfo,确认方式为ALL时，不接受指定收款方信息,确认方式为PART时，若创单时未指定收款方则接受确认时重新指定；若创单时已指定了收款方则需要再次指定，ACCP系统后台进行验证一致性,支持一次性指定多个收款方进行确认", required = true)
        private BigDecimal confirm_amount;
        /**
         * payee_id,收款方id,N,String,收款方标识，指定为用户或者平台商户的唯一id
         */
        @ApiModelProperty("payee_id,收款方id,N,String,收款方标识，指定为用户或者平台商户的唯一id")
        private String payee_id;
        /**
         * payee_type,收款方类型,N,String,用户：USER,平台商户：MERCHANT
         */
        @ApiModelProperty("payee_type,收款方类型,N,String,用户：USER,平台商户：MERCHANT")
        private String payee_type;
        /**
         * payee_amount,收款方确认金额,N,Number(8,2),收款方确认金额，单位为元，精确到小数点后两位
         */
        @NotNull()
        @Digits(integer = 8, fraction = 2)
        @ApiModelProperty("payee_amount,收款方确认金额,N,Number(8,2),收款方确认金额，单位为元，精确到小数点后两位")
        private BigDecimal payee_amount;
    }
}
