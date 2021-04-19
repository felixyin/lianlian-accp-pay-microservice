package com.qdqtrj.lianlian.accp.util.accp;

import com.qdqtrj.lianlian.accp.config.AccpConstants;
import com.qdqtrj.lianlian.accp.exception.AccpPayException;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA签名公共类
 */
final class RsaUtils {

    private static RsaUtils instance;

    private RsaUtils() {

    }

    public static RsaUtils getInstance() {
        if (null == instance) {
            return new RsaUtils();
        }
        return instance;
    }

    /**
     * 签名处理
     *
     * @param prikeyvalue ：私钥文件
     * @param signStr     ：签名源内容
     */
    public String sign(String prikeyvalue, String signStr) throws AccpPayException {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Utils.getBytesBASE64(prikeyvalue));
            KeyFactory keyf = KeyFactory.getInstance(AccpConstants.RSA);
            PrivateKey myprikey = keyf.generatePrivate(priPKCS8);
            // 用私钥对信息生成数字签名
            java.security.Signature signet = java.security.Signature.getInstance("MD5withRSA");
            signet.initSign(myprikey);
            signet.update(signStr.getBytes(StandardCharsets.UTF_8));
            // 对信息的数字签名
            byte[] signed = signet.sign();
            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(signed));
        } catch (Exception e) {
            throw new AccpPayException(e);
        }
    }

    /**
     * 签名验证
     *
     * @param pubkeyvalue ：公钥
     * @param oidStr      ：源串
     * @param signedStr   ：签名结果串
     */
    public boolean checksign(String pubkeyvalue, String oidStr, String signedStr) throws AccpPayException {
        try {
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64Utils.getBytesBASE64(pubkeyvalue));
            KeyFactory keyFactory = KeyFactory.getInstance(AccpConstants.RSA);
            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
            // 这是SignatureData输出的数字签名
            byte[] signed = Base64Utils.getBytesBASE64(signedStr);
            java.security.Signature signetcheck = java.security.Signature.getInstance("MD5withRSA");
            signetcheck.initVerify(pubKey);
            signetcheck.update(oidStr.getBytes(StandardCharsets.UTF_8));
            return signetcheck.verify(signed);
        } catch (Exception e) {
            throw new AccpPayException(e);
        }
    }

    /**
     * 公钥加密的方法
     */
    public String encrypt(String source, String publicKey) throws AccpPayException {
        try {
            //注不要使用.getDecoder();
            java.util.Base64.Decoder decoder = java.util.Base64.getMimeDecoder();
            byte[] keyByte = decoder.decode(publicKey);
            X509EncodedKeySpec x509ek = new X509EncodedKeySpec(keyByte);
            KeyFactory keyFactory = KeyFactory.getInstance(AccpConstants.RSA);
            PublicKey newPublicKey = keyFactory.generatePublic(x509ek);

            Cipher cipher = Cipher.getInstance(AccpConstants.RSA);
            cipher.init(Cipher.ENCRYPT_MODE, newPublicKey);
            // 中文情况下 linux 环境要使用这个
            byte[] sbt = source.getBytes(StandardCharsets.UTF_8);
            byte[] epByte = cipher.doFinal(sbt);
            //注不要使用.getDecoder();
            java.util.Base64.Encoder encoder = java.util.Base64.getMimeEncoder();
            byte[] epStr = encoder.encode(epByte);
            return new String(epStr);
        } catch (Exception e) {
            throw new AccpPayException(e);
        }
    }

    /**
     * 私钥解密的方法
     */
    public String decrypt(String cryptograph, String privateKey) throws AccpPayException {
        try {
            //注不要使用.getDecoder();
            java.util.Base64.Decoder decoder = java.util.Base64.getMimeDecoder();
            byte[] keyByte = decoder.decode(privateKey);

            PKCS8EncodedKeySpec s8ek = new PKCS8EncodedKeySpec(keyByte);
            KeyFactory keyFactory = KeyFactory.getInstance(AccpConstants.RSA);
            PrivateKey newPrivateKey = keyFactory.generatePrivate(s8ek);

            //      得到Cipher对象对已用公钥加密的数据进行RSA解密
            Cipher cipher = Cipher.getInstance(AccpConstants.RSA);
            cipher.init(Cipher.DECRYPT_MODE, newPrivateKey);
            //注不要使用.getDecoder();
            java.util.Base64.Encoder encoder = java.util.Base64.getMimeEncoder();
            byte[] b1 = encoder.encode(cryptograph.getBytes());
            //      执行解密操作
            byte[] b = cipher.doFinal(b1);
            return new String(b);
        } catch (Exception e) {
            throw new AccpPayException(e);
        }
    }
}
