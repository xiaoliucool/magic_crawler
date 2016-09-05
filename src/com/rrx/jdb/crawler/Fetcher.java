package com.rrx.jdb.crawler;

import java.util.List;

import org.jsoup.Jsoup;

import com.rrx.jdb.db.bean.PhoneBrand;
import com.rrx.jdb.utils.SheetUtils;
import com.rrx.jdb.utils.net.HttpUtils;

/**
 * com.rrx.jdb.crawler.Fetcher
 * 
 * @author 刘文超
 * @date 2016年9月4日-上午11:54:58
 * @version v1.0
 * @desc 通过url获取网页源码
 */
public class Fetcher {
	public static String fetch(String url) {
		if (null == url || 0 == url.length()) {
			return "";
		}
		return HttpUtils.getHtml(url);
	}

	public static void main(String[] args) {
		String tHtml = fetch("http://www.zol.com.cn/brand.html");
		List<PhoneBrand> tPhoneBrands = new Parser().parse(tHtml);
		for (PhoneBrand tPhoneBrand : tPhoneBrands) {
			System.out.println(tPhoneBrand);
		}
		SheetUtils.generatePhoneBrandSheet(tPhoneBrands);
	}
}
