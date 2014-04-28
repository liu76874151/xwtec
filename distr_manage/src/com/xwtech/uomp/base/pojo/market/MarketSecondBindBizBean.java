package com.xwtech.uomp.base.pojo.market;

import java.io.Serializable;

/**
 * 二级营销案绑定业务 
 * This class is used for ...
 * @author zhangel
 * @version 1.0, 2013-10-17 下午08:29:27
 */
public class MarketSecondBindBizBean implements Serializable {
	private static final long serialVersionUID = 8513177534652965024L;

	private String bizId;

	private String bizCode;

	private String marketSecondPkid;

	private String bizName;

	private Short city;

	private String bizComment;

	private String bizType;

	private String state;

	private String gprsCode;
	private String channel;
	private String parentBizCode;
	private String parentBizName;

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

	/**
	 * @return the parentBizName
	 */
	public String getParentBizName() {
		return parentBizName;
	}

	/**
	 * @param parentBizName the parentBizName to set
	 */
	public void setParentBizName(String parentBizName) {
		this.parentBizName = parentBizName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode == null ? null : bizCode.trim();
	}

	public String getMarketSecondPkid() {
		return marketSecondPkid;
	}

	public void setMarketSecondPkid(String marketSecondPkid) {
		this.marketSecondPkid = marketSecondPkid;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName == null ? null : bizName.trim();
	}

	public Short getCity() {
		return city;
	}

	public void setCity(Short city) {
		this.city = city;
	}

	public String getBizComment() {
		return bizComment;
	}

	public void setBizComment(String bizComment) {
		this.bizComment = bizComment == null ? null : bizComment.trim();
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType == null ? null : bizType.trim();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

	public String getGprsCode() {
		return gprsCode;
	}

	public void setGprsCode(String gprsCode) {
		this.gprsCode = gprsCode == null ? null : gprsCode.trim();
	}
}