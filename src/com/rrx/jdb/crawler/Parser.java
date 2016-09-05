package com.rrx.jdb.crawler;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.rrx.jdb.db.bean.PhoneBrand;

/**
 * com.rrx.jdb.crawler.Parser
 * 
 * @author 刘文超
 * @date 2016年9月4日-上午11:56:38
 * @version v1.0
 * @desc 源码解析器
 */
public class Parser {
	/**
	 * DOM标签中的detail class
	 */
	private static final String DETAIL_CLASS_NAME = "detail";
	/**
	 * DOM标签中的image class
	 */
	private static final String IMAGE_CLASS_NAME = "image";
	/**
	 * DOM标签中的text class
	 */
	private static final String TEXT_CLASS_NAME = "text";
	/**
	 * 网址前缀，因为<a href="/xx.html">中往往是相对路径
	 */
	private static final String ZOL_PREFIX = "http://mobile.zol.com.cn/";

	/**
	 * 解析手机信息
	 * 
	 * @param html
	 *            网页对应的html源码字符串
	 * @return 手机列表
	 */
	public List<PhoneBrand> parse(String html) {
		List<PhoneBrand> tPhoneBrands = new LinkedList<>();
		if (null == html || 0 == html.length()) {
			return tPhoneBrands;
		}
		Document tDocument = Jsoup.parse(html);
		Element tDetailElement = tDocument
				.getElementsByClass(DETAIL_CLASS_NAME).get(0);
		// 推荐品牌
		Element tImageElement = tDetailElement.getElementsByClass(
				IMAGE_CLASS_NAME).get(0);
		// 更多品牌
		Element tTextElement = tDetailElement.getElementsByClass(
				TEXT_CLASS_NAME).get(0);
		List<PhoneBrand> tRecommends = parseRecommend(tImageElement);
		List<PhoneBrand> tMores = parseMore(tTextElement);
		if (tRecommends != null && tRecommends.size() != 0) {
			tPhoneBrands.addAll(tRecommends);
		}
		if (tMores != null && tMores.size() != 0) {
			tPhoneBrands.addAll(tMores);
		}
		return tPhoneBrands;
	}

	/**
	 * 解析html中 推荐品牌 标签下的手机信息
	 * 
	 * @param element
	 * @return
	 */
	public List<PhoneBrand> parseRecommend(Element element) {
		List<PhoneBrand> tPhoneBrands = new LinkedList<>();
		if (element == null) {
			return tPhoneBrands;
		}
		Elements tPElements = element.getElementsByTag("p");
		for (Element tP : tPElements) {
			Element tLink = tP.getElementsByTag("a").get(0);
			String tHref = tLink.attr("href");
			int tIdStartIndex = tHref.indexOf("_") + 1;
			int tIdEndIndex = tHref.lastIndexOf(".");
			String tPhoneId = tHref.substring(tIdStartIndex, tIdEndIndex);
			String tPhoneName = tLink.text();
			PhoneBrand tPhoneBrand = new PhoneBrand();
			tPhoneBrand.setUrl(tHref);
			tPhoneBrand.setPhoneNameInChinese(tPhoneName);
			tPhoneBrand.setId(tPhoneId);
			tPhoneBrands.add(tPhoneBrand);
		}
		return tPhoneBrands;
	}

	/**
	 * 解析html中更多标签下的手机信息
	 * 
	 * @param element
	 * @return
	 */
	public List<PhoneBrand> parseMore(Element element) {
		List<PhoneBrand> tPhoneBrands = new LinkedList<>();
		if (element == null) {
			return tPhoneBrands;
		}
		Elements tLinkElements = element.getElementsByTag("a");
		for (Element tLink : tLinkElements) {
			String tHref = tLink.attr("href");
			int tIdStartIndex = tHref.indexOf("_") + 1;
			int tIdEndIndex = tHref.lastIndexOf(".");
			String tPhoneId = tHref.substring(tIdStartIndex, tIdEndIndex);
			String tPhoneName = tLink.text();
			PhoneBrand tPhoneBrand = new PhoneBrand();
			tPhoneBrand.setUrl(tHref);
			tPhoneBrand.setPhoneNameInChinese(tPhoneName);
			tPhoneBrand.setId(tPhoneId);
			tPhoneBrands.add(tPhoneBrand);
		}
		return tPhoneBrands;
	}
}
