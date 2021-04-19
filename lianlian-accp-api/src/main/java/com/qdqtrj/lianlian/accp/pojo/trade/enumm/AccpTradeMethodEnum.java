package com.qdqtrj.lianlian.accp.pojo.trade.enumm;

/**
 * 4.2. 付款方式列表
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public enum AccpTradeMethodEnum {
    BALANCE("BALANCE", "余额"),
    COUPON("COUPON", "优惠券"),
    WECHAT_APP("WECHAT_APP", "微信APP"),
    WECHAT_JSAPI("WECHAT_JSAPI", "微信公众号"),
    WECHAT_NATIVE("WECHAT_NATIVE", "微信扫码"),
    WECHAT_H5("WECHAT_H5", "微信H5"),
    WECHAT_WXA("WECHAT_WXA", "微信小程序"),
    WECHAT_MICROPAY("WECHAT_MICROPAY", "微信付款码"),
    WECHAT_PAPAY("WECHAT_PAPAY", "微信委托代扣"),
    WECHAT_NATIVE_ICBC("WECHAT_NATIVE_ICBC", "微信扫码-工银e支付"),
    ALIPAY_NATIVE("ALIPAY_NATIVE", "支付宝扫码"),
    ALIPAY_APP("ALIPAY_APP", "支付宝APP"),
    ALIPAY_H5("ALIPAY_H5", "支付宝H5"),
    ALIPAY_WEB("ALIPAY_WEB", "支付宝WEB"),
    ALIPAY_MICROPAY("ALIPAY_MICROPAY", "支付宝条码"),
    ALIPAY_PAPAY("ALIPAY_PAPAY", "支付宝商户代扣"),
    ALIPAY_WXA("ALIPAY_WXA", "支付宝小程序"),
    ALIPAY_NATIVE_ICBC("ALIPAY_NATIVE_ICBC", "支付宝扫码-工银e支付"),
    EBANK_DEBIT_CARD("EBANK_DEBIT_CARD", "网银借记卡"),
    EBANK_CREDIT_CARD("EBANK_CREDIT_CARD", "网银信用卡"),
    EBANK_B2B("EBANK_B2B", "企业网银"),
    AGRT_DEBIT_CARD("AGRT_DEBIT_CARD", "协议支付借记卡"),
    AGRT_CREDIT_CARD("AGRT_CREDIT_CARD", "协议支付信用卡"),
    ;

    /**
     * 编码
     */
    private final String code;

    /**
     * 描述
     */
    private final String value;

    AccpTradeMethodEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }


    public static String getCode(String code) {
        AccpTradeMethodEnum[] imageFormatTypes = values();
        for (AccpTradeMethodEnum imageFormatType : imageFormatTypes) {
            if (imageFormatType.code().equals(code)) {
                return imageFormatType.code();
            }
        }
        return null;
    }

    public static String getValue(String code) {
        AccpTradeMethodEnum[] imageFormatTypes = values();
        for (AccpTradeMethodEnum imageFormatType : imageFormatTypes) {
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
