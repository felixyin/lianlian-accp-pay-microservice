package com.qdqtrj.lianlian.accp.notify;

import com.qdqtrj.lianlian.accp.pojo.manage.dto.callback.*;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.callback.NotifyPaymentPapay;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.callback.NotifyQueryRefund;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.callback.NotifyWithdrawalCheck;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 */
@Slf4j
@Component
public class NotifyBusinessServerHystrix implements FallbackFactory<INotifyBusinessServiceClient> {

    @Override
    public INotifyBusinessServiceClient create(Throwable cause) {

        String msg = cause == null ? "" : cause.getMessage();

        return new INotifyBusinessServiceClient() {

            @Override
            public boolean openacctVerifyIndividual(NotifyOpenacctVerifyIndividual req) {
                log.error(msg);
                return false;
            }

            @Override
            public boolean openacctApplyEnterprise(NotifyOpenacctApplyEnterprise req) {
                log.error(msg);
                return false;
            }

            @Override
            public boolean individualBindcardVerify(NotifyIndividualBindcardVerify req) {
                log.error(msg);
                return false;
            }

            @Override
            public boolean enterpriseChangecardApply(NotifyEnterpriseChangecardApply req) {
                log.error(msg);
                return false;
            }

            @Override
            public boolean unlinkedacctIndApply(NotifyUnlinkedacctIndApply req) {
                log.error(msg);
                return false;
            }

            @Override
            public boolean changeLinkedphoneVerify(NotifyChangeLinkedphoneVerify req) {
                log.error(msg);
                return false;
            }

            @Override
            public boolean changeRegphoneVerify(NotifyChangeRegphoneVerify req) {
                log.error(msg);
                return false;
            }

            @Override
            public boolean cancelApply(NotifyCancelApply req) {
                log.error(msg);
                return false;
            }

            @Override
            public boolean paymentPapay(NotifyPaymentPapay req) {
                log.error(msg);
                return false;
            }

            @Override
            public boolean withdrawalCheck(NotifyWithdrawalCheck req) {
                log.error(msg);
                return false;
            }

            @Override
            public boolean queryRefund(NotifyQueryRefund req) {
                log.error(msg);
                return false;
            }

        };
    }
}
