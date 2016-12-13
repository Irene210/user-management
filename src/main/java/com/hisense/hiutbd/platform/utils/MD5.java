package com.hisense.hiutbd.platform.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 封装MD5
 * 
 * @author xo
 */
public class MD5 {

	/**
     * 获取md5加密后字符串<br />
     * 根据已知字符串，进行MD5加密
     * 
     * @param  str 预加密字符串
     * @return String 加密后字符串
     * @throws NoSuchAlgorithmException 加密失败
     */
	public static String getMD5(String str) {
        if(str == null) {
            return "";
        } else {
            MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
            md.update(str.getBytes());
            byte by[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for(int offset = 0; offset < by.length; offset++) {
                i = by[offset];
                if(i < 0) i += 256;
                if(i < 16) buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        }
    }

}
