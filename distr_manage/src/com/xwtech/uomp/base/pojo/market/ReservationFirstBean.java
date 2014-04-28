package com.xwtech.uomp.base.pojo.market;

public class ReservationFirstBean {
	/*
	 * 一级营销方案主键标识
	 */
    private String marketFirstPkid;
    /*
     * 一级预约boss编码
     */
    private String marketFirstCode;
    /*
     * 一级预约boss编码名称
     */
    private String marketFirstName;
    /*
     * 营销案展示名称
     */
    private String viewName;
    /*
     * 网厅小图片路径
     */
    private String imgDir;
    /*
     * flash路径
     */
    private String flashDir;
    /*
     * 活动对象
     */
    private String toObject;
    /*
     * 活动说明
     */
    private String activityComment;
    /*
     * 开始时间
     */
    private String beginTime;
    /*
     * 结束时间
     */
    private String endTime;
    /*
     * 是否flash展示
     */
    private String isFlashView;
    /*
     * 是否在营销案方案办理列表
     */
    private String isListView;
    /*
     * 展示模板标识
     */
    private String viewTemplateId;
    /*
     * 方案状态：0无效，1有效
     */
    private String state;
	/*
	 * 创建时间
	 */
    private String createTime;
    /*
     * 城市
     */
    private String city;
    /*
     * 审核状态：0待审核，1.审核通过，2审核不通过
     */
    private String verifyState;
    /*
     * 配置工号
     */
    private String cfgUserId;
    /*
     * 一级方案活动类型 0普通优惠活动，1家庭宽带，2家庭电话
     */
    private String marketFirstType;
    /*
     * 渠道:0无权限，1有权限，第1位代表网厅，第2位代表网村组服务站
     */
    private String tChannal;
    /*
     * 营销案类型： 0一般预约营销案，1校验预约营销案
     */
    private String type;
    /*
     * 营销案展示顺序
     */
    private String marketOrder;
    /*
     * 网厅大图片路径
     */
    private String bigImgDir;
    /*
     * 0无目标客户，1，有目标客户
     */
    private String isInGroup;
    /*
     * 1，全球通，2，动感地带3，神州行
     */
    private String isInBrand;
    /*
     * 2,无所谓，1，预付费，0，后付费
     */
    private String paymode;
    /*
     * 营销案图片
     */
    private String yxImgDir;
    /*
     * 奖品内容
     */
    private String prize;
    /*
     * boss一级编码
     */
    private String bossFirstCode;
    /*
     * 预约营销案温馨提示，一级预约营销案下的温馨提示相同
     */
    private String tips;
    /*
     * 4网厅数据 ，5掌厅数据，6短厅数据
     */
    private String channalData;
    /*
     * 列表展示操作按钮
     */
    private String linkOper;
    
    /**
     * 关联二级预约营销案
     */
    private String relateSecondList;
    
    private String proVerifyState;
    private String localVerifyState;
    private String testOnlineState;
    private String areaName;
    private String proOper;
    private String localOper;
    private String testOper;
    private MarketAuditBean marketAuditBean;
    

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
     * 网厅列表展示状态
     */
	private String wtIsListView;
	
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
	 * @return the relateSecondList
	 */
	public String getRelateSecondList() {
		return relateSecondList;
	}

	/**
	 * @param relateSecondList the relateSecondList to set
	 */
	public void setRelateSecondList(String relateSecondList) {
		this.relateSecondList = relateSecondList;
	}

	public String getMarketFirstPkid() {
		return marketFirstPkid;
	}

	public void setMarketFirstPkid(String marketFirstPkid) {
		this.marketFirstPkid = marketFirstPkid;
	}

	public String getMarketFirstCode() {
		return marketFirstCode;
	}

	public void setMarketFirstCode(String marketFirstCode) {
		this.marketFirstCode = marketFirstCode;
	}

	public String getMarketFirstName() {
		return marketFirstName;
	}

	public void setMarketFirstName(String marketFirstName) {
		this.marketFirstName = marketFirstName;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(String verifyState) {
		this.verifyState = verifyState;
	}

	public String getCfgUserId() {
		return cfgUserId;
	}

	public void setCfgUserId(String cfgUserId) {
		this.cfgUserId = cfgUserId;
	}

	public String getMarketFirstType() {
		return marketFirstType;
	}

	public void setMarketFirstType(String marketFirstType) {
		this.marketFirstType = marketFirstType;
	}

	public String getTChannal() {
		return tChannal;
	}

	public void setTChannal(String tChannal) {
		this.tChannal = tChannal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMarketOrder() {
		return marketOrder;
	}

	public void setMarketOrder(String marketOrder) {
		this.marketOrder = marketOrder;
	}

	public String getBigImgDir() {
		return bigImgDir;
	}

	public void setBigImgDir(String bigImgDir) {
		this.bigImgDir = bigImgDir;
	}

	public String getIsInGroup() {
		return isInGroup;
	}

	public void setIsInGroup(String isInGroup) {
		this.isInGroup = isInGroup;
	}

	public String getIsInBrand() {
		return isInBrand;
	}

	public void setIsInBrand(String isInBrand) {
		this.isInBrand = isInBrand;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public String getYxImgDir() {
		return yxImgDir;
	}

	public void setYxImgDir(String yxImgDir) {
		this.yxImgDir = yxImgDir;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}

	public String getBossFirstCode() {
		return bossFirstCode;
	}

	public void setBossFirstCode(String bossFirstCode) {
		this.bossFirstCode = bossFirstCode;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String gettChannal() {
		return tChannal;
	}

	public void settChannal(String tChannal) {
		this.tChannal = tChannal;
	}

	public String getChannalData() {
		return channalData;
	}

	public void setChannalData(String channalData) {
		this.channalData = channalData;
	}

	public String getLinkOper() {
		return linkOper;
	}

	public void setLinkOper(String linkOper) {
		this.linkOper = linkOper;
	}



}