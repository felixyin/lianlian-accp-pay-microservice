package com.qdqtrj.order.action;

import com.alibaba.fastjson.JSON;
import com.qdqtrj.lianlian.accp.client.IPayClient;
import com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqGetRandom;
import com.qdqtrj.lianlian.accp.pojo.manage.dto.response.ResGetRandom;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 调用支付接口例子
 *
 * @author yinbin
 * @version 1.0
 */
@RequestMapping("/order")
public class OrderAction {

    @Resource
    private IPayClient payClient;

    /**
     * 测试：调用支付服务随机因子接口
     *
     * @return
     */
    @PostMapping("test")
    public String test() {
        ReqGetRandom req = new ReqGetRandom();
        req.setOid_partner("");
        //        req.set
        ResGetRandom random = payClient.getRandom(req);
        return JSON.toJSONString(random);
    }
}
