package com.bds.portal.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONException;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bds.model.DataConfig;
import com.bds.model.ExportModel;
import com.bds.model.ExportRecord;
import com.bds.model.ExportTable;
import com.bds.model.Result;
import com.bds.model.TaskPage;
import com.bds.model.TaskPageParse;
import com.bds.model.Template;
import com.bds.model.TemplateDetail;
import com.bds.portal.util.Const;
import com.bds.portal.util.ExcelUtil;

@Component
public class ExportService {

	private static Logger logger = Logger.getLogger(ExportService.class);

	@Resource
	private NutDao mysqlDao;

	public HSSFWorkbook exportExcel(Long user_id, String request_id) {
		try {
			Template t = mysqlDao.fetch(Template.class, Cnd.where("request_id", "=", request_id));
			if (t != null) {
				List<ExportModel> list = new ArrayList<ExportModel>();

				for (int i = 1; i <= t.getMax_page(); i++) {
					Result ret = this.getData(user_id, request_id, i);
					if (ret.getData() != null) {
						ExportModel exp = new ExportModel();
						exp.setList((JSONArray) ret.getData());
						exp.setHead((JSONArray) ret.getInfo());
						list.add(exp);
						//
					}
				}
				HSSFWorkbook wb = ExcelUtil.exportExcel(list);
				return wb;
			}
		} catch (Exception e) {
			logger.info("", e);

		}
		return null;
	}

	public JSONObject exportJson(Long user_id, String request_id) {
		JSONObject json = new JSONObject();
		try {
			Template t = mysqlDao.fetch(Template.class, Cnd.where("request_id", "=", request_id));
			if (t != null) {
				JSONArray list = new JSONArray();
				JSONArray head = new JSONArray();
				for (int i = 1; i <= t.getMax_page(); i++) {
					Result ret = this.getData(user_id, request_id, i);
					if (ret.getData() != null) {
						JSONObject row = new JSONObject();
						JSONArray array = (JSONArray) ret.getData();
						row.put("page", i);
						row.put("data", array);
						list.add(row);
					}
					if (head.size() == 0) {
						head = (JSONArray) ret.getInfo();
					}
				}

				json.put("head", head);
				json.put("list", list);

				return json;
			}
		} catch (Exception e) {
			logger.info("", e);

		}
		return null;
	}

	public Result getData(Long user_id, String request_id, Integer index) {

		Result ret = new Result();
		try {
			TaskPage job = mysqlDao.fetch(TaskPage.class,
					Cnd.where("request_id", "=", request_id).and("page", "=", index).and("status", "=", 5));
			if (job == null) {
				return ret;
			}
			// String requestid = job.getRequest_id();
			// int count = mysqlDao.count(TaskPage.class, Cnd.where("request_id", "=",
			// requestid));
			TaskPageParse page = mysqlDao.fetch(TaskPageParse.class,
					Cnd.where("tpid", "=", job.getId()).and("user_id", "=", user_id));
			if (page != null) {
				JSONArray array = JSONObject.parseArray(page.getResult());
				JSONArray head = new JSONArray();
				String[] ts = page.getHead().split(",");
				String[] titles = page.getTitle().split(",");
				for (int i = 0; i < ts.length; i++) {
					String prop = ts[i];
					JSONObject h = new JSONObject();
					h.put("prop", prop);
					h.put("title", titles[i]);
					head.add(h);
				}
				ret.setInfo(head);

				ret.setData(array);
				ret.setCode(Const.SUC);
			} else {
				ret.setData(new JSONArray());
				ret.setCode(Const.SUC);
			}

			return ret;
		} catch (Exception e) {
			logger.info("", e);
			ret.setCode(Const.FAIL);

		}
		return ret;
	}

