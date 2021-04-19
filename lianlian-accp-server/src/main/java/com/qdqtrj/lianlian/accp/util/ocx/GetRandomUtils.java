package com.qdqtrj.lianlian.accp.util.ocx;

import java.util.Random;

/**
 * @author accp
 */
public final class GetRandomUtils {

    public static final String ALL_CHAR = "1234567890";
    public static final int LENGTH = 32;

    private GetRandomUtils() {
    }

    public static String generateStringDefaultLength(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));
        }
        return sb.toString();
    }

    public static String generateStringDefaultLength() {
        return generateStringDefaultLength(LENGTH);
    }

   /* public static void main(String[] args) {
        System.out.println(generateString(32));
    }*/
}

