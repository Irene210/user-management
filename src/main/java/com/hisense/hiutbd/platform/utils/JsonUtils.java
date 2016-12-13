package com.hisense.hiutbd.platform.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;


/**
 * json处理工具类
 * 
 * @author  xo
 * @version 2014-03-17
 */
public class JsonUtils {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	static{
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 将json字符串转成对象
	 * 
	 * @param 	str json字符串
	 * @param 	clazz 对象所属类
	 * @return	<T> T 对象
	 */
	public static <T> T readValue(String str, Class<T> clazz){
		T t = null;
		if(StringUtils.isBlank(str)){
			return null;
		}
		try {
			t = mapper.readValue(str, clazz);
		} catch (JsonParseException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return t;
	}

	/**
	 * 将对象转成json字符串
	 * 
	 * @param 	obj
	 * @return	String
	 */
	public static String writeValueAsString(Object obj){
		String str = null;
		try {
			str = mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return str;
	}
}
