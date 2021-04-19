package com.qdqtrj.lianlian.accp.util.ocx;


import com.qdqtrj.lianlian.accp.exception.AccpPayException;


final class Rijndael {
    public static final int DIR_ENCRYPT = 1;
    public static final int DIR_DECRYPT = 2;
    public static final int DIR_BOTH = 3;
    public static final int BLOCK_BITS = 128;
    public static final int BLOCK_SIZE = 16;
    //    private static final String SS = "\u637C\u777B\uF26B\u6FC5\u3001\u672B\uFED7\uAB76\uCA82\uC97D\uFA59\u47F0\uADD4\uA2AF\u9CA4\u72C0\uB7FD\u9326\u363F\uF7CC\u34A5\uE5F1\u71D8\u3115\u04C7\u23C3\u1896\u059A\u0712\u80E2\uEB27\uB275\u0983\u2C1A\u1B6E\u5AA0\u523B\uD6B3\u29E3\u2F84\u53D1\u00ED\u20FC\uB15B\u6ACB\uBE39\u4A4C\u58CF\uD0EF\uAAFB\u434D\u3385\u45F9\u027F\u503C\u9FA8\u51A3\u408F\u929D\u38F5\uBCB6\uDA21\u10FF\uF3D2\uCD0C\u13EC\u5F97\u4417\uC4A7\u7E3D\u645D\u1973\u6081\u4FDC\u222A\u9088\u46EE\uB814\uDE5E\u0BDB\uE032\u3A0A\u4906\u245C\uC2D3\uAC62\u9195\uE479\uE7C8\u376D\u8DD5\u4EA9\u6C56\uF4EA\u657A\uAE08\uBA78\u252E\u1CA6\uB4C6\uE8DD\u741F\u4BBD\u8B8A\u703E\uB566\u4803\uF60E\u6135\u57B9\u86C1\u1D9E\uE1F8\u9811\u69D9\u8E94\u9B1E\u87E9\uCE55\u28DF\u8CA1\u890D\uBFE6\u4268\u4199\u2D0F\uB054\uBB16";
    private static final byte[] SE = new byte[256];


    private static final int[] Te0 = new int[256];
    private static final int[] Te1 = new int[256];
    private static final int[] Te2 = new int[256];
    private static final int[] Te3 = new int[256];


    private static final byte[] Sd = new byte[256];


    private static final int[] Td0 = new int[256];
    private static final int[] Td1 = new int[256];
    private static final int[] Td2 = new int[256];
    private static final int[] Td3 = new int[256];


    private static final int[] rcon = new int[10];

