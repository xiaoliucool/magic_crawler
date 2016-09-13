package com.rrx.jdb.utils.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.rrx.jdb.utils.FileUtils;

/**
 * 
 * @author 刘文超
 * @date 2016年8月31日-下午3:47:10
 * @version v1.0
 * @desc
 */
public class HttpUtils {
	/**
	 * 连接超时时间
	 */
	private static final int CONNECTION_TIME_OUT_IN_MS = 10000;
	/**
	 * 获取输入流的超时时间
	 */
	private static final int READ_TIME_OUT_IN_MS = 10000;
	/**
	 * html采用的是utf-8编码集
	 */
	private static final String UTF_8 = "utf-8";
	/**
	 * html采用的是gbk编码集
	 */
	private static final String GBK = "gbk";
	/**
	 * 获取html源文件，供其他模块解析
	 * @param strUrl 输入的url
	 * @return html源文件字符串
	 */
	public static String getHtml(String strUrl){
		String tContent = null;
		HttpURLConnection tConnection = openConnection(strUrl);
		addRequiredHeader(tConnection);
		int tResponseCode = getResponseCode(tConnection);
		System.out.println("response code " + tResponseCode);
		if (200==tResponseCode) {
			tContent = getConnectionOutputMsg(tConnection);
		}
		return tContent;
	}
	/**
	 * 打开链接
	 * 
	 * @param strUrl
	 * @return
	 */
	private static HttpURLConnection openConnection(String strUrl) {
		URL url;
		try {
			url = new URL(strUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("connection failed..");
			return null;
		}
	}
	/**
	 * 添加请求头信息
	 * @param conn
	 * @return
	 */
	private static boolean addRequiredHeader(HttpURLConnection conn) {
		conn.setConnectTimeout(CONNECTION_TIME_OUT_IN_MS);
		conn.setReadTimeout(READ_TIME_OUT_IN_MS);
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36");
		try {
			conn.setRequestMethod("GET");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 获取响应码
	 * @param conn
	 * @return
	 */
	private static int getResponseCode(HttpURLConnection conn) {
		int responseCode;
		try {
			responseCode = conn.getResponseCode();
			return responseCode;
		} catch (Exception e) {
			System.err.println("get response code failed");
			return -1;
		}
	}
	/**
	 * 获取响应信息
	 * @param connection
	 * @return
	 */
	private static String getConnectionOutputMsg(HttpURLConnection connection) {
		String tResponseMsg;
		BufferedReader tBr = null;
		try {
			StringBuilder tSb = new StringBuilder();
			//动态根据html页面的编码集，设置输入流的编码集
			String tContentType = connection.getContentType();
			boolean tIsUTF8 = tContentType.toLowerCase().contains(UTF_8);
			String tCharset = UTF_8;
			if (!tIsUTF8) {
				tCharset = GBK;
			}
			tBr = new BufferedReader(new InputStreamReader(connection.getInputStream(), tCharset));
			String tLine = "";
			while ((tLine = tBr.readLine())!=null) {
				//System.out.println(tLine);
				tSb.append(tLine+"\r\n");
			}
			tResponseMsg = tSb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			FileUtils.closeCloseable(tBr);
		}
		return tResponseMsg;
	}
	public static void main(String[] args) {
		
		System.out.println(HttpUtils.getHtml("http://www.zol.com.cn/brand.html"));
	}
}
