package com.qdqtrj.lianlian.accp.util.accp;


import com.qdqtrj.lianlian.accp.exception.AccpPayException;

import java.nio.charset.StandardCharsets;

/**
 * RSA签名公共类
 *
 * @author accp
 */
public final class SignatureUtils {

    private static SignatureUtils instance;

    private SignatureUtils() {

    }

    public static SignatureUtils getInstance() {
        if (null == instance) {
            return new SignatureUtils();
        }
        return instance;
    }

   /* public static void main(String[] args) {
        String rsa_public = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqsEbJDbhe64JnkHNcpSljhkrE6rZekhE1cDklh/GLdoK4Gsd6/n/8icHNIIn6b8R3Ba+/3S5GxiuGfEc4rhBAffLKZPV2QNh0DafbWDHnu2S6PYGLrrWINawnKJJu5NLcLA0n5As8ZYmwCe5oO1rwCf2reoNWfd+K3b4LV/yTVwIDAQAB";
        String oid_str = "api_version=1.0&no_order=20170216200007&oid_plat=201704100000005002&sign_type=RSA";
        String signed_str = "oqqEKP2NMP15fPU1Z7ecOI5GXA213cx3ylTYkEQuLIF9tKkt6pRDNMxYmqiC1j96l0MJKBkIVYy/Nx86Il4mYiP/ZG+2buGLQ8sHhicz6Q16P1htcsV3sEauoYvTZ3jMfkTObce/adf9hHYx0OGnjbvhpDjnFi6ohGz5WnNT2jo=";
        System.out.print(SignatureUtil.getInstance().checksign(rsa_public, oid_str, signed_str));
    }*/

    /**
     * 签名处理
     *
     * @param prikeyvalue ：私钥
     * @param signStr     ：签名源内容
     * @return
     */
    public String sign(String prikeyvalue, String signStr) throws AccpPayException {
        String hash = Md5Algorithm.getInstance().md5Digest(signStr.getBytes(StandardCharsets.UTF_8));
        return RsaUtils.getInstance().sign(prikeyvalue, hash);
    }

    /**
     * 签名验证
     *
     * @param pubkeyvalue ：公钥
     * @param signStr     ：源串
     * @param signedStr   ：签名结果串
     * @return
     */
    public boolean checksign(String pubkeyvalue, String signStr, String signedStr) throws AccpPayException {
        String hash = Md5Algorithm.getInstance().md5Digest(signStr.getBytes(StandardCharsets.UTF_8));
        return RsaUtils.getInstance().checksign(pubkeyvalue, hash, signedStr);
    }
}
