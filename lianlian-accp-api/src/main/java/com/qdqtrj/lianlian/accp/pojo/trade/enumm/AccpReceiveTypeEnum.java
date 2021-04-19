package com.qdqtrj.lianlian.accp.pojo.trade.enumm;

/**
 * 4.4. 到账类型列表
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public enum AccpReceiveTypeEnum {
    TRANS_THIS_TIME("TRANS_THIS_TIME", "实时到账"),
    TRANS_NORMAL("TRANS_NORMAL", "普通到账（2小时内）"),
    TRANS_NEXT_TIME("TRANS_NEXT_TIME", "次日到账"),
    ;

    /**
     * 编码
     */
    private final String code;

    /**
     * 描述
     */
    private final String value;


    AccpReceiveTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }


    public static String getCode(String code) {
        AccpReceiveTypeEnum[] imageFormatTypes = values();
        for (AccpReceiveTypeEnum imageFormatType : imageFormatTypes) {
            if (imageFormatType.code().equals(code)) {
                return imageFormatType.code();
            }
        }
        return null;
    }

    public static String getValue(String code) {
        AccpReceiveTypeEnum[] imageFormatTypes = values();
        for (AccpReceiveTypeEnum imageFormatType : imageFormatTypes) {
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
