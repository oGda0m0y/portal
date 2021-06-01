package com.bds.portal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bds.model.Result;
import com.bds.model.TNode;
import com.bds.model.Task;
import com.bds.model.Template;
import com.bds.model.TemplateDetail;
import com.bds.portal.util.Const;
import com.bds.portal.util.HTMLUploadOSSUtil;

import shaded.org.apache.commons.lang3.StringUtils;

@Component
public class TempletService {
	private static Logger logger = Logger.getLogger(TempletService.class);

	@Resource
	private NutDao mysqlDao;

	public Result addTemplateDetail(String requestId, String columnName, String type, String thinkDataId) {
		Result result = new Result();

		List<TNode> nodeArray = new ArrayList<TNode>();

		try {
			String html = HTMLUploadOSSUtil.downloadHtml(requestId);
			Task task = mysqlDao.fetch(Task.class, Cnd.where("request_id", "=", requestId));

			Document doc = Jsoup.parse(html, task.getCharset());

			List<TemplateDetail> tlist = new ArrayList<TemplateDetail>();

			TemplateDetail templateDetail = new TemplateDetail();
			templateDetail.setCreate_time(new Date());
			templateDetail.setColumn_name(columnName);
			templateDetail.setColumn_type(type);
			templateDetail.setRequest_id(requestId);

			/**
			 * 解析列表或者单个元素的cssSelector
			 */
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			Element nodeTag = doc.getElementsByAttributeValue(Const.THINKDATA_ID, thinkDataId).first();
			String tagName = nodeTag.tagName();
			String cssSelector = nodeTag.cssSelector();
			int siblingIndex = doc.select(cssSelector).first().siblingIndex();
			Integer index = 0;
			if (type.equals(Const.TYPE_LIST)) {
				index = 0;
				String[][] tmp = parseListCssSelector(cssSelector, doc);
				String merge = "";

				for (int i = 0; i < tmp.length; i++) {
					merge += tmp[0][i];
					if (tmp[1][0] != null) {
						// urlMerge += tmp[1][i];
					}
					if (i == 0) {
						templateDetail.setList_path(tmp[0][i]);
					} else {
						templateDetail.setNode_path(tmp[0][i]);
					}
				}
				// 获取与传来的元素类似的所有元素
				Elements eles = doc.select(merge);

				nodeArray = parseElements(eles, uuid, templateDetail, siblingIndex, false);

			} else {
				index = 1;
				nodeArray = parseElements(doc.select(cssSelector), uuid, templateDetail, siblingIndex, false);

				templateDetail.setNode_path(cssSelector);
			}
			boolean aflage = false;
			TNode fnode = nodeArray.get(0);
			String th = "";
			if (fnode != null) {
				if (tagName.equalsIgnoreCase("a")) {
					aflage = true;

					th = "<th index=" + index + "  uuid='" + uuid + "'><a href='javascript:void(0)'>" + columnName
							+ " </a> " + this.getButtons(uuid, templateDetail) + " </th>";
					templateDetail.setNode_value("text");

				} else if (fnode.getAname().equals("src")) {

					th = "<th  index=" + index + " uuid='" + uuid + "'><a href='javascript:void(0)'>" + columnName
							+ "图片</a> " + this.getButtons(uuid, templateDetail) + "</th>";
					templateDetail.setNode_value("href");
				} else {

					th = "<th index=" + index + "  uuid='" + uuid + "'><a  href='javascript:void(0)'>" + columnName
							+ " </a>" + this.getButtons(uuid, templateDetail) + "</th>";
					templateDetail.setNode_value("text");
				}
			}
			templateDetail.setTh(th);

			templateDetail.setUnique_id(uuid);

			templateDetail.setTag_name(tagName);
			templateDetail.setElements(nodeArray);
			templateDetail.setSibling_index(siblingIndex);
			tlist.add(templateDetail);
			if (aflage) {
				TemplateDetail target = this.getTemplateDetail(requestId, columnName, type, thinkDataId, doc);
				if (target != null) {
					tlist.add(target);
				}

			}

			result.setData(tlist);

			result.setMsg("解析元素成功");
			result.setCode(Const.SUC);

			logger.info("解析元素成功：requestid：" + requestId);
		} catch (Exception e) {
			result.setData(null);
			result.setRet(false);
			result.setMsg("解析元素异常");
			result.setCode(Const.FAIL);

			logger.warn("解析元素异常：requestid：" + requestId, e);
		}

		return result;
	}

