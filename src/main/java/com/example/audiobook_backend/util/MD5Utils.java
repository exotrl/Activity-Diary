package com.example.audiobook_backend.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author molimark<br />
 * @date: 2022/8/3 15:01<br/>
 * @description: <br/>
 */
public class MD5Utils {
    public static String code(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[]byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }
    //密码加密显示
    public static void main(String[] args) {
        System.out.println(code("123456"));
    }
}

