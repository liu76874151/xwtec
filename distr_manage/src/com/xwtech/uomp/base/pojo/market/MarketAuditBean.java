package com.xwtech.uomp.base.pojo.market;

import java.io.Serializable;

/**
 * 营销案审批
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-31 上午11:26:17
 */
public class MarketAuditBean implements Serializable{
	
	private static final long serialVersionUID = 7991516943974770469L;

	private Long marketPkid;

	private String auditPerson;

	private String auditTime;

	private String autitState;

	private String marketType;
	
	private String auditContent;
	
	private String marketLevel;
	
	private String auditLevel;
	
	private String operType;
	
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

	/**
	 * @return the operType
	 */
	public String getOperType() {
		return operType;
	}

	/**
	 * @param operType the operType to set
	 */
	public void setOperType(String operType) {
		this.operType = operType;
	}

	/**
	 * @return the auditLevel
	 */
	public String getAuditLevel() {
		return auditLevel;
	}

	/**
	 * @param auditLevel the auditLevel to set
	 */
	public void setAuditLevel(String auditLevel) {
		this.auditLevel = auditLevel;
	}

	/**
	 * @return the marketLevel
	 */
	public String getMarketLevel() {
		return marketLevel;
	}

	/**
	 * @param marketLevel the marketLevel to set
	 */
	public void setMarketLevel(String marketLevel) {
		this.marketLevel = marketLevel;
	}

	/**
	 * @return the auditContent
	 */
	public String getAuditContent() {
		return auditContent;
	}

	/**
	 * @param auditContent the auditContent to set
	 */
	public void setAuditContent(String auditContent) {
		this.auditContent = auditContent;
	}

	public Long getMarketPkid() {
		return marketPkid;
	}

	public void setMarketPkid(Long marketPkid) {
		this.marketPkid = marketPkid;
	}

	public String getAuditPerson() {
		return auditPerson;
	}

	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson == null ? null : auditPerson.trim();
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime == null ? null : auditTime.trim();
	}

	public String getAutitState() {
		return autitState;
	}

	public void setAutitState(String autitState) {
		this.autitState = autitState == null ? null : autitState.trim();
	}

	public String getMarketType() {
		return marketType;
	}

	public void setMarketType(String marketType) {
		this.marketType = marketType == null ? null : marketType.trim();
	}
}