package com.qdqtrj.lianlian.accp.pojo.manage.enumm;

/**
 * 4.8. 行业代码
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public enum AccpIndustryCodeEnum {

    INDUSTRY_110("110", "内资-国有全资"),
    INDUSTRY_120("120", "内资-集体全资"),
    INDUSTRY_130("130", "内资-股份合作"),
    INDUSTRY_141("141", "内资-联营-国有联营"),
    INDUSTRY_142("142", "内资-联营-集体联营"),
    INDUSTRY_143("143", "内资-联营-国有与集体联营"),
    INDUSTRY_149("149", "内资-联营-其他联营"),
    INDUSTRY_151("151", "内资-有限责任(公司)-国有独资(公司)"),
    INDUSTRY_152("152", "内资-有限责任(公司)-其他有限责任(公司)"),
    INDUSTRY_160("160", "内资-股份有限(公司)"),
    INDUSTRY_171("171", "内资-私有(私营)-独资"),
    INDUSTRY_172("172", "内资-私有(私营)-合伙"),
    INDUSTRY_173("173", "内资-私有(私营)-有限责任(公司)"),
    INDUSTRY_174("174", "内资-私有(私营)-股份有限(公司)"),
    INDUSTRY_175("175", "内资-个体经营"),
    INDUSTRY_176("176", "内资-其他私有"),
    INDUSTRY_190("190", "内资-其他内资"),
    INDUSTRY_210("210", "港澳台投资-内地和港澳台合资"),
    INDUSTRY_220("220", "港澳台投资-内地和港澳台合作"),
    INDUSTRY_230("230", "港澳台投资-港澳台独资"),
    INDUSTRY_240("240", "港澳台投资-港澳台投资股份有限(公司)"),
    INDUSTRY_250("250", "港澳台投资-其他港澳台投资"),
    INDUSTRY_310("310", "国外投资-中外合资"),
    INDUSTRY_320("320", "国外投资-中外合作"),
    INDUSTRY_330("330", "国外投资-外资独资"),
    INDUSTRY_340("340", "国外投资-国外投资股份有限(公司)"),
    INDUSTRY_350("350", "国外投资-其他国外投资"),
    INDUSTRY_400("400", "个体经营"),
    INDUSTRY_410("410", "个体经营-个体户"),
    INDUSTRY_420("420", "个体经营-个人合伙"),
    INDUSTRY_500("500", "国家党政机关"),
    INDUSTRY_510("510", "事业单位"),
    INDUSTRY_600("600", "军队"),
    INDUSTRY_700("700", "社会团体"),
    INDUSTRY_810("810", "境外公司-外资公司"),
    INDUSTRY_820("820", "境外公司-中资公司"),
    INDUSTRY_830("830", "境外公司-金融机构"),
    INDUSTRY_900("900", "外国政府"),
    ;

    /**
     * 编码
     */
    private final String code;

    /**
     * 描述
     */
    private final String value;


    AccpIndustryCodeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }


    public static String getCode(String code) {
        AccpIndustryCodeEnum[] imageFormatTypes = values();
        for (AccpIndustryCodeEnum imageFormatType : imageFormatTypes) {
            if (imageFormatType.code().equals(code)) {
                return imageFormatType.code();
            }
        }
        return null;
    }

    public static String getValue(String code) {
        AccpIndustryCodeEnum[] imageFormatTypes = values();
        for (AccpIndustryCodeEnum imageFormatType : imageFormatTypes) {
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
