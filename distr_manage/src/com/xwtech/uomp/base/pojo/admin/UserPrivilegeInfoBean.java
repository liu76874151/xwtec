package com.xwtech.uomp.base.pojo.admin;

import java.io.Serializable;

public class UserPrivilegeInfoBean implements Serializable {
    private static final long serialVersionUID = 6604253755391172426L;

    private String subSysNum;

    private String userId;

    /**
     * 用户类别（0-用户组；1-用户）
     */
    private int userType;

    private String funcId;

    private int optType;

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getOptType() {
        return optType;
    }

    public void setOptType(int optType) {
        this.optType = optType;
    }

    public String getSubSysNum() {
        return subSysNum;
    }

    public void setSubSysNum(String subSysNum) {
        this.subSysNum = subSysNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
