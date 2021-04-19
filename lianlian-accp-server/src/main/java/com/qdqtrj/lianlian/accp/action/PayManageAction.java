package com.qdqtrj.lianlian.accp.action;

import com.alibaba.fastjson.JSON;
import com.qdqtrj.lianlian.accp.api.IaccpManagePayClient;
import com.qdqtrj.lianlian.accp.api.impl.AccpManagePayClientImpl;
import com.qdqtrj.lianlian.accp.config.AccpConstants;
import com.qdqtrj.lianlian.accp.config.AccpPayConfig;
import com.qdqtrj.lianlian.accp.exception.AccpPayException;
import com.qdqtrj.lianlian.accp.exception.AccpReqParamException;
import com.qdqtrj.lianlian.accp.pojo.AccpPayRecordModel;
import com.qdqtrj.lianlian.accp.pojo.GenRiskItemHelper;
import com.qdqtrj.lianlian.accp.pojo.manage.dto.request.*;
import com.qdqtrj.lianlian.accp.pojo.manage.dto.response.*;
import com.qdqtrj.lianlian.accp.service.AsyncMongoService;
import com.qdqtrj.lianlian.accp.util.ocx.AesWithJceUtils;
import com.qdqtrj.lianlian.accp.util.ocx.GetRandomUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;


