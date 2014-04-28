package com.xwtech.uomp.base.util;

import com.xwtech.uomp.base.WEBApp;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.RequestConstants;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.pojo.sso.LoginResponseBean;
import com.xwtech.uomp.base.service.ILoginService;
import com.xwtech.uomp.base.service.cache.ICacheService;
import com.xwtech.uomp.base.service.cache.ICacheServiceFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SSOUtil {

    private static Logger loger = Logger.getLogger(SSOUtil.class);

    private static ICacheServiceFactory cacheServiceFactory = (ICacheServiceFactory) WEBApp.SPRING_CONTEXT.getBean("cacheServiceFactory");

    private static ICacheService cache_sso = cacheServiceFactory.getCacheService("ecm_code");

    /**
     * 获取客户端IP
     *
     * @param request
     * @return
     */
    public static String getMyIpAddr(HttpServletRequest request) {
        String strUserIp = null;
        /** * */
        // Apache 代理 解决IP地址问题
        strUserIp = request.getHeader("X-Forwarded-For");
        if (strUserIp == null || strUserIp.length() == 0 || "unknown".equalsIgnoreCase(strUserIp)) {
            strUserIp = request.getHeader("Proxy-Client-IP");
        }
        if (strUserIp == null || strUserIp.length() == 0 || "unknown".equalsIgnoreCase(strUserIp)) {
            strUserIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (strUserIp == null || strUserIp.length() == 0 || "unknown".equalsIgnoreCase(strUserIp)) {
            strUserIp = request.getRemoteAddr();
        }

        // 解决获取多网卡的IP地址问题
        if (strUserIp != null) {
            strUserIp = strUserIp.split(",")[0];
        } else {
            strUserIp = "127.0.0.1";
        }

        // 解决获取IPv6地址 暂时改为本机地址模式
        if (strUserIp.length() > 16) {
            strUserIp = "127.0.0.1";
        }
        return strUserIp;

        // 没有IP Apache 代理 解决IP地址问题
        // strUserIp=request.getRemoteAddr();
        // if (strUserIp != null) {strUserIp = strUserIp.split(",")[0];}
        // return strUserIp;
    }

    /**
     * 登录成功后，设置用户信息到cookie中
     *
     * @param request
     * @param response
     */
    public static void setSSOLogin(HttpServletRequest request, HttpServletResponse response) {
        UserInfoBean user = (UserInfoBean) request.getSession(true).getAttribute(RequestConstants.ADMIN_SESSION_KEY);
        //获取sessionId
        String sessionId = request.getSession(true).getId();
        String clientIp = getMyIpAddr(request);
        String loginName = user.getLoginName();
        String tokenKey = loginName + "_" + sessionId + "_" + clientIp;
        //MD5加密
        loger.info("加密前tokenKey======================" + tokenKey);
        String MdsKey = Md5Util.toMD5(tokenKey);
        loger.info("加密后tokenKey======================" + MdsKey);
        cache_sso.add(MdsKey, "true", 1000 * 60 * WEBApp.SYS_TIMEOUT);
        loger.info("超时时间timeout======================" + 1000 * 60 * WEBApp.SYS_TIMEOUT);
        Cookie oItem = new Cookie(RequestConstants.TOKEN_COOKIE_KEY, MdsKey);
        //设置域
        oItem.setDomain(WEBApp.SSO_COOKIE_DOMAIN);
        loger.info("设置DOMIAN======================" + WEBApp.SSO_COOKIE_DOMAIN);
        //设置cookie的有效时间
        oItem.setMaxAge(-1);
        oItem.setPath("/");
        loger.info("设置CCOOKIE成功！");
        response.addCookie(oItem);
        Cookie nameCookie = new Cookie(RequestConstants.LOGIN_NAME, loginName);
        //设置域
        nameCookie.setDomain(WEBApp.SSO_COOKIE_DOMAIN);
        //设置cookie的有效时间
        nameCookie.setMaxAge(-1);
        nameCookie.setPath("/");
        response.addCookie(nameCookie);
    }

    /**
     * 检查登录状态
     *
     * @param loginReqBean
     * @param request
     * @return
     */
    public static LoginResponseBean checkSSOState(LoginRequestBean loginReqBean, HttpServletRequest request) {
        LoginResponseBean loginResBean = new LoginResponseBean();
        String tokenKey = loginReqBean.getTokenKey();
        String loginName = loginReqBean.getLoginName();
        String flag = (String) cache_sso.get(tokenKey);
        if (StringUtils.isBlank(flag) || !"true".equals(flag)) {
            loginResBean.setRetCode(IResultCode.SYS_FAILED);
            loginResBean.setErrorCode(SystemCodeConstants.NOT_LOGIN);
        } else {
            if (!StringUtils.isBlank(loginName)) {
                UserInfoBean user = new UserInfoBean();
                try {
                    ILoginService loginService = (ILoginService) WEBApp.SPRING_CONTEXT.getBean("loginService");
                    UserInfoBean userInfoBean = loginService.checkUserInfo(loginName);
                    if (userInfoBean != null) {
                        user = userInfoBean;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                user.setTokenKey(tokenKey);
                loginResBean.setUserInfoBean(user);
            } else {
                loginResBean.setUserInfoBean(loginReqBean.getUserInfoBean());
            }
            loginResBean.setRetCode(IResultCode.SYS_SUCCESS);
            //刷新SSO
            cache_sso.replace(tokenKey, "true", 1000 * 60 * WEBApp.SYS_TIMEOUT);
        }
        return loginResBean;
    }

    /**
     * 登出操作
     *
     * @param request
     * @param response
     */
    public static void setSSOLogout(HttpServletRequest request, HttpServletResponse response) {
        //SSO服务器上保存用户登录状态
        String tokenKey = getLoginInfoForCookie(RequestConstants.TOKEN_COOKIE_KEY, request);
        if (tokenKey != null) {
            cache_sso.delete(tokenKey);
        }

        //获取客户端cookies
        Cookie cookie = new Cookie(RequestConstants.TOKEN_COOKIE_KEY, null);
        //设置域
        cookie.setDomain(WEBApp.SSO_COOKIE_DOMAIN);
        //立即删除
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        //获取客户端cookies
        Cookie nameCookie = new Cookie(RequestConstants.LOGIN_NAME, null);
        //设置域
        nameCookie.setDomain(WEBApp.SSO_COOKIE_DOMAIN);
        //立即删除
        nameCookie.setMaxAge(0);
        nameCookie.setPath("/");
        response.addCookie(nameCookie);
    }

    /**
     * 获取登录Cookie信息
     *
     * @param request
     * @return
     */
    public static String getLoginInfoForCookie(String key, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (key.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
