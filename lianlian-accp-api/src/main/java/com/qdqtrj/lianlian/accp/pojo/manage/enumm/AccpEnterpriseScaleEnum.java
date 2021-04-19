package com.qdqtrj.lianlian.accp.pojo.manage.enumm;

/**
 * 4.7. 企业规模
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public enum AccpEnterpriseScaleEnum {

    LARGE("LARGE", "大型"),
    MEDIUM("MEDIUM", "中型"),
    SMALL("SMALL", "小型"),
    TINY("TINY", "微型"),
    OTHER("OTHER", "其他"),
    ;

    /**
     * 编码
     */
    private final String code;

    /**
     * 描述
     */
    private final String value;


    AccpEnterpriseScaleEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }


    public static String getCode(String code) {
        AccpEnterpriseScaleEnum[] imageFormatTypes = values();
        for (AccpEnterpriseScaleEnum imageFormatType : imageFormatTypes) {
            if (imageFormatType.code().equals(code)) {
                return imageFormatType.code();
            }
        }
        return null;
    }

    public static String getValue(String code) {
        AccpEnterpriseScaleEnum[] imageFormatTypes = values();
        for (AccpEnterpriseScaleEnum imageFormatType : imageFormatTypes) {
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
