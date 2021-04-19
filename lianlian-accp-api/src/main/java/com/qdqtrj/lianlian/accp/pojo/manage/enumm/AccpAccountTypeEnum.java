package com.qdqtrj.lianlian.accp.pojo.manage.enumm;

/**
 * 4.2. 账户类型列表
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public enum AccpAccountTypeEnum {

    USEROWN_PSETTLE("USEROWN_PSETTLE", "用户自有待结算账户"),
    USEROWN_AVAILABLE("USEROWN_AVAILABLE", "用户自有可用账户"),
    MCHOWN_PSETTLE("MCHOWN_PSETTLE", "平台商户自有待结算账户"),
    MCHOWN_AVAILABLE("MCHOWN_AVAILABLE", "平台商户自有可用账户"),
    MCHASSURE_PSETTLE("MCHASSURE_PSETTLE", "平台商户担保待结算账户"),
    MCHASSURE_AVAILABLE("MCHASSURE_AVAILABLE", "平台商户担保可用账户"),
    MCHCOUPON_PSETTLE("MCHCOUPON_PSETTLE", "平台商户优惠券待结算账户"),
    MCHCOUPON_AVAILABLE("MCHCOUPON_AVAILABLE", "平台商户优惠券可用账户"),
    MCHFEE_PSETTLE("MCHFEE_PSETTLE", "平台商户手续费结算账户"),
    MCHFEE_AVAILABLE("MCHFEE_AVAILABLE", "平台商户手续费可用账户"),
    BANKCARD_DEBIT("BANKCARD_DEBIT", "银行账户（借记卡）"),
    BANKCARD_CREDIT("BANKCARD_CREDIT", "银行卡账户（信用卡）"),
    BANKCARD_ENTERPRISE("BANKCARD_ENTERPRISE", "银行账户（对公）"),
    THIRD_PARTY("THIRD_PARTY", "第三方账户"),
    ;

    /**
     * 编码
     */
    private final String code;

    /**
     * 描述
     */
    private final String value;


    AccpAccountTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }


    public static String getCode(String code) {
        AccpAccountTypeEnum[] imageFormatTypes = values();
        for (AccpAccountTypeEnum imageFormatType : imageFormatTypes) {
            if (imageFormatType.code().equals(code)) {
                return imageFormatType.code();
            }
        }
        return null;
    }

    public static String getValue(String code) {
        AccpAccountTypeEnum[] imageFormatTypes = values();
        for (AccpAccountTypeEnum imageFormatType : imageFormatTypes) {
            if (imageFormatType.code().equals(code)) {
                return imageFormatType.value();
            }
        }
        return null;
    }

    public String code() {
        return code;
    }

    public String value() {
        return value;
    }

}
