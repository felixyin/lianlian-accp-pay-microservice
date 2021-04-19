package com.qdqtrj.lianlian.accp.pojo.manage.enumm;

/**
 * 4.3. 用户状态
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public enum AccpUserStatusEnum {

    ACTIVATE_PENDING("ACTIVATE_PENDING", "待激活"),
    CHECK_PENDING("CHECK_PENDING", "待审核"),
    REMITTANCE_VALID_PENDING("REMITTANCE_VALID_PENDING", "审核通过，待打款验证（企业用户使用）"),
    NORMAL("NORMAL", "正常"),
    CANCEL("CANCEL", "销户"),
    ;

    /**
     * 编码
     */
    private final String code;

    /**
     * 描述
     */
    private final String value;


    AccpUserStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }


    public static String getCode(String code) {
        AccpUserStatusEnum[] imageFormatTypes = values();
        for (AccpUserStatusEnum imageFormatType : imageFormatTypes) {
            if (imageFormatType.code().equals(code)) {
                return imageFormatType.code();
            }
        }
        return null;
    }

    public static String getValue(String code) {
        AccpUserStatusEnum[] imageFormatTypes = values();
        for (AccpUserStatusEnum imageFormatType : imageFormatTypes) {
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
