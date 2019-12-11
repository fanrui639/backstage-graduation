package com.fanr.graduation.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 功能简介：MD5加密工具类
 * 密码等安全信息存入数据库时，转换成MD5加密形式
 */
public class MD5Util {
    public static String salt = "關門"; //加盐密码

    public static String getMd5(String pwd) {


        String x = salt + pwd;
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            m.update(x.getBytes("UTF8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte s[] = m.digest();
        String result = "";
        for (int i = 0; i < s.length; i++) {
            result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
        }
        return result;

    }

    public static void main(String[] args) {
        String pwd = "123456";
        pwd = MD5Util.getMd5(pwd);
        System.out.println(pwd);

    }

}