	public TemplateDetail getTemplateDetail(String requestId, String columnName, String type, String thinkDataId,
			Document doc) {

		List<TNode> nodeArray = new ArrayList<TNode>();

		try {

			TemplateDetail templateDetail = new TemplateDetail();
			templateDetail.setCreate_time(new Date());
			templateDetail.setColumn_name(columnName);
			templateDetail.setColumn_type(type);
			templateDetail.setRequest_id(requestId);

			/**
			 * 解析列表或者单个元素的cssSelector
			 */
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			Element nodeTag = doc.getElementsByAttributeValue(Const.THINKDATA_ID, thinkDataId).first();
			String tagName = nodeTag.tagName();
			String cssSelector = nodeTag.cssSelector();
			int siblingIndex = doc.select(cssSelector).first().siblingIndex();
			Integer index = 0;
			if (type.equals(Const.TYPE_LIST)) {
				index = 0;
				String[][] tmp = parseListCssSelector(cssSelector, doc);
				String merge = "";

				for (int i = 0; i < tmp.length; i++) {
					merge += tmp[0][i];
					if (tmp[1][0] != null) {
						// urlMerge += tmp[1][i];
					}
					if (i == 0) {
						templateDetail.setList_path(tmp[0][i]);
					} else {
						templateDetail.setNode_path(tmp[0][i]);
					}
				}
				// 获取与传来的元素类似的所有元素
				Elements eles = doc.select(merge);

				nodeArray = parseElements(eles, uuid, templateDetail, siblingIndex, true);

			} else {
				index = 1;
				nodeArray = parseElements(doc.select(cssSelector), uuid, templateDetail, siblingIndex, true);

				templateDetail.setNode_path(cssSelector);
			}

			TNode fnode = nodeArray.get(0);
			String th = "";
			if (fnode != null) {
				if (tagName.equalsIgnoreCase("a")) {
					th = "<th index=" + index + "  uuid='" + uuid + "'><a href='javascript:void(0)'>" + columnName
							+ "链接</a> " + this.getButtons(uuid, templateDetail) + " </th>";
					templateDetail.setNode_value("href");
				} else if (fnode.getAname().equals("src")) {

					th = "<th  index=" + index + " uuid='" + uuid + "'><a href='javascript:void(0)'>" + columnName
							+ "图片</a> " + this.getButtons(uuid, templateDetail) + "</th>";
					templateDetail.setNode_value("href");
				} else {

					th = "<th index=" + index + "  uuid='" + uuid + "'><a  href='javascript:void(0)'>" + columnName
							+ " </a>" + this.getButtons(uuid, templateDetail) + "</th>";
					templateDetail.setNode_value("text");
				}
			}
			templateDetail.setTh(th);

			templateDetail.setUnique_id(uuid);

			templateDetail.setTag_name(tagName);
			templateDetail.setElements(nodeArray);
			templateDetail.setSibling_index(siblingIndex);
			return templateDetail;

		} catch (Exception e) {

			logger.error("解析元素异常：requestid：" + requestId, e);
		}

		return null;
	}

	private String getButtons(String uuid, TemplateDetail templateDetail) {
		String html = "<div class=\"btn-group\">"
				+ "                                                <a href=\"#\" data-toggle=\"dropdown\" type=\"button\" class=\"btn btn-default btn-sm dropdown-toggle\">编辑<i class=\"fa fa-cogs\"></i> <span class=\"caret\"></span></a>"
				+ "                                                <ul role=\"menu\" class=\"dropdown-menu\">"
				+ "                                       			 <li><a href='javascript:void(0)' onclick=\"editCol('"
				+ uuid + "')\">编辑标题 <i class=\"fa fa-font\"></i> </a></li>"
				+ "                                                    <li><a href='javascript:void(0)' onclick=\"delCol(this,'"
				+ uuid + "')\">删除列 <i class=\"fa fa-trash-o\"></i></a> </li>"
				+ "                                                </ul>" + " 		</div>";
		return html;
	}

