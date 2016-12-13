package com.hisense.hiutbd.platform.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {

	
	/**
	 * Pattern yyyy-MM-dd HH:mm:ss
	 */
	public static final String PATTERN1 = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * Pattern yyyyMMddHHmmss
	 */
	public static final String PATTERN2 = "yyyyMMdd";

    /**
     * 获取当前日期<br />
     * 根据输入格式，返回当前日期
     * 
     * @param  date 转换日期
     * @param  dateFormatStr 日期格式，默认形式为yyyy-MM-dd HH:mm:ss
     * @return 日期字符串
     */
    public static String getDateToStr(Date date, String... dateFormatStr) {
        SimpleDateFormat dateFormat = null;
        if(dateFormatStr != null && dateFormatStr.length > 0) {
            dateFormat = new SimpleDateFormat(dateFormatStr[0]);
        } else {
            dateFormat = new SimpleDateFormat(PATTERN1);
        }
        return dateFormat.format(date);
    }
    
    public static Date getStrToDate(String str, String... dateFormatStr){
    	SimpleDateFormat dateFormat = null;
    	if(dateFormatStr != null && dateFormatStr.length > 0) {
            dateFormat = new SimpleDateFormat(dateFormatStr[0]);
        } else {
            dateFormat = new SimpleDateFormat(PATTERN1);
        }
		Date date = null;
		try {
			date = dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("日期解析异常！str="+str);
		} 
		return date;
	}
    /**
     * 获取指定过去日期零时<br />
     * 根据输入格式，获取指定过去日期零时
     * 
     * @param  date 转换日期
     * @param  dateFormatStr 日期格式，默认形式为yyyy-MM-dd HH:mm:ss
     * @return 日期字符串
     */
    public static String getOldDateToStr(Date date, int days, String... dateFormatStr) {
        SimpleDateFormat dateFormat = null;
        if(dateFormatStr != null && dateFormatStr.length > 0) {
            dateFormat = new SimpleDateFormat(dateFormatStr[0]);
        } else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        }
        
        Calendar calendar =Calendar.getInstance();   
        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)-days);   
        
        return dateFormat.format(calendar.getTime());
    }
    
    
    /**
     * 根据日期偏移量获得日期字符串
     * 
     * @param date   起始日期
     * @param months 日期偏移量
     * @param dateFormatStr 日期格式，默认形式为yyyy-MM-01
     * 
     * @return 日期字符串
     */
    public static String getOldMonthToStr(Date date, int months, String... dateFormatStr) {
        SimpleDateFormat dateFormat = null;
        if(dateFormatStr != null && dateFormatStr.length > 0) {
            dateFormat = new SimpleDateFormat(dateFormatStr[0]);
        } else {
            dateFormat = new SimpleDateFormat("yyyy-MM-01");
        }
        
        Calendar calendar =Calendar.getInstance();
        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH) + months);   
        
        return dateFormat.format(calendar.getTime());
    }

    
    
    
    
    /**
     * 判断是否为有效日期（yyyy-mm-dd）
     * @param sDate
     * @return
     */
    public static boolean isValidDate(String sDate) {
        String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
        String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
                + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
                + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
                + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        if((sDate != null)) {
            Pattern pattern = Pattern.compile(datePattern1);
            Matcher match = pattern.matcher(sDate);
            if(match.matches()) {
                pattern = Pattern.compile(datePattern2);
                match = pattern.matcher(sDate);
                return match.matches();
            } else {
                return false;
            }
        }
        return false;
    }

    public static Date getMiddleNight() {
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        c1.add(Calendar.DATE, 1);

        return c1.getTime();
    }
    
	// 获取两个日期直接的天数差
	public static int getBetweenDays(Date startDate, Date endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			startDate = sdf.parse(sdf.format(startDate));
			endDate = sdf.parse(sdf.format(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(endDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
    
	
}