/**
 * 包括所有 doc/《ACCP产品商户接口说明书-账户管理类（API）V1.4-标准版.pdf》 接口
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@RestController
@Slf4j
@RequestMapping("/payManageAction")
@Api(value = "PayManageAction", tags = "支付微服务《管理类》API接口")
public class PayManageAction {

    /**
     * accp账户管理类接口
     */
    @Resource(type = AccpManagePayClientImpl.class)
    private IaccpManagePayClient manageClient;

    /**
     * accp支付配置
     */
    @Resource
    private AccpPayConfig payConfig;

    /**
     * web端口
     */
    @Value("${server.port}")
    private String port;

    /**
     * 异步写库
     */
    @Resource
    private AsyncMongoService asyncMongoService;

    /**
     * 如果业务系统没有传递notifyurl，则启用本服务提供的
     *
     * @param thisNotifyUrl
     */
    private String getDefaultNotifyUrl(String thisNotifyUrl) {
        String notifyUrl = this.payConfig.getThisDomainServerUrl() + "/payNotifyAction/" + thisNotifyUrl + "/white";
        log.info("accp->异步通知url：{}", notifyUrl);
        return notifyUrl;
    }


    /**
     * 生成和返回pc web端ocx密码控件所需初始化参数
     *
     * @return ResponseEntity实例
     * @see AccpPayConfig
     */
    @ApiOperation(value = "生成和返回pc web端ocx密码控件所需初始化参数", response = Map.class)
    @GetMapping("generateAesForOcx")
    @PostMapping("generateAesForOcx")
    public Map<String, String> generateAesForOcx() {
        Map<String, String> map = new HashMap<>(4);
        try {
            String sKey = GetRandomUtils.generateStringDefaultLength();
            String enStr = AesWithJceUtils.getCipher(sKey, sKey);

            map.put("sKey", sKey);
            map.put("enStr", enStr);
            map.put("public_key", payConfig.getOcxPublicKey());
            map.put("public_key_convert", payConfig.getOcxPublicKeyConvert());

            log.debug("accp->生成ocx密码控件所需数据：{}", JSON.toJSONString(map));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            map.put("error", e.getMessage());
        }
        return map;
    }

    /**
     * 3. 交易接口定义 3.1. 绑定手机验证码申请 3.1.1. 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/regphone-verifycode-apply
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_REGPHONE_VERIFYCODE_APPLY
     *
     * @param req ReqRegphoneVerifycodeApply实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqRegphoneVerifycodeApply
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResRegphoneVerifycodeApply
     */
    @ApiOperation(value = "3. 交易接口定义 3.1. 绑定手机验证码申请 3.1.1. " +
            "请求地址https://accpapi.lianlianpay.com/v1/acctmgr/regphone-verifycode-apply",
            response = ResRegphoneVerifycodeApply.class)
    @ApiImplicitParam(dataTypeClass = ReqRegphoneVerifycodeApply.class)
    @PostMapping("regphoneVerifycodeApply/white")
    public ResRegphoneVerifycodeApply regphoneVerifycodeApply(@RequestBody ReqRegphoneVerifycodeApply req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResRegphoneVerifycodeApply res = this.manageClient.regphoneVerifycodeApply(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_REGPHONE_VERIFYCODE_APPLY, "3.1 绑定手机验证码申请", req, res, sourceIp));
        return res;
    }

    /**
     * 3.2. 绑定手机验证码验证 3.2.1. 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/regphone-verifycode-verify
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_REGPHONE_VERIFYCODE_VERIFY
     *
     * @param req ReqRegphoneVerifycodeVerify实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqRegphoneVerifycodeVerify
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResRegphoneVerifycodeVerify
     */
    @ApiOperation(value = "3.2. 绑定手机验证码验证 3.2.1. 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/regphone-verifycode-verify",
            response = ResRegphoneVerifycodeVerify.class)
    @ApiImplicitParam(dataTypeClass = ReqRegphoneVerifycodeVerify.class)
    @PostMapping("regphoneVerifycodeVerify/white")
    public ResRegphoneVerifycodeVerify regphoneVerifycodeVerify(@RequestBody ReqRegphoneVerifycodeVerify req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResRegphoneVerifycodeVerify res = this.manageClient.regphoneVerifycodeVerify(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_REGPHONE_VERIFYCODE_VERIFY, "3.2. 绑定手机验证码验证", req, res, sourceIp));
        return res;
    }

    /**
     * 3.3. 个人用户开户 3.3.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-individual
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：AccpConstants.URL_OPENACCT_APPLY_INDIVIDUAL
     *
     * @param req ReqOpenacctApplyIndividual实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqOpenacctApplyIndividual
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResOpenacctApplyIndividual
     */
    @ApiOperation(value = "3.3. 个人用户开户 3.3.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-individual", response = ResOpenacctApplyIndividual.class)
    @ApiImplicitParam(dataTypeClass = ReqOpenacctApplyIndividual.class)
    @PostMapping("openacctApplyIndividual/white")
    public ResOpenacctApplyIndividual openacctApplyIndividual(@RequestBody ReqOpenacctApplyIndividual req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isBlank(req.getNotify_url())) {
            req.setNotify_url(getDefaultNotifyUrl("openacctVerifyIndividual"));
        }
        if (isEmpty(req.getRisk_item())) {
            req.setRisk_item(GenRiskItemHelper.randomRiskItem());
        }
        ResOpenacctApplyIndividual res = this.manageClient.openacctApplyIndividual(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_OPENACCT_APPLY_INDIVIDUAL, "3.3.1. 个人用户开户-开户申请", req, res, sourceIp));
        return res;
    }

    /**
     * 3.3.2. 开户验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-verify-individual
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_OPENACCT_VERIFY_INDIVIDUAL
     *
     * @param req ReqOpenacctVerifyIndividual实例
     * @return ResponseEntity实例
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.callback.NotifyOpenacctVerifyIndividual
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqOpenacctVerifyIndividual
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResOpenacctVerifyIndividual
     */
    @ApiOperation(value = "3.3.2. 开户验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-verify-individual",
            response = ResOpenacctVerifyIndividual.class)
    @ApiImplicitParam(dataTypeClass = ReqOpenacctVerifyIndividual.class)
    @PostMapping("openacctVerifyIndividual/white")
    public ResOpenacctVerifyIndividual openacctVerifyIndividual(@RequestBody ReqOpenacctVerifyIndividual req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResOpenacctVerifyIndividual res = this.manageClient.openacctVerifyIndividual(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_OPENACCT_VERIFY_INDIVIDUAL, "3.3.2. 开户验证", req, res, sourceIp));
        return res;
    }

    /**
     * 3.4. 企业用户开户 3.4.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-enterprise
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：AccpConstants.URL_OPENACCT_APPLY_ENTERPRISE
     *
     * @param req ReqOpenacctApplyEnterprise实例
     * @return ResponseEntity实例
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.callback.NotifyOpenacctApplyEnterprise
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqOpenacctApplyEnterprise
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResOpenacctApplyEnterprise
     */
    @ApiOperation(value = "3.4. 企业用户开户 3.4.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-enterprise",
            response = ResOpenacctApplyEnterprise.class)
    @ApiImplicitParam(dataTypeClass = ReqOpenacctApplyEnterprise.class)
    @PostMapping("openacctApplyEnterprise")
    public ResOpenacctApplyEnterprise openacctApplyEnterprise(@RequestBody ReqOpenacctApplyEnterprise req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isBlank(req.getNotify_url())) {
            req.setNotify_url(getDefaultNotifyUrl("openacctApplyEnterprise"));
        }
        if (isEmpty(req.getRisk_item())) {
            req.setRisk_item(GenRiskItemHelper.randomRiskItem());
        }
        ResOpenacctApplyEnterprise res = this.manageClient.openacctApplyEnterprise(this.payConfig, req);
        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_OPENACCT_APPLY_ENTERPRISE, "3.4.1. 企业用户开户-开户申请", req, res, sourceIp));
        return res;
    }

    /**
     * 3.5. 个人用户新增绑卡 3.5.1. 绑卡申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/individual-bindcard-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * <p>
     * 地址常量：AccpConstants.URL_INDIVIDUAL_BINDCARD_APPLY
     *
     * @param req ReqIndividualBindcardApply实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqIndividualBindcardApply
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResIndividualBindcardApply
     */
    @ApiOperation(value = "3.5. 个人用户新增绑卡 3.5.1. 绑卡申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/individual-bindcard-apply",
            response = ResIndividualBindcardApply.class)
    @ApiImplicitParam(dataTypeClass = ReqIndividualBindcardApply.class)
    @PostMapping("individualBindcardApply/white")
    public ResIndividualBindcardApply individualBindcardApply(@RequestBody ReqIndividualBindcardApply req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isBlank(req.getNotify_url())) {
            req.setNotify_url(getDefaultNotifyUrl("individualBindcardVerify"));
        }
        if (isEmpty(req.getRisk_item())) {
            req.setRisk_item(GenRiskItemHelper.randomRiskItem());
        }
        ResIndividualBindcardApply res = this.manageClient.individualBindcardApply(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_INDIVIDUAL_BINDCARD_APPLY, "3.5.1. 个人用户新增绑卡-绑卡申请", req, res, sourceIp));
        return res;
    }

    /**
     * 3.5.2. 绑卡验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/individual-bindcard-verify
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_INDIVIDUAL_BINDCARD_VERIFY
     *
     * @param req ReqIndividualBindcardVerify实例
     * @return ResponseEntity实例
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.callback.NotifyIndividualBindcardVerify
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqIndividualBindcardVerify
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResIndividualBindcardVerify
     */
    @ApiOperation(value = "3.5.2. 绑卡验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/individual-bindcard-verify",
            response = ResIndividualBindcardVerify.class)
    @ApiImplicitParam(dataTypeClass = ReqIndividualBindcardVerify.class)
    @PostMapping("individualBindcardVerify/white")
    public ResIndividualBindcardVerify individualBindcardVerify(@RequestBody ReqIndividualBindcardVerify req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResIndividualBindcardVerify res = this.manageClient.individualBindcardVerify(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_INDIVIDUAL_BINDCARD_VERIFY, "3.5.2. 绑卡验证", req, res, sourceIp));
        return res;
    }

    /**
     * 3.6. 企业用户更换绑定账号 3.6.1. 更换申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/enterprise-changecard-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：AccpConstants.URL_ENTERPRISE_CHANGECARD_APPLY
     *
     * @param req ReqEnterpriseChangecardApply实例
     * @return ResponseEntity实例
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.callback.NotifyEnterpriseChangecardApply
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqEnterpriseChangecardApply
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResEnterpriseChangecardApply
     */
    @ApiOperation(value = "3.6. 企业用户更换绑定账号 3.6.1. 更换申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/enterprise-changecard-apply",
            response = ResEnterpriseChangecardApply.class)
    @ApiImplicitParam(dataTypeClass = ReqEnterpriseChangecardApply.class)
    @PostMapping("enterpriseChangecardApply")
    public ResEnterpriseChangecardApply enterpriseChangecardApply(@RequestBody ReqEnterpriseChangecardApply req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isBlank(req.getNotify_url())) {
            req.setNotify_url(getDefaultNotifyUrl("enterpriseChangecardApply"));
        }
        if (isEmpty(req.getRisk_item())) {
            req.setRisk_item(GenRiskItemHelper.randomRiskItem());
        }
        ResEnterpriseChangecardApply res = this.manageClient.enterpriseChangecardApply(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_ENTERPRISE_CHANGECARD_APPLY, "3.6.1. 企业用户更换绑定账号-更换申请", req, res, sourceIp));
        return res;
    }

    /**
     * 3.7. 个人用户解绑银行卡 3.7.1. 解绑 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/unlinkedacct-ind-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：AccpConstants.URL_UNLINKEDACCT_IND_APPLY
     *
     * @param req ReqUnlinkedacctIndApply实例
     * @return ResponseEntity实例
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.callback.NotifyUnlinkedacctIndApply
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqUnlinkedacctIndApply
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResUnlinkedacctIndApply
     */
    @ApiOperation(value = "3.7. 个人用户解绑银行卡 3.7.1. 解绑 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/unlinkedacct-ind-apply",
            response = ResUnlinkedacctIndApply.class)
    @ApiImplicitParam(dataTypeClass = ReqUnlinkedacctIndApply.class)
    @PostMapping("unlinkedacctIndApply/white")
    public ResUnlinkedacctIndApply unlinkedacctIndApply(@RequestBody ReqUnlinkedacctIndApply req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isBlank(req.getNotify_url())) {
            req.setNotify_url(getDefaultNotifyUrl("unlinkedacctIndApply"));
        }
        ResUnlinkedacctIndApply res = this.manageClient.unlinkedacctIndApply(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_UNLINKEDACCT_IND_APPLY, "3.7.1. 个人用户解绑银行卡", req, res, sourceIp));
        return res;
    }

    /**
     * 3.8. 个人用户修改预留手机号 3.8.1. 申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-linkedphone-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：AccpConstants.URL_CHANGE_LINKEDPHONE_APPLY
     *
     * @param req ReqChangeLinkedphoneApply实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqChangeLinkedphoneApply
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResChangeLinkedphoneApply
     */
    @ApiOperation(value = "3.8. 个人用户修改预留手机号 3.8.1. 申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-linkedphone-apply",
            response = ResChangeLinkedphoneApply.class)
    @ApiImplicitParam(dataTypeClass = ReqChangeLinkedphoneApply.class)
    @PostMapping("changeLinkedphoneApply")
    public ResChangeLinkedphoneApply changeLinkedphoneApply(@RequestBody ReqChangeLinkedphoneApply req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isBlank(req.getNotify_url())) {
            req.setNotify_url(getDefaultNotifyUrl("changeLinkedphoneApply"));
        }
        ResChangeLinkedphoneApply res = this.manageClient.changeLinkedphoneApply(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_CHANGE_LINKEDPHONE_APPLY, "3.8.1. 申请个人用户修改预留手机号", req, res, sourceIp));
        return res;
    }

    /**
     * 3.8.2. 验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-linkedphone-verify
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_CHANGE_LINKEDPHONE_VERIFY
     *
     * @param req ReqChangeLinkedphoneVerify实例
     * @return ResponseEntity实例
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.callback.NotifyChangeLinkedphoneVerify
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqChangeLinkedphoneVerify
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResChangeLinkedphoneVerify
     */
    @ApiOperation(value = "3.8.2. 验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-linkedphone-verify",
            response = ResChangeLinkedphoneVerify.class)
    @ApiImplicitParam(dataTypeClass = ReqChangeLinkedphoneVerify.class)
    @PostMapping("changeLinkedphoneVerify")
    public ResChangeLinkedphoneVerify changeLinkedphoneVerify(@RequestBody ReqChangeLinkedphoneVerify req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResChangeLinkedphoneVerify res = this.manageClient.changeLinkedphoneVerify(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_CHANGE_LINKEDPHONE_VERIFY, "3.8.2. 验证", req, res, sourceIp));
        return res;
    }

    /**
     * 3.9. 修改绑定手机 3.9.1. 申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-regphone-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：AccpConstants.URL_CHANGE_REGPHONE_APPLY
     *
     * @param req ReqChangeRegphoneApply实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqChangeRegphoneApply
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResChangeRegphoneApply
     */
    @ApiOperation(value = "3.9. 修改绑定手机 3.9.1. 申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-regphone-apply",
            response = ResChangeRegphoneApply.class)
    @ApiImplicitParam(dataTypeClass = ReqChangeRegphoneApply.class)
    @PostMapping("changeRegphoneApply")
    public ResChangeRegphoneApply changeRegphoneApply(@RequestBody ReqChangeRegphoneApply req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isBlank(req.getNotify_url())) {
            req.setNotify_url(getDefaultNotifyUrl("changeRegphoneApply"));
        }
        if (isEmpty(req.getRisk_item())) {
            req.setRisk_item(GenRiskItemHelper.randomRiskItem());
        }
        ResChangeRegphoneApply res = this.manageClient.changeRegphoneApply(this.payConfig, req);
        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_CHANGE_REGPHONE_APPLY, "3.9.1. 申请修改绑定手机", req, res, sourceIp));
        return res;
    }

    /**
     * 3.9.2. 验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-regphone-verify
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_CHANGE_REGPHONE_VERIFY
     *
     * @param req ReqChangeRegphoneVerify实例
     * @return ResponseEntity实例
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.callback.NotifyChangeRegphoneVerify
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqChangeRegphoneVerify
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResChangeRegphoneVerify
     */
    @ApiOperation(value = "3.9.2. 验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-regphone-verify",
            response = ResChangeRegphoneVerify.class)
    @ApiImplicitParam(dataTypeClass = ReqChangeRegphoneVerify.class)
    @PostMapping("changeRegphoneVerify")
    public ResChangeRegphoneVerify changeRegphoneVerify(@RequestBody ReqChangeRegphoneVerify req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResChangeRegphoneVerify res = this.manageClient.changeRegphoneVerify(this.payConfig, req);
        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_CHANGE_REGPHONE_VERIFY, "", req, res, sourceIp));
        return res;
    }

    /**
     * 3.10. 销户 3.10.1.  销户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/cancel-apply
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：AccpConstants.URL_CANCEL_APPLY
     *
     * @param req ReqCancelApply实例
     * @return ResponseEntity实例
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.callback.NotifyCancelApply
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqCancelApply
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResCancelApply
     */
    @ApiOperation(value = "3.10. 销户 3.10.1.  销户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/cancel-apply",
            response = ResCancelApply.class)
    @ApiImplicitParam(dataTypeClass = ReqCancelApply.class)
    @PostMapping("cancelApply")
    public ResCancelApply cancelApply(@RequestBody ReqCancelApply req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isBlank(req.getNotify_url())) {
            req.setNotify_url(getDefaultNotifyUrl("cancelApply"));
        }
        ResCancelApply res = this.manageClient.cancelApply(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_CANCEL_APPLY, "3.10.1.  销户申请", req, res, sourceIp));
        return res;
    }

    /**
     * 3.11. 重置密码 3.11.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-password
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_CHANGE_PASSWORD
     *
     * @param req ReqChangePassword实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqChangePassword
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResChangePassword
     */
    @ApiOperation(value = "3.11. 重置密码 3.11.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/change-password",
            response = ResChangePassword.class)
    @ApiImplicitParam(dataTypeClass = ReqChangePassword.class)
    @PostMapping("changePassword")
    public ResChangePassword changePassword(@RequestBody ReqChangePassword req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isEmpty(req.getRisk_item())) {
            req.setRisk_item(GenRiskItemHelper.randomRiskItem());
        }
        if (isEmpty(req.getRisk_item())) {
            req.setRisk_item(GenRiskItemHelper.randomRiskItem());
        }
        ResChangePassword res = this.manageClient.changePassword(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_CHANGE_PASSWORD, "3.11. 重置密码", req, res, sourceIp));
        return res;
    }

    /**
     * 3.12. 找回密码 3.12.1.  申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/find-password-apply
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_FIND_PASSWORD_APPLY
     *
     * @param req ReqFindPasswordApply实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqFindPasswordApply
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResFindPasswordApply
     */
    @ApiOperation(value = "3.12. 找回密码 3.12.1.  申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/find-password-apply",
            response = ResFindPasswordApply.class)
    @ApiImplicitParam(dataTypeClass = ReqFindPasswordApply.class)
    @PostMapping("findPasswordApply")
    public ResFindPasswordApply findPasswordApply(@RequestBody ReqFindPasswordApply req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        if (isEmpty(req.getRisk_item())) {
            req.setRisk_item(GenRiskItemHelper.randomRiskItem());
        }
        ResFindPasswordApply res = this.manageClient.findPasswordApply(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_FIND_PASSWORD_APPLY, "3.12.1.  申请找回密码", req, res, sourceIp));
        return res;
    }

    /**
     * 3.12.2.  验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/find-password-verify
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_FIND_PASSWORD_VERIFY
     *
     * @param req ReqFindPasswordVerify实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqFindPasswordVerify
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResFindPasswordVerify
     */
    @ApiOperation(value = "3.12.2.  验证 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/find-password-verify",
            response = ResFindPasswordVerify.class)
    @ApiImplicitParam(dataTypeClass = ReqFindPasswordVerify.class)
    @PostMapping("findPasswordVerify")
    public ResFindPasswordVerify findPasswordVerify(@RequestBody ReqFindPasswordVerify req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResFindPasswordVerify res = this.manageClient.findPasswordVerify(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_FIND_PASSWORD_VERIFY, "3.12.2.  验证 ", req, res, sourceIp));
        return res;
    }

    /**
     * 3.13. 随机因子获取 3.13.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/get-random
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_GET_RANDOM
     *
     * @param req ReqGetRandom实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqGetRandom
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResGetRandom
     */
    @ApiOperation(value = "3.13. 随机因子获取 3.13.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/get-random",
            response = ResGetRandom.class)
    @ApiImplicitParam(dataTypeClass = ReqGetRandom.class)
    @PostMapping("getRandom/white")
    public ResGetRandom getRandom(@RequestBody ReqGetRandom req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResGetRandom res = this.manageClient.getRandom(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_GET_RANDOM, "3.13.1. 随机因子获取", req, res, sourceIp));
        return res;
    }

    /**
     * 3.14. 绑卡信息查询 3.14.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-linkedacct
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_QUERY_LINKEDACCT
     *
     * @param req ReqQueryLinkedacct实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqQueryLinkedacct
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResQueryLinkedacct
     */
    @ApiOperation(value = "3.14. 绑卡信息查询 3.14.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-linkedacct",
            response = ResQueryLinkedacct.class)
    @ApiImplicitParam(dataTypeClass = ReqQueryLinkedacct.class)
    @PostMapping("queryLinkedacct")
    public ResQueryLinkedacct queryLinkedacct(@RequestBody ReqQueryLinkedacct req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResQueryLinkedacct res = this.manageClient.queryLinkedacct(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_QUERY_LINKEDACCT, "3.14.1. 绑卡信息查询", req, res, sourceIp));
        return res;
    }

    /**
     * 3.15. 用户信息查询 3.15.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-userinfo
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_QUERY_USERINFO
     *
     * @param req ReqQueryUserinfo实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqQueryUserinfo
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResQueryUserinfo
     */
    @ApiOperation(value = "3.15. 用户信息查询 3.15.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-userinfo",
            response = ResQueryUserinfo.class)
    @ApiImplicitParam(dataTypeClass = ReqQueryUserinfo.class)
    @PostMapping("queryUserinfo")
    public ResQueryUserinfo queryUserinfo(@RequestBody ReqQueryUserinfo req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResQueryUserinfo res = this.manageClient.queryUserinfo(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_QUERY_USERINFO, "3.15.1.1 用户信息查询", req, res, sourceIp));
        return res;
    }

    /**
     * 3.16. 账户信息查询 3.16.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-acctinfo
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_QUERY_ACCTINFO
     *
     * @param req ReqQueryAcctinfo实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqQueryAcctinfo
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResQueryAcctinfo
     */
    @ApiOperation(value = "3.16. 账户信息查询 3.16.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-acctinfo",
            response = ResQueryAcctinfo.class)
    @ApiImplicitParam(dataTypeClass = ReqQueryAcctinfo.class)
    @PostMapping("queryAcctinfo/white")
    public ResQueryAcctinfo queryAcctinfo(@RequestBody ReqQueryAcctinfo req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResQueryAcctinfo res = this.manageClient.queryAcctinfo(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_QUERY_ACCTINFO, "3.16.1. 账户信息查询", req, res, sourceIp));
        return res;
    }

    /**
     * 3.17. 资金流水查询 3.17.1.  资金流水列表查询 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-acctserial
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_QUERY_ACCTSERIAL
     *
     * @param req ReqQueryAcctserial实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqQueryAcctserial
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResQueryAcctserial
     */
    @ApiOperation(value = "3.17. 资金流水查询 3.17.1.  资金流水列表查询 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-acctserial",
            response = ResQueryAcctserial.class)
    @ApiImplicitParam(dataTypeClass = ReqQueryAcctserial.class)
    @PostMapping("queryAcctserial")
    public ResQueryAcctserial queryAcctserial(@RequestBody ReqQueryAcctserial req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResQueryAcctserial res = this.manageClient.queryAcctserial(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_QUERY_ACCTSERIAL, "3.17. 资金流水查询", req, res, sourceIp));
        return res;
    }

    /**
     * 3.17.2.  资金流水详情查询 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-acctserialdetail
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_QUERY_ACCTSERIALDETAIL
     *
     * @param req ReqQueryAcctserialdetail实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqQueryAcctserialdetail
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResQueryAcctserialdetail
     */
    @ApiOperation(value = "3.17.2.  资金流水详情查询 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-acctserialdetail",
            response = ResQueryAcctserialdetail.class)
    @ApiImplicitParam(dataTypeClass = ReqQueryAcctserialdetail.class)
    @PostMapping("queryAcctserialdetail")
    public ResQueryAcctserialdetail queryAcctserialdetail(@RequestBody ReqQueryAcctserialdetail req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResQueryAcctserialdetail res = this.manageClient.queryAcctserialdetail(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_QUERY_ACCTSERIALDETAIL, "3.17.2. 资金流水详情查询", req, res, sourceIp));
        return res;
    }

    /**
     * 3.18. 交易流水结果查询 3.18.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-txn
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_QUERY_TXN
     *
     * @param req ReqQueryTxn实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqQueryTxn
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResQueryTxn
     */
    @ApiOperation(value = "3.18. 交易流水结果查询 3.18.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-txn",
            response = ResQueryTxn.class)
    @ApiImplicitParam(dataTypeClass = ReqQueryTxn.class)
    @PostMapping("queryTxn")
    public ResQueryTxn queryTxn(@RequestBody ReqQueryTxn req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResQueryTxn res = this.manageClient.queryTxn(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_QUERY_TXN, "3.18.1. 交易流水结果查询", req, res, sourceIp));
        return res;
    }

    /**
     * 3.19. 大额行号查询 3.19.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-cnapscode
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_QUERY_CNAPSCODE
     *
     * @param req ReqQueryCnapscode实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqQueryCnapscode
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResQueryCnapscode
     */
    @ApiOperation(value = "3.19. 大额行号查询 3.19.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/query-cnapscode",
            response = ResQueryCnapscode.class)
    @ApiImplicitParam(dataTypeClass = ReqQueryCnapscode.class)
    @PostMapping("queryCnapscode")
    public ResQueryCnapscode queryCnapscode(@RequestBody ReqQueryCnapscode req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResQueryCnapscode res = this.manageClient.queryCnapscode(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_QUERY_CNAPSCODE, "3.19.1. 大额行号查询", req, res, sourceIp));
        return res;
    }

    /**
     * 3.20. 上传照片 该接口用于上传个人用户的身份证照片及企业用户的统一社会信用代码证照片和企业法人 身份证照片。 3.20.1.  请求地址https://accpapi.lianlianpay.com/v1/acctmgr/upload-photos
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_UPLOAD_PHOTOS
     *
     * @param req ReqUploadPhotos实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqUploadPhotos
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResUploadPhotos
     */
    @ApiOperation(value = "3.20. 上传照片 该接口用于上传个人用户的身份证照片及企业用户的统一社会信用代码证照片和企业法人 身份证照片。 3.20.1.  " +
            "请求地址https://accpapi.lianlianpay.com/v1/acctmgr/upload-photos",
            response = ResUploadPhotos.class)
    @ApiImplicitParam(dataTypeClass = ReqUploadPhotos.class)
    @PostMapping("uploadPhotos")
    public ResUploadPhotos uploadPhotos(@RequestBody ReqUploadPhotos req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResUploadPhotos res = this.manageClient.uploadPhotos(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_UPLOAD_PHOTOS, "3.20.1. 上传照片 该接口用于上传个人用户的身份证照片及企业用户的统一社会信用代码证照片和企业法人身份证照片。", req, res, sourceIp));
        return res;
    }

    /**
     * 3.21. 支付密码校验 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/validate-password
     * 接口类型：非异步通知接口
     * 地址常量：AccpConstants.URL_VALIDATE_PASSWORD
     *
     * @param req ReqValidatePassword实例
     * @return ResponseEntity实例
     * @see AccpPayConfig
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqValidatePassword
     * @see com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResValidatePassword
     */
    @ApiOperation(value = "3.21. 支付密码校验 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/validate-password",
            response = ResValidatePassword.class)
    @ApiImplicitParam(dataTypeClass = ReqValidatePassword.class)
    @PostMapping("validatePassword")
    public ResValidatePassword validatePassword(@RequestBody ReqValidatePassword req, @RequestHeader("X-Real-IP") String sourceIp) throws AccpReqParamException, AccpPayException {
        req.setTimestamp(new Date());
        if (isBlank(req.getOid_partner())) {
            req.setOid_partner(this.payConfig.getOidPartner());
        }
        ResValidatePassword res = this.manageClient.validatePassword(this.payConfig, req);

        this.asyncMongoService.save(new AccpPayRecordModel(AccpConstants.URL_VALIDATE_PASSWORD, "3.21. 支付密码校验", req, res, sourceIp));
        return res;
    }

}