	public Result addTemplate(String requestId, long userId, String templateName, JSONArray rows, Integer rlength) {
		Result result = new Result();
		Template template = mysqlDao.fetch(Template.class,
				Cnd.where("request_id", "=", requestId).and("user_id", "=", userId));

		Task task = mysqlDao.fetch(Task.class, Cnd.where("request_id", "=", requestId));

		try {
			if (template != null) {
				mysqlDao.clear(TemplateDetail.class, Cnd.where("tid", "=", template.getId()));
			} else {
				template = new Template();
				template.setRequest_id(requestId);
				template.setTemplate_name(templateName);
				template.setUser_id(userId);
				template.setUrl(task.getUrl());
				template.setCreate_time(new Date());
				mysqlDao.insert(template);
			}

			int j = 0;
			for (int i = 0; i < rows.size(); i++) {
				JSONObject row = rows.getJSONObject(i);
				String th = row.getString("th");
				String column_name = row.getString("column_name");
				String thfrist = th.substring(0, th.indexOf(">") + 1);
				// Document thtml = Jsoup.parse("<div>"+th+"</div>");
				Document thtml = Jsoup.parseBodyFragment(th);
				Element ea = thtml.select("a").first();
				ea.text(column_name);
				String thead = thtml.select("body").html();
				row.put("th", thfrist + thead + "</th>");
				TemplateDetail detail = JSONObject.parseObject(row.toJSONString(), TemplateDetail.class);
				// TemplateDetail detailUrl = null;

				// if (row.getString("tag_name").equals("a")) {
				// detail.setNode_value("text");
				// detailUrl = (TemplateDetail) detail.clone();
				// detailUrl.setNode_value("href");
				// detailUrl.setColumn_name(detail.getColumn_name());
				// detailUrl.setUnique_id(UUID.randomUUID().toString().replaceAll("-", ""));
				// } else if (row.getString("tag_name").equals("img")) {
				// detail.setNode_value("src");
				// } else {
				// detail.setNode_value("text");
				// }

				detail.setUser_id(userId);
				detail.setRequest_id(template.getRequest_id());
				detail.setCreate_time(new Date());
				detail.setTid(template.getId());
				detail.setPropert("prop" + (j + 1));
				j = j + 1;
				mysqlDao.fastInsert(detail);
				// if (detailUrl != null) {
				// detailUrl.setUser_id(userId);
				// detailUrl.setRequest_id(template.getRequest_id());
				// detailUrl.setCreate_time(new Date());
				// detailUrl.setTid(template.getId());
				// detailUrl.setPropert("prop" + (j + 1));
				// mysqlDao.fastInsert(detailUrl);
				// j = j + 1;
				// }
			}
			mysqlDao.update(Task.class, Chain.make("rows", rlength).add("json", rows),
					Cnd.where("id", "=", task.getId()));
			List<TemplateDetail> details = mysqlDao.query(TemplateDetail.class,
					Cnd.where("tid", "=", template.getId()).asc("propert"));
			JSONArray array = new JSONArray();
			for (TemplateDetail d : details) {
				JSONObject o = new JSONObject();
				o.put("prop", d.getPropert());
				o.put("len", d.getNode_length());
				array.add(o);
			}
			mysqlDao.update(Template.class, Chain.make("props", array.toJSONString()),
					Cnd.where("id", "=", template.getId()));
			result.setCode(Const.SUC);
			result.setRet(true);
			result.setMsg("创建成功");

			logger.info("插入template成功：userId:" + userId + ",requestId:" + requestId);
		} catch (Exception e) {
			result.setCode(Const.FAIL);
			result.setRet(false);
			result.setMsg("创建异常");

			logger.warn("插入template异常：userId:" + userId + ",requestId:" + requestId, e);
		}

		return result;
	}

