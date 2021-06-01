package com.bds.portal.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.nutz.dao.impl.NutDao;

import com.alibaba.fastjson.JSONObject;
import com.bds.model.MessageTemplate;
import com.ibm.icu.text.MessageFormat;

/**
 * 获取模板内容
 * 
 * @author Administrator
 *
 */
public class MessageUtils {

	private static NutDao dao = (NutDao) SpringContextHolder.getBean("portalDao");
	private static String PREFIX = "TAMPLATE_";
	private static final Object LOCK = new Object();
	/** 10分钟有效期 */
	private static int VALID_PERIOD = 10 * 60;

	/**
	 * 获取模板对应内容
	 * 
	 * @param templateId
	 * @param json
	 * @return
	 */
	public static String getTemplateContent(Long templateId, String json) {
		String f = "";
		synchronized (LOCK) {
			// String key = PREFIX+templateId;
			// if(StringUtils.isEmpty(RedisUtil.getString(key))){
			MessageTemplate template = dao.fetch(MessageTemplate.class, templateId);
			// for(MessageTemplate b : list){
			// RedisUtil.setexString(PREFIX+b.getId(),b.getTemplate(),VALID_PERIOD);
			// }
			// }
			String t = template.getTemplate();
			JSONObject _json = JSONObject.parseObject(json);
			MessageFormat mf = new MessageFormat(t);
			Set<String> keys = mf.getArgumentNames();
			Map<String, Object> map = new HashMap<String, Object>();
			for (String k : keys) {
				map.put(k, _json.get(k));
			}
			f = mf.format(map, new StringBuffer(), null).toString();
		}
		return f;
	}

	public static String getTemplateType(Long templateId) {
		String f = "";
		synchronized (LOCK) {

			MessageTemplate template = dao.fetch(MessageTemplate.class, templateId);
			f = template.getName();
		}
		return f;
	}

}
