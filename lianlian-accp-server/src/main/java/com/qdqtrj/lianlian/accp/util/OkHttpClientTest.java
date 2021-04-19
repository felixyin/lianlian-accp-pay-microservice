package com.qdqtrj.lianlian.accp.util;

import com.alibaba.fastjson.JSON;
import com.qdqtrj.lianlian.accp.config.AccpConstants;
import com.qdqtrj.lianlian.accp.config.AccpPayConfig;
import com.qdqtrj.lianlian.accp.exception.AccpPayException;
import com.qdqtrj.lianlian.accp.pojo.manage.dto.request.ReqGetRandom;
import com.qdqtrj.lianlian.accp.util.accp.SignatureUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

/**
 * @author yinbin
 * @version 1.0
 * @date 2021/3/15 4:45 下午
 */
@Slf4j
public final class OkHttpClientTest {
    public static final MediaType MEDIA_TYPE = MediaType.get("application/json; charset=utf-8");
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();

    private OkHttpClientTest() {
    }

    /**
     * 1. 连接池
     * 2. 数字签名加密
     * 3. 验签
     * 4. 性能
     *
     * @param accpPayConfig
     * @param apiPath
     * @param jsonData
     * @return
     * @throws Exception
     */
    static String post(AccpPayConfig accpPayConfig, String apiPath, String jsonData) throws AccpPayException, IOException {
        RequestBody body = RequestBody.create(MEDIA_TYPE, jsonData);
        String signatureData = SignatureUtils.getInstance().sign(accpPayConfig.getRsaPrivateKey(), jsonData);
        Request request = new Request.Builder()
                .header(AccpConstants.SIGNATURE_TYPE_UPPER, AccpConstants.RSA)
                .header(AccpConstants.SIGNATURE_DATA_UPPER, signatureData)
                .url(accpPayConfig.getPayDomainServerUrl() + apiPath)
                .post(body)
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            assert response.body() != null;
            String resStr = response.body().string();
            response.close();
            return resStr;
        }
    }

    public static void main(String[] args) throws IOException, AccpPayException {
        AccpPayConfig accpPayConfig = new AccpPayConfig();
        accpPayConfig.setOidPartner("2019082100299004");
        accpPayConfig.setPayDomainServerUrl("https://accptest.lianlianpay-inc.com/openapi/");
        accpPayConfig.setRsaPrivateKey("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAPD+malK2W3UJXfX5rO79gRUbLe+kwCskw7VzETXA4Qf/5VWlPxOb16SlflkE4zyInhGwehjUTvXPvebNtZJJpKS+Q/7oWw6hMQ1cIC99DWmV6Orjtz61Tmi5A/4QnYqUm2GRScfrnyILQw9/qikkvyjo0pPsIMT2rhmu31LSySNAgMBAAECgYEAokaubeKq2lu6ByLohCqTFINM2cWH8zJBrAGnFMu74GIzlfnBRMwEDiiiuFX9HDGHqHns5HDMKIFeMxjfKhgD0exp3S06xpSbmkIbvWLM+xBl70/+SLG7wztZ4KtdKu7PR26xJht6zM/KDrovuRzFYNB6ZbyO3My9CJXaZS6GU/kCQQD+/wsf0M7Byp+sPzy3SEn8katFopVOz8oESBBuSNNXl1rgyWfgVXBUKRDAus8oa/Nhx2zWNqpuchrHerPp5McHAkEA8fFyidW4nMkL3x4ULQmbsZBqsNEXoKv3fDDvHWRljX0AElel+XaVuxrtpYiDxwqFSM0s92nCBj2ZXt4O+d2eywJAS5mFzMr1YZMXP9QHxjcSaGUvqBeJuLH2LMrIxEmnDuL6uIY928643NrH8rvvywYmRCkB5YiTgucldVq1mHSRZQJAYny8+WrsqbYVhQ/DesnsfQ2iwLN9AMTAC+gHjlluFXiK7OyM/c3OCcpebwHxUrbvpsEOyvBcMRomMr4GLqSOnQJAcDKoXpkYFGakejn6LQj57EBtMgfVNatTipBnQxPaHMGGO9V9SzedbkgNg0NBSzsNsauKnFOy+yFwFqf6oGHm0A==");

        ReqGetRandom req = new ReqGetRandom();
        req.setOid_partner(accpPayConfig.getOidPartner());
        req.setApp_name("test");
        req.setFlag_chnl("ANDROID");
        req.setPkg_name("test");
        req.setUser_id("jkalsdfjlkasdf");

        String res = post(accpPayConfig, AccpConstants.URL_GET_RANDOM, JSON.toJSONString(req));

        log.info(res);
    }
}
