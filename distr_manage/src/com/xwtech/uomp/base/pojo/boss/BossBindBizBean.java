package com.xwtech.uomp.base.pojo.boss;

import java.io.Serializable;

/**
 * boss绑定业务
 * 
 * @author zhangel
 * 
 */
public class BossBindBizBean implements Serializable {

    private static final long serialVersionUID = -5818940229169921451L;

    private Long bizId;

    private String bizCode;

    private String marketSecondCode;

    private String bizName;

    private String cityId;

    private String tchannal;
    
    private String parentBizCode;
    
    private String state;
    

    /**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the parentBizCode
	 */
	public String getParentBizCode() {
		return parentBizCode;
	}

	/**
	 * @param parentBizCode the parentBizCode to set
	 */
	public void setParentBizCode(String parentBizCode) {
		this.parentBizCode = parentBizCode;
	}

	public String getTchannal() {
	return tchannal;
    }

    public void setTchannal(String tchannal) {
	this.tchannal = tchannal;
    }

    public Long getBizId() {
	return bizId;
    }

    public void setBizId(Long bizId) {
	this.bizId = bizId;
    }

    public String getBizCode() {
	return bizCode;
    }

    public void setBizCode(String bizCode) {
	this.bizCode = bizCode == null ? null : bizCode.trim();
    }

    public String getMarketSecondCode() {
	return marketSecondCode;
    }

    public void setMarketSecondCode(String marketSecondCode) {
	this.marketSecondCode = marketSecondCode == null ? null : marketSecondCode.trim();
    }

    public String getBizName() {
	return bizName;
    }

    public void setBizName(String bizName) {
	this.bizName = bizName == null ? null : bizName.trim();
    }

    public String getCityId() {
	return cityId;
    }

    public void setCityId(String cityId) {
	this.cityId = cityId;
    }

}