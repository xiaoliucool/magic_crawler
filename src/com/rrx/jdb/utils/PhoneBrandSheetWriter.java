package com.rrx.jdb.utils;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.rrx.jdb.db.bean.PhoneBrand;

/**
 * com.rrx.jdb.utils.PhoneBrandSheetWriter
 * 
 * @author 刘文超
 * @date 2016年8月31日-下午3:30:10
 * @version v1.0
 * @desc 手机品牌对应的Excel生成器
 */
class PhoneBrandSheetWriter implements ISheetWriter<PhoneBrand> {
	/**
	 * sheet名
	 */
	private static final String SHEET_NAME = "phone brand";

	@Override
	public boolean generate(HSSFWorkbook workbook, List<PhoneBrand> phoneBrandList) {
		HSSFSheet tSheet = workbook.getSheet(SHEET_NAME);
		if (tSheet == null) {
			tSheet = workbook.createSheet(SHEET_NAME);
			HSSFRow row = tSheet.createRow(0);
			row.createCell(0).setCellValue("手机品牌");
			row.createCell(1).setCellValue("英文名");
			row.createCell(2).setCellValue("手机ID");
			row.createCell(3).setCellValue("对应的url链接");
		}
		int tAlreadyRows = tSheet.getLastRowNum() + 1;
		for (int i = 0; i < phoneBrandList.size(); i++) {
			PhoneBrand tBrand = phoneBrandList.get(i);
			HSSFRow iterRow = tSheet.createRow(tAlreadyRows++);
			iterRow.createCell(0).setCellValue(tBrand.getPhoneNameInChinese());
			iterRow.createCell(1).setCellValue(tBrand.getPhoneNameInEnglish());
			iterRow.createCell(2).setCellValue(tBrand.getId());
			iterRow.createCell(3).setCellValue(tBrand.getUrl());
		}
		return true;
	}

}
