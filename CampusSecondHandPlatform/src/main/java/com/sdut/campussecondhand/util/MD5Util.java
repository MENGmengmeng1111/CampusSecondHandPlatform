package com.sdut.campussecondhand.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类（实际使用SHA-256更安全）
 * 用于用户密码加密存储
 * @author 孟冠宇
 * @date 2026-05-18
 */
public class MD5Util {

    /**
     * SHA-256加密
     * @param str 待加密字符串
     * @return 加密后的十六进制字符串
     */
    public static String encrypt(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes());
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}