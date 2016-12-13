package com.hisense.hiutbd.platform.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie 操作工具类
 * 
 * @author xq.zhao
 */
public class CookieUtils {

    /**
     * 设置cookie
     * 
     * @param response 响应
     * @param name cookie名字
     * @param value cookie值
     * @param maxAge cookie生命周期 以秒为单位
     */
    public static void addCookie(HttpServletResponse response, String name, String value, String path, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     * 删除Cookie
     * 
     * @param response 响应
     * @param name 名称
     */
    public static void delCookie(HttpServletResponse response, String name, String path) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * 根据名字获取cookie
     * 
     * @param request 请求
     * @param name cookie名字
     * @return Cookie 
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
    	Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
        	return null;
        }
        for(Cookie c : cookies){
        	if(c.getName().equals(name)){
        		cookie = c;
        		break;
        	}
        }
        return cookie;
    }
    
    /**
     * 根据名字获取cookie值
     * 
     * @param  request 请求
     * @param  name cookie名字
     * @return String Cookie值
     */
    public static String getCookieValueByName(HttpServletRequest request, String name) {
    	Cookie cookie = getCookieByName(request, name);
    	if(cookie == null){
    		return null;
    	}
        return cookie.getValue();
    }
}