	/**
	 * 分解cssSelector， 第一部分：列表分支之前的（用于循环） 第二部分：列表分支之后的（用于取值）
	 * 若包含url，则二维数组中r[1][0],r[1][1]放url
	 */
	private String[][] parseListCssSelector(String cssSelector, Document doc) {
		String[][] result = new String[2][2];
		String r1 = "", r2 = "";
		String tmp = "";

		/*
		 * if (cssSelector.indexOf("#") == 0) { try { String ele = tmp.substring(0,
		 * tmp.indexOf(">")); String tagName = doc.getElementById(ele.replace("#",
		 * "")).tagName();
		 * 
		 * tmp = tmp.replaceAll(ele, tagName); } catch (Exception e) { result[0][0] =
		 * tmp; result[0][1] = ""; return result; } }
		 */

		// 只取class的第一部分
		for (String classes : cssSelector.split(">")) {
			String[] classesArray = classes.split("\\.", 3);
			switch (classesArray.length) {
			case 1: {
				tmp = tmp + ">" + classesArray[0];
				break;
			}
			case 2: {
				tmp = tmp + ">" + classesArray[0] + "." + classesArray[1];
				break;
			}
			case 3: {
				tmp = tmp + ">" + classesArray[0] + "." + classesArray[1];
				break;
			}
			}
		}

		tmp = tmp.substring(1).replaceAll(" ", "");
		try {
			r1 = tmp.substring(0, tmp.lastIndexOf(":"));
			result[0][0] = r1;

			try {
				r2 = tmp.replace(r1, "").substring(tmp.replace(r1, "").indexOf(">"));
				String r2Tmp = r2.split(">")[r2.split(">").length - 1];
				String[] nodeTmp = r2Tmp.split("\\.");
				if (nodeTmp.length > 1) {
					r2 = r2.replace(r2Tmp, nodeTmp[0] + "." + nodeTmp[1]);
				}
				result[0][1] = r2;
			} catch (Exception e) {
				result[0][1] = "";
				// logger.error("r2异常", e);
			}
		} catch (Exception e) {

			result[0][0] = tmp;
			result[0][1] = "";
			// return result;
		}

		String[] selectorTmp = (result[0][0] + result[0][1]).split(">");
		String preNode = "";// 记录前一次循环的元素节点

		/**
		 * 从最后一级往上找，直到找到元素数量大于1的为止
		 */
		for (int i = selectorTmp.length - 1; i >= 0; i--) {
			String parentNodeTmp = "";

			// 拼接上一级元素
			for (int j = 0; j <= i; j++) {
				parentNodeTmp = parentNodeTmp + selectorTmp[j] + ">";
			}

			if (i != 0) {
				// 如果元素数量大于1时的操作
				if (doc.select(parentNodeTmp.substring(0, parentNodeTmp.length() - 1)).size() > 1) {
					// 标明是否真的满足条件，用来去除兄弟节点相同path的情况,
					// 计算后，若flag>1,则满足
					int flag = 0;
					for (int c = 0; c < doc.select(parentNodeTmp.substring(0, parentNodeTmp.length() - 1))
							.size(); c++) {
						Node node = doc.select(parentNodeTmp.substring(0, parentNodeTmp.length() - 1)).get(c);
						Node parentNode;
						if (i == selectorTmp.length - 1) {
							parentNode = doc.select(cssSelector).first();
						} else {
							parentNode = doc.select(cssSelector).parents().get(selectorTmp.length - 2 - i);
						}
						if (node.attr(Const.THINKDATA_ID).equals(parentNode.attr(Const.THINKDATA_ID))) {
							flag += 1;
							// 减少多余循环，一旦大于1了，尽早跳出
							if (flag > 1) {
								break;
							}
						}
					}
					// 若计算后，flag<=1，则进入上一级继续判断
					if (flag <= 1) {
						preNode = parentNodeTmp;
						continue;
					}
					// 如果在不是最后一级（即不是第一次循环）满足元素数量大于1了，
					// 则将该元素后一级的tag替换原来的值
					// （原来的值可能包含类名，id等，即div.className被替换成div，因为className是特殊属性）
					if (i < selectorTmp.length - 1) {
						selectorTmp[i + 1] = doc.select(preNode.substring(0, preNode.length() - 1)).get(0).tag()
								.getName();
					} else {
						// 如果在最后一级就满足了元素数量大于1，则说明是正确的，不用特殊处理了
						break;
					}
					// 拼接新的cssSelector
					String newCss = "";
					for (int j = 0; j < selectorTmp.length; j++) {
						newCss = newCss + selectorTmp[j] + ">";
					}
					// 用新的cssSelector替换原来的
					if (result[0][1].equals("")) {
						result[0][0] = newCss.substring(0, newCss.length() - 1);
					} else {
						result[0][1] = newCss.substring(0, newCss.length() - 1).replace(result[0][0], "");
					}
					break;
				} else {
					// 如果不满足元素数量大于1，则记录本次循环的元素，该元素作为下一次循环的后一级元素
					preNode = parentNodeTmp;
				}
			} else {
				// 如果到第一级还没找到满足元素数量大于1的情况，则说明第一级就是特殊的，取第一级的tag即可
				parentNodeTmp = doc.select(parentNodeTmp.substring(0, parentNodeTmp.length() - 1)).get(0).tag()
						.getName();
				selectorTmp[0] = parentNodeTmp;
				String newCss = "";
				for (int j = 0; j < selectorTmp.length; j++) {
					newCss = newCss + selectorTmp[j] + ">";
				}
				if (result[0][1].equals("")) {
					result[0][0] = newCss.substring(0, newCss.length() - 1);
				} else {
					result[0][0] = newCss.substring(0, newCss.length() - 1).replace(result[0][1], "");
				}
			}
		}

		// 递归，获取对应的url
		Elements childNodes = doc.select(cssSelector).get(0).children();
		for (Element e : childNodes) {
			if (!e.attr("href").equals("") && !e.attr("href").startsWith("javascript")) {
				result[1][0] = result[0][0];
				result[1][1] = result[0][1] + ">" + e.tagName();
				break;
			} else {
				String nodePath = e.cssSelector();
				parseListCssSelector(nodePath, doc);
			}
		}

		return result;
	}

