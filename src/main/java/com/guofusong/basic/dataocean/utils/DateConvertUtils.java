package com.guofusong.basic.dataocean.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: ConvertUtils
 * @version 2.0 
 * @Desc: 
 * @author guofusong
 * @date 2018年5月25日上午9:36:20
 * @history v2.0
 *
 */
public class DateConvertUtils {
	
	private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
		protected synchronized SimpleDateFormat initialValue(){
		return new SimpleDateFormat();
	}};
	
	
	/**
	 * 
	 * 描述：时间格式字符串转换成时间
	 * @author guofusong 
	 * @date 2018年5月25日上午9:42:31
	 * @param s
	 * @param regex
	 * @return
	 * @throws ParseException
	 */
	public static Date str2DateE(String s,String regex) throws ParseException{
		SimpleDateFormat dateFormat = threadLocal.get();
		dateFormat.applyPattern(regex);
		return dateFormat.parse(s);
	}
	
	/**
	 * 
	 * 描述：时间装换成字符
	 * @author guofusong 
	 * @date 2018年5月25日上午9:46:36
	 * @param d
	 * @param regex
	 * @return
	 */
	public static String date2Str(Date d,String regex){
		SimpleDateFormat dateFormat = threadLocal.get();
		dateFormat.applyPattern(regex);
		return dateFormat.format(d);
	}
	
	/**
	 * 
	 * 描述：时间装换成字符
	 * @author guofusong 
	 * @date 2018年5月25日上午9:48:36
	 * @param d
	 * @param regex
	 * @return
	 * @throws Exception 
	 */
	public static String date2StrE(Date d,String regex) throws Exception{
		SimpleDateFormat dateFormat = threadLocal.get();
		dateFormat.applyPattern(regex);
		try{
			return dateFormat.format(d);			
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
}
