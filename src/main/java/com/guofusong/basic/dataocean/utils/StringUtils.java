package com.guofusong.basic.dataocean.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: StringUtils
 * @version 2.0
 * @Desc: 
 * @author guofusong
 * @date 2018年5月9日下午2:24:28
 * @history v2.0
 *
 */
public class StringUtils {
	
	public static boolean isBlank(String s){
		if(s == null || s.trim().length() == 0){
			return true;
		}
		return false;
	}
	
	public static boolean isNotBlank(String s){
		return !isBlank(s);
	}

	public static String splitSuffix(String s,String r){
		int i = s.indexOf(r);
		if(i < 0)return s;
		return s.substring(i+1, s.length());
	}
	
	public static String splitPerfix(String s,String r){
		int i = s.indexOf(r);
		if(i < 0)return s;
		return s.substring(0, i);
	}
	
	public static String[] splitWithoutBlank(String s, String regex) {
		List<String> list = new ArrayList<String>();
		String[] strs = s.split(regex);
		for (int i = 0; i < strs.length; i++) {
			if(isNotBlank(strs[i])){
				list.add(strs[i]);
			}
		}
		return list.toArray(new String[list.size()]);
	}
	
}
