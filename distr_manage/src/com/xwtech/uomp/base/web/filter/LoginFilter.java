package com.xwtech.uomp.base.web.filter;

import com.xwtech.uomp.base.WEBApp;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.ResultConstants;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.RequestHandleUrlBean;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.ILoginService;
import com.xwtech.uomp.base.service.cache.ICacheService;
import com.xwtech.uomp.base.service.cache.ICacheServiceFactory;
import com.xwtech.uomp.base.util.StringUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFilter extends OncePerRequestFilter {
    private String excludeURL;
    private String[] whiteList;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ICacheServiceFactory cacheServiceFactory = (ICacheServiceFactory) WEBApp.SPRING_CONTEXT.getBean("cacheServiceFactory");
        ICacheService cache_sso = cacheServiceFactory.getCacheService("ecm_code");
        ILoginService loginService = (ILoginService) WEBApp.SPRING_CONTEXT.getBean("loginService");

        // 请求参数
        LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");

        String requestUrl = request.getRequestURI();

        // 根目录不需要登录鉴权
        if (requestUrl.equals(request.getContextPath() + "/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 过滤那些不需要登录就能访问的资源
        for (RequestHandleUrlBean urlBean : loginReqBean.getHandleList()) {
            String url = urlBean.getHandleUrl();
            for (int i = 0; i < whiteList.length; i++) {
                String whiteListUrl = whiteList[i];
                if (url.indexOf(whiteListUrl) != -1) {
                    loginReqBean.setHandleList(loginReqBean.getHandleList());
                    loginReqBean.setSysNum(WEBApp.SYS_NUM);

                    request.setAttribute("reqParams", loginReqBean);

                    filterChain.doFilter(request, response);

                    return;
                }
            }
        }

        UserInfoBean userInfo = loginReqBean.getUserInfoBean();

        // 如果从其他子系统过来的请求userInfo为null
        if (null == userInfo) {

            String isLogin = (String) cache_sso.get(loginReqBean.getTokenKey());

            if (StringUtil.isNull(isLogin) || !"true".equals(isLogin)) {
                // 未登录
                HandlerResult result = HandlerResult.newInstance();

                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.NOT_LOGIN);
                result.setResMsg(ResultConstants.ADMIN_LOGIN_OVERTIME);

                String msg = StringUtil.alert(result, request.getContextPath());
                response.setContentType("text/html;charset=UTF-8");

                PrintWriter pw = response.getWriter();
                pw.print(msg);
                pw.close();

                return;
            }

            if (null == cache_sso.get("KEY_USER_" + loginReqBean.getLoginName())) {
                cache_sso.add("KEY_USER_" + loginReqBean.getLoginName(), loginService.checkUserInfo(loginReqBean.getLoginName()));
            }

            userInfo = (UserInfoBean) cache_sso.get("KEY_USER_" + loginReqBean.getLoginName());

        }

        loginReqBean.setUserInfoBean(userInfo);

        request.setAttribute("reqParams", loginReqBean);

        // 更新超时时间
        cache_sso.replace(loginReqBean.getTokenKey(), "true", 1000 * 60 * WEBApp.SYS_TIMEOUT);

        filterChain.doFilter(request, response);

    }


    public String getExcludeURL() {
        return excludeURL;
    }

    public void setExcludeURL(String excludeURL) {
        this.excludeURL = excludeURL;
        this.whiteList = excludeURL.split(";");
    }

}
