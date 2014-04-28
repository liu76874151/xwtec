/**
 * Title: ReservationMarketSecondBean.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-10-25 
 * @ time 下午2:10:49
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.pojo.market;

import java.math.BigDecimal;

/**
 * @author zhanglu
 *
 */
public class ReservationMarketSecondBean {

	/**
	 * 二级营销案标识
	 */
	String marketSecondPkId;
	/**
	 * 二级boss编码
	 */
	String marketSecondCode;
	/**
	 * 一级营销案标识
	 */
	String marketFirstPkid;
	/**
	 * 二级boss编码名称
	 */
	String marketSecondName;
	/**
	 * 城市编码
	 */
	String city;
	/**
	 * 营销案展示名称
	 */
	String viewName;
	/**
	 * 图片路径
	 */
	String imgDir;
	/**
	 * 活动对象
	 */
	String toObject;
	/**
	 * 活动说明
	 */
	String activityComment;
	/**
	 * 充值金额
	 */
	BigDecimal moneyVal;
	/**
	 * 开始时间
	 */
	String beginTime;
	/**
	 * 结束时间
	 */
	String endTime;
	/**
	 * 是否在营销案办理列表展示
	 */
	String isListView;
	/**
	 * 方案状态：0无效，1有效
	 */
	String state;
	/**
	 * 创建时间
	 */
	String createTime;
	/**
	 * 最终审核状态：0待审核，1，审核通过2，审核不通过
	 */
	String verifyState;
	/**
	 * 配置工号
	 */
	String cfgUserId;
	/**
	 * 注意事项
	 */
	String notice;
	/**
	 * 省级审核状态：0待审核，1审核通过，2审核不通过
	 */
	String proVerifyState;
	/**
	 * 地市审核状态：0待审核，1审核通过，2审核不通过
	 */
	String localVerifyState;
	/**
	 * 是否目标客户的营销方案，0不是，1是
	 */
	String isInGroup;
	/**
	 * 礼品和业务备注
	 */
	String giftBinzInfo;
	/**
	 * 预约业务编码
	 */
	String bizCode;
	/**
	 * 预约业务名称
	 */
	String bizName;
	/**
	 * 预约礼品编码
	 */
	String bossGiftId;
	/**
	 * 预约礼品名称
	 */
	String bossGiftName;
	/**
	 * BOSS二级编码
	 */
	String bossSecondCode;
	/**
	 * BOSS礼品和业务集合
	 */
	String bossBizGiftInfo;
	/**
	 * 一级预约营销案名称
	 */
	String marketFirstName;
	/**
	 * 渠道
	 */
	String channelData;
	/**
	 * 列表展示操作按钮
	 */
	String linkOper;
	/**
	 * 查看按钮
	 */
	String viewOper;
	
	private String testOnlineState;
	private String areaName;
	private String proOper;
	private String localOper;
	private String testOper;
	private MarketAuditBean marketAuditBean;
	
	private String cityManager;
	private String proManager;
	
	/**
	 * 网厅审核状态
	 */
	private String wtProVerifyState;
	private String wtLocalVerifyState;
	private String wtVerifyState;
	
