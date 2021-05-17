package com.zhishu.portal.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.zhishu.common.AlipayConfig;
import com.zhishu.model.Result;
import com.zhishu.portal.service.RechargeService;

@Controller
@RequestMapping("/action")
public class AlipayAction extends BaseAction {
    private static Logger logger = Logger.getLogger(SettingAction.class);

    @Resource
    private RechargeService rechargeService;
    /**
     * 支付宝网关通知接口
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/gateway")
    public Result gateway(HttpServletRequest request, HttpServletResponse response) throws Exception {
	Result res = new Result();
	// 获取支付宝POST过来反馈信息
	Map<String, String> params = new HashMap<String, String>();
	Map<String, String[]> requestParams = request.getParameterMap();
	for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
	    String name = (String) iter.next();
	    String[] values = (String[]) requestParams.get(name);
	    String valueStr = "";
	    for (int i = 0; i < values.length; i++) {
		valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
	    }
	    // 乱码解决，这段代码在出现乱码时使用
	    valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
	    params.put(name, valueStr);
	}

	boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,
		AlipayConfig.sign_type); // 调用SDK验证签名

	// ——请在这里编写您的程序（以下代码仅作参考）——

	/*
	 * 实际验证过程建议商户务必添加以下校验： 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号， 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）， 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email） 4、验证app_id是否为该商户本身。
	 */
	if (signVerified) {// 验证成功
	    // 商户订单号
	    String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

	    // 支付宝交易号
	    String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

	    // 交易状态
	    String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

	    
	    rechargeService.trade(out_trade_no,trade_no,trade_status,"gateway");
	    
	    if (trade_status.equals("TRADE_FINISHED")) {
		// 判断该笔订单是否在商户网站中已经做过处理
		// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
		// 如果有做过处理，不执行商户的业务程序

		// 注意：
		// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
	    } else if (trade_status.equals("TRADE_SUCCESS")) {
		// 判断该笔订单是否在商户网站中已经做过处理
		// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
		// 如果有做过处理，不执行商户的业务程序

		// 注意：
		// 付款完成后，支付宝系统发送该交易状态通知
	    }

	    System.out.println("success");
	    res.setResult(200, "接收成功");

	} else {// 验证失败
	    System.out.println("fail");
	    res.setResult(500, "接收成功");
	    // 调试用，写文本函数记录程序运行情况是否正常
	    // String sWord = AlipaySignature.getSignCheckContentV1(params);
	    // AlipayConfig.logResult(sWord);
	}

	return res;
    }

    /**
     * 支付宝回调接口
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/pay")
    public ModelAndView pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
	ModelAndView view = new ModelAndView();
	// 获取支付宝GET过来反馈信息
	Map<String, String> params = new HashMap<String, String>();
	Map<String, String[]> requestParams = request.getParameterMap();
	for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
	    String name = (String) iter.next();
	    String[] values = (String[]) requestParams.get(name);
	    String valueStr = "";
	    for (int i = 0; i < values.length; i++) {
		valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
	    }
	    // 乱码解决，这段代码在出现乱码时使用
	    valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
	    params.put(name, valueStr);
	}

	boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,
		AlipayConfig.sign_type); // 调用SDK验证签名

	// ——请在这里编写您的程序（以下代码仅作参考）——
	if (signVerified) {
	    // 商户订单号
	    String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

	    // 支付宝交易号
	    String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

	    // 付款金额
	    String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
	    
	    
	    rechargeService.trade(out_trade_no,trade_no,"TRADE_SUCCESS","pay");

	    System.out.println(
		    "trade_no:" + trade_no + "<br/>out_trade_no:" + out_trade_no + "<br/>total_amount:" + total_amount);
	   // res.setResult(200, "支付成功");
	    //
	    view.setViewName("redirect:/action/my/s/orderDetail?order_no="+out_trade_no);
	} else {
	    System.out.println("支付失败");
	    view.setViewName("redirect:/page/500.html");
	}

	return view;
    }

    /**
     * 跳转支付页面
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/topay")
    public void topay(HttpServletRequest request, HttpServletResponse response) throws Exception {
	// 获得初始化的AlipayClient
	AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
		AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
		AlipayConfig.sign_type);

	// 设置请求参数
	AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
	alipayRequest.setReturnUrl(AlipayConfig.return_url);
	alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

	// 商户订单号，商户网站订单系统中唯一订单号，必填
	String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
	// 付款金额，必填
	String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
	// 订单名称，必填
	String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");
	// 商品描述，可空
	String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");

	alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
		+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

	// 若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
	// alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
	// + "\"total_amount\":\""+ total_amount +"\","
	// + "\"subject\":\""+ subject +"\","
	// + "\"body\":\""+ body +"\","
	// + "\"timeout_express\":\"10m\","
	// + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
	// 请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

	// 请求
	String form = alipayClient.pageExecute(alipayRequest).getBody();

	// 输出
	System.out.println(form);
	response.setContentType("text/html;charset=utf-8");
	response.getWriter().write(form);// 直接将完整的表单html输出到页面
	response.getWriter().flush();
	response.getWriter().close();
    }

}
