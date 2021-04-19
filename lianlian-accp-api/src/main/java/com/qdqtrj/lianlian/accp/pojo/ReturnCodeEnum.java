package com.qdqtrj.lianlian.accp.pojo;

/**
 * Accp支付平台错误码 对照表
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public enum ReturnCodeEnum {
    SUCCESS("0000", "交易成功"),
    ERROR_1000("1000", "token授权令牌失效或错误"),
    ERROR_1001("1001", "商户请求签名未通过，请联系客服处理"),
    ERROR_1002("1002", "商户请求IP未报备，请联系客服处理"),
    ERROR_1003("1003", "商户APP应用标识未报备，请联系客服处理"),
    ERROR_1004("1004", "商户请求来源域名未报备，请联系客服处理"),
    ERROR_1005("1005", "商户请求参数校验错误（%s）"),
    ERROR_1006("1006", "商户状态异常，请联系客服处理"),
    ERROR_1007("1007", "商户支付服务暂停，请联系客服处理"),
    ERROR_1008("1008", "商户出金服务暂停，请联系客服处理"),
    ERROR_1009("1009", "商户未配置该支付产品或支付方式，请联系客服处理"),
    ERROR_1010("1010", "商户流水号重复，请勿重复提交"),
    ERROR_1011("1011", "订单查询无记录"),
    ERROR_1012("1012", "支付密码随机因子获取失败"),
    ERROR_1013("1013", "支付密码随机因子失效或错误"),
    ERROR_1014("1014", "支付密码错误次数超限，请稍后重试"),
    ERROR_1015("1015", "支付密码错误"),
    ERROR_1016("1016", "支付密码设置、重置或找回失败"),
    ERROR_1017("1017", "短信验证码错误，请重新输入"),
    ERROR_1018("1018", "短信验证码已失效，请重新获取"),
    ERROR_1019("1019", "短信验证码错误次数超过最大次数"),
    ERROR_1020("1020", "短信验证码发送异常"),
    ERROR_1021("1021", "短信发送过于频繁，请稍后再试"),
    ERROR_1022("1022", "风控验证未通过"),
    ERROR_1023("1023", "用户输入的姓名、证件号或手机号有误，请检查信息后重试"),
    ERROR_1024("1024", "银行卡号错误或与证件号不符，请检查信息后重试"),
    ERROR_1025("1025", "银行卡状态异常"),
    ERROR_1026("1026", "用户证件号错误，请检查信息后重试"),
    ERROR_1027("1027", "用户姓名错误，请检查信息后重试"),
    ERROR_1028("1028", "输入的银行预留手机号错误或该银行卡未预留手机号"),
    ERROR_1029("1029", "用户选择的银行暂不支持，请重新选择其他银行进行支付/签约"),
    ERROR_1030("1030", "签约协议号不存在"),
    ERROR_1031("1031", "商户信息不存在"),
    ERROR_1032("1032", "流水号状态异常，请更换流水号"),
    ERROR_1033("1033", "session_id已过期， 请返回重试"),
    ERROR_1034("1034", "请求次数超限，请明天重试"),
    ERROR_1035("1035", "接口限流，请稍后重试"),
    ERROR_1036("1036", "商户平台商户号未配置"),
    ERROR_1037("1037", "不在有效期"),
    ERROR_2000("2000", "交易流水创建失败"),
    ERROR_2001("2001", "用户状态异常"),
    ERROR_2002("2002", "账户状态异常"),
    ERROR_2003("2003", "用户查询失败或不存在"),
    ERROR_2004("2004", "用户账户查询失败或不存在"),
    ERROR_2005("2005", "用户创建或激活失败，请检查信息后重试"),
    ERROR_2006("2006", "用户ID已经存在"),
    ERROR_2007("2007", "用户证件号已经超过最大开户数量"),
    ERROR_2008("2008", "该银行卡已签约绑卡成功，请重新选择其他银行卡进行签约绑卡"),
    ERROR_2009("2009", "用户签约解绑操作失败"),
    ERROR_2010("2010", "销户申请失败"),
    ERROR_2011("2011", "用户原绑定手机号错误，请输入正确的原绑定手机号"),
    ERROR_2012("2012", "用户新绑定手机号与原绑定手机号一致，请绑定其他手机号"),
    ERROR_2013("2013", "用户不存在绑卡信息"),
    ERROR_2014("2014", "用户类型错误"),
    ERROR_2015("2015", "大额行号查询失败"),
    ERROR_2016("2016", "上传证件失败或异常"),
    ERROR_2017("2017", "用户合作银行开户失败"),
    ERROR_2018("2018", "反洗钱黑名单拦截"),
    ERROR_2019("2019", "签约类型不支持"),
    ERROR_3000("3000", "支付单创建失败"),
    ERROR_3001("3001", "支付单已关闭"),
    ERROR_3002("3002", "支付单已交易成功"),
    ERROR_3003("3003", "交易正在处理中"),
    ERROR_3004("3004", "支付信息与原支付单参数不一致"),
    ERROR_3005("3005", "账户余额不足"),
    ERROR_3006("3006", "账户余额不足"),
    ERROR_3007("3007", "用户银行卡余额不足"),
    ERROR_3008("3008", "该交易不支持信用卡"),
    ERROR_3009("3009", "平台商户账户不允许进行消费交易"),
    ERROR_3010("3010", "用户单笔交易限额超限"),
    ERROR_3011("3011", "用户单日交易限额超限"),
    ERROR_3012("3012", "用户单月交易限额超限"),
    ERROR_3013("3013", "商户单笔交易限额超限"),
    ERROR_3014("3014", "商户单日交易限额超限"),
    ERROR_3015("3015", "商户单月交易限额超限"),
    ERROR_3016("3016", "支付宝或微信APPID错误或者不存在"),
    ERROR_3017("3017", "退款原单不存在"),
    ERROR_3018("3018", "退款交易失败"),
    ERROR_3019("3019", "原交易不支持退款"),
    ERROR_3020("3020", "该笔交易暂不支持"),
    ERROR_3021("3021", "权限不足"),
    ERROR_3022("3022", "原交易已在进行处理，请勿重复提交"),
    ERROR_3023("3023", "银行交易出错，请稍后重试（%s）"),
    ERROR_3024("3024", "电子回单不存在"),
    ERROR_3025("3025", "生成电子回单异常"),
    ERROR_3026("3026", "电子回单更新次数超过最大值"),
    ERROR_3027("3027", "不支持电子回单功能"),
    ERROR_3028("3028", "电子回单状态非法"),
    ERROR_3029("3029", "商户批量付款文件未上传或者下载失败"),
    ERROR_3030("3030", "商户批量付款文件MD5验证失败"),
    ERROR_3031("3031", "商户批次信息验证失败"),
    ERROR_3032("3032", "商户批次信息已存在"),
    ERROR_3033("3033", "商户批次总笔数总金额验证失败"),
    ERROR_3034("3034", "商户批次信息处理失败"),
    ERROR_3035("3035", "商户所属银行验证失败"),
    ERROR_3036("3036", "批量付款并发批次数量超限，请稍后重试"),
    ERROR_3037("3037", "批量付款请求文件格式错误"),
    ERROR_3038("3038", "用户信息修改申请失败"),
    ERROR_3039("3039", "无效用户信息修改申请"),
    ERROR_3203("3203", "银行交易出错，请稍后重试"),
    ERROR_8000("8000", "密钥信息缺失"),
    ERROR_8888("8888", "交易申请成功"),
    ERROR_8889("8889", "交易申请成功"),
    ERROR_9970("9970", "系统错误，请稍后重试"),
    ERROR_9999("9999", "系统错误ERROR"),
    ;

    /**
     * 编码
     */
    private final String code;

    /**
     * 描述
     */
    private final String value;


    ReturnCodeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }


    public static String getCode(String code) {
        ReturnCodeEnum[] imageFormatTypes = values();
        for (ReturnCodeEnum imageFormatType : imageFormatTypes) {
            if (imageFormatType.code().equals(code)) {
                return imageFormatType.code();
            }
        }
        return null;
    }

    public static String getValue(String code) {
        ReturnCodeEnum[] imageFormatTypes = values();
        for (ReturnCodeEnum imageFormatType : imageFormatTypes) {
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
