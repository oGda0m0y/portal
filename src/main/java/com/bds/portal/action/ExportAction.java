package com.bds.portal.action;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.bds.portal.common.result.USession;
import com.bds.portal.service.ExportService;
import com.bds.portal.service.UserService;

@Controller
@RequestMapping("/action/my/m")
public class ExportAction extends BaseAction {
	private static Logger logger = Logger.getLogger(ExportAction.class);
	@Resource
	private UserService userService;
	@Resource
	private ExportService exportService;

	@RequestMapping(value = "/export")
	public void toMyCenter(HttpServletRequest request, HttpServletResponse response, String requestid, String type)
			throws Exception {

		try {
			USession us = this.getUserSession(request);
			if (type.equals("excel")) {
				HSSFWorkbook wb = exportService.exportExcel(us.getId(), requestid);
				if (wb != null) {
					DateFormat FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition",
							"attachment;filename=" + FORMAT.format(new Date()) + ".xls");
					OutputStream out = response.getOutputStream();
					wb.write(out);
					out.flush();
					out.close();
				}
			} else if (type.equals("json")) {
				// ServletOutputStream outputStream = response.getOutputStream();
				JSONObject ret = exportService.exportJson(us.getId(), requestid);
				DateFormat FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
				response.setContentType("application/json");
				response.addHeader("Content-Disposition", "attachment;filename=" + FORMAT.format(new Date()) + ".json");
				OutputStream out = response.getOutputStream();
				out.write(ret.toJSONString().getBytes("utf-8"));
				out.flush();
				out.close();
			}

		} catch (Exception e) {
			logger.error("", e);

		}

	}

}
