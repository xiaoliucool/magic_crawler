package com.rrx.jdb.utils;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface ISheetWriter<T> {
	boolean generate(HSSFWorkbook workbook, List<T> list);
}
