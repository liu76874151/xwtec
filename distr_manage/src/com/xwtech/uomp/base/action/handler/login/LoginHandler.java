package com.xwtech.uomp.base.action.handler.login;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.RequestConstants;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.service.ILoginService;
import com.xwtech.uomp.base.util.SSOUtil;
import com.xwtech.uomp.base.util.SessionUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component("H_login")
public class LoginHandler implements IHandler {
    private final static Logger log = Logger.getLogger(LoginHandler.class);

    @Autowired
    ILoginService loginService;

    public HandlerResult loginCheck(HandlerRequestContext context) {

        long begin = System.currentTimeMillis();
        //返回结果对象
        HandlerResult result = HandlerResult.newInstance();

        //登陆帐号
        String loginId = context.getRequest().getParameter("loginId");

        //登陆密码 MD5
        String loginPwd = context.getRequest().getParameter("loginPwd");
        //登陆验证码
        String loginCode = context.getRequest().getParameter("loginCode");
        //系统获取验证码
        String sessionCode = "";

        try {
            //从Session中获取验证码
            sessionCode = (String) SessionUtil.getObjectAttribute(context.getRequest(), RequestConstants.ADMIN_CHECK_CODE);
            //设置验证码失效
            SessionUtil.removeObjectAttribute(context.getRequest(), RequestConstants.ADMIN_CHECK_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            sessionCode = null;
        }

        //判断验证码是否正确
        if (!loginCode.equals(sessionCode)) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.VERIFICATION_CODE_ERROR);
            return result;
        }

        //判断登陆信息有效性 判断登陆密码是否有效
        if (("".equals(loginId)) || ("".equals(loginPwd))) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.LOGIN_PWD_EMPTY);
            return result;
        }
        //从数据库中获取管理员信息
        UserInfoBean userInfo = (UserInfoBean) loginService.checkUserInfo(loginId);

        //判断是否取到密码
        if (userInfo == null) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.NOT_USER);
            return result;
        }

        //判断密码是否正确
        if (!loginPwd.equals(userInfo.getLoginPwd())) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.LOGIN_PWD_ERROR);
            return result;
        }

        //判断管理状态
        if (userInfo.getUserState() != 1) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.USER_STATE);
            return result;
        }
        //session中存储登录用户信息
        SessionUtil.setObjectAttribute(context.getRequest(), RequestConstants.ADMIN_SESSION_KEY, userInfo);

        SSOUtil.setSSOLogin(context.getRequest(), context.getResponse());

        long end = System.currentTimeMillis();
        log.info("login check cost " + (end - begin) + " ms!");
        //进入管理界面
        return result;
    }

    public HandlerResult getLogout(HandlerRequestContext context) {

        //返回结果对象
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();
        //sso推出登录状态
        SSOUtil.setSSOLogout(request, context.getResponse());
        //清空session中内容
        SessionUtil.removeObjectAttribute(request, RequestConstants.ADMIN_SESSION_KEY);
        result.setRetCode(IResultCode.SYS_SUCCESS);
        result.setSysCode(SystemCodeConstants.LOGOUT_SYSTEM_INFO);
        return result;

    }
}
