package com.xwtech.uomp.base.pojo.market;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.xwtech.uomp.base.pojo.gift.GiftInfoBean;
/**
 * 二级营销案
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-17 下午02:38:09
 */
public class MarketSecondBean implements Serializable {

	private static final long serialVersionUID = -8662102676496941995L;

	private String marketSecondPkid;// 营销方案id
	private String marketFirstPkid;// 一级营销方案id
	private String marketSecondCode;// 二级营销案编码
	private String marketSecondName;// boss名称
	private String city;//城市标识
	private String cityName;//城市标识
	private String viewName;//二级营销案名称
	private String imgDir;
	private String flashDir;
	private String toObject;//活动对象
	private String activityComment;
	private String isNeedMoney;
	private BigDecimal moneyVal;
	private String beginTime;
	private String endTime;
	private String isFlashView;
	private String isListView;
	private String isListViewLink;
	private String viewTemplateId;
	private String pactTemplateId;
	private String state;
	private String createTime;
	private String verifyState;
	private String pactTemplateContent;
	private String giftGetTypes;
	private String cfgUserId;
	private String notice;
	private String proVerifyState;
	private String localVerifyState;
	private String marketSecondType;
	private String isInGroup;
	private String fnestificationType;
	private String businessNum;
	private String lowestConsumption;
	private String firstAccount;
	private String monthlyRelease;
	private String agreementTime;
	private String firstTop;
	private String ticketFlag;
	private String channalData;
	
	private String dtBrandId;
	private String dtConfirmExtraMsg;
	private String dtIsRecommend;
	private String dtMutexEnable;
	private String dtMutexBusi;
	private String dtUnallowBusi;
	private String dtBeforeEffectMsg;
	private String dtExpireMsg;
	private String dtSpCode;
	private String dtSendSuccMsgEnable;
	private String dtSuccMsg;
	private String cmdEnableFlag;
	private String cmdCotent;
	private String busiMarketSms;
	private String dtConfigureMoney;
	private String dtIsInGroup;
	private String wtIsInGroup;
	private String isDT;

	
	private List<MarketSecondBindBizBean> marketSecondBindBizBeans;
	private List<MarketSecondBindBizBean> wtMarketSecondBindBizBeans;
	private List<MarketSecondBindBizBean> dtMarketSecondBindBizBeans;
	private List<GiftInfoBean> giftInfoBeans;
	private List<GiftInfoBean> wtGiftInfoBeans;
	private List<GiftInfoBean> dtGftInfoBeans;
	private List<MarketGroupRelationBean> relationBeans;
	private List<MarketGroupRelationBean> wtRelationBeans;
	private List<MarketGroupRelationBean> dtRelationBeans;
	private List<MarketOrgRelationBean> orgRelationBeans;
	private List<MarketPayShowBean> payShowBeans;
	
	private String modify;
	private String verify;
	private MarketFirstBean marketFirstBean;
	private MarketAuditBean marketAuditBean;
	private String preview;

	private String cityManager;
	private String proManager;
	
	private String proOper;
	private String localOper;
	
	private String testOnlineState;
	private String testOper;
	
	private String areaName;
	
	private String sweetPrompt;
	private String isNeedCashBooks;
	
	private String marketFirstName;
	
	/**
	 * 短厅审核状态
	 */
	private String dtProVerifyState;
	private String dtLocalVerifyState;
	private String dtVerifyState;
	
	/**
	 * 网厅审核状态
	 */
	private String wtProVerifyState;
	private String wtLocalVerifyState;
	private String wtVerifyState;
	
	/**
	 * 短厅测试状态
	 */
	private String dtTestOnlineState;
	/**
	 * 网厅测试状态
	 */
	private String wtTestOnlineState;
	/**
	 * 网厅列表展示状态
	 */
	private String wtIsListView;
	/**
     * 掌厅营销案展示顺序
     */
    private String ztMarketOrder;
    /**
     * 网厅营销案展示顺序
     */
    private String wtMarketOrder;
	
	public String getZtMarketOrder() {
		return ztMarketOrder;
	}

	public void setZtMarketOrder(String ztMarketOrder) {
		this.ztMarketOrder = ztMarketOrder;
	}

	public String getWtMarketOrder() {
		return wtMarketOrder;
	}

	public void setWtMarketOrder(String wtMarketOrder) {
		this.wtMarketOrder = wtMarketOrder;
	}

	/**
	 * @return the wtIsListView
	 */
	public String getWtIsListView() {
		return wtIsListView;
	}

	/**
	 * @param wtIsListView the wtIsListView to set
	 */
	public void setWtIsListView(String wtIsListView) {
		this.wtIsListView = wtIsListView;
	}

	/**
	 * @return the dtTestOnlineState
	 */
	public String getDtTestOnlineState() {
		return dtTestOnlineState;
	}

