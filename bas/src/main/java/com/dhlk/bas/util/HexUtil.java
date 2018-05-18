package com.dhlk.bas.util;

import java.math.BigInteger;

/**
 * HexUtil工具类
 */
public class HexUtil {

    // 帧编号1-512
    private static int frameNumber = 1;

    // hex2Decimal
    public static Integer hex2Decimal(String hex) {
        if (hex != null && hex.length() > 0) {
            return new BigInteger(hex, 16).intValue();
        }
        return null;
    }

    // decimal2Hex
    public static String decimal2Hex(Integer decimal) {
        String hex = null;
        try {
            hex = new BigInteger(String.valueOf(decimal), 10).toString(16).toUpperCase();
            while (hex.length() / 2 * 2 != hex.length()) {
                hex = "0" + hex;
                return hex.toUpperCase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hex.toUpperCase();
    }

    // decimal2Binary
    public static String decimal2Binary(Integer decimal) {
        String binary = null;
        try {
            binary = new BigInteger(String.valueOf(decimal), 10).toString(2);
            while (binary.length() / 2 * 2 != binary.length()) {
                binary = "0" + binary;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return binary;
    }

    // binary2Decimal
    public static Integer binary2Decimal(String binary) {
        Integer decimal = null;
        try {
            decimal = new BigInteger(binary, 2).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decimal;
    }

    // binary2Hex
    public static String binary2Hex(String binary) {
        String hex = null;
        try {
            hex = new BigInteger(binary, 2).toString(16);
            while (hex.length() / 2 * 2 != hex.length()) {
                hex = "0" + hex;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hex.toUpperCase();
    }

    // hex2Binary
    public static String hex2Binary(String hex) {
        String binary = null;
        try {
            binary = new BigInteger(hex, 16).toString(2);
            while (binary.length() / 4 * 4 != binary.length()) {
                binary = "0" + binary;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return binary;
    }

    /**
     * Convert byte[] to hex string
     *
     * @param src byte[] data
     * @return hex string
     */
    public static String byteToHex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
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

    /**
     * Convert hex string to byte[]
     *
     * @param hex the hex string
     * @return byte[]
     */
    public static byte[] hexToByte(String hex) {
        if (hex == null || hex.equals("")) {
            return null;
        }
        hex = hex.toUpperCase();
        int length = hex.length() / 2;
        char[] hexChars = hex.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * char to byte
     *
     * @param c
     * @return
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 16进制转Float
     *
     * @param hex
     * @return
     */
    public static float hexToFloat(String hex) {
//        return Float.intBitsToFloat(new BigInteger(hex, 16).intValue()); // 双精度
        return new BigInteger(hex, 16).floatValue(); // 单精度
    }

    /**
     * Float转16进制
     *
     * @param f
     * @return
     */
//    public static String floatToHex(float f) {
//        return Integer.toHexString(Float.floatToIntBits(f));
//    }

    /**
     * 获取帧编号信息
     * 返回为2个字节
     *
     * @return
     */
    public static String getFrameNum() {

        ++frameNumber;

        String frameNum = decimal2Hex(frameNumber);

        while (frameNum.length() < 4) {
            frameNum = "0" + frameNum;
        }

        if (frameNumber >= 512) {
            frameNumber = 1;
        }

        return frameNum;
    }

    /**
     * CRC16
     *
     * @param hex
     * @return
     */
    public static String CRC16(String hex) {

        // 将16进制转换成byte[]
        byte[] bytes = hexToByte(hex);

        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;
        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        return Integer.toHexString(CRC);
    }

    /**
     * checkSum
     * 灯控协议校验字
     *
     * @param hex
     * @return
     */
    public static String checkCode(String hex) {

        int length = hex.length();
        if (length / 2 * 2 != length) {
            return null;
        }

        int i = 0;
        BigInteger bigInteger = new BigInteger("00", 16);
        while (i < length) {
            String subHex = hex.substring(i, i + 2);
            bigInteger = bigInteger.add(new BigInteger(subHex, 16));
            i += 2;
        }
        bigInteger = bigInteger.subtract(new BigInteger("01", 16));

        int len = bigInteger.toString(16).length();
        if (len <= 0) {
            return null;
        } else if (len >= 2) {
            return bigInteger.toString(16).substring(len - 2).toUpperCase();
        } else if (len == 1) {
            return "0" + bigInteger.toString(16).toUpperCase();
        }

        return bigInteger.toString(16).substring(bigInteger.toString(16).length() - 2);
    }

    /**
     * checkSum
     * 空调命令校验字
     *
     * @param hex
     * @return
     */
    public static String checkSum(String hex) {

        // 分析hex是否合法
        if (hex != null) {
            hex = hex.trim().replace(" ", "");
        }

        // 判断长度是否合适
        if (hex == null || hex.length() / 2 * 2 != hex.length() || hex.length() != 14) {
            return null;
        }

        BigInteger checkSum = new BigInteger("00", 16);

        for (int i = 0; i < 7; i++) {
            String sub = hex.substring(i * 2, i * 2 + 2);
            checkSum = checkSum.add(new BigInteger(sub, 16));
        }

        // and or
        checkSum = checkSum.and(new BigInteger("FF", 16)).xor(new BigInteger("A5", 16));
//        System.out.println(checkSum.toString(16));

        return checkSum.toString(16).toUpperCase();
    }

    // 55bb0a8008000000b4099307503310000000150d4e000000000000000000000000000000000000000000000000000000000000000000000000004e
    public static void main(String[] args) {
//        String s = checkCode("9307503310000000150d");
//        System.out.println(s);
//        String s2 = s + CRC16("9307503310000000150d");
//        System.out.println(s2);

        String s3 = "A1 33 10 10 02 12 00";
        s3 = s3.replace(" ", "");
        s3 += checkSum(s3);
        for (int i = 0; i < s3.length(); i += 2) {
            System.out.print(s3.substring(i, i + 2) + " ");
        }

//        System.out.println(s3 + checkSum(s3));
//        System.out.println(floatToHex(220.47f));
//        System.out.println(hexToFloat("57cb"));

    }

}
