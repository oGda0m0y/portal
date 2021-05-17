package com.zhishu.portal.service;

import java.util.List;

import javax.annotation.Resource;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.springframework.stereotype.Component;

import com.zhishu.model.Message;
import com.zhishu.portal.common.result.Page;
import com.zhishu.portal.util.Const;
import com.zhishu.portal.util.MessageUtils;

@Component
public class MessageService {

	@Resource
	private NutDao mysqlDao;

	/**
	 * 新增消息
	 * 
	 * @param message
	 * @return
	 */
	public Message addMessage(Message message) {
		message.setIsread(0);
		mysqlDao.insert(message);
		return message;
	}

	public void redMessage() {

		mysqlDao.update(Message.class, Chain.make("isread", 1), null);

	}

	/**
	 * Nutz正常查询message list后转换成显示的消息
	 * 
	 * @param list
	 * @return
	 */
	public List<Message> getMessageList(List<Message> list) {
		for (Message m : list) {
			m.setMsg(MessageUtils.getTemplateContent(m.getTemplate_id(), m.getContent()));
			m.setType(MessageUtils.getTemplateType(m.getTemplate_id()));
		}
		return list;
	}

	public Page getMessageList(Long user_id, Integer pageNum, Integer pageSize, Integer isread) {
		Pager pager = mysqlDao.createPager(pageNum, pageSize);

		Criteria cri = Cnd.cri();
		cri.where().andEquals("user_id", user_id);
		cri.getOrderBy().desc("create_time");
		if (isread != null) {
			cri.where().andEquals("isread", isread);
		}
		int count = mysqlDao.count(Message.class, cri);
		List<Message> list = mysqlDao.query(Message.class, cri, pager);
		list = this.getMessageList(list);
		Page p = new Page(count, pager.getPageNumber(), pager.getPageSize());
		p.setCode(Const.SUC);
		p.setData(list);
		return p;
	}

	/**
	 * 获取消息详情，转换成显示类型的消息
	 * 
	 * @param id
	 * @return
	 */
	public Message getMessage(Long id) {
		Message m = mysqlDao.fetch(Message.class, id);
		if (m == null) {
			return m;
		}
		m.setContent(MessageUtils.getTemplateContent(m.getTemplate_id(), m.getContent()));
		return m;
	}

}
