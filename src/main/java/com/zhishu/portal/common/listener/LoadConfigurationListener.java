package com.zhishu.portal.common.listener;

import com.zhishu.portal.util.Const;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by guoyu on 2017/4/24.
 */
public class LoadConfigurationListener implements ServletContextListener {

    private final Logger logger = Logger.getLogger(LoadConfigurationListener.class);

    public static final String configFile = "/WEB-INF/classes/config.properties";

    public void contextDestroyed(ServletContextEvent evt) {
        logger.debug("上下文销毁，系统停止...");
    }

    public void contextInitialized(ServletContextEvent evt) {
        logger.info("loadConfiguration...");

        String path = evt.getServletContext().getRealPath("/");
        Const.Context = evt.getServletContext().getRealPath("/");
        path = path + configFile;
        // -------------------------------权限配置文件
        PropertiesConfiguration config = new PropertiesConfiguration();
        config.setEncoding("utf-8");

        // ------------------------------其他配置文件
        try {
            config.load(path);

            Const.Host_Ip = config.getString("host.ip");
            Const.Host_Port = config.getInt("host.port");
            Const.TASK_API_URL = config.getString("task_api.url");
            Const.USER_API_URL = config.getString("user_api.url");
            Const.TEMPLATE_API_URL = config.getString("template_api.url");
        	Const.REDIS_HOST = config.getString("redis.host");
			Const.REDIS_PORT = config.getInt("redis.port");
			Const.REDIS_PWD = config.getString("redis.pwd");


        } catch (ConfigurationException e) {
            logger.error("加载配置文件" + configFile + "异常，启动失败！系统即将退出！");
            e.printStackTrace();
            System.exit(0);
        }

        // ------------------------------机器信息

    }
}
