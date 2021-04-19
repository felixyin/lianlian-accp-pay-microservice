# 说明

本项目是针对连连支付ACCP产品的java SpringCloud的实现。

- Java版本：https://github.com/felixyin/lianlianpay-accp-sdk
- SpringCloud版本：https://github.com/felixyin/lianlian-accp-pay-microservice

## 一、环境

1. java11
2. maven
3. SpringCloud

## 二、模块说明

1. biz-example-server 你的业务微服务
2. lianlian-accp-api 连连支付ACCP API公共依赖包
3. lianlian-accp-server 连连支付ACCP微服务

## 三、lianlian-accp-server配置

```yaml
accp:
  rsaPrivateKey: 请求加密私钥
  rsaPublicKey: 响应验签公钥
  rsaNotifyPublicKey: 一般与上面公钥相同
  payDomainServerUrl: https://accptest.lianlianpay-inc.com/openapi/
  reqTimeout: 30
  thisDomainServerUrl: https://www.abc.com
  oidPartner: 连连支付分配的商户号
  ocxPublicKey: 连连支付密码控件公钥
  ocxPublicKeyConvert: 连连支付密码控件加盐key
```

## 四、接口鉴权

TODO，接口鉴权请在你的gateway微服务中自行实现
