package com.xwtech.uomp.base.constants;

public interface RequestConstants {

    /**
     * 0-普通用户；1-扩展普通用户；2-正常组管理员；3-扩展组管理员
     */
    String COM_USER = "0";
    String EXP_USER = "1";
    String COM_ADMIN = "2";
    String EXP_ADMIN = "3";
    /**
     * 权限认证 *
     */
    String LOGIN_KEY = "ln";
    String MODEL_KEY = "mid";
    String TOKEN_COOKIE_KEY = "token";
    String LOGIN_NAME = "name";
    String CURRENT_POSITION = "currentPosition";


    String ADMIN_SESSION_KEY = "admin";//管理员存储session 的对象Key
    String ADMIN_CHECK_CODE = "admin_login_code";//管理员登陆的验证码
    String RESULT_INFO = "resultInfo";//信息页面结果对象
    String INFORMATION = "information";//信息页面需要的信息对象
}
