package com.qdqtrj.lianlian.accp.util.ocx;


final class OcxArrayUtils {
    private OcxArrayUtils() {
    }

    public static byte[] addByteArrays(byte[] first, byte[] second) {
        byte[] result = new byte[first.length + second.length];

        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);

        return result;
    }
}

