package com.bds.common;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

    // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2017092608938313";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCJ7MnjrRRgOebb2+Hc3fHCCQp1JnKXqeU9et2NZh3SKJvi4qOPerPEaZQy+PKnrCl4eoW7QA6FwqbMBU6/ltusEZh8kPGs/A1dHZ9xXeQQs4AA41LzmXxCx0xS5yBzVHhbMt+ggjt7qvHVcImNBt+HRLq6nlIoaIE7TReI1ak+3BkkfBkD3F5g/TBrc8yq7inykXkrYaLNr2DzNz9oubMDB6k7RgK4NffZcIYI9khUneYAWD+h79HBSLsHNH4VRyCVwCOieZU99+lBssXKsKLhDXrFhIDcFVP0MTy9FTAlMjRJCbQZgZ33blfiuX5xItcYCBcjDybo0ojUmeqePoGFAgMBAAECggEBAIG6UGJvhzzC+VtOQ/EEaMJC0q1BzoLMcCwVhpHavK3ROF59Ia5wkOlrDNOsyD/hdNg3FdX7+Czj2xMG4vzdnh0IJ87yXf6AzOkKVc/BqiPzUbCEIJ+ze+croaRQdHtFtjbBcQl9tQkqznAde6NEFbcbdC7Xfxs2wGbQqhQwMxJFfU04eF95tknfI/bMjspBynoVpBI2+tXHXH166seisfGeZYPtBc083vlzPllSGWwiP0hHJ8e/W65Gz0OoCaA1BXGTBeWLlRJN3vxkTprmijbla9Sm1J0AZeR4HUoWfIyX8uo3F4ziCmHVQ8zESWwLd6U+g2kTsVk4N2+9SOaBfPkCgYEAwhxqybe5PU2ftCWzTIQCKeRNXySsBReEZDpfsxDQQN+O0fu6tciaUXV2YncL6CqR5TFCSSShWIdmTPpNB/BRxDqJ0wsa7vQTS1j3lEW1C/cjkWmbTq1xkCw4LFqYQeJpfgolAwIqt8A8YMA7cOZ/lDjWyzOW8PVvmRXKe2ivb68CgYEAteZlXDBHSUuOzIrKGqQGWij2Cn6aQGxo24+WorNwK9wns+3+TG/AJVBGORSK4vEPhN9fQUXvEInaVVrK6qsn/MjiC0iQd29Q6eqcf5/Stf9JYMQh1n4fyWA0qSX2gvvsWudUyFTfR2nJ5HRQj10EcWhVKumRVa8rv6D0l8j22wsCgYANprP35bzYS9o8cB5Q9kO0sIKke07BuOqtpdFPJZKPzWkc6WgzGp2HtwwRVq/2ru+9v54ayMG0GSmfpka+lS8a7sb8mZ5WScIFnYcj3VcjNUlCE7xBZz9CVWnJ7MEieQrUO2Bo+sEIVTOp1jsX8I3uXaT1e3ZVEd28VWT9EtqrrwKBgQCzxWozfDtXwub0W852Gdl1TboskQbd2MahB/31+qVHk6NhfjqvTFp4BigAzb6dn2SZCzHXDsjAbZ1scfjNiC5S9C4CvMqaX1d2dzoIH+pdmFnq2qwekjPUD9K7wRsqRNRI2wu9mbO5nmG7+QCNT37OnjBd4sySBDeWgfp58FGYFQKBgC6xyBEqw2x9cbyWjaod3XMiECAeEnhbECLpFhL1X3r9a6mxU8AMs4RStG3Z8Es0kHSOg95E3cNFxx+72NNsRE/oJQ/H93aOV6qUe07i23ApffuerdNjgEyTQ6dlZvIo7dwrSTFqopRDYruC9SR8FZAsKi6UDZJIS6BERYBuEZbY";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAie/v+xQ2q/UDug4E2UoXbh89mwjJcKaVo/sjzGee0ksifpQ2CiRyt2Hp29pOnI53tkvIKdOw8tu+Af1NeZSnDNvGL1QDBE1CYVvdcn+UIMXBibA8h90hY9JPbgcDhhUhEuFLn4g4hVHO18+FF8tS+IwFEKCwKqC0+2XoKRAKOruxQ3/d1jumdg4ClBepvxLPMG7PtlrYW4aUxapxb5utDMxtwYJgyfXGTbtt+FX88gUVQqJhi5qIGu3Ey+I/YQnHWp5zdpcJkl+55a9VYZXOC3V9esAhsfIrao5MZCKm3tpyr3bMHgNKKBSuF9FEj7cY3cT70ucccI129FPpJZfVOwIDAQAB";

    // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.think-data.cn/action/gateway";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://www.think-data.cn/action/pay";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";

    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * 
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
	FileWriter writer = null;
	try {
	    writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
	    writer.write(sWord);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (writer != null) {
		try {
		    writer.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}
