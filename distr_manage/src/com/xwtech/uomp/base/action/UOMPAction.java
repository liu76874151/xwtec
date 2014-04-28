package com.xwtech.uomp.base.action;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.pojo.PageDynamicRequestInfo;
import com.xwtech.uomp.base.pool.ExecutorPool;
import com.xwtech.uomp.base.service.IResultMsgFormat;

@Controller
@RequestMapping(value = "/actionDispatcher.do")
public class UOMPAction {
    protected static final Logger logger = Logger.getLogger(UOMPAction.class);

    @Autowired
    private IResultMsgFormat resultMsgFormat;

    /**
     * 统一入口
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "")
    public void defaultHandle(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain;charset=UTF-8");
        try {
            Object retObj = dispatchRequest(request, response);
            if (retObj!=null) {
            	String s = JSON.toJSONStringWithDateFormat(retObj, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
                PrintWriter pw = response.getWriter();
                pw.print(s);
                pw.close();
            }
        } catch (Exception ex) {
            logger.error(ex, ex);
            throw new RuntimeException("UOMP REQUEST HANDLE ERROR!", ex);
        }
    }

    /**
     * 分发请求
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private Object dispatchRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object ret = null;
        String jsonParam = request.getParameter("jsonParam");
        if (!StringUtils.isBlank(jsonParam)) {
            List<PageDynamicRequestInfo> requests = JSONArray.parseArray(jsonParam, PageDynamicRequestInfo.class);
            ret = multiRequests(requests, request, response);
        } else {
            ret = singleRequest(request, response);
        }
        return ret;
    }

    /**
     * 多个请求
     *
     * @param requests
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private Object multiRequests(List<PageDynamicRequestInfo> requests, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final Map<String, Object> ret = new HashMap<String, Object>();
        if (requests.size() > 1) {
            final CountDownLatch countDownLatch = new CountDownLatch(requests.size());
            for (int i = 0; i < requests.size(); i++) {
                final PageDynamicRequestInfo tmpReq = requests.get(i);
                ExecutorPool.getInstance().exe(new Runnable() {
                    public void run() {
                        ret.put(tmpReq.getDynamicDataNodeName(), multiRequest(tmpReq, request, response));
                        countDownLatch.countDown();
                    }
                });

            }
            countDownLatch.await(100, TimeUnit.SECONDS);
        } else {
            PageDynamicRequestInfo tmpReq = requests.get(0);
            ret.put(tmpReq.getDynamicDataNodeName(), multiRequest(tmpReq, request, response));
        }
        return ret;
    }

    /**
     * 处理多个请求
     *
     * @param requestInfo
     * @param request
     * @param response
     * @return
     */
    private Object multiRequest(PageDynamicRequestInfo requestInfo, HttpServletRequest request, HttpServletResponse response) {
        HandlerResult result = null;
        try {
            HandlerRequestContext reqContext = new HandlerRequestContext(request, response);
            reqContext.setReqParam(requestInfo.getDynamicParameter());
            IHandler handler = findHandler(requestInfo.getDynamicURI(), request);
            String reqMethod = requestInfo.getDynamicParameter().get("reqMethod");
            Method m = handler.getClass().getMethod(reqMethod, new Class[]
                    {HandlerRequestContext.class});
            result = (HandlerResult) m.invoke(handler, new Object[]
                    {reqContext});
        } catch (Exception e) {
            e.printStackTrace();
            result = HandlerResult.newInstance(IResultCode.SYS_FAILED);
        } finally {
            genResultMsg(result);
        }

        return result;
    }

    /**
     * 单个请求
     *
     * @param request
     * @param response
     * @return
     */
    private Object singleRequest(HttpServletRequest request, HttpServletResponse response) {
        HandlerResult result = null;
        try {
            HandlerRequestContext reqContext = new HandlerRequestContext(request, response);
            String reqUrl = request.getParameter("reqUrl");
            System.out.println("reqUrl>>>>>>>>>>>>>>>>>>>>>>>>>>"+reqUrl);
            IHandler handler = findHandler(reqUrl, request);
            String reqMethod = request.getParameter("reqMethod");
            System.out.println("reqMethod>>>>>>>>>>>>>>>>>>>>>>>>>>"+reqMethod);
            Method m = handler.getClass().getMethod(reqMethod, new Class[]{HandlerRequestContext.class});
            result = (HandlerResult) m.invoke(handler, new Object[]{reqContext});
        } catch (Exception e) {
            e.printStackTrace();
            result = HandlerResult.newInstance(IResultCode.SYS_FAILED);
        } finally {
            genResultMsg(result);
        }
        return result;
    }

    /**
     * 查找Handler
     *
     * @param request
     * @return
     */
    private IHandler findHandler(String reqUrl, HttpServletRequest request) {
        if (reqUrl.startsWith("/")) {
            reqUrl = reqUrl.substring(1);
        }
        reqUrl = "H_" + reqUrl.replaceAll("\\/", "_");
        ApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
        return (IHandler) springContext.getBean(reqUrl);
    }

    /**
     * 构造返回提示信息
     *
     * @param result
     */
    private void genResultMsg(HandlerResult result) {
    	if (result != null) {
    		resultMsgFormat.formatResultMsg(result);
		}
    }
}
