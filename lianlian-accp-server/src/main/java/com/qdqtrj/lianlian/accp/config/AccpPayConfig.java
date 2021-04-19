package com.qdqtrj.lianlian.accp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Accp支付平台配置对象
 * <p>
 * 非spring使用方式：直接new实例
 * springcloud使用方式：
 * <pre>
 * accp:
 *   rsaPrivateKey: MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAPD+malK2W3UJXfX5rO79gRUbLe+kwCskw7VzETXA4Qf/5VWlPxOb16SlflkE4zyInhGwehjUTvXPvebNtZJJpKS+Q/7oWw6hMQ1cIC99DWmV6Orjtz61Tmi5A/4QnYqUm2GRScfrnyILQw9/qikkvyjo0pPsIMT2rhmu31LSySNAgMBAAECgYEAokaubeKq2lu6ByLohCqTFINM2cWH8zJBrAGnFMu74GIzlfnBRMwEDiiiuFX9HDGHqHns5HDMKIFeMxjfKhgD0exp3S06xpSbmkIbvWLM+xBl70/+SLG7wztZ4KtdKu7PR26xJht6zM/KDrovuRzFYNB6ZbyO3My9CJXaZS6GU/kCQQD+/wsf0M7Byp+sPzy3SEn8katFopVOz8oESBBuSNNXl1rgyWfgVXBUKRDAus8oa/Nhx2zWNqpuchrHerPp5McHAkEA8fFyidW4nMkL3x4ULQmbsZBqsNEXoKv3fDDvHWRljX0AElel+XaVuxrtpYiDxwqFSM0s92nCBj2ZXt4O+d2eywJAS5mFzMr1YZMXP9QHxjcSaGUvqBeJuLH2LMrIxEmnDuL6uIY928643NrH8rvvywYmRCkB5YiTgucldVq1mHSRZQJAYny8+WrsqbYVhQ/DesnsfQ2iwLN9AMTAC+gHjlluFXiK7OyM/c3OCcpebwHxUrbvpsEOyvBcMRomMr4GLqSOnQJAcDKoXpkYFGakejn6LQj57EBtMgfVNatTipBnQxPaHMGGO9V9SzedbkgNg0NBSzsNsauKnFOy+yFwFqf6oGHm0A==
 *   rsaPublicKey: MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSS/DiwdCf/aZsxxcacDnooGph3d2JOj5GXWi+q3gznZauZjkNP8SKl3J2liP0O6rU/Y/29+IUe+GTMhMOFJuZm1htAtKiu5ekW0GlBMWxf4FPkYlQkPE0FtaoMP3gYfh+OwI+fIRrpW3ySn3mScnc6Z700nU/VYrRkfcSCbSnRwIDAQAB
 *   rsaNotifyPublicKey: MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDw/pmpStlt1CV31+azu/YEVGy3vpMArJMO1cxE1wOEH/+VVpT8Tm9ekpX5ZBOM8iJ4RsHoY1E71z73mzbWSSaSkvkP+6FsOoTENXCAvfQ1plejq47c+tU5ouQP+EJ2KlJthkUnH658iC0MPf6opJL8o6NKT7CDE9q4Zrt9S0skjQIDAQAB
 *   payDomainServerUrl: https://accptest.lianlianpay-inc.com/openapi/
 *   reqTimeout: 30
 *   thisDomainServerUrl: http://127.0.0.1
 * </pre>
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@Component
@ConfigurationProperties(prefix = "accp")
public class AccpPayConfig {

    /**
     * 发送至Accp平台的支付报文加密用 私钥
     */
    private String rsaPrivateKey;

    /**
     * Accp支付平台返回的报文 解密用 公钥
     */
    private String rsaPublicKey;

    /**
     * Accp支付平台异步通知 验签用 公钥
     */
    private String rsaNotifyPublicKey;

    /**
     * 支付平台域名地址，不包括具体接口path，调用时需要拼接具体接口path
     */
    private String payDomainServerUrl;

    /**
     * 请求超时时间
     * 单位：毫秒
     * 可以不传递，默认为：30秒
     */
    private Integer reqTimeout = AccpConstants.DEFAULT_REQ_TIMEOUT_SECOND;

    /**
     * 当前web服务的域名地址（不包括端口）
     */
    private String thisDomainServerUrl;

    /**
     * oid_partner,商户号,Y,String(18),ACCP 系统分配给平台商户的唯一编号
     */
    private String oidPartner;

    /**
     * 密码控件公钥
     */
    private String ocxPublicKey;

    /**
     * 密码控件公钥转化码
     */
    private String ocxPublicKeyConvert;
}
