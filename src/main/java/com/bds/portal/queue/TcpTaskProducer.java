package com.bds.portal.queue;

import java.util.Properties;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;

public class TcpTaskProducer {
	private Producer producer;

	private TcpTaskProducer() {
	}

	private static volatile TcpTaskProducer instance = null;

	public static TcpTaskProducer getInstance() {
		if (instance == null) {
			synchronized (TcpApiProducer.class) {
				if (instance == null) {
					instance = new TcpTaskProducer();
					instance.doStart();
				}
			}
		}
		return instance;
	}
	// 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可

	private void doStart() {
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.ProducerId, "PID_BDS_TASK");
		// AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.AccessKey, "hc44qMkvgMdfP51v");
		// SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.SecretKey, "T8rPyyqzj7RSfYM8ZOgRCYiARWxH1D");
		// 设置发送超时时间，单位毫秒
		properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");
		// 设置 TCP 接入域名（此处以公共云生产环境为例）
		properties.put(PropertyKeyConst.ONSAddr, "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
		this.producer = ONSFactory.createProducer(properties);
		producer.start();
	}

	public SendResult send(String body) {
		try {
			Message msg = new Message("bds-task", "task", body.getBytes());

			SendResult sendResult = producer.send(msg);
			System.out.println(sendResult);
			return sendResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
