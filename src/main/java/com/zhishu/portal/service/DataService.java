package com.zhishu.portal.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.springframework.stereotype.Component;

import com.zhishu.model.DataConfig;
import com.zhishu.model.ExportRecord;
import com.zhishu.model.ExportTable;
import com.zhishu.model.Result;
import com.zhishu.model.Template;
import com.zhishu.portal.common.result.Page;
import com.zhishu.portal.util.Const;
import com.zhishu.portal.util.SpringContextHolder;
import com.zhishu.portal.util.ThreadPools;

@Component
public class DataService {

	private static Logger logger = Logger.getLogger(DataService.class);

	@Resource
	private NutDao mysqlDao;

	public List<DataConfig> getDataConfig(Long id) {
		List<DataConfig> list = mysqlDao.query(DataConfig.class, Cnd.where("user_id", "=", id));
		for (int i = 0; i < list.size(); i++) {
			List<ExportTable> tableLists = mysqlDao.query(ExportTable.class,
					Cnd.where("config_id", "=", list.get(i).getId()));
			list.get(i).setTableList(tableLists);
		}
		return list;
	}

	public Page getExportDataList(Long user_id, Integer pageNum, Integer pageSize) {
		Pager pager = mysqlDao.createPager(pageNum, pageSize);
		Criteria cri = Cnd.cri();
		cri.where().andEquals("user_id", user_id);
		List<ExportRecord> list = mysqlDao.query(ExportRecord.class, cri, pager);
		for (ExportRecord e : list) {
			ExportTable et = mysqlDao.fetch(ExportTable.class, e.getTable_id());
			if (et != null) {
				DataConfig ds = mysqlDao.fetch(DataConfig.class, et.getConfig_id());
				et.setDs(ds);
				e.setTable(et);
			}

		}
		int count = mysqlDao.count(ExportRecord.class, cri);
		Page p = new Page(count, pager.getPageNumber(), pager.getPageSize());
		p.setCode(Const.SUC);
		p.setData(list);
		return p;

	}

	public Boolean addSaveDataSourse(String type, String host, String port, String username, String password,
			String sqldb, Long user_id) {
		Boolean flag = false;
		try {
			DataConfig dataConfig = new DataConfig();
			dataConfig.setDb(sqldb);
			dataConfig.setHost(host);
			dataConfig.setPort(port);
			dataConfig.setPwd(password);
			dataConfig.setType(type);

			dataConfig.setUsername(username);
			dataConfig.setUser_id(user_id);
			dataConfig.setCreate_time(new Date());
			String url = "";
			if (type.equalsIgnoreCase("oracle")) {
				url = "jdbc:oracle:thin:@" + dataConfig.getHost() + ":" + dataConfig.getPort() + ":"
						+ dataConfig.getDb();
			} else if (type.equalsIgnoreCase("sqlserver")) {
				url = "jdbc:sqlserver://" + dataConfig.getHost() + ":" + dataConfig.getPort() + ";DatabaseName="
						+ dataConfig.getDb();
			} else if (type.equalsIgnoreCase("db2")) {
				url = "jdbc:db2://" + dataConfig.getHost() + ":" + dataConfig.getPort() + "/" + dataConfig.getDb();
			} else if (type.equalsIgnoreCase("mysql")) {
				url = "jdbc:mysql://" + dataConfig.getHost() + ":" + dataConfig.getPort() + "/" + dataConfig.getDb()
						+ "?useUnicode=true&characterEncoding=UTF-8";
			} else {
				url = type;
			}
			dataConfig.setUrl(url);
			mysqlDao.insert(dataConfig);
			flag = true;
		} catch (Exception e) {
			logger.info("", e);
		}
		return flag;
	}

	public Boolean modifySaveDataSourse(String type, String host, String port, String username, String password,
			String sqldb, String id) {
		Boolean flag = false;
		try {
			String url = "";
			if (type.equalsIgnoreCase("oracle")) {
				url = "jdbc:oracle:thin:@" + host + ":" + port + ":" + sqldb;
			} else if (type.equalsIgnoreCase("sqlserver")) {
				url = "jdbc:sqlserver://" + host + ":" + port + ";DatabaseName=" + sqldb;
			} else if (type.equalsIgnoreCase("db2")) {
				url = "jdbc:db2://" + host + ":" + port + "/" + sqldb;
			} else if (type.equalsIgnoreCase("mysql")) {
				url = "jdbc:mysql://" + host + ":" + port + "/" + sqldb + "?useUnicode=true&characterEncoding=UTF-8";
			} else {
				url = type;
			}
			Long Id = Long.parseLong(id);
			mysqlDao.update(DataConfig.class, Chain.make("db", sqldb).add("host", host).add("port", port)
					.add("pwd", password).add("type", type).add("url", url).add("username", username),
					Cnd.where("id", "=", Id));
			flag = true;
		} catch (Exception e) {
			logger.info("", e);
		}
		return flag;
	}

	public Boolean delDataSource(Long id) {
		Boolean flag = false;
		try {
			mysqlDao.delete(DataConfig.class, id);
			flag = true;
		} catch (Exception e) {
			logger.info("", e);
		}
		return flag;
	}

