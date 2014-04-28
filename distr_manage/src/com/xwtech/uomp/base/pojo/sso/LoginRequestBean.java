package com.xwtech.uomp.base.pojo.sso;

import java.util.List;

import com.xwtech.uomp.base.pojo.RequestHandleUrlBean;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;


public class LoginRequestBean {
    /**
     * 登录名 *
     */
    private String loginName;
    /**
     * 令牌  *
     */
    private String tokenKey;
    /**
     * 模块URL *
     */
    private List<RequestHandleUrlBean> handleList = null;
    /**
     * 系统编码 *
     */
    private String sysNum;
    /**
     * 请求类型 :1.页面，2业务类型 *
     */
    private String requestType;
    /**
     * 用户信息 *
     */
    private UserInfoBean userInfoBean;

    public List<RequestHandleUrlBean> getHandleList() {
        return handleList;
    }

    public void setHandleList(List<RequestHandleUrlBean> handleList) {
        this.handleList = handleList;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getSysNum() {
        return sysNum;
    }

    public void setSysNum(String sysNum) {
        this.sysNum = sysNum;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public UserInfoBean getUserInfoBean() {
        return userInfoBean;
    }

    public void setUserInfoBean(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }

}
