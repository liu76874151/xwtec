package com.xwtech.uomp.base.pojo.admin;

import java.io.Serializable;

import com.xwtech.uomp.base.pojo.OrderInfoBean;

public class UserInfoBean extends OrderInfoBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3600940046598973044L;

    /**
     * 登陆名称
     */
    private String loginName;

    /**
     * 登陆密码
     */
    private String loginPwd;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户所属组别
     */
    private String userGroup;

    /**
     * 用户所属组别名称
     */
    private String userGroupName;

    /**
     * 用户状态（0-禁用； 1-启用）
     */
    private int userState;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 备注
     */
    private String bz;

    /**
     * 用户所属地区
     */
    private String userArea;

    private String userType;

    private String tokenKey;
    
    /**
     * 用户所属地区code
     */
    private String userAreaCode;
    /**
     * 渠道
     * 创建日期：2014-1-23下午8:21:59
     * 修改日期：
     * 作者：zhanglu
     * @param:
     * @return:String
     */
    private String channelNum;

    /**
	 * @return the channelNum
	 */
	public String getChannelNum() {
		return channelNum;
	}

	/**
	 * @param channelNum the channelNum to set
	 */
	public void setChannelNum(String channelNum) {
		this.channelNum = channelNum;
	}

	public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public String getUserArea() {
        return userArea;
    }

    public void setUserArea(String userArea) {
        this.userArea = userArea;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

	public String getUserAreaCode() {
		return userAreaCode;
	}

	public void setUserAreaCode(String userAreaCode) {
		this.userAreaCode = userAreaCode;
	}

}
