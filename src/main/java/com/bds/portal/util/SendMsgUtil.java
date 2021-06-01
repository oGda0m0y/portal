package com.bds.portal.util;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.log4j.Logger;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;

public class SendMsgUtil {
	private static Logger logger = Logger.getLogger(SendMsgUtil.class);
	private static String endpoint = "http://1109356724962371.mns.cn-hangzhou.aliyuncs.com/";
	private static String accessId = "hc44qMkvgMdfP51v";
	private static String accessKey = "T8rPyyqzj7RSfYM8ZOgRCYiARWxH1D";
	private static String topics = "zs-ms";
	private static String templateCode = "SMS_84965001";
	private static String code = "sms-message";
	private static String signname = "智数";
	private static String mailusername="zhishu_service@163.com";//邮箱账号
	private static String pwd="zhishu888!@#";//邮箱登录密码
	private static String password="zhishu888";//邮箱授权码
	protected static CloudAccount account = new CloudAccount(accessId, accessKey, endpoint);

	public synchronized static String SendPhoneCode(String phone) throws Exception {
		MNSClient client = account.getMNSClient();
		String randcode = getCode();
	
		CloudTopic topic = client.getTopicRef(topics);
		RawTopicMessage msg = new RawTopicMessage();
		msg.setMessageBody(code);
		MessageAttributes messageAttributes = new MessageAttributes();
		BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
		// 3.1 设置发送短信的签名（SMSSignName）
		batchSmsAttributes.setFreeSignName(signname);
		// 3.2 设置发送短信使用的模板（SMSTempateCode）
		batchSmsAttributes.setTemplateCode(templateCode);
		// 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
		BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
		// 3.4 增加接收短信的号码
		smsReceiverParams.setParam("code", randcode);
		batchSmsAttributes.addSmsReceiver(phone, smsReceiverParams);
		messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
		try {
			TopicMessage ret = topic.publishMessage(msg, messageAttributes);
			System.out.println("MessageId: " + ret.getMessageId());
			System.out.println("MessageMD5: " + ret.getMessageBodyMD5());

		} catch (ServiceException se) {

			logger.info("", se);
			throw new Exception(se);

		} catch (Exception e) {
			logger.info("", e);
			throw new Exception(e);
		} finally {
			//client.close();
		}

		return randcode;
	}

	public synchronized static String SendMailCode(String mail) throws Exception {
		String subject="智数验证码";
		String randcode ="";
		try {
			Properties prop = new Properties();
			prop.setProperty("mail.transport.protocol", "smtp"); //协议
			prop.setProperty("mail.smtp.host", "smtp.163.com"); //主机名
			prop.setProperty("mail.smtp.auth", "true"); //是否开启权限控制
			prop.setProperty("mail.debug", "true"); //返回发送的cmd源码
			Session session = Session.getInstance(prop);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("zhishu_service@163.com")); //自己的email
			msg.setRecipient(RecipientType.TO, new InternetAddress(mail));
			msg.setSubject(subject);//邮件标题
			randcode = getCode();
			msg.setText("Dear:"+mail+"\n\t【智数】您的动态验证码为："+randcode+"，请于15分钟内输入验证，请勿向他人泄露");
			//不被当作垃圾邮件的关键代码--Begin ，如果不加这些代码，发送的邮件会自动进入对方的垃圾邮件列表
			msg.addHeader("X-Priority", "3"); 
			msg.addHeader("X-MSMail-Priority", "Normal"); 
			msg.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869"); //本文以outlook名义发送邮件，不会被当作垃圾邮件 
			msg.addHeader("X-MimeOLE", "Produced By Microsoft MimeOLE V6.00.2900.2869"); 
			msg.addHeader("ReturnReceipt", "1"); 
			Transport trans = session.getTransport(); 
			trans.connect(mailusername,password); // 邮件的账号密码
			trans.sendMessage(msg, msg.getAllRecipients());
		} catch (Exception e) {
			logger.info("",e);
			randcode ="";
		}
		return randcode;
	}

	public static String getCode() {
		String code = "";
		String num[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		for (int i = 0; i < 6; i++) {
			int random = new Random().nextInt(9);
			code = code + num[random];
		}
		return code;
	}

	public static void main(String[] args) {
		String code = "";
		String type = "mail";
		if (type == "phone") {
			try {
				code = SendPhoneCode("15001932315");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				code = SendMailCode("bin.e@bds-analytics.com");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("验证码是：" + code);
	}
}
