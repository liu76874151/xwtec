package com.xwtech.uomp.base.pojo.sso;

import com.xwtech.uomp.base.pojo.admin.UserInfoBean;

public class LoginResponseBean {
    /**
     * 返回码 *
     */
    private String retCode;
    /**
     * 错误码 *
     */
    private String errorCode;
    /**
     * 返回信息 *
     */
    private String retMsg;
    /**
     * 返回结果 *
     */
    private Object retObj;
    /**
     * 请求类型 *
     */
    private String requestType;
    /**
     * 用户信息 *
     */
    private UserInfoBean userInfoBean;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public Object getRetObj() {
        return retObj;
    }

    public void setRetObj(Object retObj) {
        this.retObj = retObj;
    }

    public UserInfoBean getUserInfoBean() {
        return userInfoBean;
    }

    public void setUserInfoBean(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


}
