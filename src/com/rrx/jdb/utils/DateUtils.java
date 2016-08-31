package com.rrx.jdb.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.rrx.jdb.utils.DateUtils
 * @author 刘文超
 * @date 2016年8月30日-下午12:10:29
 * @version v1.0
 * @desc 日期工具类
 */
public class DateUtils {
	/**
	 * 产生特定的日期格式
	 * @return 日期字符串
	 */
	public static String generateDate() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy_MM_dd");
		return format.format(date);
	}

	public static void main(String[] args) {
		System.out.println(generateDate());
	}
}