    static {
        int root = 283;

        for (int i1 = 0; i1 < 256; i1++) {
            char c = "\u637C\u777B\uF26B\u6FC5\u3001\u672B\uFED7\uAB76\uCA82\uC97D\uFA59\u47F0\uADD4\uA2AF\u9CA4\u72C0\uB7FD\u9326\u363F\uF7CC\u34A5\uE5F1\u71D8\u3115\u04C7\u23C3\u1896\u059A\u0712\u80E2\uEB27\uB275\u0983\u2C1A\u1B6E\u5AA0\u523B\uD6B3\u29E3\u2F84\u53D1\u00ED\u20FC\uB15B\u6ACB\uBE39\u4A4C\u58CF\uD0EF\uAAFB\u434D\u3385\u45F9\u027F\u503C\u9FA8\u51A3\u408F\u929D\u38F5\uBCB6\uDA21\u10FF\uF3D2\uCD0C\u13EC\u5F97\u4417\uC4A7\u7E3D\u645D\u1973\u6081\u4FDC\u222A\u9088\u46EE\uB814\uDE5E\u0BDB\uE032\u3A0A\u4906\u245C\uC2D3\uAC62\u9195\uE479\uE7C8\u376D\u8DD5\u4EA9\u6C56\uF4EA\u657A\uAE08\uBA78\u252E\u1CA6\uB4C6\uE8DD\u741F\u4BBD\u8B8A\u703E\uB566\u4803\uF60E\u6135\u57B9\u86C1\u1D9E\uE1F8\u9811\u69D9\u8E94\u9B1E\u87E9\uCE55\u28DF\u8CA1\u890D\uBFE6\u4268\u4199\u2D0F\uB054\uBB16".charAt(i1 >>> 1);
            int s1 = (byte) (((i1 & 0x1) == 0) ? (c >>> 8) : c) & 0xFF;
            int s2 = s1 << 1;
            if (s2 >= 256) {
                s2 ^= root;
            }
            int s3 = s2 ^ s1;
            int i2 = i1 << 1;
            if (i2 >= 256) {
                i2 ^= root;
            }
            int i4 = i2 << 1;
            if (i4 >= 256) {
                i4 ^= root;
            }
            int i8 = i4 << 1;
            if (i8 >= 256) {
                i8 ^= root;
            }
            int i9 = i8 ^ i1;
            int ib = i9 ^ i2;
            int id = i9 ^ i4;
            int ie = i8 ^ i4 ^ i2;

            SE[i1] = (byte) s1;
            int t = s2 << 24 | s1 << 16 | s1 << 8 | s3;
            Te1[i1] = t >>> 8 | t << 24;
            Te2[i1] = t >>> 16 | t << 16;
            Te3[i1] = t >>> 24 | t << 8;

            Sd[s1] = (byte) i1;
            Td0[s1] = t = ie << 24 | i9 << 16 | id << 8 | ib;
            Td1[s1] = t >>> 8 | t << 24;
            Td2[s1] = t >>> 16 | t << 16;
            Td3[s1] = t >>> 24 | t << 8;
        }


        int r = 1;
        rcon[0] = r << 24;
        for (int i = 1; i < 10; i++) {
            r <<= 1;
            if (r >= 256) {
                r ^= root;
            }
            rcon[i] = r << 24;
        }
    }

    private int nr = 0;
    private int nk = 0;
    private int nw = 0;
    private int[] rek = null;
    private int[] rdk = null;

