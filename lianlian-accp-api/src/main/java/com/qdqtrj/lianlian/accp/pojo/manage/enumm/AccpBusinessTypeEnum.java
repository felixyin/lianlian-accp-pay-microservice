package com.qdqtrj.lianlian.accp.pojo.manage.enumm;

/**
 * 4.5. 业务类型
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public enum AccpBusinessTypeEnum {

    OpenActivatedUser("OpenActivatedUser", "待激活开户"),
    OpenNormelUser("OpenNormelUser", "正常开户"),
    ChangePhone("ChangePhone", "绑定手机号变更"),
    ChangeBankAcct("ChangeBankAcct", "企业银行账户变更"),
    TideCard("TideCard", "个人绑卡"),
    UntieCard("UntieCard", "个人解绑卡"),
    CancelUser("CancelUser", "用户注销"),
    ;

    /**
     * 编码
     */
    private final String code;

    /**
     * 描述
     */
    private final String value;


    AccpBusinessTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }


    public static String getCode(String code) {
        AccpBusinessTypeEnum[] imageFormatTypes = values();
        for (AccpBusinessTypeEnum imageFormatType : imageFormatTypes) {
            if (imageFormatType.code().equals(code)) {
                return imageFormatType.code();
            }
        }
        return null;
    }

    public static String getValue(String code) {
        AccpBusinessTypeEnum[] imageFormatTypes = values();
        for (AccpBusinessTypeEnum imageFormatType : imageFormatTypes) {
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
