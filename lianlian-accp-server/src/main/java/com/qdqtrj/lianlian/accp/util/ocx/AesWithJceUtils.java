package com.qdqtrj.lianlian.accp.util.ocx;

import com.qdqtrj.lianlian.accp.config.AccpConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

/**
 * @author accp
 */
@Slf4j
public final class AesWithJceUtils {

    public static final int MULTIPLE = 8;
    public static final int KEY_LENGTH = 32;

    private AesWithJceUtils() {
    }

    public static String getResult(String transferKey, String paramStr) {
        String data = "";
        try {
            Base64 base64 = new Base64();
            if (paramStr.contains(" ")) {
                data = paramStr.replaceAll(" ", "+");
            }
            byte[] cypherArray = base64.decode(data);

            Rijndael aes256 = new Rijndael();
            aes256.makeKey(AccpConstants.RESULT_KEY_BITS.getBytes(),
                    AccpConstants.RESULT_KEY_BITS.length() * MULTIPLE);

            byte[] tmp = aes256.decryptArrayNP(transferKey.getBytes(), 0);

            byte[] realKey = new byte[KEY_LENGTH];
            System.arraycopy(base64.encode(tmp), 0, realKey, 0, KEY_LENGTH);
            aes256.makeKey(realKey, realKey.length * MULTIPLE);

            byte[] plainArray = aes256.decryptArrayNP(cypherArray, 0);
            return (new String(plainArray)).trim();
        } catch (Exception ex) {
            log.error("accp->显示密码控件前调用，获取result失败：{}", ex.getMessage());
            return null;
        }
    }

    public static String getCipher(String transferKey, String paramStr) {
        String data = "";
        try {
            Base64 base64 = new Base64();
            if (paramStr.contains(" ")) {
                data = paramStr.replaceAll(" ", "+");
            }
            byte[] cypherArray = data.getBytes();

            Rijndael aes256 = new Rijndael();
            aes256.makeKey(AccpConstants.CIPHER_KEY_BITS.getBytes(), AccpConstants.CIPHER_KEY_BITS.length() * MULTIPLE);

            byte[] tmp = aes256.decryptArrayNP(transferKey.getBytes(), 0);

            byte[] realKey = new byte[KEY_LENGTH];
            System.arraycopy(base64.encode(tmp), 0, realKey, 0, KEY_LENGTH);
            aes256.makeKey(realKey, realKey.length * MULTIPLE);

            byte[] plainArray = aes256.encryptArrayNP(cypherArray, 0);
            return (new String(base64.encode(plainArray))).trim();
        } catch (Exception ex) {
            log.error("accp->显示密码控件前调用，获取cipher失败：{}", ex.getMessage());
            return null;
        }
    }

    public static String getResultHex(String transferKey, String data) {
        String decryptData = getResult(transferKey, data);
        byte[] hextmp = Base64.decodeBase64(decryptData);
        return bytesToHexString(hextmp);
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}

