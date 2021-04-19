package com.qdqtrj.lianlian.accp.pojo.manage.enumm;

/**
 * 4.4. 账户状态
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public enum AccpAccountStatusEnum {

    NORMAL("NORMAL", "正常"),
    CANCEL("CANCEL", "注销"),
    LOCK("LOCK", "锁定"),
    LOSS("LOSS", "挂失"),
    ACTIVATE_PENDING("ACTIVATE_PENDING", "待激活"),
    ;

    /**
     * 编码
     */
    private final String code;

    /**
     * 描述
     */
    private final String value;


    AccpAccountStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }


    public static String getCode(String code) {
        AccpAccountStatusEnum[] imageFormatTypes = values();
        for (AccpAccountStatusEnum imageFormatType : imageFormatTypes) {
            if (imageFormatType.code().equals(code)) {
                return imageFormatType.code();
            }
        }
        return null;
    }

    public static String getValue(String code) {
        AccpAccountStatusEnum[] imageFormatTypes = values();
        for (AccpAccountStatusEnum imageFormatType : imageFormatTypes) {
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
