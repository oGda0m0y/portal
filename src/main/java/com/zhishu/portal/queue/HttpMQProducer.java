package com.zhishu.portal.queue;

import java.nio.charset.StandardCharsets;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.impl.authority.AuthUtil;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.zhishu.portal.util.MD5Util;

public class HttpMQProducer {
	private static Logger logger = Logger.getLogger(HttpMQProducer.class);
	private static String accessKey = "hc44qMkvgMdfP51v";
	private static String secretKey = "T8rPyyqzj7RSfYM8ZOgRCYiARWxH1D";

	private static String url = "http://publictest-rest.ons.aliyun.com/message";

	private static final String NEWLINE = "\n";

	/**
	 * 发送定时消息
	 * 
	 * @param msg
	 * @param tag
	 * @param key
	 * @param startDeliverTime
	 * @return
	 */
	public static JSONObject send(String topic, String producerId, String msg, String tag) {
		long time = System.currentTimeMillis();
		JSONObject ret = new JSONObject();
		HttpRequestWithBody req = Unirest.post(url);
		String signString = topic + NEWLINE + producerId + NEWLINE + MD5Util.getMD5(msg) + NEWLINE + time;
		String sign = AuthUtil.calSignature(signString.getBytes(StandardCharsets.UTF_8), secretKey);
		req.header("Signature", sign);
		req.header("AccessKey", accessKey);
		req.header("ProducerID", producerId);
		req.queryString("topic", topic);
		req.queryString("time", time);

		req.body(msg);

		try {
			HttpResponse<String> res = req.asString();
			if (res.getStatus() == 201) {
				ret = JSONObject.parseObject(res.getBody());
				logger.info(res.getBody());
				return ret;
			} else {

				ret = JSONObject.parseObject(res.getBody());
				logger.error(res.getBody());
				logger.error("post message error: {}" + msg + res.getBody());
				return ret;
			}
		} catch (UnirestException e) {
			ret.put("sendStatus", "SEND_FAIL");
			logger.error("post message error: {}", e);
		}
		return ret;
	}

}
