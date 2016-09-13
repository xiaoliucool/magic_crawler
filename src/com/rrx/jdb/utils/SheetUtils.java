package com.rrx.jdb.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.rrx.jdb.db.bean.PhoneBrand;

/**
 * com.rrx.jdb.utils.SheetUtils
 * 
 * @author 刘文超
 * @date 2016年8月31日-下午3:11:37
 * @version v1.0
 * @desc Excel表格处理工具类
 */
public class SheetUtils {
	/**
	 * 将手机品牌信息生成对应的excel表格
	 * 
	 * @param phoneList
	 *            手机品牌信息数据
	 * @return true 成功； false 失败
	 */
	public static boolean generatePhoneBrandSheet(List<PhoneBrand> phoneList) {
		File tFile = new File("f:"+File.separator+"tt");
		HSSFWorkbook tWorkbook = null;
		try {
			tWorkbook = new HSSFWorkbook();
			System.out.println("新建工作簿");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("generate phone brand sheet failed.");
		}
		ISheetWriter<PhoneBrand> tSheetWriter = new PhoneBrandSheetWriter();
		boolean tIsSuccess = tSheetWriter.generate(tWorkbook, phoneList);
		if (tIsSuccess) {
			return write2Excel(tWorkbook, tFile);
		}
		return false;
	}
	/**
	 * 具体的写excle文件
	 * @param tWorkbook 工作簿
	 * @param outputDir 输入excel文件的路径
	 * @return true 成功；false 失败
	 */
	private static boolean write2Excel(HSSFWorkbook tWorkbook, File outputDir){
		if (!outputDir.exists()) {
			outputDir.mkdirs();
		}
		File outputFile = new File(outputDir, "abc.xls");
		FileOutputStream tFos = null;
		try {
			tFos = new FileOutputStream(outputFile);
			tWorkbook.write(tFos);
			return true;
		} catch (Exception e) {
			System.out.println("写文件出错了");
			e.printStackTrace();
			return false;
		} finally{
			if (tFos!=null) {
				try {
					tFos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
