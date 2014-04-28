package com.xwtech.uomp.base.web.listen;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xwtech.uomp.base.WEBApp;
import com.xwtech.uomp.base.pojo.VisitLogQueue;
import com.xwtech.uomp.base.pool.ExecutorPool;
import com.xwtech.uomp.base.service.IVisitorLogService;
import com.xwtech.uomp.base.util.ConfigurationRead;

public class ApplicationContextListener implements ServletContextListener {
    private Logger logger = Logger.getLogger(ApplicationContextListener.class);

    static {
        System.setProperty("java.awt.headless", "true");
    }

    public void contextDestroyed(ServletContextEvent arg0) {
    }

    public void contextInitialized(ServletContextEvent context) {
        logger.info(".........初始化系统参数开始..................");
        String realPath = context.getServletContext().getRealPath("");
        if (!realPath.endsWith("/")) {
            realPath += "/";
        }
        WEBApp.APP_PATH = realPath;
        WEBApp.CONTEXT_NAME = context.getServletContext().getServletContextName();
        WEBApp.SPRING_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(context.getServletContext());
        ConfigurationRead read = ConfigurationRead.getInstance();
        // 当前系统编码
        WEBApp.SYS_NUM = read.getValue("sys.num");
        // 当前系统字符编码
        WEBApp.SSO_COOKIE_DOMAIN = read.getValue("sys.domain");
        // 系统
        // WEBApp.SYS_TIMEOUT
        String timeOut = read.getValue("sys.timeout");
        if (!StringUtils.isBlank(timeOut)) {
            WEBApp.SYS_TIMEOUT = Integer.parseInt(timeOut);
        } else {
            WEBApp.SYS_TIMEOUT = 30;
        }
        logger.info("..........初始化系统参数结束..................");

        final IVisitorLogService logService = (IVisitorLogService) WEBApp.SPRING_CONTEXT.getBean("visitorLogService");

        ExecutorPool.getInstance().exe(new Runnable() {

            public void run() {
                VisitLogQueue queue = VisitLogQueue.getInstance();

                List logBeans = new ArrayList();

                while (true) {
                    if (!queue.isEmpty()) {

                        logBeans.addAll(queue);

                        queue.removeAll(logBeans);

                        logService.batchAddLog(logBeans);

                        logBeans.clear();

                    }
                }

            }
        });
    }
}
