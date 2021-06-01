package com.bds.portal.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.bds.portal.util.Const;
import com.bds.portal.util.HTMLUploadOSSUtil;

@Controller
@RequestMapping("/action/my/m/uplaod")
public class UploadAction {

	private static Logger logger = Logger.getLogger(UploadAction.class);

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public void uploadFile(HttpServletRequest httpRequest, HttpServletResponse response) throws Exception {
		JSONObject ret = new JSONObject();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		try {

			DiskFileItemFactory factory = new DiskFileItemFactory();

			ServletFileUpload upload = new ServletFileUpload(factory);

			// 获取所有文件列表
			List<FileItem> items = upload.parseRequest(httpRequest);
			FileItem item = items.get(0);
			String fileName = item.getName();

			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			long size = item.getSize();

			if (size > 5048000) {

				ret.put("code", Const.FAIL);
				ret.put("msg", "文件不能超过5M");
				response.getWriter().write(ret.toString());
				return;
			}
			String url = HTMLUploadOSSUtil.uploadFile(fileName, item.getInputStream());

			ret.put("data", url);
			ret.put("code", Const.SUC);
			ret.put("message", "成功上传");

			response.getWriter().write(ret.toString());

			return;
		} catch (Exception e) {
			ret.put("code", "fail");
			ret.put("message", "登陆超时!!!");
			response.getWriter().write(ret.toString());
			logger.error("", e);
			return;
		}

	}

}
