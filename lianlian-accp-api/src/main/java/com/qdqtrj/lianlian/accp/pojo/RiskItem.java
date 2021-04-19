package com.qdqtrj.lianlian.accp.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 风控参数
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/23 2:59 下午
 */
@Getter
@Setter
public class RiskItem implements Serializable {
    private static final long serialVersionUID = -1513639610732798582L;
    /**
     * frms_ware_category	必传	String	商品类目。 详见商品类目表。
     */
    @NotBlank
    private String frms_ware_category;
    /**
     * user_info_mercht_userno	必传	String	商户用户唯一标识。 商户系统中对用户的唯一编号， 可与支付请求时的user_id相同。
     */
    @NotBlank
    private String user_info_mercht_userno;
    /**
     * user_info_bind_phone	必传	String	用户绑定手机号。 如商户确实没有收集的， 需要与连连技术支持特殊说明。（接入微信支付的商户，若未强制绑定手机号，在商户无法获取用户手机号的情况下，该参数可不传）
     */
    @NotBlank
    private String user_info_bind_phone;
    /**
     * user_info_dt_register	必传	String	注册时间。用户在商户系统中的注册时间， 格式须为YYYYMMDDHHMMSS， 24小时制。
     */
    @NotBlank

    private String user_info_dt_register;
    /**
     * goods_name	非必传	String	商品名称。
     */
    private String goods_name;

    // 一下是api调用类风控参数

    /**
     * 业务来源。
     * 0 - 商户代扣模式
     * 10 - APP。
     * 13 - PC。
     * 16 - H5。
     * 18 - IVR。
     */
    private String frms_client_chnl = "0";

    /**
     * 用户交易请求IP，frms_client_chnl=0可不传。
     */
    private String frms_ip_addr;

    /**
     * 用户授权标记。
     * 0 - 否
     * 1 - 是
     * 该字段指开通API的商户，用户有无勾选连连支付服务协议。（商户接入微信支付时，该字段无需传输）
     */
    private String user_auth_flag = "0";

    public RiskItem() {
    }

    public RiskItem(@NotBlank String frms_ware_category, @NotBlank String user_info_mercht_userno, @NotBlank String user_info_bind_phone, @NotBlank String user_info_dt_register, @NotBlank String goods_name) {
        this.frms_ware_category = frms_ware_category;
        this.user_info_mercht_userno = user_info_mercht_userno;
        this.user_info_bind_phone = user_info_bind_phone;
        this.user_info_dt_register = user_info_dt_register;
        this.goods_name = goods_name;
    }

    public RiskItem(@NotBlank String frms_ware_category, @NotBlank String user_info_mercht_userno, @NotBlank String user_info_bind_phone, @NotBlank String user_info_dt_register, @NotBlank String goods_name, String frms_client_chnl, String frms_ip_addr, String user_auth_flag) {
        this.frms_ware_category = frms_ware_category;
        this.user_info_mercht_userno = user_info_mercht_userno;
        this.user_info_bind_phone = user_info_bind_phone;
        this.user_info_dt_register = user_info_dt_register;
        this.goods_name = goods_name;
        this.frms_client_chnl = frms_client_chnl;
        this.frms_ip_addr = frms_ip_addr;
        this.user_auth_flag = user_auth_flag;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
