package com.xwtech.uomp.base.web.init.impl;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.util.ConfigurationRead;
import com.xwtech.uomp.base.web.init.ISystemInit;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;

/**
 * 
 * This class is used for ...
 * 
 * @author unique
 * @version 1.0, 2014-1-22 上午11:30:50
 */
@Service("ecpClientInit")
public class EcpClientInitImpl implements ISystemInit {

    private static final Logger logger = Logger.getLogger("ecplog");

    @Value("${boss.clientChannel}")
    String clientChannel;

    @Value("${boss.xwecpURL}")
    String url;

    @Value("${boss.user}")
    String user;

    @Value("${boss.password}")
    String password;
    
    String timeout;
    String soTimeout;

    /**
     * 网厅
     * 
     * @author unique
     */
    public void init() {
	clientChannel = ConfigurationRead.getInstance().getValue("boss.clientChannel");
	url = ConfigurationRead.getInstance().getValue("boss.xwecpURL");
	user = ConfigurationRead.getInstance().getValue("boss.user");
	password = ConfigurationRead.getInstance().getValue("boss.password");
	timeout = ConfigurationRead.getInstance().getValue("boss.timeout");
	soTimeout = ConfigurationRead.getInstance().getValue("boss.soTimeout");
	
	try {
	    // 初始化ecp客户端片段
	    Properties props = new Properties();
	    props.put("client.channel", clientChannel);
	    props.put("platform.url", url);
	    props.put("platform.user", user);
	    props.put("platform.password", password);
	    props.put("connection.timeout", timeout);
	    props.put("connection.soTimeout", soTimeout);

	    XWECPLIClient.createInstance(props);
	    logger.info("EcpClientInitImpl:ECP网厅客户端初始化成功！");
	} catch (Exception e) {
	    logger.info("EcpClientInitImpl:ECP网厅客户端初始化失败！=" + e);
	}
    }

    /**
     * 掌厅
     *   
     * @author unique
     */
    public void initwap() {
	clientChannel = ConfigurationRead.getInstance().getValue("boss.wap.clientChannel");
	url = ConfigurationRead.getInstance().getValue("boss.wap.xwecpURL");
	user = ConfigurationRead.getInstance().getValue("boss.wap.user");
	password = ConfigurationRead.getInstance().getValue("boss.wap.password");
	timeout = ConfigurationRead.getInstance().getValue("boss.wap.timeout");
	soTimeout = ConfigurationRead.getInstance().getValue("boss.wap.soTimeout");

	try {
	    // 初始化ecp客户端片段
	    Properties props = new Properties();
	    props.put("client.channel", clientChannel);
	    props.put("platform.url", url);
	    props.put("platform.user", user);
	    props.put("platform.password", password);
	    props.put("connection.timeout", timeout);
	    props.put("connection.soTimeout", soTimeout);

	    XWECPLIClient.createInstance(props);
	    logger.info("EcpClientInitImpl:ECP掌厅客户端初始化成功！");
	} catch (Exception e) {
	    logger.info("EcpClientInitImpl:ECP掌厅客户端初始化失败！=" + e);
	}
    }

    /**
     * 短厅
     *   
     * @author unique
     */
    public void initsms() {
	clientChannel = ConfigurationRead.getInstance().getValue("boss.sms.clientChannel");
	url = ConfigurationRead.getInstance().getValue("boss.sms.xwecpURL");
	user = ConfigurationRead.getInstance().getValue("boss.sms.user");
	password = ConfigurationRead.getInstance().getValue("boss.sms.password");
	timeout = ConfigurationRead.getInstance().getValue("boss.sms.timeout");
	soTimeout = ConfigurationRead.getInstance().getValue("boss.sms.soTimeout");

	try {
	    // 初始化ecp客户端片段
	    Properties props = new Properties();
	    props.put("client.channel",  clientChannel  );
	    props.put("platform.url", url);
	    props.put("platform.user", user);
	    props.put("platform.password", password);
	    props.put("connection.timeout", timeout);
	    props.put("connection.soTimeout", soTimeout);

	    XWECPLIClient.createInstance(props);
	    logger.info("EcpClientInitImpl:ECP短厅客户端初始化成功！");
	} catch (Exception e) {
	    logger.info("EcpClientInitImpl:ECP短厅客户端初始化失败！=" + e);
	}
    }
}