	/**
	 * 网厅测试状态
	 */
	private String wtTestOnlineState;
	
	
	/**
	 * @return the cityManager
	 */
	public String getCityManager() {
		return cityManager;
	}
	/**
	 * @param cityManager the cityManager to set
	 */
	public void setCityManager(String cityManager) {
		this.cityManager = cityManager;
	}
	/**
	 * @return the proManager
	 */
	public String getProManager() {
		return proManager;
	}
	/**
	 * @param proManager the proManager to set
	 */
	public void setProManager(String proManager) {
		this.proManager = proManager;
	}
	/**
	 * @return the wtProVerifyState
	 */
	public String getWtProVerifyState() {
		return wtProVerifyState;
	}
	/**
	 * @param wtProVerifyState the wtProVerifyState to set
	 */
	public void setWtProVerifyState(String wtProVerifyState) {
		this.wtProVerifyState = wtProVerifyState;
	}
	/**
	 * @return the wtLocalVerifyState
	 */
	public String getWtLocalVerifyState() {
		return wtLocalVerifyState;
	}
	/**
	 * @param wtLocalVerifyState the wtLocalVerifyState to set
	 */
	public void setWtLocalVerifyState(String wtLocalVerifyState) {
		this.wtLocalVerifyState = wtLocalVerifyState;
	}
	/**
	 * @return the wtVerifyState
	 */
	public String getWtVerifyState() {
		return wtVerifyState;
	}
	/**
	 * @param wtVerifyState the wtVerifyState to set
	 */
	public void setWtVerifyState(String wtVerifyState) {
		this.wtVerifyState = wtVerifyState;
	}
	/**
	 * @return the wtTestOnlineState
	 */
	public String getWtTestOnlineState() {
		return wtTestOnlineState;
	}
	/**
	 * @param wtTestOnlineState the wtTestOnlineState to set
	 */
	public void setWtTestOnlineState(String wtTestOnlineState) {
		this.wtTestOnlineState = wtTestOnlineState;
	}
	/**
	 * @return the testOnlineState
	 */
	public String getTestOnlineState() {
		return testOnlineState;
	}
	/**
	 * @param testOnlineState the testOnlineState to set
	 */
	public void setTestOnlineState(String testOnlineState) {
		this.testOnlineState = testOnlineState;
	}
	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * @return the proOper
	 */
	public String getProOper() {
		return proOper;
	}
	/**
	 * @param proOper the proOper to set
	 */
	public void setProOper(String proOper) {
		this.proOper = proOper;
	}
	/**
	 * @return the localOper
	 */
	public String getLocalOper() {
		return localOper;
	}
	/**
	 * @param localOper the localOper to set
	 */
	public void setLocalOper(String localOper) {
		this.localOper = localOper;
	}
	/**
	 * @return the testOper
	 */
	public String getTestOper() {
		return testOper;
	}
	/**
	 * @param testOper the testOper to set
	 */
	public void setTestOper(String testOper) {
		this.testOper = testOper;
	}
	/**
	 * @return the marketAuditBean
	 */
	public MarketAuditBean getMarketAuditBean() {
		return marketAuditBean;
	}
	/**
	 * @param marketAuditBean the marketAuditBean to set
	 */
	public void setMarketAuditBean(MarketAuditBean marketAuditBean) {
		this.marketAuditBean = marketAuditBean;
	}
	/**
	 * @return the viewOper
	 */
	public String getViewOper() {
		return viewOper;
	}
	/**
	 * @param viewOper the viewOper to set
	 */
	public void setViewOper(String viewOper) {
		this.viewOper = viewOper;
	}
	/**
	 * @return the channelData
	 */
	public String getChannelData() {
		return channelData;
	}
	/**
	 * @param channelData the channelData to set
	 */
	public void setChannelData(String channelData) {
		this.channelData = channelData;
	}
	/**
	 * @return the marketFirstName
	 */
	public String getMarketFirstName() {
		return marketFirstName;
	}
	/**
	 * @param marketFirstName the marketFirstName to set
	 */
	public void setMarketFirstName(String marketFirstName) {
		this.marketFirstName = marketFirstName;
	}
	/**
	 * @return the linkOper
	 */
	public String getLinkOper() {
		return linkOper;
	}
	/**
	 * @param linkOper the linkOper to set
	 */
	public void setLinkOper(String linkOper) {
		this.linkOper = linkOper;
	}
	/**
	 * @return the marketSecondPkId
	 */
	public String getMarketSecondPkId() {
		return marketSecondPkId;
	}
	/**
	 * @param marketSecondPkId the marketSecondPkId to set
	 */
	public void setMarketSecondPkId(String marketSecondPkId) {
		this.marketSecondPkId = marketSecondPkId;
	}
	/**
	 * @return the marketSecondCode
	 */
	public String getMarketSecondCode() {
		return marketSecondCode;
	}
	/**
	 * @param marketSecondCode the marketSecondCode to set
	 */
	public void setMarketSecondCode(String marketSecondCode) {
		this.marketSecondCode = marketSecondCode;
	}
	/**
	 * @return the marketFirstPkid
	 */
	public String getMarketFirstPkid() {
		return marketFirstPkid;
	}
	/**
	 * @param marketFirstPkid the marketFirstPkid to set
	 */
	public void setMarketFirstPkid(String marketFirstPkid) {
		this.marketFirstPkid = marketFirstPkid;
	}
	/**
	 * @return the marketSecondName
	 */
	public String getMarketSecondName() {
		return marketSecondName;
	}
	/**
	 * @param marketSecondName the marketSecondName to set
	 */
	public void setMarketSecondName(String marketSecondName) {
		this.marketSecondName = marketSecondName;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the viewName
	 */
	public String getViewName() {
		return viewName;
	}
	/**
	 * @param viewName the viewName to set
	 */
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	/**
	 * @return the imgDir
	 */
	public String getImgDir() {
		return imgDir;
	}
	/**
	 * @param imgDir the imgDir to set
	 */
	public void setImgDir(String imgDir) {
		this.imgDir = imgDir;
	}
	/**
	 * @return the toObject
	 */
	public String getToObject() {
		return toObject;
	}
	/**
	 * @param toObject the toObject to set
	 */
	public void setToObject(String toObject) {
		this.toObject = toObject;
	}
	/**
	 * @return the activityComment
	 */
	public String getActivityComment() {
		return activityComment;
	}
	/**
	 * @param activityComment the activityComment to set
	 */
	public void setActivityComment(String activityComment) {
		this.activityComment = activityComment;
	}
	/**
	 * @return the moneyVal
	 */
	public BigDecimal getMoneyVal() {
		return moneyVal;
	}
	/**
	 * @param moneyVal the moneyVal to set
	 */
	public void setMoneyVal(BigDecimal moneyVal) {
		this.moneyVal = moneyVal;
	}
	/**
	 * @return the beginTime
	 */
	public String getBeginTime() {
		return beginTime;
	}
	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the isListView
	 */
	public String getIsListView() {
		return isListView;
	}
	/**
	 * @param isListView the isListView to set
	 */
	public void setIsListView(String isListView) {
		this.isListView = isListView;
	}
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
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the verifyState
	 */
	public String getVerifyState() {
		return verifyState;
	}
	/**
	 * @param verifyState the verifyState to set
	 */
	public void setVerifyState(String verifyState) {
		this.verifyState = verifyState;
	}
	/**
	 * @return the cfgUserId
	 */
	public String getCfgUserId() {
		return cfgUserId;
	}
	/**
	 * @param cfgUserId the cfgUserId to set
	 */
	public void setCfgUserId(String cfgUserId) {
		this.cfgUserId = cfgUserId;
	}
	/**
	 * @return the notice
	 */
	public String getNotice() {
		return notice;
	}
	/**
	 * @param notice the notice to set
	 */
	public void setNotice(String notice) {
		this.notice = notice;
	}
	/**
	 * @return the proVerifyState
	 */
	public String getProVerifyState() {
		return proVerifyState;
	}
	/**
	 * @param proVerifyState the proVerifyState to set
	 */
	public void setProVerifyState(String proVerifyState) {
		this.proVerifyState = proVerifyState;
	}
	/**
	 * @return the localVerifyState
	 */
	public String getLocalVerifyState() {
		return localVerifyState;
	}
	/**
	 * @param localVerifyState the localVerifyState to set
	 */
	public void setLocalVerifyState(String localVerifyState) {
		this.localVerifyState = localVerifyState;
	}
	/**
	 * @return the isInGroup
	 */
	public String getIsInGroup() {
		return isInGroup;
	}
	/**
	 * @param isInGroup the isInGroup to set
	 */
	public void setIsInGroup(String isInGroup) {
		this.isInGroup = isInGroup;
	}
	/**
	 * @return the giftBinzInfo
	 */
	public String getGiftBinzInfo() {
		return giftBinzInfo;
	}
	/**
	 * @param giftBinzInfo the giftBinzInfo to set
	 */
	public void setGiftBinzInfo(String giftBinzInfo) {
		this.giftBinzInfo = giftBinzInfo;
	}
	/**
	 * @return the bizCode
	 */
	public String getBizCode() {
		return bizCode;
	}
	/**
	 * @param bizCode the bizCode to set
	 */
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	/**
	 * @return the bizName
	 */
	public String getBizName() {
		return bizName;
	}
	/**
	 * @param bizName the bizName to set
	 */
	public void setBizName(String bizName) {
		this.bizName = bizName;
	}
	/**
	 * @return the bossGiftId
	 */
	public String getBossGiftId() {
		return bossGiftId;
	}
	/**
	 * @param bossGiftId the bossGiftId to set
	 */
	public void setBossGiftId(String bossGiftId) {
		this.bossGiftId = bossGiftId;
	}
	/**
	 * @return the bossGiftName
	 */
	public String getBossGiftName() {
		return bossGiftName;
	}
	/**
	 * @param bossGiftName the bossGiftName to set
	 */
	public void setBossGiftName(String bossGiftName) {
		this.bossGiftName = bossGiftName;
	}
	/**
	 * @return the bossSecondCode
	 */
	public String getBossSecondCode() {
		return bossSecondCode;
	}
	/**
	 * @param bossSecondCode the bossSecondCode to set
	 */
	public void setBossSecondCode(String bossSecondCode) {
		this.bossSecondCode = bossSecondCode;
	}
	/**
	 * @return the bossBizGiftInfo
	 */
	public String getBossBizGiftInfo() {
		return bossBizGiftInfo;
	}
	/**
	 * @param bossBizGiftInfo the bossBizGiftInfo to set
	 */
	public void setBossBizGiftInfo(String bossBizGiftInfo) {
		this.bossBizGiftInfo = bossBizGiftInfo;
	}
	

}
