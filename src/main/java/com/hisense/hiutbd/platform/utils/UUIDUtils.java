package com.hisense.hiutbd.platform.utils;

import java.util.UUID;

public class UUIDUtils {

    /**
     * 获取UUID<br />
     * 返回UUID，做为主键使用
     * 
     * @return uuid（32位）
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }
    public static void main(String[] args) {
		System.out.println(getUUID());
	}
}
