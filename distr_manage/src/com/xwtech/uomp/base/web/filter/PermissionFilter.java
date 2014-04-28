package com.xwtech.uomp.base.web.filter;

import com.alibaba.fastjson.JSON;
import com.xwtech.uomp.base.WEBApp;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.RequestHandleUrlBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.pojo.sso.LoginResponseBean;
import com.xwtech.uomp.base.service.ILoginService;
import com.xwtech.uomp.base.util.StringUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PermissionFilter extends OncePerRequestFilter {

    private String excludeURL;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestUrl = request.getRequestURI();

        // 请求参数
        LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");

        // 根目录不需要登录鉴权
        if (requestUrl.equals(request.getContextPath() + "/")) {
            filterChain.doFilter(request, response);
          //  System.out.println("==根目录不需要登录鉴权=====");
            return;
        }

        ILoginService loginService = (ILoginService) WEBApp.SPRING_CONTEXT.getBean("loginService");
        String systemCode = "";

        // 查询当前账号的权限列表
        for (RequestHandleUrlBean handleUrlBean : loginReqBean.getHandleList()) {
///System.out.println(excludeURL+"========"+handleUrlBean.getHandleUrl());
            if (excludeURL.indexOf(handleUrlBean.getHandleUrl()) != -1) {
                filterChain.doFilter(request, response);

                return;
            }

            systemCode = SystemCodeConstants.OPERATE_SUCCEED;
        	//System.out.println("===handleUrlBean===="+loginService.checkPermission(handleUrlBean));
            if (!loginService.checkPermission(handleUrlBean)) {
                systemCode = SystemCodeConstants.NOT_ACCESS_PAGE;

                String msg = "";

                if (requestUrl.indexOf(request.getContextPath() + "/validate.do") != -1) {
                    LoginResponseBean loginResBean = new LoginResponseBean();

                    loginResBean.setRetCode(IResultCode.SYS_FAILED);
                    loginResBean.setErrorCode(systemCode);
                    loginResBean.setRetMsg(loginService.getCheckPrivilegeInfo(systemCode) + "->" + handleUrlBean.getHandleUrl());
                    loginResBean.setRequestType(loginReqBean.getRequestType());

                    msg = JSON.toJSONString(loginResBean);
                } else {
                    HandlerResult result = HandlerResult.newInstance();

                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(systemCode);
                    result.setResMsg(loginService.getCheckPrivilegeInfo(systemCode));

                    msg = StringUtil.alert(result, request.getContextPath());
                }

                response.setContentType("text/html;charset=UTF-8");

                PrintWriter pw = response.getWriter();
                pw.print(msg);
                pw.close();

                return;
            }
        }
//System.out.println("gogogogogo");
        filterChain.doFilter(request, response);
    }

    public String getExcludeURL() {
        return excludeURL;
    }

    public void setExcludeURL(String excludeURL) {
        this.excludeURL = excludeURL;
    }
}
