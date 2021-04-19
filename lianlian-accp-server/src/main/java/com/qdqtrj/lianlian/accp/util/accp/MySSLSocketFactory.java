package com.qdqtrj.lianlian.accp.util.accp;

/**
 *
 */

import org.apache.http.conn.ssl.SSLSocketFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * SSL全部信任登录
 *
 * @author accp
 *
 */
@SuppressWarnings("deprecation")
public class MySSLSocketFactory extends SSLSocketFactory {

    private final SSLContext sslContext = SSLContext.getInstance("TLS");


    public MySSLSocketFactory(KeyStore truststore)
            throws NoSuchAlgorithmException, KeyManagementException,
            KeyStoreException, UnrecoverableKeyException {

        super(truststore);

        TrustManager tm = new X509TrustManager() {

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

        };
        sslContext.init(null, new TrustManager[]{tm}, null);
    }

    /**
     *
     * @param socket
     * @param host
     * @param port
     * @param autoClose
     * @return
     * @throws IOException
     */
    @Override
    public Socket createSocket(Socket socket, String host, int port,
                               boolean autoClose) throws IOException {
        return sslContext.getSocketFactory().createSocket(socket, host, port,
                autoClose);
    }

    /**
     *
     * @return
     * @throws IOException
     */
    @Override
    public Socket createSocket() throws IOException {
        return sslContext.getSocketFactory().createSocket();
    }

}