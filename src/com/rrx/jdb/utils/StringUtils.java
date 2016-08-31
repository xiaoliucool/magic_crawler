package com.rrx.jdb.utils;

/**
 * com.rrx.jdb.utils.StringUtils
 * @author 刘文超
 * @date 2016年8月30日-下午12:14:47
 * @version v1.0
 * @desc 字符串判断工具类
 */
public class StringUtils {
	/**
	 * 判断字符串为空
	 * @param content 要判断的字符串
	 * @return true 为空； false 不空
	 */
	public static boolean isEmpty(String content) {
		return content == null || content.trim().equals("");
	}
	/**
	 * 判断字符串不为空
	 * @param content 要判断的字符串
	 * @return true 不为空； false 空
	 */
	public static boolean isNotEmpty(String content){
		return !isEmpty(content);
	}

}
