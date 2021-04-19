package com.qdqtrj.lianlian.accp.util.accp;

import com.qdqtrj.lianlian.accp.config.AccpConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * 线程安全的HttpCLient,单例模式，支持http、https协议
 *
 * @author yinbin
 */
@SuppressWarnings("deprecation")
@Slf4j
public final class AccpPayHttpClient {
    private static HttpClient customHttpClient;

    private AccpPayHttpClient() {
    }

    /**
     * @return
     */
    public static synchronized HttpClient getInstance() {
        if (customHttpClient == null) {
            return httpClientInstance(AccpConstants.DEFAULT_REQ_TIMEOUT_SECOND * AccpConstants.MS);
        }
        return customHttpClient;
    }

    /**
     * @param reqTimeout
     * @return
     */
    public static synchronized HttpClient getInstance(Integer reqTimeout) {
        int newReqTimeout = AccpConstants.DEFAULT_REQ_TIMEOUT_SECOND * AccpConstants.MS;
        if (null != reqTimeout && 0 != reqTimeout) {
            newReqTimeout = reqTimeout * AccpConstants.MS;
        }
        return httpClientInstance(newReqTimeout);
    }

    private static HttpClient httpClientInstance(int reqTimeout) {

        KeyStore trustStore;
        SSLSocketFactory sslSocketFactory = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            sslSocketFactory = new MySSLSocketFactory(trustStore);
            sslSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (KeyManagementException | NoSuchAlgorithmException | UnrecoverableKeyException
                | IOException | CertificateException | KeyStoreException e) {
            log.error("accp->从SSLSocketFactory获取连接失败：{}", e.getMessage());
        }
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
        ConnManagerParams.setTimeout(params, reqTimeout);
        HttpConnectionParams.setConnectionTimeout(params, reqTimeout);
        HttpConnectionParams.setSoTimeout(params, reqTimeout);
        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), AccpConstants.HTTP_PORT));
        assert sslSocketFactory != null;
        schReg.register(new Scheme("https", sslSocketFactory, AccpConstants.HTTPS_PORT));

        ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
                params, schReg);
        customHttpClient = new DefaultHttpClient(conMgr, params);
        return customHttpClient;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

}
