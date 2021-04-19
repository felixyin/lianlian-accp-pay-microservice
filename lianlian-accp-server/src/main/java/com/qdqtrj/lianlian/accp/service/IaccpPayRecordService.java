package com.qdqtrj.lianlian.accp.service;

import com.qdqtrj.lianlian.accp.pojo.AccpPayRecordModel;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public interface IaccpPayRecordService extends IBaseService<AccpPayRecordModel, ObjectId> {

    AccpPayRecordModel findFirstByTxnSeqno(String txnSeqno);

    List<AccpPayRecordModel> search(String queryStr);

}
