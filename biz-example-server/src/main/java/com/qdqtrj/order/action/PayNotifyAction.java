package com.qdqtrj.order.action;

import com.qdqtrj.lianlian.accp.pojo.manage.dto.callback.NotifyOpenacctVerifyIndividual;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 支付接口回调例子
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/4/19 9:22 上午
 */
@RequestMapping("/order/notifybiz")
public class PayNotifyAction {
    /**
     * 3.3. 个人用户开户 3.3.1. 开户申请 请求地址https://accpapi.lianlianpay.com/v1/acctmgr/openacct-apply-individual
     * 接口类型：异步通知接口 响应（返回） Success 字符串则视为成功，停止通知；否则Accp支付平台会按照通知策略发起重试，直到通知机制完成。
     * 地址常量：IAccpManagePayClient.URL_OPENACCT_APPLY_INDIVIDUAL
     *
     * @param req NotifyOpenacctVerifyIndividual
     * @see NotifyOpenacctVerifyIndividual
     */
    @PostMapping("/order/notifybiz/openacctVerifyIndividual")
    boolean openacctVerifyIndividual(@RequestBody NotifyOpenacctVerifyIndividual req) {
        String txn_seqno = req.getTxn_seqno();
        System.out.println(txn_seqno);
        // do something
        // true表示回调业务处理成功
        return true;
    }

    // 其他INotifyBusinessServiceClient中定义的接口

}
