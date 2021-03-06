/**
 * Project Name:excelutil
 * File Name:ExcelUtil.java
 * Package Name:com.lkx.util
 * Date:2017年6月7日上午9:44:58
 * Copyright (c) 2017~2020, likaixuan@test.com.cn All Rights Reserved.
 *
*/

package com.bds.portal.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bds.model.ExportModel;

/**
 * ClassName:ExcelUtil Function: TODO ADD FUNCTION. Reason: TODO ADD REASON.
 * Date: 2017年6月7日 上午9:44:58
 * 
 * 只需要两步即可完成以前复杂的Excel读取 用法步骤： 1.定义需要读取的表头字段和表头对应的属性字段 String keyValue
 * ="手机名称:phoneName,颜色:color,售价:price"; 2.读取数据 List<PhoneModel> list =
 * ExcelUtil.readXls("C://test.xlsx",ExcelUtil.getMap(keyValue),"com.lkx.excel.PhoneModel");
 *
 * 
 * @author likaixuan
 * @version V1.0
 * @since JDK 1.7
 * @see
 */
public class ExcelUtil implements Serializable {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.7
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

	/**
	 * getMap:(将传进来的表头和表头对应的属性存进Map集合，表头字段为key,属性为value)
	 *
	 * @author likaixuan
	 * @param 把传进指定格式的字符串解析到Map中
	 *            形如: String keyValue = "手机名称:phoneName,颜色:color,售价:price";
	 * @return
	 * @since JDK 1.7
	 */
	public static Map<String, String> getMap(String keyValue) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (keyValue != null) {
			String[] str = keyValue.split(",");
			for (String element : str) {
				String[] str2 = element.split(":");
				map.put(str2[0], str2[1]);
			}
		}
		return map;
	}

	/**
	 * readXlsPart:(根据传进来的map集合读取Excel) 传进来4个参数 <String,String>类型，第二个要反射的类的具体路径)
	 *
	 * @author likaixuan
	 * @param filePath
	 *            Excel文件路径
	 * @param map
	 *            表头和属性的Map集合,其中Map中Key为Excel列的名称，Value为反射类的属性
	 * @param classPath
	 *            需要映射的model的路径
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	public static <T> List<T> readXlsPart(String filePath, Map map, String classPath, int... rowNumIndex)
			throws Exception {

		Set keySet = map.keySet();// 返回键的集合

		/** 反射用 **/
		Class<?> demo = null;
		Object obj = null;
		/** 反射用 **/

		List<Object> list = new ArrayList<Object>();
		demo = Class.forName(classPath);
		String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
		InputStream is = new FileInputStream(filePath);
		Workbook wb = null;

		if (fileType.equals("xls")) {
			wb = new HSSFWorkbook(is);
		} else if (fileType.equals("xlsx")) {
			wb = new XSSFWorkbook(is);
		} else {
			LOGGER.error("您输入的excel格式不正确");
			throw new Exception("您输入的excel格式不正确");
		}
		for (int sheetNum = 0; sheetNum < 1; sheetNum++) {// 获取每个Sheet表

			int rowNum_x = -1;// 记录第x行为表头
			Map<String, Integer> cellmap = new HashMap<String, Integer>();// 存放每一个field字段对应所在的列的序号
			List<String> headlist = new ArrayList();// 存放所有的表头字段信息

			Sheet hssfSheet = wb.getSheetAt(sheetNum);

			// 设置默认最大行为2w行
			if (hssfSheet != null && hssfSheet.getLastRowNum() > 20000) {
				throw new Exception("Excel 数据超过20000行,请检查是否有空行,或分批导入");
			}

			// 循环行Row
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {

				if (rowNumIndex != null && rowNumIndex.length > 0 && rowNum_x == -1) {// 如果传值指定从第几行开始读，就从指定行寻找，否则自动寻找
					Row hssfRow = hssfSheet.getRow(rowNumIndex[0]);
					if (hssfRow == null) {
						throw new RuntimeException("指定的行为空，请检查");
					}
					rowNum = rowNumIndex[0] - 1;
				}
				Row hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				boolean flag = false;
				for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
					if (hssfRow.getCell(i) != null && !("").equals(hssfRow.getCell(i).toString().trim())) {
						flag = true;
					}
				}
				if (!flag) {
					continue;
				}

				if (rowNum_x == -1) {
					// 循环列Cell
					for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {

						Cell hssfCell = hssfRow.getCell(cellNum);
						if (hssfCell == null) {
							continue;
						}

						String tempCellValue = hssfSheet.getRow(rowNum).getCell(cellNum).getStringCellValue();

						tempCellValue = StringUtils.remove(tempCellValue, (char) 160);
						tempCellValue = tempCellValue.trim();

						headlist.add(tempCellValue);

						Iterator it = keySet.iterator();

						while (it.hasNext()) {
							Object key = it.next();
							if (StringUtils.isNotBlank(tempCellValue)
									&& StringUtils.equals(tempCellValue, key.toString())) {
								rowNum_x = rowNum;
								cellmap.put(map.get(key).toString(), cellNum);
							}
						}
						if (rowNum_x == -1) {
							LOGGER.error("没有找到对应的字段或者对应字段行上面含有不为空白的行字段");
							throw new Exception("没有找到对应的字段或者对应字段行上面含有不为空白的行字段");
						}
					}

				} else {
					obj = demo.newInstance();
					Iterator it = keySet.iterator();
					while (it.hasNext()) {
						Object key = it.next();
						Integer cellNum_x = cellmap.get(map.get(key).toString());
						if (cellNum_x == null || hssfRow.getCell(cellNum_x) == null) {
							continue;
						}
						String attr = map.get(key).toString();// 得到属性

						Class<?> attrType = BeanUtils.findPropertyType(attr, new Class[] { obj.getClass() });

						Cell cell = hssfRow.getCell(cellNum_x);
						getValue(cell, obj, attr, attrType, rowNum, cellNum_x, key);

					}
					list.add(obj);
				}

			}
		}
		is.close();
		// wb.close();
		return (List<T>) list;
	}

	/**
	 * readXls:(根据传进来的map集合读取Excel) 传进来3个参数 <String,String>类型，第二个要反射的类的具体路径)
	 *
	 * @author likaixuan
	 * @param filePath
	 *            Excel文件路径
	 * @param map
	 *            表头和属性的Map集合,其中Map中Key为Excel列的名称，Value为反射类的属性
	 * @param classPath
	 *            需要映射的model的路径
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	public static <T> List<T> readXls(String filePath, Map map, String classPath, int... rowNumIndex) throws Exception {

		Set keySet = map.keySet();// 返回键的集合

		/** 反射用 **/
		Class<?> demo = null;
		Object obj = null;
		/** 反射用 **/

		List<Object> list = new ArrayList<Object>();
		demo = Class.forName(classPath);
		String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
		InputStream is = new FileInputStream(filePath);
		Workbook wb = null;

		if (fileType.equals("xls")) {
			wb = new HSSFWorkbook(is);
		} else if (fileType.equals("xlsx")) {
			wb = new XSSFWorkbook(is);
		} else {
			LOGGER.error("您输入的excel格式不正确");
			throw new Exception("您输入的excel格式不正确");
		}
		for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {// 获取每个Sheet表

			int rowNum_x = -1;// 记录第x行为表头
			Map<String, Integer> cellmap = new HashMap<String, Integer>();// 存放每一个field字段对应所在的列的序号
			List<String> headlist = new ArrayList();// 存放所有的表头字段信息

			Sheet hssfSheet = wb.getSheetAt(sheetNum);

			// 循环行Row
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				if (rowNumIndex != null && rowNumIndex.length > 0 && rowNum_x == -1) {// 如果传值指定从第几行开始读，就从指定行寻找，否则自动寻找
					Row hssfRow = hssfSheet.getRow(rowNumIndex[0]);
					if (hssfRow == null) {
						throw new RuntimeException("指定的行为空，请检查");
					}
					rowNum = rowNumIndex[0] - 1;
				}
				Row hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				boolean flag = false;
				for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
					if (hssfRow.getCell(i) != null && !("").equals(hssfRow.getCell(i).toString().trim())) {
						flag = true;
					}
				}
				if (!flag) {
					continue;
				}

				if (rowNum_x == -1) {
					// 循环列Cell
					for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {

						Cell hssfCell = hssfRow.getCell(cellNum);
						if (hssfCell == null) {
							continue;
						}

						String tempCellValue = hssfSheet.getRow(rowNum).getCell(cellNum).getStringCellValue();

						tempCellValue = StringUtils.remove(tempCellValue, (char) 160);
						tempCellValue = tempCellValue.trim();

						headlist.add(tempCellValue);
						Iterator it = keySet.iterator();
						while (it.hasNext()) {
							Object key = it.next();
							if (StringUtils.isNotBlank(tempCellValue)
									&& StringUtils.equals(tempCellValue, key.toString())) {
								rowNum_x = rowNum;
								cellmap.put(map.get(key).toString(), cellNum);
							}
						}
						if (rowNum_x == -1) {
							LOGGER.error("没有找到对应的字段或者对应字段行上面含有不为空白的行字段");
							throw new Exception("没有找到对应的字段或者对应字段行上面含有不为空白的行字段");
						}
					}

					// 读取到列后，检查表头是否完全一致--start
					for (int i = 0; i < headlist.size(); i++) {
						boolean boo = false;
						Iterator itor = keySet.iterator();
						while (itor.hasNext()) {
							String tempname = itor.next().toString();
							if (tempname.equals(headlist.get(i))) {
								boo = true;
							}
						}
						if (boo == false) {
							throw new Exception("表头字段和定义的属性字段不匹配，请检查");
						}
					}

					Iterator itor = keySet.iterator();
					while (itor.hasNext()) {
						boolean boo = false;
						String tempname = itor.next().toString();
						for (int i = 0; i < headlist.size(); i++) {
							if (tempname.equals(headlist.get(i))) {
								boo = true;
							}
						}
						if (boo == false) {
							throw new Exception("表头字段和定义的属性字段不匹配，请检查");
						}
					}
					// 读取到列后，检查表头是否完全一致--end

				} else {
					obj = demo.newInstance();
					Iterator it = keySet.iterator();
					while (it.hasNext()) {
						Object key = it.next();
						Integer cellNum_x = cellmap.get(map.get(key).toString());
						if (cellNum_x == null || hssfRow.getCell(cellNum_x) == null) {
							continue;
						}
						String attr = map.get(key).toString();// 得到属性

						Class<?> attrType = BeanUtils.findPropertyType(attr, new Class[] { obj.getClass() });

						Cell cell = hssfRow.getCell(cellNum_x);
						getValue(cell, obj, attr, attrType, rowNum, cellNum_x, key);

					}
					list.add(obj);
				}

			}
		}
		is.close();
		// wb.close();
		return (List<T>) list;
	}

	/**
	 * setter:(反射的set方法给属性赋值)
	 *
	 * @author likaixuan
	 * @param obj
	 *            具体的类
	 * @param att
	 *            类的属性@注意首字母记得大写
	 * @param value
	 *            赋予属性的值
	 * @param type
	 *            属性是哪种类型 比如:String double boolean等类型
	 * @throws Exception
	 * @since JDK 1.7
	 */
	public static void setter(Object obj, String att, Object value, Class<?> type, int row, int col, Object key)
			throws Exception {
		try {
			Method method = obj.getClass().getMethod("set" + StringUtil.toUpperCaseFirstOne(att), type);
			method.invoke(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("第" + (row + 1) + " 行  " + (col + 1) + "列   属性：" + key + " 赋值异常  " + e.getStackTrace());
			throw new Exception("第" + (row + 1) + " 行  " + (col + 1) + "列   属性：" + key + " 赋值异常  ");
		}

	}

	/**
	 * getAttrVal:(反射的get方法得到属性值)
	 *
	 * @author likaixuan
	 * @param obj
	 *            具体的类
	 * @param att
	 *            类的属性
	 * @param value
	 *            赋予属性的值
	 * @param type
	 *            属性是哪种类型 比如:String double boolean等类型
	 * @throws Exception
	 * @since JDK 1.7
	 */
	public static Object getAttrVal(Object obj, String att, Class<?> type) throws Exception {
		try {
			String attrMethod = StringUtil.toUpperCaseFirstOne(att);
			Method method = obj.getClass().getMethod("get" + attrMethod);
			Object value = new Object();
			value = method.invoke(obj);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * getValue:(得到Excel列的值)
	 *
	 * @author likaixuan
	 * @param hssfCell
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	public static void getValue(Cell cell, Object obj, String attr, Class attrType, int row, int col, Object key)
			throws Exception {
		Object val = null;
		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			val = cell.getBooleanCellValue();

		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (DateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					if (attrType == String.class) {
						val = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
					} else {
						val = dateConvertFormat(sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue())));
					}
				} catch (ParseException e) {
					LOGGER.error("日期格式转换错误");
					throw new Exception("第" + (row + 1) + " 行  " + (col + 1) + "列   属性：" + key + " 日期格式转换错误  ");
				}
			} else {
				if (attrType == String.class) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					val = cell.getStringCellValue();
				} else if (attrType == BigDecimal.class) {
					val = new BigDecimal(cell.getNumericCellValue());
				} else if (attrType == long.class) {
					val = (long) cell.getNumericCellValue();
				} else if (attrType == Double.class) {
					val = cell.getNumericCellValue();
				} else if (attrType == Float.class) {
					val = (float) cell.getNumericCellValue();
				} else if (attrType == int.class || attrType == Integer.class) {
					val = (int) cell.getNumericCellValue();
				} else if (attrType == Short.class) {
					val = (short) cell.getNumericCellValue();
				} else {
					val = cell.getNumericCellValue();
				}
			}

		} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			val = cell.getStringCellValue();
		}

		setter(obj, attr, val, attrType, row, col, key);
	}

	/**
	 * exportExcel:(导出Excel)
	 *
	 * @author likaixuan
	 * @param hssfCell
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	public static void exportExcel(String outFilePath, List<?> list, Class<?> classPath) throws Exception {
		// Class<?> demo = null;
		try {

			File file = new File(outFilePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			// 创建HSSFWorkbook对象(excel的文档对象)
			HSSFWorkbook wb = new HSSFWorkbook();
			// 建立新的sheet对象（excel的表单）
			HSSFSheet sheet = wb.createSheet("sheet1");
			// 声明样式
			HSSFCellStyle style = wb.createCellStyle();
			// 居中显示
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 在sheet里创建第一行为表头，参数为行索引(excel的行)，可以是0～65535之间的任何一个
			HSSFRow rowHeader = sheet.createRow(0);
			// 创建单元格并设置单元格内容

			Field[] fields = classPath.getDeclaredFields();
			int headerIndex = 0;
			for (int j = 0; j < fields.length; j++) {
				Field field = fields[j];
				ExcelAnnotation allowExcel = field.getAnnotation(ExcelAnnotation.class);
				if (allowExcel != null && allowExcel.name() != null) {
					rowHeader.createCell(headerIndex).setCellValue(allowExcel.name());
					headerIndex++;
				}
			}

			// 在sheet里创建表头下的数据
			for (int i = 1; i <= list.size(); i++) {
				HSSFRow row = sheet.createRow(i);
				int cellIndex = 0;
				for (int j = 0; j < fields.length; j++) {
					Field field = fields[j];
					ExcelAnnotation allowExcel = field.getAnnotation(ExcelAnnotation.class);
					if (allowExcel != null && allowExcel.name() != null) {
						Class<?> attrType = field.getType();

						String name = field.getName();
						Object value = getAttrVal(list.get(i - 1), name, attrType);
						if (value != null) {
							String tname = attrType.getName();
							if (tname.equals("java.util.Date") || tname.equals("java.util.DateTime")) {
								String fmt = allowExcel.fmt();
								if (StringUtils.isNotEmpty(fmt)) {

									SimpleDateFormat formatter = new SimpleDateFormat(fmt);
									String date = formatter.format(value);
									row.createCell(cellIndex).setCellValue(date);
								} else {
									row.createCell(cellIndex).setCellValue(value.toString());

								}
							} else {
								row.createCell(cellIndex).setCellValue(value.toString());

							}

						} else {
							row.createCell(cellIndex).setCellValue("");

						}
						style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
						cellIndex++;
					}

				}
			}

			// 输出Excel文件
			try {
				FileOutputStream out = new FileOutputStream(outFilePath);
				wb.write(out);
				out.close();
				LOGGER.info("导出成功!");
			} catch (FileNotFoundException e) {
				LOGGER.info("导出失败！");
				e.printStackTrace();
			} catch (IOException e) {
				LOGGER.info("导出失败！");
				e.printStackTrace();
			}
		} catch (Exception e) {
			LOGGER.info("导出失败！");
			e.printStackTrace();
		}

	}

	public static HSSFWorkbook exportExcel(List<ExportModel> array) throws Exception {
		// Class<?> demo = null;
		try {

			// 创建HSSFWorkbook对象(excel的文档对象)
			HSSFWorkbook wb = new HSSFWorkbook();

			for (int k = 0; k < array.size(); k++) {
				ExportModel exp = array.get(k);

				JSONArray list = exp.getList();
				// 建立新的sheet对象（excel的表单）
				HSSFSheet sheet = wb.createSheet("sheet" + k);
				// 声明样式
				HSSFCellStyle style = wb.createCellStyle();
				// 居中显示
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				// 在sheet里创建第一行为表头，参数为行索引(excel的行)，可以是0～65535之间的任何一个
				HSSFRow rowHeader = sheet.createRow(0);
				// 创建单元格并设置单元格内容

				int headerIndex = 0;
				JSONArray head = exp.getHead();
				if (head != null) {
					for (int j = 0; j < head.size(); j++) {
						JSONObject h = head.getJSONObject(j);

						rowHeader.createCell(headerIndex).setCellValue(h.getString("title"));

						headerIndex++;

					}
				}

				// 在sheet里创建表头下的数据
				for (int i = 1; i <= list.size(); i++) {
					HSSFRow row = sheet.createRow(i);
					int cellIndex = 0;
					for (int j = 0; j < head.size(); j++) {
						JSONObject h = head.getJSONObject(j);
						String field = h.getString("prop");
						JSONObject json = list.getJSONObject(i - 1);

						String name = field;
						Object value = json.get(name);
						if (value != null) {

							row.createCell(cellIndex).setCellValue(value.toString());

						}

						style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
						cellIndex++;
					}
				}

			}
			return wb;

		} catch (Exception e) {
			LOGGER.info("导出失败！");
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * String类型日期转为Date类型
	 *
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	public static Date dateConvertFormat(String dateStr) throws ParseException {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		date = format.parse(dateStr);
		return date;
	}

}
