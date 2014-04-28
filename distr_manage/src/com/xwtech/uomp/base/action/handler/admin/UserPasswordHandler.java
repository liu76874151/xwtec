package com.xwtech.uomp.base.action.handler.admin;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.RequestConstants;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.service.IUserPasswordService;
import com.xwtech.uomp.base.util.Md5Util;
import com.xwtech.uomp.base.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yugonggan
 * @ClassName: UserPasswordHandler
 * @Description: TODO(密码修改)
 * @date Mar 6, 2013 8:43:57 PM
 */

@Component("H_userPassword")
public class UserPasswordHandler implements IHandler {

    @Autowired
    IUserPasswordService userPasswordService;

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: modifyUserPasswd
     * @Description: TODO(add by yugonggan 管理员密码修改)
     */
    public HandlerResult modifyUserPasswd(HandlerRequestContext context) {

        HandlerResult result = new HandlerResult();

        String oldPasswd = context.getRequest().getParameter("oldPasswd");// 获取旧密码
        String newPasswd = context.getRequest().getParameter("newPasswd"); // 获取新密码

        try {
            UserInfoBean userInfo = (UserInfoBean) SessionUtil
                    .getObjectAttribute(context.getRequest(),
                            RequestConstants.ADMIN_SESSION_KEY);
            if ((userInfo == null)
                    || ("".equals(oldPasswd.trim()) || ("".equals(newPasswd
                    .trim())))) {
                result.setSysCode(SystemCodeConstants.CHECK_FIELD_EMPTY);
                result.setRetCode(IResultCode.SYS_FAILED);

            } else if (newPasswd.trim()
                    .equals("123456")) {
                result.setSysCode(SystemCodeConstants.UPDATE_DEFALUTPWD_FAILED);
                result.setRetCode(IResultCode.SYS_FAILED);
            } else {
                String loginName = userInfo.getLoginName();
                if (!Md5Util.toMD5(oldPasswd).equals(userInfo.getLoginPwd())) {
                    // 旧密码错误
                    result
                            .setSysCode(SystemCodeConstants.CHECK_OLDPASSWORD_ERROR);
                    result.setRetCode(IResultCode.SYS_FAILED);
                } else {
                    UserInfoBean userInfoBean = new UserInfoBean();
                    userInfoBean.setLoginName(loginName);
                    userInfoBean.setLoginPwd(Md5Util.toMD5(newPasswd));
                    int intResult = userPasswordService
                            .modifyUserPasswd(userInfoBean); // 密码修改
                    if (intResult == 0) {
                        userInfo.setLoginPwd(Md5Util.toMD5(newPasswd));
                        result
                                .setSysCode(SystemCodeConstants.UPDATE_PASSWORD_SUCCESS);
                        result.setRetCode(IResultCode.SYS_SUCCESS);
                    } else {

                        result
                                .setSysCode(SystemCodeConstants.UPDATE_PASSWORD_FAILED);
                        result.setRetCode(IResultCode.SYS_FAILED);

                    }
                }
            }
        } catch (Exception e) {
            result.setSysCode(SystemCodeConstants.UPDATE_PASSWORD_FAILED);
            result.setRetCode(IResultCode.SYS_FAILED);
        }

        return result;
    }
}
