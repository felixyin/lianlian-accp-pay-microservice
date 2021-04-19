package com.qdqtrj.lianlian.accp.pojo.trade.enumm;

/**
 * 4. 附录
 * 4.3. 账户类型列表
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public enum AccpAccountTypeEnum {
    USEROWN("USEROWN", "用户账户"),
    MCHOWN("MCHOWN", "平台商户自有资金账户"),
    MCHASSURE("MCHASSURE", "平台商户担保账户"),
    MCHCOUPON("MCHCOUPON", "平台商户优惠券账户"),
    MCHFEE("MCHFEE", "平台商户手续费账户"),
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