	public Result delTemplateDetail(String uniqueId) {
		Result result = new Result();
		try {
			// detail应该在删除模板的时候关联删除
			// mysqlDao.clear(TemplateDetail.class, Cnd.where("unique_id", "=", uniqueId));
			result.setData(null);
			result.setRet(true);
			result.setMsg("删除column成功");
			result.setCode(Const.SUC);
			logger.info("删除column成功：unique_id：" + uniqueId);
		} catch (Exception e) {
			result.setData(null);
			result.setRet(false);
			result.setMsg("删除column失败");
			result.setCode(Const.FAIL);
			logger.error("删除column失败：unique_id：" + uniqueId, e);
		}
		return result;
	}

	public Result updTemplateDetail(String newColumnName, String uniqueId) {
		Result result = new Result();
		try {
			mysqlDao.update(TemplateDetail.class, Chain.make("", newColumnName), Cnd.where("unique_id", "=", uniqueId));
			result.setData(null);
			result.setRet(true);
			result.setMsg("更新column成功");
			result.setCode(Const.SUC);
			logger.info("更新column成功：unique_id：" + uniqueId + ",new_column_name" + newColumnName);
		} catch (Exception e) {
			result.setData(null);
			result.setRet(false);
			result.setMsg("更新column失败");
			result.setCode(Const.FAIL);
			logger.error("更新column失败：unique_id：" + uniqueId + ",new_column_name" + newColumnName);
		}
		return result;
	}

	public Result getTemplateDetail(String requestId) {
		Result result = new Result();
		try {

			// Document doc = Jsoup.parse(html, task.getCharset());
			// List<TemplateDetail> details = mysqlDao.query(TemplateDetail.class,
			// Cnd.where("request_id", "=", requestId));
			// details = parseDetail(details, doc);

			Task task = mysqlDao.fetch(Task.class, Cnd.where("request_id", "=", requestId));
			String json = task.getJson();
			JSONArray array = JSONArray.parseArray(json);
			result.setData(array);
			result.setCode(Const.SUC);
			logger.info("获取模板detail成功：requestId：" + requestId);
		} catch (Exception e) {
			result.setData(null);
			result.setRet(false);
			result.setMsg("获取模板detail失败");
			result.setCode(Const.FAIL);
			logger.error("获取模板detail失败：requestId：" + requestId);
		}
		return result;
	}

	public List<TemplateDetail> parseDetail(List<TemplateDetail> details, Document doc) {

		for (TemplateDetail detail : details) {

			if (detail.getColumn_type().equals(Const.TYPE_LIST)) {
				if (detail.getNode_value().equals("href")) {
					detail.setElements(parseElements(doc.select(detail.getList_path() + detail.getNode_path()),
							detail.getUnique_id(), detail, detail.getSibling_index(), true));
				} else {
					detail.setElements(parseElements(doc.select(detail.getList_path() + detail.getNode_path()),
							detail.getUnique_id(), detail, detail.getSibling_index(), false));
				}

			} else {
				if (detail.getNode_value().equals("href")) {
					detail.setElements(parseElements(doc.select(detail.getNode_path()), detail.getUnique_id(), detail,
							detail.getSibling_index(), true));
				} else {
					detail.setElements(parseElements(doc.select(detail.getNode_path()), detail.getUnique_id(), detail,
							detail.getSibling_index(), false));
				}

			}

		}
		return details;
	}

