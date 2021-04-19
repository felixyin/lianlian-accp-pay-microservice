package com.qdqtrj.lianlian.accp.client.impl;

import com.qdqtrj.lianlian.accp.client.IPayClient;
import com.qdqtrj.lianlian.accp.pojo.manage.dto.request.*;
import com.qdqtrj.lianlian.accp.pojo.manage.dto.response.*;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.request.*;
import com.qdqtrj.lianlian.accp.pojo.trade.dto.response.*;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 服务降级
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Slf4j
@Component
public class PayServerHystrix implements FallbackFactory<IPayClient> {


    /**
     * @param cause
     * @return
     */
    @Override
    public IPayClient create(Throwable cause) {

        String msg = cause == null ? "" : cause.getMessage();

        return new PayClient(msg);
    }

    private static class PayClient implements IPayClient {

        private final String msg;

        PayClient(String msg) {
            this.msg = msg;
        }

        @Override
        public ResRegphoneVerifycodeApply regphoneVerifycodeApply(ReqRegphoneVerifycodeApply req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResRegphoneVerifycodeVerify regphoneVerifycodeVerify(ReqRegphoneVerifycodeVerify req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResOpenacctApplyIndividual openacctApplyIndividual(ReqOpenacctApplyIndividual req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResOpenacctVerifyIndividual openacctVerifyIndividual(ReqOpenacctVerifyIndividual req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResOpenacctApplyEnterprise openacctApplyEnterprise(ReqOpenacctApplyEnterprise req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResIndividualBindcardApply individualBindcardApply(ReqIndividualBindcardApply req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResIndividualBindcardVerify individualBindcardVerify(ReqIndividualBindcardVerify req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResEnterpriseChangecardApply enterpriseChangecardApply(ReqEnterpriseChangecardApply req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResUnlinkedacctIndApply unlinkedacctIndApply(ReqUnlinkedacctIndApply req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResChangeLinkedphoneApply changeLinkedphoneApply(ReqChangeLinkedphoneApply req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResChangeLinkedphoneVerify changeLinkedphoneVerify(ReqChangeLinkedphoneVerify req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResChangeRegphoneApply changeRegphoneApply(ReqChangeRegphoneApply req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResChangeRegphoneVerify changeRegphoneVerify(ReqChangeRegphoneVerify req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResCancelApply cancelApply(ReqCancelApply req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResChangePassword changePassword(ReqChangePassword req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResFindPasswordApply findPasswordApply(ReqFindPasswordApply req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResFindPasswordVerify findPasswordVerify(ReqFindPasswordVerify req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResGetRandom getRandom(ReqGetRandom req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResQueryLinkedacct queryLinkedacct(ReqQueryLinkedacct req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResQueryUserinfo queryUserinfo(ReqQueryUserinfo req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResQueryAcctinfo queryAcctinfo(ReqQueryAcctinfo req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResQueryAcctserial queryAcctserial(ReqQueryAcctserial req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResQueryAcctserialdetail queryAcctserialdetail(ReqQueryAcctserialdetail req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResQueryTxn queryTxn(ReqQueryTxn req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResQueryCnapscode queryCnapscode(ReqQueryCnapscode req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResUploadPhotos uploadPhotos(ReqUploadPhotos req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResValidatePassword validatePassword(ReqValidatePassword req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResTradecreate tradecreate(ReqTradecreate req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResPaymentBalance paymentBalance(ReqPaymentBalance req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResPaymentBankcard paymentBankcard(ReqPaymentBankcard req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResPaymentGw paymentGw(ReqPaymentGw req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResPaymentPapay paymentPapay(ReqPaymentPapay req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResQueryPayment queryPayment(ReqQueryPayment req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResWithdrawal withdrawal(ReqWithdrawal req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResWithdrawalCheck withdrawalCheck(ReqWithdrawalCheck req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResQueryWithdrawal queryWithdrawal(ReqQueryWithdrawal req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResTransfer transfer(ReqTransfer req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResSecuredConfirm securedConfirm(ReqSecuredConfirm req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResSecuredQuery securedQuery(ReqSecuredQuery req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResValidationSms validationSms(ReqValidationSms req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResAsynRefund asynRefund(ReqAsynRefund req) {
            log.error(msg);
            return null;
        }

        @Override
        public ResQueryRefund queryRefund(ReqQueryRefund req) {
            log.error(msg);
            return null;
        }

    }
}