	/**
	 * @param dtTestOnlineState the dtTestOnlineState to set
	 */
	public void setDtTestOnlineState(String dtTestOnlineState) {
		this.dtTestOnlineState = dtTestOnlineState;
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
	 * @return the dtProVerifyState
	 */
	public String getDtProVerifyState() {
		return dtProVerifyState;
	}

	/**
	 * @param dtProVerifyState the dtProVerifyState to set
	 */
	public void setDtProVerifyState(String dtProVerifyState) {
		this.dtProVerifyState = dtProVerifyState;
	}

	/**
	 * @return the dtLocalVerifyState
	 */
	public String getDtLocalVerifyState() {
		return dtLocalVerifyState;
	}

	/**
	 * @param dtLocalVerifyState the dtLocalVerifyState to set
	 */
	public void setDtLocalVerifyState(String dtLocalVerifyState) {
		this.dtLocalVerifyState = dtLocalVerifyState;
	}

	/**
	 * @return the dtVerifyState
	 */
	public String getDtVerifyState() {
		return dtVerifyState;
	}

	/**
	 * @param dtVerifyState the dtVerifyState to set
	 */
	public void setDtVerifyState(String dtVerifyState) {
		this.dtVerifyState = dtVerifyState;
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

	public List<GiftInfoBean> getWtGiftInfoBeans() {
		return wtGiftInfoBeans;
	}

	public void setWtGiftInfoBeans(List<GiftInfoBean> wtGiftInfoBeans) {
		this.wtGiftInfoBeans = wtGiftInfoBeans;
	}

	public List<GiftInfoBean> getDtGftInfoBeans() {
		return dtGftInfoBeans;
	}

	public void setDtGftInfoBeans(List<GiftInfoBean> dtGftInfoBeans) {
		this.dtGftInfoBeans = dtGftInfoBeans;
	}

	public List<MarketSecondBindBizBean> getWtMarketSecondBindBizBeans() {
		return wtMarketSecondBindBizBeans;
	}

	public void setWtMarketSecondBindBizBeans(
			List<MarketSecondBindBizBean> wtMarketSecondBindBizBeans) {
		this.wtMarketSecondBindBizBeans = wtMarketSecondBindBizBeans;
	}

	public List<MarketSecondBindBizBean> getDtMarketSecondBindBizBeans() {
		return dtMarketSecondBindBizBeans;
	}

	public void setDtMarketSecondBindBizBeans(
			List<MarketSecondBindBizBean> dtMarketSecondBindBizBeans) {
		this.dtMarketSecondBindBizBeans = dtMarketSecondBindBizBeans;
	}

	public String getIsDT() {
		return isDT;
	}

	public void setIsDT(String isDT) {
		this.isDT = isDT;
	}

	public List<MarketGroupRelationBean> getWtRelationBeans() {
		return wtRelationBeans;
	}

	public void setWtRelationBeans(List<MarketGroupRelationBean> wtRelationBeans) {
		this.wtRelationBeans = wtRelationBeans;
	}

	public List<MarketGroupRelationBean> getDtRelationBeans() {
		return dtRelationBeans;
	}

	public void setDtRelationBeans(List<MarketGroupRelationBean> dtRelationBeans) {
		this.dtRelationBeans = dtRelationBeans;
	}

	public String getDtIsInGroup() {
		return dtIsInGroup;
	}

	public void setDtIsInGroup(String dtIsInGroup) {
		this.dtIsInGroup = dtIsInGroup;
	}

	public String getWtIsInGroup() {
		return wtIsInGroup;
	}

	public void setWtIsInGroup(String wtIsInGroup) {
		this.wtIsInGroup = wtIsInGroup;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDtSuccMsg() {
		return dtSuccMsg;
	}

	public void setDtSuccMsg(String dtSuccMsg) {
		this.dtSuccMsg = dtSuccMsg;
	}

	public String getIsNeedCashBooks() {
		return isNeedCashBooks;
	}

	public void setIsNeedCashBooks(String isNeedCashBooks) {
		this.isNeedCashBooks = isNeedCashBooks;
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
	 * @return the sweetPrompt
	 */
	public String getSweetPrompt() {
		return sweetPrompt;
	}

	/**
	 * @param sweetPrompt the sweetPrompt to set
	 */
	public void setSweetPrompt(String sweetPrompt) {
		this.sweetPrompt = sweetPrompt;
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

	public List<GiftInfoBean> getGiftInfoBeans() {
		return giftInfoBeans;
	}

	public void setGiftInfoBeans(List<GiftInfoBean> giftInfoBeans) {
		this.giftInfoBeans = giftInfoBeans;
	}

	public List<MarketGroupRelationBean> getRelationBeans() {
		return relationBeans;
	}

	public void setRelationBeans(List<MarketGroupRelationBean> relationBeans) {
		this.relationBeans = relationBeans;
	}

	public List<MarketOrgRelationBean> getOrgRelationBeans() {
		return orgRelationBeans;
	}

	public void setOrgRelationBeans(List<MarketOrgRelationBean> orgRelationBeans) {
		this.orgRelationBeans = orgRelationBeans;
	}

	public List<MarketPayShowBean> getPayShowBeans() {
		return payShowBeans;
	}

	public void setPayShowBeans(List<MarketPayShowBean> payShowBeans) {
		this.payShowBeans = payShowBeans;
	}

	public List<MarketSecondBindBizBean> getMarketSecondBindBizBeans() {
		return marketSecondBindBizBeans;
	}

	public void setMarketSecondBindBizBeans(
			List<MarketSecondBindBizBean> marketSecondBindBizBeans) {
		this.marketSecondBindBizBeans = marketSecondBindBizBeans;
	}

	public String getDtBrandId() {
		return dtBrandId;
	}

	public void setDtBrandId(String dtBrandId) {
		this.dtBrandId = dtBrandId;
	}

	public String getDtConfirmExtraMsg() {
		return dtConfirmExtraMsg;
	}

	public void setDtConfirmExtraMsg(String dtConfirmExtraMsg) {
		this.dtConfirmExtraMsg = dtConfirmExtraMsg;
	}



	public String getDtSpCode() {
		return dtSpCode;
	}

	public void setDtSpCode(String dtSpCode) {
		this.dtSpCode = dtSpCode;
	}

	public String getDtSendSuccMsgEnable() {
		return dtSendSuccMsgEnable;
	}

	public void setDtSendSuccMsgEnable(String dtSendSuccMsgEnable) {
		this.dtSendSuccMsgEnable = dtSendSuccMsgEnable;
	}

	public String getCmdEnableFlag() {
		return cmdEnableFlag;
	}

	public void setCmdEnableFlag(String cmdEnableFlag) {
		this.cmdEnableFlag = cmdEnableFlag;
	}

	public String getCmdCotent() {
		return cmdCotent;
	}

	public void setCmdCotent(String cmdCotent) {
		this.cmdCotent = cmdCotent;
	}

	public String getBusiMarketSms() {
		return busiMarketSms;
	}

	public void setBusiMarketSms(String busiMarketSms) {
		this.busiMarketSms = busiMarketSms;
	}

	public String getDtConfigureMoney() {
		return dtConfigureMoney;
	}

	public void setDtConfigureMoney(String dtConfigureMoney) {
		this.dtConfigureMoney = dtConfigureMoney;
	}

	public String getDtIsRecommend() {
		return dtIsRecommend;
	}

	public void setDtIsRecommend(String dtIsRecommend) {
		this.dtIsRecommend = dtIsRecommend;
	}

	public String getDtMutexEnable() {
		return dtMutexEnable;
	}

	public void setDtMutexEnable(String dtMutexEnable) {
		this.dtMutexEnable = dtMutexEnable;
	}

	public String getDtMutexBusi() {
		return dtMutexBusi;
	}

	public void setDtMutexBusi(String dtMutexBusi) {
		this.dtMutexBusi = dtMutexBusi;
	}

	public String getDtUnallowBusi() {
		return dtUnallowBusi;
	}

	public void setDtUnallowBusi(String dtUnallowBusi) {
		this.dtUnallowBusi = dtUnallowBusi;
	}

	public String getDtBeforeEffectMsg() {
		return dtBeforeEffectMsg;
	}

	public void setDtBeforeEffectMsg(String dtBeforeEffectMsg) {
		this.dtBeforeEffectMsg = dtBeforeEffectMsg;
	}

	public String getDtExpireMsg() {
		return dtExpireMsg;
	}

	public void setDtExpireMsg(String dtExpireMsg) {
		this.dtExpireMsg = dtExpireMsg;
	}

	public String getFnestificationType() {
		return fnestificationType;
	}

	public void setFnestificationType(String fnestificationType) {
		this.fnestificationType = fnestificationType;
	}

	

	public String getMarketSecondPkid() {
		return marketSecondPkid;
	}

	public void setMarketSecondPkid(String marketSecondPkid) {
		this.marketSecondPkid = marketSecondPkid;
	}

	public String getMarketFirstPkid() {
		return marketFirstPkid;
	}

	public void setMarketFirstPkid(String marketFirstPkid) {
		this.marketFirstPkid = marketFirstPkid;
	}

	public String getMarketSecondCode() {
		return marketSecondCode;
	}

	public void setMarketSecondCode(String marketSecondCode) {
		this.marketSecondCode = marketSecondCode;
	}

	public String getMarketSecondName() {
		return marketSecondName;
	}

	public void setMarketSecondName(String marketSecondName) {
		this.marketSecondName = marketSecondName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getImgDir() {
		return imgDir;
	}

	public void setImgDir(String imgDir) {
		this.imgDir = imgDir;
	}

	public String getFlashDir() {
		return flashDir;
	}

	public void setFlashDir(String flashDir) {
		this.flashDir = flashDir;
	}

	public String getToObject() {
		return toObject;
	}

	public void setToObject(String toObject) {
		this.toObject = toObject;
	}

	public String getActivityComment() {
		return activityComment;
	}

	public void setActivityComment(String activityComment) {
		this.activityComment = activityComment;
	}

	public String getIsNeedMoney() {
		return isNeedMoney;
	}

	public void setIsNeedMoney(String isNeedMoney) {
		this.isNeedMoney = isNeedMoney;
	}

	public BigDecimal getMoneyVal() {
		return moneyVal;
	}

	public void setMoneyVal(BigDecimal moneyVal) {
		this.moneyVal = moneyVal;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIsFlashView() {
		return isFlashView;
	}

	public void setIsFlashView(String isFlashView) {
		this.isFlashView = isFlashView;
	}

	public String getIsListView() {
		return isListView;
	}

	public void setIsListView(String isListView) {
		this.isListView = isListView;
	}

	public String getViewTemplateId() {
		return viewTemplateId;
	}

	public void setViewTemplateId(String viewTemplateId) {
		this.viewTemplateId = viewTemplateId;
	}

	public String getPactTemplateId() {
		return pactTemplateId;
	}

	public void setPactTemplateId(String pactTemplateId) {
		this.pactTemplateId = pactTemplateId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(String verifyState) {
		this.verifyState = verifyState;
	}

	public String getPactTemplateContent() {
		return pactTemplateContent;
	}

	public void setPactTemplateContent(String pactTemplateContent) {
		this.pactTemplateContent = pactTemplateContent;
	}

	public String getGiftGetTypes() {
		return giftGetTypes;
	}

	public void setGiftGetTypes(String giftGetTypes) {
		this.giftGetTypes = giftGetTypes;
	}

	public String getCfgUserId() {
		return cfgUserId;
	}

	public void setCfgUserId(String cfgUserId) {
		this.cfgUserId = cfgUserId;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getProVerifyState() {
		return proVerifyState;
	}

	public void setProVerifyState(String proVerifyState) {
		this.proVerifyState = proVerifyState;
	}

	public String getLocalVerifyState() {
		return localVerifyState;
	}

	public void setLocalVerifyState(String localVerifyState) {
		this.localVerifyState = localVerifyState;
	}

	public String getMarketSecondType() {
		return marketSecondType;
	}

	public void setMarketSecondType(String marketSecondType) {
		this.marketSecondType = marketSecondType;
	}

	public String getIsInGroup() {
		return isInGroup;
	}

	public void setIsInGroup(String isInGroup) {
		this.isInGroup = isInGroup;
	}

	public String getBusinessNum() {
		return businessNum;
	}

	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}

	public String getLowestConsumption() {
		return lowestConsumption;
	}

	public void setLowestConsumption(String lowestConsumption) {
		this.lowestConsumption = lowestConsumption;
	}

	public String getFirstAccount() {
		return firstAccount;
	}

	public void setFirstAccount(String firstAccount) {
		this.firstAccount = firstAccount;
	}

	public String getMonthlyRelease() {
		return monthlyRelease;
	}

	public void setMonthlyRelease(String monthlyRelease) {
		this.monthlyRelease = monthlyRelease;
	}

	public String getAgreementTime() {
		return agreementTime;
	}

	public void setAgreementTime(String agreementTime) {
		this.agreementTime = agreementTime;
	}

	public String getFirstTop() {
		return firstTop;
	}

	public void setFirstTop(String firstTop) {
		this.firstTop = firstTop;
	}

	public String getTicketFlag() {
		return ticketFlag;
	}

	public void setTicketFlag(String ticketFlag) {
		this.ticketFlag = ticketFlag;
	}

	public String getChannalData() {
		return channalData;
	}

	public void setChannalData(String channalData) {
		this.channalData = channalData;
	}
	
	public String getModify() {
		return modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public MarketFirstBean getMarketFirstBean() {
		return marketFirstBean;
	}

	public void setMarketFirstBean(MarketFirstBean marketFirstBean) {
		this.marketFirstBean = marketFirstBean;
	}

	public MarketAuditBean getMarketAuditBean() {
		return marketAuditBean;
	}

	public void setMarketAuditBean(MarketAuditBean marketAuditBean) {
		this.marketAuditBean = marketAuditBean;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getIsListViewLink() {
		return isListViewLink;
	}

	public void setIsListViewLink(String isListViewLink) {
		this.isListViewLink = isListViewLink;
	}
}
