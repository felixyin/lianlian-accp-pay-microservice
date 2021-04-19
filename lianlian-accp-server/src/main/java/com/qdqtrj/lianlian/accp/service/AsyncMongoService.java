package com.qdqtrj.lianlian.accp.service;

import com.google.common.collect.Lists;
import com.qdqtrj.lianlian.accp.pojo.AccpPayRecordModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 访问mongo 异步处理，提高接口响应能力
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Service
@Slf4j
public class AsyncMongoService {

    @Resource
    private IaccpPayRecordService payRecordService;

    /**
     * req、res对象绑定在一起异步存储到mongodb
     *
     * @param record
     * @return
     */
    @Async
    public Future<AccpPayRecordModel> save(AccpPayRecordModel record) {
        return new AsyncResult<>(this.payRecordService.save(record));
    }

    /**
     * notify异步通知对象，和对应的req、res放在一起更新到mongodb
     *
     * @param notifyBean Notify开头类的实例
     * @return
     */
    @Async
    public Future<AccpPayRecordModel> update(Object notifyBean) {
        if (null == notifyBean) {
            return new AsyncResult<>(null);
        }

        try {
            // 1 从异步通知对象上获取流水号
            String txnSeqno = BeanUtils.getSimpleProperty(notifyBean, "txn_seqno");

            // 2 先根据回调中的流水号查询record
            AccpPayRecordModel recordModel = this.payRecordService.findFirstByTxnSeqno(txnSeqno);

            // 3 再设置notify document，更新
            recordModel.setNotify(notifyBean);
            this.payRecordService.update(recordModel.getId(), recordModel);

        } catch (Exception e) {
            log.info("accp->里需要处理NotifyBean上没有txn_seqno属性的情况，大概有两个bean：NotifyOfflineAddbill、NotifyQueryRefund");
        }
        // 现在用不到返回值
        return new AsyncResult<>(null);
    }

    /**
     * 查询支付链路
     *
     * @param accpPayRecordModel
     * @return
     */
    public List<AccpPayRecordModel> queryPayChain(AccpPayRecordModel accpPayRecordModel) {

        return Lists.newArrayList();
    }

    /**
     * 搜索支付日志
     *
     * @param queryStr
     * @return
     */
    public List<AccpPayRecordModel> search(String queryStr) {
        List<AccpPayRecordModel> models = this.payRecordService.search(queryStr);

        return models;
    }
}
