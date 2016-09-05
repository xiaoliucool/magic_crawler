package com.rrx.jdb.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
		File file = new File("f:"+File.separator+"tt"+File.separator+"ab.xls");
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(file.getAbsolutePath());
		System.out.println(file.isFile());
		System.out.println(file.isDirectory());
		HSSFWorkbook tWorkbook = null;
		try {
			tWorkbook = new HSSFWorkbook(new FileInputStream(file));
			System.out.println("dfdfdfd");
			ISheetWriter<PhoneBrand> tSheetWriter = new PhoneBrandSheetWriter();
			boolean tIsSuccess = tSheetWriter.generate(tWorkbook, phoneList);
			if (tIsSuccess) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("generate phone brand sheet failed.");
		} 
		return false;
	}
}
