package com.zhishu.portal.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExportExcelUtil {

	public static void exportExcel(String path, List<?> list, Class<?> classz) throws Exception {
		try {
			ExcelUtil.exportExcel(path, list, classz);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static void main(String[] args) {
		Long time = 1501727029322L;

		Date date = new Date(time);

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		System.out.println(fmt.format(date));

	}

}
