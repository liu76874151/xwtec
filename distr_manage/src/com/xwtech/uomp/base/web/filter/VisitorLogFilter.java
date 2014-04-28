package com.xwtech.uomp.base.web.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xwtech.uomp.base.WEBApp;
import com.xwtech.uomp.base.constants.RequestConstants;
import com.xwtech.uomp.base.pojo.PageDynamicRequestInfo;
import com.xwtech.uomp.base.pojo.RequestHandleUrlBean;
import com.xwtech.uomp.base.pojo.VisitLogQueue;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.admin.VisitorLogBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.util.DateTimeUtil;
import com.xwtech.uomp.base.util.SSOUtil;
import com.xwtech.uomp.base.util.SessionUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VisitorLogFilter extends OncePerRequestFilter {

    @SuppressWarnings("unchecked")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String handleUrl = request.getRequestURI();

        UserInfoBean userInfo = (UserInfoBean) SessionUtil.getObjectAttribute(request, RequestConstants.ADMIN_SESSION_KEY);

        String loginName = SSOUtil.getLoginInfoForCookie(RequestConstants.LOGIN_NAME, request);
        String tokenKey = SSOUtil.getLoginInfoForCookie(RequestConstants.TOKEN_COOKIE_KEY, request);

        LoginRequestBean loginReqBean = new LoginRequestBean();

        if (null == loginName && handleUrl.indexOf(request.getContextPath() + "/validate.do") != -1) {

            InputStream in = request.getInputStream();
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            int nRead = 0;
            byte[] buf = new byte[10240];

            while ((nRead = in.read(buf)) > 0) {
                bytes.write(buf, 0, nRead);
                bytes.flush();
            }

            String reqInfo = bytes.toString("UTF-8");

            loginReqBean = (LoginRequestBean) JSON.parseObject(reqInfo, LoginRequestBean.class);

            tokenKey = loginReqBean.getTokenKey();
            loginName = loginReqBean.getLoginName();

        }

        List<RequestHandleUrlBean> handleUrls = this.getFuncUrl(request);

        loginReqBean.setHandleList(handleUrls.isEmpty() ? loginReqBean.getHandleList() : handleUrls);
        loginReqBean.setLoginName(loginName);
        loginReqBean.setTokenKey(tokenKey);
        loginReqBean.setSysNum(WEBApp.SYS_NUM);
        loginReqBean.setUserInfoBean(userInfo);

        request.setAttribute("reqParams", loginReqBean);

        if (loginReqBean.getHandleList() != null) {
            for (RequestHandleUrlBean bean : loginReqBean.getHandleList()) {
                handleUrl += ';' + bean.getHandleUrl();
            }

        }

        VisitorLogBean logBean = new VisitorLogBean();

        logBean.setF_v_account(loginName);
        logBean.setF_v_host(request.getRemoteAddr());
        logBean.setF_v_time(DateTimeUtil.getSystemTime());
        logBean.setF_v_uri(handleUrl);
        logBean.setF_v_sysNum(WEBApp.SYS_NUM);

        VisitLogQueue.getInstance().add(logBean);

        filterChain.doFilter(request, response);
    }

    private List<RequestHandleUrlBean> getFuncUrl(HttpServletRequest request) {
        String requestUrl = request.getRequestURI();

        List<RequestHandleUrlBean> handleUrls = new ArrayList<RequestHandleUrlBean>();

        if (requestUrl.indexOf(".do") != -1) {
            String jsonParam = request.getParameter("jsonParam");

            if (!StringUtils.isBlank(jsonParam)) {
                List<PageDynamicRequestInfo> params = JSONArray.parseArray(jsonParam, PageDynamicRequestInfo.class);

                if (params != null && !params.isEmpty()) {
                    Iterator<PageDynamicRequestInfo> it = params.iterator();

                    while (it.hasNext()) {
                        PageDynamicRequestInfo requestInfo = (PageDynamicRequestInfo) it.next();
                        String reqUrl = requestInfo.getDynamicParameter().get("reqUrl");
                        String reqMethod = requestInfo.getDynamicParameter().get("reqMethod");

                        RequestHandleUrlBean handleUrlBean = new RequestHandleUrlBean();
                        handleUrlBean.setHandleUrl(reqUrl + "," + reqMethod);
                        handleUrlBean.setSysNum(WEBApp.SYS_NUM);
                        handleUrlBean.setLoginName(SSOUtil.getLoginInfoForCookie(RequestConstants.LOGIN_NAME, request));

                        handleUrls.add(handleUrlBean);
                    }
                }
            } else {
                String reqUrl = request.getParameter("reqUrl");
                String reqMethod = request.getParameter("reqMethod");

                if (null != reqUrl && null != reqMethod) {
                    RequestHandleUrlBean handleUrlBean = new RequestHandleUrlBean();
                    handleUrlBean.setHandleUrl(reqUrl + "," + reqMethod);
                    handleUrlBean.setSysNum(WEBApp.SYS_NUM);
                    handleUrlBean.setLoginName(SSOUtil.getLoginInfoForCookie(RequestConstants.LOGIN_NAME, request));

                    handleUrls.add(handleUrlBean);
                }

            }
        } else if (requestUrl.indexOf(".jsp") != -1) {
            String handleUrl = null;
            RequestHandleUrlBean handleUrlBean = new RequestHandleUrlBean();

            if (requestUrl.indexOf(request.getContextPath()) != -1) {
                int length = request.getContextPath().length();
                handleUrl = requestUrl.substring(length);
            } else {
                handleUrl = requestUrl;
            }

            handleUrlBean.setHandleUrl(handleUrl);
            handleUrlBean.setSysNum(WEBApp.SYS_NUM);
            handleUrlBean.setLoginName(SSOUtil.getLoginInfoForCookie(RequestConstants.LOGIN_NAME, request));

            handleUrls.add(handleUrlBean);
        }

        return handleUrls;
    }
}