	/**
	 * 表示是取url还是普通元素，url：1，普通：0
	 */
	private List<TNode> parseElements(Elements elements, String uuid, TemplateDetail node, int siblingIndex,
			boolean a) {
		List<TNode> nodes = new ArrayList<TNode>();

		Integer length = 0;
		for (Element e : elements) {
			if (e.siblingIndex() != siblingIndex) {
				continue;
			}
			String thinkDataIdTmp = e.attr(Const.THINKDATA_ID);
			String elementValue = e.text();
			String href = e.attr("href");
			String src = e.attr("src");
			String tag = e.tagName();
			// 若没有href或者src，则试着去子节点里找，看看有没有，
			// 例如<div><a href='http://' /></div> 就属于这种情况
			// if (href.equals("") && src.equals("")) {
			// Elements childNodes = e.children();
			// while (childNodes.size() > 0) {
			// for (Element tmp : childNodes) {
			// childNodes = tmp.children();
			// href = tmp.attr("href");
			// src = tmp.attr("src");
			// if (!href.equals("") || !src.equals("")) {
			// break;
			// }
			// }
			// if (!href.equals("") || !src.equals("")) {
			// break;
			// }
			// }
			// }
			TNode tnode = new TNode();
			tnode.setId(thinkDataIdTmp);
			tnode.setValue(elementValue);
			if (StringUtils.isNotEmpty(elementValue)) {
				if (elementValue.length() > length) {
					length = elementValue.length() * 2;
				}
			}

			tnode.setTag(tag);
			String td = "";
			if (tag.equalsIgnoreCase("a")) {
				if (a) {
					td = "<td class='tdspan'  uuid='" + uuid + "'>" + href + "</td>";
					tnode.setAname("href");
					tnode.setAvalue(href);
				} else {
					td = "<td class='tdspan'  uuid='" + uuid + "'>" + elementValue + "</td>";
					tnode.setAname("href");
					tnode.setAvalue(href);
				}

			} else if (tag.equalsIgnoreCase("img")) {
				td = "<td class='tdspan'  uuid='" + uuid + "'>" + src + "</td>";
				tnode.setAname("src");
				tnode.setAvalue(src);
			} else {
				td = "<td class='tdspan'  uuid='" + uuid + "'>" + elementValue + "</td>";
				tnode.setAname("text");
				tnode.setAvalue(elementValue);
			}

			tnode.setTd(td);
			nodes.add(tnode);
		}
		if (length < 128) {
			length = 128;
		}
		node.setNode_length(length);

		return nodes;
	}
	/**
	 * 回显示
	 * 
	 * @param listPath
	 * @param nodePath
	 * @param requestId
	 * @param columnName
	 * @param columnType
	 * @return
	 */

	/**
	 * public Result parseElePath(String listPath, String nodePath, String
	 * requestId, String columnName, String columnType) { Result result = new
	 * Result(); try { String html = HTMLUploadOSSUtil.downloadHtml(requestId); Task
	 * task = mysqlDao.fetch(Task.class, Cnd.where("request_id", "=", requestId));
	 * Document doc = Jsoup.parse(html, task.getCharset()); Elements eles =
	 * doc.select(listPath + nodePath); int siblingIndex =
	 * eles.get(0).siblingIndex();
	 * 
	 * TemplateDetail templateDetail = new TemplateDetail();
	 * templateDetail.setCreate_time(new Date());
	 * templateDetail.setColumn_name(columnName);
	 * templateDetail.setColumn_type(columnType);
	 * templateDetail.setRequest_id(requestId);
	 * templateDetail.setList_path(listPath); templateDetail.setNode_path(nodePath);
	 * templateDetail.setUnique_id(UUID.randomUUID().toString().replaceAll("-",
	 * "")); List<TNode> nodes = parseElements(eles, templateDetail.getUnique_id(),
	 * templateDetail, siblingIndex,false); templateDetail .setElements(nodes);
	 * 
	 * result.setData(templateDetail); result.setRet(true);
	 * result.setMsg("编辑模板detail元素成功"); result.setCode(Const.SUC);
	 * logger.info("编辑模板detail元素成功：requestId：" + requestId); } catch (Exception e) {
	 * result.setData(null); result.setRet(false); result.setMsg("编辑模板detail元素失败");
	 * result.setCode(Const.FAIL); logger.error("编辑模板detail元素失败：requestId：" +
	 * requestId); } return result; }
	 **/
}