	public void exportSql(Long user_id, String sql_id, String config_id, String sel_requestId) {
		ExportRecord exportRecord = new ExportRecord();
		try {
			exportRecord.setUser_id(user_id);
			exportRecord.setCreate_time(new Date());
			exportRecord.setRequest_id(sel_requestId);
			exportRecord.setTable_id(Long.parseLong(config_id));
			exportRecord.setStatus(1);
			mysqlDao.insert(exportRecord);
			List<TaskPageParse> task_list = mysqlDao.query(TaskPageParse.class,
					Cnd.where("request_id", "=", sel_requestId));
			DataConfig dataConfig = mysqlDao.fetch(DataConfig.class, Cnd.where("id", "=", sql_id));
			ExportTable t_table = mysqlDao.fetch(ExportTable.class, Cnd.where("id", "=", config_id));
			List<TemplateDetail> templateDetail = mysqlDao.query(TemplateDetail.class,
					Cnd.where("request_id", "=", sel_requestId));
			Connection con = null;
			if (dataConfig.getType().equals("mysql")) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(dataConfig.getUrl(), dataConfig.getUsername(), dataConfig.getPwd());
			} else if (dataConfig.getType().equals("sqlserver")) {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				con = DriverManager.getConnection(dataConfig.getUrl(), dataConfig.getUsername(), dataConfig.getPwd());
			} else if (dataConfig.getType().equals("oracle")) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(dataConfig.getUrl(), dataConfig.getUsername(), dataConfig.getPwd());
			} else if (dataConfig.getType().equals("postgresql")) {
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection(dataConfig.getUrl(), dataConfig.getUsername(), dataConfig.getPwd());
			}
			Statement statement = con.createStatement();
			if (null != task_list) {
				for (int i = 0; i < task_list.size(); i++) {
					String task_arr = task_list.get(i).getResult();
					// 获取当前页码下的数据
					org.json.JSONArray array = new org.json.JSONArray(task_arr);
					String sql_col = "(";
					String attr_name[] = new String[templateDetail.size()];
					int num = 0;
					String sql_val = "(";
					if (null != array) {
						// 用迭代的方法获取当前页json属性名。
						org.json.JSONObject json = array.getJSONObject(0);
						Iterator<String> iterator = json.keys();
						while (iterator.hasNext()) {
							attr_name[num] = iterator.next();
							num++;
						}
						for (int j = 0; j < array.length(); j++) {
							// 当前jsonObject

							// 拼接insert语句
							for (int k = 0; k < num; k++) {
								if (j != array.length() - 1) {
									if (k != num - 1) {
										if (j == 0) {
											sql_col += "" + attr_name[k] + ",";
										}
										try {
											sql_val += "'" + array.getJSONObject(j).get(attr_name[k]) + "',";
										} catch (JSONException e) {
											sql_val += "'',";
										}

									} else {
										if (j == 0) {
											sql_col += "" + attr_name[k] + ")";
										}
										try {
											sql_val += "'" + array.getJSONObject(j).get(attr_name[k]) + "'),(";
										} catch (JSONException e) {
											sql_val += "''),(";
										}
									}
								} else {
									if (k != num - 1) {
										if (j == 0) {
											sql_col += "" + attr_name[k] + ",";
										}
										try {
											sql_val += "'" + array.getJSONObject(j).get(attr_name[k]) + "',";
										} catch (JSONException e) {
											sql_val += "'',";
										}
									} else {
										if (j == 0) {
											sql_col += "" + attr_name[k] + ")";
										}
										try {
											sql_val += "'" + array.getJSONObject(j).get(attr_name[k]) + "');";
										} catch (JSONException e) {
											sql_val += "'');";
										}
									}
								}
							}
						}
						// 不为null每一页插入一次数据
						String sql_insert = "INSERT INTO " + t_table.getName() + sql_col + " VALUES" + sql_val;
						statement.execute(sql_insert);
					}
				}
			}
			mysqlDao.update(ExportRecord.class, Chain.make("status", 2).add("update_time", new Date()),
					Cnd.where("id", "=", exportRecord.getId()));
		} catch (Exception e) {
			logger.info("", e);
			mysqlDao.update(ExportRecord.class, Chain.make("status", 3).add("update_time", new Date()),
					Cnd.where("id", "=", exportRecord.getId()));
		}
	}
}