	public boolean testConnection(Long id) {
		Boolean flag = false;
		DataConfig dataConfig = mysqlDao.fetch(DataConfig.class, Cnd.where("id", "=", id));
		Connection con = null;
		try {
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
			DatabaseMetaData dbMetaData = con.getMetaData();
			logger.info("数据库已知的用户: " + dbMetaData.getUserName());
			logger.info("数据库的系统函数的逗号分隔列表: " + dbMetaData.getSystemFunctions());
			logger.info("数据库的时间和日期函数的逗号分隔列表: " + dbMetaData.getTimeDateFunctions());
			logger.info("数据库的字符串函数的逗号分隔列表: " + dbMetaData.getStringFunctions());
			logger.info("数据库供应商用于 'schema' 的首选术语: " + dbMetaData.getSchemaTerm());
			logger.info("数据库URL: " + dbMetaData.getURL());
			logger.info("是否允许只读:" + dbMetaData.isReadOnly());
			logger.info("数据库的产品名称:" + dbMetaData.getDatabaseProductName());
			logger.info("数据库的版本:" + dbMetaData.getDatabaseProductVersion());
			logger.info("驱动程序的名称:" + dbMetaData.getDriverName());
			logger.info("驱动程序的版本:" + dbMetaData.getDriverVersion());
			flag = true;
		} catch (Exception e) {
			logger.info("", e);
		}
		return flag;
	}

	public Result saveTable(Long sqlUrlId, String name, String sel_requestId) {
		Result ret = new Result();
		Date date = new Date();
		String createTableSql = "";
		try {
			Template template = mysqlDao.fetch(Template.class, Cnd.where("request_id", "=", sel_requestId));
			DataConfig dataConfig = mysqlDao.fetch(DataConfig.class, Cnd.where("id", "=", sqlUrlId));
			org.json.JSONArray list = new org.json.JSONArray(template.getProps());
			if (dataConfig.getType().equals("mysql")) {
				createTableSql = "CREATE TABLE " + name + "(\n id bigint(40) NOT NULL AUTO_INCREMENT,\n";
			} else if (dataConfig.getType().equals("sqlserver")) {
				createTableSql = "CREATE TABLE " + name + "(\n id bigint IDENTITY (1, 1) ,\n";
			} else if (dataConfig.getType().equals("oracle")) {
				createTableSql = "CREATE TABLE " + name + "(\n id number primary key ,\n";
			} else if (dataConfig.getType().equals("postgresql")) {
				createTableSql = "CREATE TABLE " + name + "(\n id INT PRIMARY KEY NOT NULL,\n";
			}
			if (dataConfig.getType().equals("mysql") || dataConfig.getType().equals("sqlserver")
					|| dataConfig.getType().equals("oracle")) {
				for (int i = 0; i < list.length(); i++) {
					if (i != list.length() - 1) {
						createTableSql += list.getJSONObject(i).get("prop") + " VARCHAR("
								+ list.getJSONObject(i).get("len") + "),";
					} else {
						createTableSql += list.getJSONObject(i).get("prop") + " VARCHAR("
								+ list.getJSONObject(i).get("len") + ")";
					}
				}
			} else if (dataConfig.getType().equals("postgresql")) {
				for (int i = 0; i < list.length(); i++) {
					if (i != list.length() - 1) {
						createTableSql += list.getJSONObject(i).get("prop") + " CHAR("
								+ list.getJSONObject(i).get("len") + "),";
					} else {
						createTableSql += list.getJSONObject(i).get("prop") + " CHAR("
								+ list.getJSONObject(i).get("len") + ")";
					}

				}
			}
			if (dataConfig.getType().equals("mysql")) {
				createTableSql += ",\n PRIMARY KEY  (id) \n)";
			} else if (dataConfig.getType().equals("sqlserver")) {
				createTableSql += ",\n PRIMARY KEY (id)\n)";
			} else if (dataConfig.getType().equals("oracle")) {
				createTableSql += "\n)\n\n CREATE SEQUENCE " + name
						+ "_id_SEQ \nINCREMENT BY 1\nSTART WITH 1\nNOMAXVALUE\nNOCYCLE\nNOCACHE;\n\n create or replace trigger "
						+ name + "_id_SEQ\n before insert on " + name + "\n for each row\nbegin\nselect " + name
						+ "_id_SEQ.nextval into :new.id from dual;\nend;";
			} else if (dataConfig.getType().equals("postgresql")) {
				createTableSql += "\n);\n\n CREATE SEQUENCE " + name
						+ "id_seq\n START WITH 1 \n INCREMENT BY 1 \n NO MINVALUE \n NO MAXVALUE \n CACHE 1; \n\n alter table "
						+ name + " alter column id set default nextval('" + name + "id_seq');";
			}

			ExportTable tableList = new ExportTable();
			tableList.setCreate_date(date);
			tableList.setName(name);
			tableList.setConfig_id(sqlUrlId);
			tableList.setRequest_id(sel_requestId);
			tableList.setCreate_table_sql(createTableSql);
			mysqlDao.insert(tableList);
			ret.setResult(Const.SUC, createTableSql);
		} catch (Exception e) {
			ret.setResult(Const.FAIL, "该数据库已存在该表:" + name);
			logger.info("", e);
		}
		return ret;
	}

	public void exportSource(final Long user_id, final String sql_id, final String config_id, final String sel_requestId) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				ExportService export = SpringContextHolder.getBean(ExportService.class);
				export.exportSql(user_id, sql_id, config_id, sel_requestId);
			}
		});
		ExecutorService pool = ThreadPools.getThreadPool(this, 1);
		pool.execute(thread);
	}
}
