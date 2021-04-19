package com.qdqtrj.lianlian.accp.service.impl;

import com.qdqtrj.lianlian.accp.pojo.AccpPayRecordModel;
import com.qdqtrj.lianlian.accp.repo.IAccpPayRecordRepo;
import com.qdqtrj.lianlian.accp.service.IaccpPayRecordService;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Service
public class AccpPayRecordServiceImpl extends BaseServiceImpl<AccpPayRecordModel, ObjectId> implements IaccpPayRecordService {

    @Resource
    private IAccpPayRecordRepo accpPayRecordRepo;

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * @param txnSeqno
     * @return
     */
    @Override
    public AccpPayRecordModel findFirstByTxnSeqno(String txnSeqno) {
        List<AccpPayRecordModel> recordModelList = this.accpPayRecordRepo.findFirstByTxnSeqno(txnSeqno);
        // 理论只要进入此方法，有且只有一条数据返回，so，如果没有直接则抛异常上去
        return recordModelList.get(0);
    }

    @Override
    public List<AccpPayRecordModel> search(String queryStr) {
        Criteria criteria = new Criteria();
        criteria.orOperator(
                Criteria.where("req.basicInfo.reg_email").regex(".*" + queryStr + ".*"),
                Criteria.where("req.linked_bankcode").regex(".*" + queryStr + ".*"),
                Criteria.where("res.refundInfo.refund_seqno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.new_linked_phone").regex(".*" + queryStr + ".*"),
                Criteria.where("res.payerInfo.payer_type").regex(".*" + queryStr + ".*"),
                Criteria.where("req.user_id").regex(".*" + queryStr + ".*"),
                Criteria.where("req.wechatInfo.body").regex(".*" + queryStr + ".*"),
                Criteria.where("req.linkedAcctInfo.linked_acctname").regex(".*" + queryStr + ".*"),
                Criteria.where("req.bankCardInfo.id_no").regex(".*" + queryStr + ".*"),
                Criteria.where("req.businessInfo.registered_capital").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payeeInfo.bank_acctname").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payeeInfo.payee_memo").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payeeInfo.payee_type").regex(".*" + queryStr + ".*"),
                Criteria.where("req.refund_seqno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.user_type").regex(".*" + queryStr + ".*"),
                Criteria.where("req.linked_brbankname").regex(".*" + queryStr + ".*"),
                Criteria.where("req.ANDROID").regex(".*" + queryStr + ".*"),
                Criteria.where("req.extend_params").regex(".*" + queryStr + ".*"),
                Criteria.where("req.txn_type").regex(".*" + queryStr + ".*"),
                Criteria.where("res.amt_bal").regex(".*" + queryStr + ".*"),
                Criteria.where("req.id_filetype").regex(".*" + queryStr + ".*"),
                Criteria.where("req.basicInfo.id_authority").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payeeInfo.bank_code").regex(".*" + queryStr + ".*"),
                Criteria.where("req.basicInfo.id_type").regex(".*" + queryStr + ".*"),
                Criteria.where("res.jno_acct").regex(".*" + queryStr + ".*"),
                Criteria.where("req.orderInfo.txn_purpose").regex(".*" + queryStr + ".*"),
                Criteria.where("res.amt").regex(".*" + queryStr + ".*"),
                Criteria.where("req.bankcode").regex(".*" + queryStr + ".*"),
                Criteria.where("req.confirmOrderInfo.confirm_seqno").regex(".*" + queryStr + ".*"),
                Criteria.where("res.failure_reason").regex(".*" + queryStr + ".*"),
                Criteria.where("req.wechatInfo.detail").regex(".*" + queryStr + ".*"),
                Criteria.where("res.date_acct").regex(".*" + queryStr + ".*"),
                Criteria.where("req.reg_phone").regex(".*" + queryStr + ".*"),
                Criteria.where("req.orderInfo.txn_time").regex(".*" + queryStr + ".*"),
                Criteria.where("req.pay_time_type").regex(".*" + queryStr + ".*"),
                Criteria.where("req.appid").regex(".*" + queryStr + ".*"),
                Criteria.where("res.orderInfo.txn_time").regex(".*" + queryStr + ".*"),
                Criteria.where("res.map_arr").regex(".*" + queryStr + ".*"),
                Criteria.where("req.businessInfo.scale").regex(".*" + queryStr + ".*"),
                Criteria.where("req.device_info").regex(".*" + queryStr + ".*"),
                Criteria.where("req.basicInfo.id_exp").regex(".*" + queryStr + ".*"),
                Criteria.where("res.bank_account").regex(".*" + queryStr + ".*"),
                Criteria.where("res.bankcode").regex(".*" + queryStr + ".*"),
                Criteria.where("req.refundOrderInfo.payee_type").regex(".*" + queryStr + ".*"),
                Criteria.where("req.basicAcctInfo.basicacct_no").regex(".*" + queryStr + ".*"),
                Criteria.where("res.payInfo.accp_txno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.refundMethods.method").regex(".*" + queryStr + ".*"),
                Criteria.where("req.linkedAcctInfo.linked_brbankname").regex(".*" + queryStr + ".*"),
                Criteria.where("req.bankCardInfo.linked_phone").regex(".*" + queryStr + ".*"),
                Criteria.where("req.orderInfo.order_info").regex(".*" + queryStr + ".*"),
                Criteria.where("req.bankCardInfo.id_type").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payer_type").regex(".*" + queryStr + ".*"),
                Criteria.where("req.id_no").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payerInfo.random_key").regex(".*" + queryStr + ".*"),
                Criteria.where("req.H5").regex(".*" + queryStr + ".*"),
                Criteria.where("req.jno_acct").regex(".*" + queryStr + ".*"),
                Criteria.where("req.confirmOrderInfo.payee_id").regex(".*" + queryStr + ".*"),
                Criteria.where("req.legalreptInfo.legalrept_name").regex(".*" + queryStr + ".*"),
                Criteria.where("req.refundOrderInfo.payee_accttype").regex(".*" + queryStr + ".*"),
                Criteria.where("req.device_version").regex(".*" + queryStr + ".*"),
                Criteria.where("res.other_acct").regex(".*" + queryStr + ".*"),
                Criteria.where("req.basicInfo.id_no").regex(".*" + queryStr + ".*"),
                Criteria.where("res.orderInfo.order_info").regex(".*" + queryStr + ".*"),
                Criteria.where("req.refund_reason").regex(".*" + queryStr + ".*"),
                Criteria.where("req.refundOrderInfo.refund_time").regex(".*" + queryStr + ".*"),
                Criteria.where("res.bank_code").regex(".*" + queryStr + ".*"),
                Criteria.where("req.confirmOrderInfo.confirm_time").regex(".*" + queryStr + ".*"),
                Criteria.where("req.id_type").regex(".*" + queryStr + ".*"),
                Criteria.where("req.contactsInfo.contacts_phone").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payeeInfo.bank_acctno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.acct_type").regex(".*" + queryStr + ".*"),
                Criteria.where("res.orderInfo.secure_status").regex(".*" + queryStr + ".*"),
                Criteria.where("req.app_name").regex(".*" + queryStr + ".*"),
                Criteria.where("res.user_status").regex(".*" + queryStr + ".*"),
                Criteria.where("req.businessInfo.business_scope").regex(".*" + queryStr + ".*"),
                Criteria.where("res.chnl_txno").regex(".*" + queryStr + ".*"),
                Criteria.where("res.txn_time").regex(".*" + queryStr + ".*"),
                Criteria.where("req.basicInfo.occupation").regex(".*" + queryStr + ".*"),
                Criteria.where("req.unified_code").regex(".*" + queryStr + ".*"),
                Criteria.where("req.flag_chnl").regex(".*" + queryStr + ".*"),
                Criteria.where("res.txn_type").regex(".*" + queryStr + ".*"),
                Criteria.where("req.alipayInfo.extend_params").regex(".*" + queryStr + ".*"),
                Criteria.where("req.linked_acctname").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payerInfo.payer_type").regex(".*" + queryStr + ".*"),
                Criteria.where("req.linkedAcctInfo.linked_acctno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.confirmOrderInfo.payee_type").regex(".*" + queryStr + ".*"),
                Criteria.where("req.client_ip").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payeeInfo.cnaps_code").regex(".*" + queryStr + ".*"),
                Criteria.where("req.user_name").regex(".*" + queryStr + ".*"),
                Criteria.where("res.ret_msg").regex(".*" + queryStr + ".*"),
                Criteria.where("req.confirm_mode").regex(".*" + queryStr + ".*"),
                Criteria.where("req.open_sms_flag").regex(".*" + queryStr + ".*"),
                Criteria.where("req.originalOrderInfo.txn_seqno").regex(".*" + queryStr + ".*"),
                Criteria.where("res.total_in_amt").regex(".*" + queryStr + ".*"),
                Criteria.where("res.reg_phone").regex(".*" + queryStr + ".*"),
                Criteria.where("req.refundOrderInfo.payee_id").regex(".*" + queryStr + ".*"),
                Criteria.where("req.date_end").regex(".*" + queryStr + ".*"),
                Criteria.where("req.date_start").regex(".*" + queryStr + ".*"),
                Criteria.where("req.legalreptInfo.legalrept_idno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.id_emblem").regex(".*" + queryStr + ".*"),
                Criteria.where("req.linked_phone").regex(".*" + queryStr + ".*"),
                Criteria.where("res.txn_seqno").regex(".*" + queryStr + ".*"),
                Criteria.where("res.license").regex(".*" + queryStr + ".*"),
                Criteria.where("res.status").regex(".*" + queryStr + ".*"),
                Criteria.where("req.PC").regex(".*" + queryStr + ".*"),
                Criteria.where("req.accp_txno").regex(".*" + queryStr + ".*"),
                Criteria.where("res.remark").regex(".*" + queryStr + ".*"),
                Criteria.where("req.alipayInfo.subject").regex(".*" + queryStr + ".*"),
                Criteria.where("req.openid").regex(".*" + queryStr + ".*"),
                Criteria.where("res.other_acct_name").regex(".*" + queryStr + ".*"),
                Criteria.where("res.acceptInfo.accp_txno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.brabank_name").regex(".*" + queryStr + ".*"),
                Criteria.where("req.legalreptInfo.legalrept_idexp").regex(".*" + queryStr + ".*"),
                Criteria.where("req.orderInfo.txn_seqno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.checkInfo.check_user").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payerInfo.contract_id").regex(".*" + queryStr + ".*"),
                Criteria.where("req.limit_card_type").regex(".*" + queryStr + ".*"),
                Criteria.where("req.linked_acctno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payerInfo.payer_accttype").regex(".*" + queryStr + ".*"),
                Criteria.where("req.sort_type").regex(".*" + queryStr + ".*"),
                Criteria.where("req.legalreptInfo.legalrept_phone").regex(".*" + queryStr + ".*"),
                Criteria.where("res.oid_userno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payerInfo.password").regex(".*" + queryStr + ".*"),
                Criteria.where("res.acceptInfo.txn_seqno").regex(".*" + queryStr + ".*"),
                Criteria.where("res.linked_agrtno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payeeInfo.payee_accttype").regex(".*" + queryStr + ".*"),
                Criteria.where("req.notify_url").regex(".*" + queryStr + ".*"),
                Criteria.where("req.basicInfo.reg_phone").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payerInfo.method").regex(".*" + queryStr + ".*"),
                Criteria.where("res.orderInfo.txn_seqno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.txn_seqno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.oid_partner").regex(".*" + queryStr + ".*"),
                Criteria.where("res.oid_partner").regex(".*" + queryStr + ".*"),
                Criteria.where("res.payerInfo.receive_amount").regex(".*" + queryStr + ".*"),
                Criteria.where("res.ret_code").regex(".*" + queryStr + ".*"),
                Criteria.where("req.linkedAcctInfo.linked_bankcode").regex(".*" + queryStr + ".*"),
                Criteria.where("req.orderInfo.goods_name").regex(".*" + queryStr + ".*"),
                Criteria.where("req.alipayInfo.seller_id").regex(".*" + queryStr + ".*"),
                Criteria.where("req.reg_phone_new").regex(".*" + queryStr + ".*"),
                Criteria.where("res.accp_txno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payerInfo.user_id").regex(".*" + queryStr + ".*"),
                Criteria.where("res.payload").regex(".*" + queryStr + ".*"),
                Criteria.where("res.total_out_amt").regex(".*" + queryStr + ".*"),
                Criteria.where("req.basicInfo.random_key").regex(".*" + queryStr + ".*"),
                Criteria.where("req.basicInfo.user_name").regex(".*" + queryStr + ".*"),
                Criteria.where("req.verify_code").regex(".*" + queryStr + ".*"),
                Criteria.where("req.city_code").regex(".*" + queryStr + ".*"),
                Criteria.where("req.basicInfo.password").regex(".*" + queryStr + ".*"),
                Criteria.where("req.bankCardInfo.linked_acctname").regex(".*" + queryStr + ".*"),
                Criteria.where("res.flag_dc").regex(".*" + queryStr + ".*"),
                Criteria.where("req.bankCardInfo.linked_agrtno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payerInfo.pap_agree_no").regex(".*" + queryStr + ".*"),
                Criteria.where("req.password").regex(".*" + queryStr + ".*"),
                Criteria.where("req.basicInfo.address").regex(".*" + queryStr + ".*"),
                Criteria.where("req.return_url").regex(".*" + queryStr + ".*"),
                Criteria.where("res.user_id").regex(".*" + queryStr + ".*"),
                Criteria.where("req.password_new").regex(".*" + queryStr + ".*"),
                Criteria.where("req.basicAcctInfo.basicacct_bankcode").regex(".*" + queryStr + ".*"),
                Criteria.where("req.bankCardInfo.linked_acctno").regex(".*" + queryStr + ".*"),
                Criteria.where("res.busi_type").regex(".*" + queryStr + ".*"),
                Criteria.where("req.linkedAcctInfo.linked_brbankno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payeeInfo.payee_id").regex(".*" + queryStr + ".*"),
                Criteria.where("req.bankCardInfo.valid_thru").regex(".*" + queryStr + ".*"),
                Criteria.where("req.pkg_name").regex(".*" + queryStr + ".*"),
                Criteria.where("req.coupon_amount").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payer_id").regex(".*" + queryStr + ".*"),
                Criteria.where("req.check_flag").regex(".*" + queryStr + ".*"),
                Criteria.where("res.payerInfo.payer_id").regex(".*" + queryStr + ".*"),
                Criteria.where("res.payerInfo.method").regex(".*" + queryStr + ".*"),
                Criteria.where("res.gateway_url").regex(".*" + queryStr + ".*"),
                Criteria.where("req.token").regex(".*" + queryStr + ".*"),
                Criteria.where("res.random_value").regex(".*" + queryStr + ".*"),
                Criteria.where("res.chnl_reason").regex(".*" + queryStr + ".*"),
                Criteria.where("req.IOS").regex(".*" + queryStr + ".*"),
                Criteria.where("req.checkInfo.check_result").regex(".*" + queryStr + ".*"),
                Criteria.where("req.linkedAcctInfo.linked_phone").regex(".*" + queryStr + ".*"),
                Criteria.where("req.refundOrderInfo.refund_seqno").regex(".*" + queryStr + ".*"),
                Criteria.where("res.random_key").regex(".*" + queryStr + ".*"),
                Criteria.where("req.contactsInfo.contacts_name").regex(".*" + queryStr + ".*"),
                Criteria.where("req.id_portrait").regex(".*" + queryStr + ".*"),
                Criteria.where("res.txn_status").regex(".*" + queryStr + ".*"),
                Criteria.where("req.bank_code").regex(".*" + queryStr + ".*"),
                Criteria.where("res.payInfo.txn_seqno").regex(".*" + queryStr + ".*"),
                Criteria.where("res.refundInfo.accp_txno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.unified_code_filetype").regex(".*" + queryStr + ".*"),
                Criteria.where("req.random_key").regex(".*" + queryStr + ".*"),
                Criteria.where("res.accounting_date").regex(".*" + queryStr + ".*"),
                Criteria.where("req.funds_flag").regex(".*" + queryStr + ".*"),
                Criteria.where("res.memo").regex(".*" + queryStr + ".*"),
                Criteria.where("req.checkInfo.check_reason").regex(".*" + queryStr + ".*"),
                Criteria.where("req.payerInfo.payer_id").regex(".*" + queryStr + ".*"),
                Criteria.where("req.orderInfo.goods_url").regex(".*" + queryStr + ".*"),
                Criteria.where("req.linked_brbankno").regex(".*" + queryStr + ".*"),
                Criteria.where("res.rsa_public_content").regex(".*" + queryStr + ".*"),
                Criteria.where("res.oid_acctno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.flag_dc").regex(".*" + queryStr + ".*"),
                Criteria.where("req.businessInfo.industry_code").regex(".*" + queryStr + ".*"),
                Criteria.where("req.orderInfo.postscript").regex(".*" + queryStr + ".*"),
                Criteria.where("req.linked_agrtno").regex(".*" + queryStr + ".*"),
                Criteria.where("req.txn_time").regex(".*" + queryStr + ".*"),
                Criteria.where("res.token").regex(".*" + queryStr + ".*"),
                Criteria.where("req.bankCardInfo.cvv2").regex(".*" + queryStr + ".*"),
                Criteria.where("req.verify_code_new").regex(".*" + queryStr + ".*"),
                Criteria.where("res.finish_time").regex(".*" + queryStr + ".*")
        );
        Query query = new Query(criteria).with(Sort.by(Sort.Order.desc("req.timestamp")));
        List<AccpPayRecordModel> accpPayRecordModels = this.mongoTemplate.find(query, AccpPayRecordModel.class);
        return accpPayRecordModels;
    }

}
