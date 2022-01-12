package org.example.common.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/9/6.
 */
public class MD5Util {

        //公盐
        private static final String PUBLIC_SALT = "demo" ;
        //十六进制下数字到字符的映射数组
        private final static String[] hexDigits = {"0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        /**
         * md5加密算法
         * @param  originString
         * @return
         */
        public static String encodeByMD5(String originString){
            return MD5(originString, Charset.defaultCharset());
        }

        /**
         * 转换字节数组为十六进制字符串
         * @param
         * @return    十六进制字符串
         */
        private static String byteArrayToHexString(byte[] b){
            StringBuffer resultSb = new StringBuffer();
            for (int i = 0; i < b.length; i++){
                resultSb.append(byteToHexString(b[i]));
            }
            return resultSb.toString();
        }

        /** 将一个字节转化成十六进制形式的字符串     */
        private static String byteToHexString(byte b){
            int n = b;
            if (n < 0)
                n = 256 + n;
            int d1 = n / 16;
            int d2 = n % 16;
            return hexDigits[d1] + hexDigits[d2];
        }

    public static String MD5(String input, Charset charset) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var9) {
            var9.printStackTrace();
        }

        md.update(input.getBytes(charset));
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        byte[] tmp = md.digest();
        char[] str = new char[32];
        int k = 0;

        for(int i = 0; i < 16; ++i) {
            byte byte0 = tmp[i];
            str[k++] = hexDigits[byte0 >>> 4 & 15];
            str[k++] = hexDigits[byte0 & 15];
        }

        String result = new String(str);
        return result;
    }
    }
