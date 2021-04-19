package com.qdqtrj.lianlian.accp.pojo.manage.enumm;

/**
 * 4.6. 职业类型
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public enum AccpOccupationTypeEnum {

    OCCUPATION_01("01", "公务员"),
    OCCUPATION_02("02", "事业单位员工"),
    OCCUPATION_03("03", "企业高管"),
    OCCUPATION_04("04", "私营业主"),
    OCCUPATION_05("05", "金融从业人员"),
    OCCUPATION_06("06", "律师"),
    OCCUPATION_07("07", "会计师"),
    OCCUPATION_08("08", "医护人员"),
    OCCUPATION_09("09", "学生"),
    OCCUPATION_10("10", "公司员工"),
    OCCUPATION_11("11", "商业服务"),
    OCCUPATION_12("12", "工人"),
    OCCUPATION_13("13", "农林牧副渔"),
    OCCUPATION_14("14", "军人武警"),
    OCCUPATION_15("15", "文体工作者"),
    OCCUPATION_16("16", "家庭主妇"),
    OCCUPATION_17("17", "退休"),
    OCCUPATION_18("18", "自由职业者"),
    OCCUPATION_19("19", "其他"),
    OCCUPATION_20("20", "司机"),
    ;

    /**
     * 编码
     */
    private final String code;

    /**
     * 描述
     */
    private final String value;


    AccpOccupationTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }


    public static String getCode(String code) {
        AccpOccupationTypeEnum[] imageFormatTypes = values();
        for (AccpOccupationTypeEnum imageFormatType : imageFormatTypes) {
            if (imageFormatType.code().equals(code)) {
                return imageFormatType.code();
            }
        }
        return null;
    }

    public static String getValue(String code) {
        AccpOccupationTypeEnum[] imageFormatTypes = values();
        for (AccpOccupationTypeEnum imageFormatType : imageFormatTypes) {
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