    public static boolean areEqual(byte[] a, byte[] b) {
        int aLength = a.length;
        if (aLength != b.length) {
            return false;
        }
        for (int i = 0; i < aLength; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    private void expandKey(byte[] cipherKey) {
        int r = 0;
        int i, k;
        for (i = 0, k = 0; i < this.nk; i++, k += 4) {
            this.rek[i] =
                    cipherKey[k] << 24 | (
                            cipherKey[k + 1] & 0xFF) << 16 | (
                            cipherKey[k + 2] & 0xFF) << 8 |
                            cipherKey[k + 3] & 0xFF;
        }
        int n;
        for (i = this.nk, n = 0; i < this.nw; i++, n--) {
            int j = this.rek[i - 1];
            if (n == 0) {
                n = this.nk;
                j =
                        SE[j >>> 16 & 0xFF] << 24 | (
                                SE[j >>> 8 & 0xFF] & 0xFF) << 16 | (
                                SE[j & 0xFF] & 0xFF) << 8 |
                                SE[j >>> 24] & 0xFF;
                j ^= rcon[r++];
            } else if (this.nk == 8 && n == 4) {
                j =
                        SE[j >>> 24] << 24 | (
                                SE[j >>> 16 & 0xFF] & 0xFF) << 16 | (
                                SE[j >>> 8 & 0xFF] & 0xFF) << 8 |
                                SE[j & 0xFF] & 0xFF;
            }
            this.rek[i] = this.rek[i - this.nk] ^ j;
        }
        //        int temp = 0;
    }

    private void invertKey() {
        int d = 0, e = 4 * this.nr;


        this.rdk[d] = this.rek[e];
        this.rdk[d + 1] = this.rek[e + 1];
        this.rdk[d + 2] = this.rek[e + 2];
        this.rdk[d + 3] = this.rek[e + 3];
        d += 4;
        e -= 4;
        for (int r = 1; r < this.nr; r++) {
            int w = this.rek[e];
            this.rdk[d] =
                    Td0[SE[w >>> 24] & 0xFF] ^
                            Td1[SE[w >>> 16 & 0xFF] & 0xFF] ^
                            Td2[SE[w >>> 8 & 0xFF] & 0xFF] ^
                            Td3[SE[w & 0xFF] & 0xFF];
            w = this.rek[e + 1];
            this.rdk[d + 1] =
                    Td0[SE[w >>> 24] & 0xFF] ^
                            Td1[SE[w >>> 16 & 0xFF] & 0xFF] ^
                            Td2[SE[w >>> 8 & 0xFF] & 0xFF] ^
                            Td3[SE[w & 0xFF] & 0xFF];
            w = this.rek[e + 2];
            this.rdk[d + 2] =
                    Td0[SE[w >>> 24] & 0xFF] ^
                            Td1[SE[w >>> 16 & 0xFF] & 0xFF] ^
                            Td2[SE[w >>> 8 & 0xFF] & 0xFF] ^
                            Td3[SE[w & 0xFF] & 0xFF];
            w = this.rek[e + 3];
            this.rdk[d + 3] =
                    Td0[SE[w >>> 24] & 0xFF] ^
                            Td1[SE[w >>> 16 & 0xFF] & 0xFF] ^
                            Td2[SE[w >>> 8 & 0xFF] & 0xFF] ^
                            Td3[SE[w & 0xFF] & 0xFF];
            d += 4;
            e -= 4;
        }
        this.rdk[d] = this.rek[e];
        this.rdk[d + 1] = this.rek[e + 1];
        this.rdk[d + 2] = this.rek[e + 2];
        this.rdk[d + 3] = this.rek[e + 3];
    }

    public void makeKey(byte[] cipherKey, int keyBits, int direction) throws AccpPayException {
        if (keyBits != 128 && keyBits != 192 && keyBits != 256) {
            throw new AccpPayException("Invalid AES key size (" + keyBits + " bits)");
        }
        this.nk = keyBits >>> 5;
        this.nr = this.nk + 6;
        this.nw = 4 * (this.nr + 1);
        this.rek = new int[this.nw];
        this.rdk = new int[this.nw];
        if ((direction & 0x3) != 0) {

            expandKey(cipherKey);


            if ((direction & 0x2) != 0) {
                invertKey();
            }
        }
    }

    public void makeKey(byte[] cipherKey, int keyBits) throws AccpPayException {
        makeKey(cipherKey, keyBits, 3);
    }

    public byte[] encryptBlock(byte[] pt, byte[] ct) {
        int k = 0;
        int t0 = (pt[0] << 24 | (
                pt[1] & 0xFF) << 16 | (
                pt[2] & 0xFF) << 8 |
                pt[3] & 0xFF) ^ this.rek[0];
        int t1 = (pt[4] << 24 | (
                pt[5] & 0xFF) << 16 | (
                pt[6] & 0xFF) << 8 |
                pt[7] & 0xFF) ^ this.rek[1];
        int t2 = (pt[8] << 24 | (
                pt[9] & 0xFF) << 16 | (
                pt[10] & 0xFF) << 8 |
                pt[11] & 0xFF) ^ this.rek[2];
        int t3 = (pt[12] << 24 | (
                pt[13] & 0xFF) << 16 | (
                pt[14] & 0xFF) << 8 |
                pt[15] & 0xFF) ^ this.rek[3];


        for (int r = 1; r < this.nr; r++) {
            k += 4;
            int a0 =
                    Te0[t0 >>> 24] ^
                            Te1[t1 >>> 16 & 0xFF] ^
                            Te2[t2 >>> 8 & 0xFF] ^
                            Te3[t3 & 0xFF] ^
                            this.rek[k];
            int a1 =
                    Te0[t1 >>> 24] ^
                            Te1[t2 >>> 16 & 0xFF] ^
                            Te2[t3 >>> 8 & 0xFF] ^
                            Te3[t0 & 0xFF] ^
                            this.rek[k + 1];
            int a2 =
                    Te0[t2 >>> 24] ^
                            Te1[t3 >>> 16 & 0xFF] ^
                            Te2[t0 >>> 8 & 0xFF] ^
                            Te3[t1 & 0xFF] ^
                            this.rek[k + 2];
            int a3 =
                    Te0[t3 >>> 24] ^
                            Te1[t0 >>> 16 & 0xFF] ^
                            Te2[t1 >>> 8 & 0xFF] ^
                            Te3[t2 & 0xFF] ^
                            this.rek[k + 3];
            t0 = a0;
            t1 = a1;
            t2 = a2;
            t3 = a3;
        }


        k += 4;

        int v = this.rek[k];
        ct[0] = (byte) (SE[t0 >>> 24] ^ v >>> 24);
        ct[1] = (byte) (SE[t1 >>> 16 & 0xFF] ^ v >>> 16);
        ct[2] = (byte) (SE[t2 >>> 8 & 0xFF] ^ v >>> 8);
        ct[3] = (byte) (SE[t3 & 0xFF] ^ v);

        v = this.rek[k + 1];
        ct[4] = (byte) (SE[t1 >>> 24] ^ v >>> 24);
        ct[5] = (byte) (SE[t2 >>> 16 & 0xFF] ^ v >>> 16);
        ct[6] = (byte) (SE[t3 >>> 8 & 0xFF] ^ v >>> 8);
        ct[7] = (byte) (SE[t0 & 0xFF] ^ v);

        v = this.rek[k + 2];
        ct[8] = (byte) (SE[t2 >>> 24] ^ v >>> 24);
        ct[9] = (byte) (SE[t3 >>> 16 & 0xFF] ^ v >>> 16);
        ct[10] = (byte) (SE[t0 >>> 8 & 0xFF] ^ v >>> 8);
        ct[11] = (byte) (SE[t1 & 0xFF] ^ v);

        v = this.rek[k + 3];
        ct[12] = (byte) (SE[t3 >>> 24] ^ v >>> 24);
        ct[13] = (byte) (SE[t0 >>> 16 & 0xFF] ^ v >>> 16);
        ct[14] = (byte) (SE[t1 >>> 8 & 0xFF] ^ v >>> 8);
        ct[15] = (byte) (SE[t2 & 0xFF] ^ v);

        return ct;
    }

    public byte[] decryptBlock(byte[] ct, byte[] pt) {
        int k = 0;
        int t0 = (ct[0] << 24 | (
                ct[1] & 0xFF) << 16 | (
                ct[2] & 0xFF) << 8 |
                ct[3] & 0xFF) ^ this.rdk[0];
        int t1 = (ct[4] << 24 | (
                ct[5] & 0xFF) << 16 | (
                ct[6] & 0xFF) << 8 |
                ct[7] & 0xFF) ^ this.rdk[1];
        int t2 = (ct[8] << 24 | (
                ct[9] & 0xFF) << 16 | (
                ct[10] & 0xFF) << 8 |
                ct[11] & 0xFF) ^ this.rdk[2];
        int t3 = (ct[12] << 24 | (
                ct[13] & 0xFF) << 16 | (
                ct[14] & 0xFF) << 8 |
                ct[15] & 0xFF) ^ this.rdk[3];


        for (int r = 1; r < this.nr; r++) {
            k += 4;
            int a0 =
                    Td0[t0 >>> 24] ^
                            Td1[t3 >>> 16 & 0xFF] ^
                            Td2[t2 >>> 8 & 0xFF] ^
                            Td3[t1 & 0xFF] ^
                            this.rdk[k];
            int a1 =
                    Td0[t1 >>> 24] ^
                            Td1[t0 >>> 16 & 0xFF] ^
                            Td2[t3 >>> 8 & 0xFF] ^
                            Td3[t2 & 0xFF] ^
                            this.rdk[k + 1];
            int a2 =
                    Td0[t2 >>> 24] ^
                            Td1[t1 >>> 16 & 0xFF] ^
                            Td2[t0 >>> 8 & 0xFF] ^
                            Td3[t3 & 0xFF] ^
                            this.rdk[k + 2];
            int a3 =
                    Td0[t3 >>> 24] ^
                            Td1[t2 >>> 16 & 0xFF] ^
                            Td2[t1 >>> 8 & 0xFF] ^
                            Td3[t0 & 0xFF] ^
                            this.rdk[k + 3];
            t0 = a0;
            t1 = a1;
            t2 = a2;
            t3 = a3;
        }


        k += 4;

        int v = this.rdk[k];
        pt[0] = (byte) (Sd[t0 >>> 24] ^ v >>> 24);
        pt[1] = (byte) (Sd[t3 >>> 16 & 0xFF] ^ v >>> 16);
        pt[2] = (byte) (Sd[t2 >>> 8 & 0xFF] ^ v >>> 8);
        pt[3] = (byte) (Sd[t1 & 0xFF] ^ v);

        v = this.rdk[k + 1];
        pt[4] = (byte) (Sd[t1 >>> 24] ^ v >>> 24);
        pt[5] = (byte) (Sd[t0 >>> 16 & 0xFF] ^ v >>> 16);
        pt[6] = (byte) (Sd[t3 >>> 8 & 0xFF] ^ v >>> 8);
        pt[7] = (byte) (Sd[t2 & 0xFF] ^ v);

        v = this.rdk[k + 2];
        pt[8] = (byte) (Sd[t2 >>> 24] ^ v >>> 24);
        pt[9] = (byte) (Sd[t1 >>> 16 & 0xFF] ^ v >>> 16);
        pt[10] = (byte) (Sd[t0 >>> 8 & 0xFF] ^ v >>> 8);
        pt[11] = (byte) (Sd[t3 & 0xFF] ^ v);

        v = this.rdk[k + 3];
        pt[12] = (byte) (Sd[t3 >>> 24] ^ v >>> 24);
        pt[13] = (byte) (Sd[t2 >>> 16 & 0xFF] ^ v >>> 16);
        pt[14] = (byte) (Sd[t1 >>> 8 & 0xFF] ^ v >>> 8);
        pt[15] = (byte) (Sd[t0 & 0xFF] ^ v);

        return pt;
    }

    public byte[] encryptArray(byte[] message, int offset) throws CryptoException {
        if (offset > message.length) {
            throw new CryptoException("Offset is greater than length of message");
        }


        int length = message.length - offset;


        int numOfBlocks = length / 16;


        int lengthOfLastPart = length - numOfBlocks * 16;


        if (lengthOfLastPart == 0) {
            lengthOfLastPart = 16;
            numOfBlocks--;
        }

        byte[] result = new byte[0];
        byte[] block = new byte[16];


        for (int i = 0; i < numOfBlocks; i++) {
            System.arraycopy(message, offset + i * 16, block, 0, 16);
            result = OcxArrayUtils.addByteArrays(result, encryptBlock(block, new byte[16]));
        }


        byte[] last = new byte[lengthOfLastPart];
        System.arraycopy(message, offset + numOfBlocks * 16, last, 0, lengthOfLastPart);


        int numOfPads = 16 - last.length;


        if (numOfPads == 0) {
            numOfPads = 16;
        }


        byte[] pads = new byte[numOfPads];
        for (int j = 0; j < numOfPads; j++) {
            pads[j] = (byte) numOfPads;
        }

        if (numOfPads != 16) {

            last = OcxArrayUtils.addByteArrays(last, pads);
        } else {

            if (last.length == 0) {
                last = pads;
            }


            result = OcxArrayUtils.addByteArrays(encryptBlock(last,
                    new byte[16]),
                    encryptBlock(pads, new byte[16]));
            return result;
        }

        result = OcxArrayUtils.addByteArrays(result,
                encryptBlock(last, new byte[16]));

        return result;
    }

    public byte[] decryptArray(byte[] message, int offset) throws CryptoException {
        if (offset > message.length) {
            throw new CryptoException("Offset is greater than length of message");
        }


        int length = message.length - offset;


        int numOfBlocks = length / 16;


        int lengthOfLastPart = length - numOfBlocks * 16;


        if (lengthOfLastPart == 0) {
            lengthOfLastPart = 16;
            numOfBlocks--;
        }

        byte[] result = new byte[0];
        byte[] block = new byte[16];


        for (int i = 0; i < numOfBlocks; i++) {
            System.arraycopy(message, offset + i * 16, block, 0, 16);
            result = OcxArrayUtils.addByteArrays(result, decryptBlock(block, new byte[16]));
        }


        byte[] last = new byte[lengthOfLastPart];
        System.arraycopy(message, offset + numOfBlocks * 16, last, 0, lengthOfLastPart);


        byte[] tmp = decryptBlock(last, new byte[16]);

        int numOfPads = tmp[tmp.length - 1];

        byte[] lastBlock = new byte[16 - numOfPads];

        System.arraycopy(tmp, 0, lastBlock, 0, lastBlock.length);

        result = OcxArrayUtils.addByteArrays(result, lastBlock);

        return result;
    }

    public byte[] encryptArrayNP(byte[] message, int offset) throws CryptoException {
        if (offset > message.length) {
            throw new CryptoException("Offset is greater than length of message");
        }


        int length = message.length - offset;


        int numOfBlocks = length / 16;


        int lengthOfLastPart = length - numOfBlocks * 16;


        if (lengthOfLastPart == 0) {
            lengthOfLastPart = 16;
            numOfBlocks--;
        }

        byte[] result = new byte[0];
        byte[] block = new byte[16];


        for (int i = 0; i < numOfBlocks; i++) {
            System.arraycopy(message, offset + i * 16, block, 0, 16);
            result = OcxArrayUtils.addByteArrays(result, encryptBlock(block, new byte[16]));
        }


        byte[] last = new byte[lengthOfLastPart];
        System.arraycopy(message, offset + numOfBlocks * 16, last, 0, lengthOfLastPart);


        int numOfZeros = 16 - last.length;


        if (numOfZeros == 0) {
            result = OcxArrayUtils.addByteArrays(result,
                    encryptBlock(last, new byte[16]));
            return result;
        }


        byte[] pads = new byte[numOfZeros];
        for (int j = 0; j < numOfZeros; j++) {
            pads[j] = 0;
        }

        if (numOfZeros != 16) {

            last = OcxArrayUtils.addByteArrays(last, pads);

        } else if (last.length == 0) {
            return result;
        }


        result = OcxArrayUtils.addByteArrays(result,
                encryptBlock(last, new byte[16]));

        return result;
    }

    public byte[] decryptArrayNP(byte[] message, int offset) throws CryptoException {
        int length = message.length;


        int numOfBlocks = length / 16;


        int lengthOfLastPart = length - numOfBlocks * 16;


        if (lengthOfLastPart != 0) {
            throw new CryptoException("Length of last part is not 0");
        }

        byte[] result = new byte[0];
        byte[] block = new byte[16];


        for (int i = 0; i < numOfBlocks; i++) {
            System.arraycopy(message, i * 16, block, 0, 16);
            result = OcxArrayUtils.addByteArrays(result,
                    decryptBlock(block, new byte[16]));
        }


        return result;
    }

    @Override
    protected void finalize() throws Throwable {
        if (this.rek != null) {
            for (int i = 0; i < this.rek.length; i++) {
                this.rek[i] = 0;
            }
            this.rek = null;
        }
        if (this.rdk != null) {
            for (int i = 0; i < this.rdk.length; i++) {
                this.rdk[i] = 0;
            }
            this.rdk = null;
        }
        super.finalize();
    }
}

