package com.bds.portal.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bds.common.CommonUtils;
import com.bds.model.Order;
import com.bds.model.Result;
import com.bds.portal.common.result.Page;
import com.bds.portal.common.result.USession;
import com.bds.portal.service.RechargeService;

@Controller
@RequestMapping("/action/my")
public class RechargeAction extends BaseAction {
    private static Logger logger = Logger.getLogger(SettingAction.class);

    private static final Integer PAGE_SIZE = 15;

    @Resource
    private RechargeService rechargeService;

    /**
     * 跳转充值页面
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/s/toRecharge")
    public ModelAndView toRecharge(HttpServletRequest request, HttpServletResponse response) throws Exception {
	ModelAndView view = new ModelAndView();
	try {
	    view.setViewName("/web/my/recharge/recharge.jsp");
	} catch (Exception e) {
	    logger.error("", e);
	    return null;
	}
	return view;
    }

    /**
     * 充值记录
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/s/toRechargeRecord")
    public ModelAndView toRechargeRecord(HttpServletRequest request, HttpServletResponse response) throws Exception {
	ModelAndView view = new ModelAndView();
	try {
	    view.setViewName("/web/my/recharge/recharge_record.jsp");
	} catch (Exception e) {
	    logger.error("", e);
	    return null;
	}
	return view;
    }

    /**
     * 查询流水记录
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/s/queryBalanceBill")
    public Page queryBalanceBill(HttpServletRequest request, HttpServletResponse response, String begin, String end,
	    Integer type, Integer p) throws Exception {
	DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Page page = new Page();
	USession user = this.getUserSession(request);
	Long user_id = user.getId();
	Date begin_date = null;
	Date end_date = null;
	if (StringUtils.isNotEmpty(begin)) {
	    try {
		begin_date = FORMAT.parse(begin + " 00:00:00");
	    } catch (Exception e) {
	    }
	}
	if (StringUtils.isNotEmpty(end)) {
	    try {
		end_date = FORMAT.parse(end + " 23:59:59");
	    } catch (Exception e) {
	    }
	}

	page = rechargeService.queryList(user_id, begin_date, end_date, type, p, PAGE_SIZE);
	return page;
    }

    /**
     * 消费记录
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/s/toConsumptionRecord")
    public ModelAndView toConsumptionRecord(HttpServletRequest request, HttpServletResponse response) throws Exception {
	ModelAndView view = new ModelAndView();
	try {
	    view.setViewName("/web/my/recharge/consumption_record.jsp");
	} catch (Exception e) {
	    logger.error("", e);
	    return null;
	}
	return view;
    }
    
    @RequestMapping(value = "/s/toOrder")
    public ModelAndView toOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
	ModelAndView view = new ModelAndView();
	try {
	    view.setViewName("/web/my/recharge/order.jsp");
	} catch (Exception e) {
	    logger.error("", e);
	    return null;
	}
	return view;
    }
    
    /**
     * 创建订单
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/s/createOrder")
    public ModelAndView createOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
	ModelAndView view = new ModelAndView();
	try {
	    
	    USession us = this.getUserSession(request);
	    if (us != null) {
		
		Long userId = us.getId();
		String t = request.getParameter("t");
		String amount = t.equals("0") ? "58" : (t.equals("1")?"158" : (t.equals("1")?"888":"1888"));
		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = CommonUtils.getOrderNo(userId);
		// 付款金额，必填
		String total_amount = amount;
		// 订单名称，必填
		String subject =amount+"套餐";
		// 商品描述，可空
		String body = "订单号为:["+out_trade_no+"],您选购智慧数字为:["+subject+"],智慧数字将为您提供最优质的抓取服务.";
		
		Order order = new Order();
		order.setAmount(Double.valueOf(amount));
		order.setBody(body);
		order.setCreate_time(new Date());
		order.setOrder_no(out_trade_no);
		order.setOrder_status(1);
		order.setSubject(subject);
		order.setType(Integer.valueOf(t));
		order.setUser_id(userId);
		Result r = this.rechargeService.createOrder(order);
		view.addObject("order", order);
		view.setViewName("/web/my/recharge/order_detail.jsp");
	    }else{
		view.setViewName("redirect:/portal/user/login.html");    
	    }
	} catch (Exception e) {
	    logger.error("", e);
	    return null;
	}
	return view;
    }
    
    
    /**
     * 创建订单
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/s/orderDetail")
    public ModelAndView orderDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
	ModelAndView view = new ModelAndView();
	try {
	    
	    USession us = this.getUserSession(request);
	    if (us != null) {
		
		Long userId = us.getId();
		String order_no = request.getParameter("order_no");
		String order_id = request.getParameter("order_id");
		
		Order order = this.rechargeService.getOrderDetail(userId,order_no,order_id);
		view.addObject("order", order);
		view.setViewName("/web/my/recharge/order_detail.jsp");
	    }else{
		view.setViewName("redirect:/portal/user/login.html");    
	    }
	} catch (Exception e) {
	    logger.error("", e);
	    return null;
	}
	return view;
    }

}
