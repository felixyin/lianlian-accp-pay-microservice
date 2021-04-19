package com.qdqtrj.lianlian.accp.action;

import com.google.common.collect.Maps;
import com.qdqtrj.lianlian.accp.pojo.AccpPayRecordModel;
import com.qdqtrj.lianlian.accp.service.AsyncMongoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 提供全面的数据查询接口和统计分析接口 但这写数据是从mongo库中查询的，并不访问Accp支付平台
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */

@Controller
@RequestMapping("/paySelectAction")
@Api(value = "PaySelectAction", tags = "支付微服务《大数据分析》API接口")
public class PaySelectAction {

    /**
     * todo 1. 先提供所有accp"操作"的历史记录查询功能，参数要灵活
     */
    @Resource
    private AsyncMongoService asyncMongoService;

    @RequestMapping("/")
    public String main() {
        return "main";
    }

    /**
     * 日志查询
     *
     * @param queryStr
     * @return
     */
    @GetMapping(value = "/search/white")
    @ResponseBody
    @ApiOperation(value = "支付报文查询", response = Map.class)
    @ApiImplicitParam(value = "模糊查询参数", dataTypeClass = String.class)
    public Map<String, Object> queryPayChain(@RequestParam("queryStr") String queryStr) {
        Map<String, Object> map = Maps.newHashMap();
        if (StringUtils.isBlank(queryStr)) {
            return map;
        }
        List<AccpPayRecordModel> payRecordModels = this.asyncMongoService.search(queryStr);
        map.put("data", payRecordModels);
        return map;
    }

    // todo 2. 针对业务系统更加关心的资金流水、对账单等功能，设计接口

}
