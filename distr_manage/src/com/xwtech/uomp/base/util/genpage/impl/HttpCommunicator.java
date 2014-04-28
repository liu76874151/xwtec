package com.xwtech.uomp.base.util.genpage.impl;

import com.xwtech.uomp.base.util.genpage.ICommunicator;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-25
 * Time: 下午1:45
 * To change this template use File | Settings | File Templates.
 */
@Component("httpCommunicator")
public class HttpCommunicator implements ICommunicator {
    private static final Logger logger = Logger.getLogger(HttpCommunicator.class);

    @Value("${communicator.connectionTimeout}")
    int connectionTimeout;

    @Value("${communicator.soTimeout}")
    int soTimeout;

    @Value("${communicator.maxThreads}")
    int maxThreads;

    @Value("${communicator.encoding}")
    String encoding;

    @Value("${pageGenerate.componentUrlPrefix}")
    String urlPrefix;

    @Value("${pageStaticGenerate.wapUrlPrefix}")
    String wapUrlPrefix;

    MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

    /**
     * 初始化
     */
    @PostConstruct
    public void initialize() {
        HttpConnectionManagerParams params = connectionManager.getParams();
        params.setConnectionTimeout(connectionTimeout);
        params.setSoTimeout(soTimeout);
        params.setMaxTotalConnections(maxThreads);
    }

    protected HttpClient createHttpClient() {
        HttpClient httpClient = new HttpClient(connectionManager);

        HostConfiguration hostConf = httpClient.getHostConfiguration();
        ArrayList<Header> headers = new ArrayList<Header>();
        headers.add(new Header("Accept-Language", "en-us,zh-cn,zh-tw,en-gb,en;q=0.7,*;q=0.3"));
        headers.add(new Header("Accept-Charset", "big5,gb2312,gbk,utf-8,ISO-8859-1;q=0.7,*;q=0.7"));
        headers.add(new Header("Accept", "text/html,application/xml;q=0.9,application/xhtml+xml,text/xml;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"));
        headers.add(new Header("Accept-Encoding", "x-gzip,gzip"));
        headers.add(new Header("Connection", "keep-alive"));

        hostConf.getParams().setParameter("http.default-headers", headers);

        return httpClient;
    }

    public String doLocalRequest(String url, Properties props) {
        HttpClient httpClient = this.createHttpClient();
        if (!url.startsWith("/")) {
            url = "/" + url;
        }
        PostMethod post = new PostMethod(this.urlPrefix + url);
        Enumeration enums = props.keys();
        while (enums.hasMoreElements()) {
            String key = enums.nextElement().toString();
            String value = props.getProperty(key);
            if (value != null) {
                try {
                    value = new String(value.getBytes(encoding), "ISO-8859-1");
                } catch (UnsupportedEncodingException e) {
                    logger.error(e, e);
                }
            }
            post.addParameter(key, value);
        }
        try {
            int ret = httpClient.executeMethod(post);
            if (ret == 200) {
                byte[] response = post.getResponseBody();
                return new String(response, encoding);
            } else if (ret == 404) {
                System.out.println("404 找不到文件或目录，请求:" + url);
            } else if (ret == 500) {
                System.out.println("500 服务器内部错误，请求:" + url);
            }
        } catch (Exception e) {
            logger.error(e, e);
            System.out.println("异常，请求:" + url);
        } finally {
            post.releaseConnection();
        }
        return null;
    }

    public String doWapRequest(String url, Properties props) {
        HttpClient httpClient = this.createHttpClient();
        if (!url.startsWith("/")) {
            url = "/" + url;
        }
        PostMethod post = new PostMethod(this.wapUrlPrefix + url);
        Enumeration enums = props.keys();
        while (enums.hasMoreElements()) {
            String key = enums.nextElement().toString();
            String value = props.getProperty(key);
            if (value != null) {
                try {
                    value = new String(value.getBytes(encoding), "ISO-8859-1");
                } catch (UnsupportedEncodingException e) {
                    logger.error(e, e);
                }
            }
            post.addParameter(key, value);
        }
        try {
            int ret = httpClient.executeMethod(post);
            if (ret == 200) {
                byte[] response = post.getResponseBody();
                return new String(response, encoding);
            } else if (ret == 404) {
                System.out.println("404 找不到文件或目录，请求:" + url);
            } else if (ret == 500) {
                System.out.println("500 服务器内部错误，请求:" + url);
            }
        } catch (Exception e) {
            logger.error(e, e);
            System.out.println("异常，请求:" + url);
        } finally {
            post.releaseConnection();
        }
        return null;
    }
}
