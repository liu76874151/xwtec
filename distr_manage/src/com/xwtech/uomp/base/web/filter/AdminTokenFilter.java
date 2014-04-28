package com.xwtech.uomp.base.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xwtech.uomp.base.WEBApp;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.RequestConstants;
import com.xwtech.uomp.base.constants.ResultConstants;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.PageDynamicRequestInfo;
import com.xwtech.uomp.base.pojo.RequestHandleUrlBean;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.pojo.sso.LoginResponseBean;
import com.xwtech.uomp.base.service.ILoginService;
import com.xwtech.uomp.base.util.SSOUtil;
import com.xwtech.uomp.base.util.SessionUtil;


public class AdminTokenFilter extends OncePerRequestFilter {

    @SuppressWarnings("unchecked")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter)
            throws ServletException, IOException {
        boolean flag = false;

        boolean privilegeFlag = false;
        String checkCode = null;
        String checkFuncErrorMsg = null;
        String errorUrl = null;
        ILoginService loginService = (ILoginService) WEBApp.SPRING_CONTEXT.getBean("loginService");

        String requestUrl = request.getRequestURI();
        if (requestUrl.indexOf(request.getContextPath() + "/validate.do") == -1) {
            UserInfoBean userInfo = (UserInfoBean) SessionUtil.getObjectAttribute(request, RequestConstants.ADMIN_SESSION_KEY);
            if (userInfo == null) {
                String loginName = SSOUtil.getLoginInfoForCookie(RequestConstants.LOGIN_NAME, request);
                String tokenKey = SSOUtil.getLoginInfoForCookie(RequestConstants.TOKEN_COOKIE_KEY, request);
                if (!StringUtils.isBlank(loginName) && !StringUtils.isBlank(tokenKey)) {
                    LoginRequestBean loginReqBean = new LoginRequestBean();
                    loginReqBean.setLoginName(loginName);
                    loginReqBean.setTokenKey(tokenKey);
                    LoginResponseBean loginResBean = SSOUtil.checkSSOState(loginReqBean, request);
                    if (IResultCode.SYS_SUCCESS.equals(loginResBean.getRetCode())) {
                        try {

                            UserInfoBean userInfoBean = loginService.checkUserInfo(loginResBean.getUserInfoBean().getLoginName());
                            if (userInfoBean != null) {
                                flag = false;
                                userInfo = userInfoBean;
                                SessionUtil.setObjectAttribute(request, RequestConstants.ADMIN_SESSION_KEY, userInfoBean);
                            } else {
                                flag = true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            SessionUtil.removeObjectAttribute(request, RequestConstants.ADMIN_SESSION_KEY);
                        }
                    } else {
                        flag = true;
                    }
                } else {
                    flag = true;
                }
            }

            try {
                List<RequestHandleUrlBean> handleUrls = this.getFuncUrl(request);
                //未登录时，过滤那些不需要登录请求
                if (userInfo == null) {
                    boolean validate = true;
                    if (handleUrls != null && handleUrls.size() > 0) {
                        List<String> notLoginUrl = loginService.getNotLoginUrl(WEBApp.SYS_NUM);
                        if (requestUrl.indexOf("actionDispatcher.do") != -1) {
                            for (RequestHandleUrlBean bean : handleUrls) {
                                validate = loginService.needValidate(bean.getHandleUrl(), notLoginUrl);
                                if (validate) {
                                    break;
                                }
                            }
                        } else {
                            RequestHandleUrlBean bean = handleUrls.get(0);
                            validate = loginService.needValidate(bean.getHandleUrl(), notLoginUrl);
                        }
                    } else {
                        //跟访问不需要校验是否登录
                        if (requestUrl.equals(request.getContextPath() + "/")) {
                            flag = false;
                        }
                    }
                    if (!validate) {
                        flag = false;
                    }
                } else {
                    //如果已经登录，则判断是否有操作权限
                    if (handleUrls != null && handleUrls.size() > 0) {
                        Iterator it = handleUrls.iterator();
                        //sunhanxin 2013-4-10删除
                        //List<String> notCheckUrl = loginService.getNotCheckUrl(WEBApp.SYS_NUM);
                        while (it.hasNext()) {
                            RequestHandleUrlBean bean = (RequestHandleUrlBean) it.next();
                            //sunhanxin 2013-4-10删除
                            //boolean check = loginService.needValidate(bean.getHandleUrl(), notCheckUrl);
                            boolean check = loginService.existsFuncInfo(bean);
                            if (check) {
                                bean.setLoginName(userInfo.getLoginName());
                                String systemCode = loginService.checkPrivilege(bean);
                                // if(!StringUtils.isBlank(resultCode) && !IResultCode.SYS_SUCCESS.equals(resultCode)){
                                if (StringUtils.isNotBlank(systemCode) && systemCode.startsWith("-")) {
                                    flag = true;
                                    privilegeFlag = true;
                                    checkFuncErrorMsg = loginService.getCheckPrivilegeInfo(systemCode);
                                    checkCode = systemCode;
                                    errorUrl = bean.getHandleUrl();
                                    checkFuncErrorMsg += errorUrl;
                                    break;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (flag) {
            String s = null;
            if (requestUrl.indexOf(".jsp") == -1) {
                Object retObj = null;
                //返回结果对象
                if (privilegeFlag) {
                    retObj = this.getDispatcherObject(request, checkCode, checkFuncErrorMsg);
                } else {
                    retObj = this.getDispatcherObject(request, SystemCodeConstants.NOT_LOGIN, ResultConstants.ADMIN_LOGIN_OVERTIME);
                }
                s = JSON.toJSONString(retObj);
            } else {
                // 返回信息提示
                HandlerResult result = HandlerResult.newInstance();
                if (privilegeFlag) {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.NOT_ACCESS_PAGE);
                    result.setResMsg("您没权限访问该页面！");
                } else {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.NOT_LOGIN);
                    result.setResMsg(ResultConstants.ADMIN_LOGIN_OVERTIME);
                }
                s = this.printOutPage(result, request.getContextPath());
                response.setContentType("text/html;charset=UTF-8");
            }
            PrintWriter pw = response.getWriter();
            pw.print(s);
            pw.close();
            return;
        }
        filter.doFilter(request, response);
    }

    private List<RequestHandleUrlBean> getFuncUrl(HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        List<RequestHandleUrlBean> handleUrls = null;
        if (!StringUtils.isBlank(requestUrl)) {
            handleUrls = new ArrayList<RequestHandleUrlBean>();
            if (requestUrl.indexOf("actionDispatcher.do") != -1) {
                String jsonParam = request.getParameter("jsonParam");
                if (!StringUtils.isBlank(jsonParam)) {
                    List<PageDynamicRequestInfo> requests = JSONArray.parseArray(jsonParam, PageDynamicRequestInfo.class);
                    if (requests != null && requests.size() > 0) {
                        Iterator it = requests.iterator();
                        while (it.hasNext()) {
                            PageDynamicRequestInfo requestInfo = (PageDynamicRequestInfo) it.next();
                            String reqUrl = requestInfo.getDynamicParameter().get("reqUrl");
                            String reqMethod = requestInfo.getDynamicParameter().get("reqMethod");
                            RequestHandleUrlBean handleUrlBean = new RequestHandleUrlBean();
                            handleUrlBean.setHandleUrl(reqUrl + "," + reqMethod);
                            handleUrlBean.setSysNum(WEBApp.SYS_NUM);
                            handleUrls.add(handleUrlBean);
                        }
                    }
                } else {
                    String reqUrl = request.getParameter("reqUrl");
                    String reqMethod = request.getParameter("reqMethod");
                    RequestHandleUrlBean handleUrlBean = new RequestHandleUrlBean();
                    handleUrlBean.setHandleUrl(reqUrl + "," + reqMethod);
                    handleUrlBean.setSysNum(WEBApp.SYS_NUM);
                    handleUrls.add(handleUrlBean);
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
                handleUrls.add(handleUrlBean);
            }
        }
        return handleUrls;
    }

    private Object getDispatcherObject(HttpServletRequest request, String systemCode, String resMsg) {

        Object dispatcherObj = null;
        Map<String, Object> map = new HashMap<String, Object>();
        HandlerResult result = HandlerResult.newInstance();
        String jsonParam = request.getParameter("jsonParam");
        if (!StringUtils.isBlank(jsonParam)) {
            List<PageDynamicRequestInfo> requests = JSONArray.parseArray(jsonParam, PageDynamicRequestInfo.class);
            if (requests != null && requests.size() > 0) {
                PageDynamicRequestInfo requestInfo = requests.get(0);
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(systemCode);
                result.setResMsg(resMsg);
                map.put(requestInfo.getDynamicDataNodeName(), result);
                dispatcherObj = map;
            }
        } else {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(systemCode);
            result.setResMsg(resMsg);
            dispatcherObj = result;
        }
        return dispatcherObj;
    }

    private String printOutPage(HandlerResult result, String contextPath) {
        StringBuffer sf = new StringBuffer();
        sf.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        sf.append("<html><head><title>信息提示</title>");
        sf.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"").append(contextPath).append("/resource/css/frame.css\"  />");
        sf.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"").append(contextPath).append("/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css\"/>");
        sf.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"").append(contextPath).append("/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.css\"/>");
        sf.append("<script type=\"text/javascript\" language=\"javascript\" src=\"").append(contextPath).append("/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js\"></script>");
        sf.append("<script type=\"text/javascript\" language=\"javascript\" src=\"").append(contextPath).append("/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js\"></script>");
        sf.append("<script type=\"text/javascript\" language=\"javascript\" src=\"").append(contextPath).append("/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js\"></script>");
        sf.append("<script type=\"text/javascript\" language=\"javascript\" src=\"").append(contextPath).append("/resource/scripts/jquery-1.7.1.min.js\"></script>");
        sf.append("<script type=\"text/javascript\" language=\"javascript\" src=\"").append(contextPath).append("/resource/scripts/uompDialog.js\"></script>");
        sf.append("<script type=\"text/javascript\" language=\"javascript\" src=\"").append(contextPath).append("/resource/scripts/main.js\" ></script>");
        sf.append("</head><body>");
        sf.append("</body></html>");
        sf.append("<script type=\"text/javascript\">");
        sf.append("$(document).ready(function(){");
        sf.append(" top.UOMPDialog.alert('" + result.getResMsg() + "',0,\"\"");
        if (SystemCodeConstants.NOT_LOGIN.equals(result.getSysCode())) {
            sf.append(", {'yes' : function(){");
            sf.append(" top.location.href = '").append(contextPath).append("/index.jsp'").append(";");
            sf.append("}}");
        }
        sf.append(");");
        sf.append(" });");
        sf.append("</script>");
        return sf.toString();
    }
}
