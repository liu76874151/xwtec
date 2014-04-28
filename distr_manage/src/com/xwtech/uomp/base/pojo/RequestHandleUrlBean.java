package com.xwtech.uomp.base.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class RequestHandleUrlBean {
    //系统编码
    private String sysNum;
    //处理类URL
    private String handleUrl;
    //登录名
    private String loginName;

    public String getHandleUrl() {
        return handleUrl;
    }

    public void setHandleUrl(String handleUrl) {
        this.handleUrl = handleUrl;
    }

    public String getSysNum() {
        return sysNum;
    }

    public void setSysNum(String sysNum) {
        this.sysNum = sysNum;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String toString() {
        // TODO Auto-generated method stub
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
