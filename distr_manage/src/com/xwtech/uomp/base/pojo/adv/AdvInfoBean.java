package com.xwtech.uomp.base.pojo.adv;

import java.util.List;

public class AdvInfoBean {
	/*
	 * 广告信息编码
	 */
	private String advNum;
	/*
	 * 渠道编码
	 */
	private String channelNum;
	/*
	 * 广告位编码
	 */
	private String positionNum;

	/*
	 * 广告信息名称
	 */
	private String advName;
	/*
	 * 广告信息简介
	 */
	private String advDesc;
	/*
	 * 广告菜单图标
	 */
	private String advMenuIcon;
	/*
	 * 广告图片
	 */
	private String advImg;
	/*
	 * 广告链接地址
	 */
	private String advUri;
	/*
	 * 链接地址的打开方式（0-在本页面打开；1-在新窗口打开）
	 */
	private String openUriType;
	/*
	 * 广告图片类型（0-图片；1-FLASH)
	 */
	private String advImgType;
	/*
	 * 显示顺序
	 */
	private String showXh;
	/*
	 * 备注
	 */
	private String bz;
	/*
	 * 在用状态（1：默认，在用；0：停用）
	 */
	private String useState;
	private String useStateLink;
	/*
	 * 广告小图片
	 */
	private String advImgS;
	/*
	 * 广告有效期开始时间
	 */
	private String startTime;
	/*
	 * 广告有效期结束时间
	 */
	private String endTime;
	/*
	 * 审核人
	 */
	private String auditer;
	/*
	 * 审核时间
	 */
	private String auditTime;
	/*
	 * 审核通过状态（0：默认，未审核；1：审核通过；2：审核不通过）
	 */
	private String auditState;
	/*
	 * 审核链接
	 */
	private String auditStateLink; 
	/*
	 * 广告位
	 */
	private AdvPositionBean advPositionBean;
	/*
	 * 广告位名称
	 */
	private String positionName;
	
	/*
	 * 广告地区
	 */
	private List<AdvAreaDzBean> advAreaDzList;
	/*
	 * 广告名称
	 */
	private String advAreaNum;
	private String advAreaName;
	/*
	 * 品牌
	 */
	private List<AdvBrandDzBean> advBrandDzList;
	/*
	 * 品牌名称
	 */
	private String advBrandNum;
	private String advBrandName; 
	
	
	
	
	public String getAdvNum() {
		return advNum;
	}

	public void setAdvNum(String advNum) {
		this.advNum = advNum;
	}

	

	public String getChannelNum() {
		return channelNum;
	}

	public void setChannelNum(String channelNum) {
		this.channelNum = channelNum;
	}

	public String getPositionNum() {
		return positionNum;
	}

	public void setPositionNum(String positionNum) {
		this.positionNum = positionNum;
	}


	public String getAdvName() {
		return advName;
	}

	public void setAdvName(String advName) {
		this.advName = advName;
	}

	public String getAdvDesc() {
		return advDesc;
	}

	public void setAdvDesc(String advDesc) {
		this.advDesc = advDesc;
	}

	public String getAdvMenuIcon() {
		return advMenuIcon;
	}

	public void setAdvMenuIcon(String advMenuIcon) {
		this.advMenuIcon = advMenuIcon;
	}

	public String getAdvImg() {
		return advImg;
	}

	public void setAdvImg(String advImg) {
		this.advImg = advImg;
	}

	public String getAdvUri() {
		return advUri;
	}

	public void setAdvUri(String advUri) {
		this.advUri = advUri;
	}

	public String getOpenUriType() {
		return openUriType;
	}

	public void setOpenUriType(String openUriType) {
		this.openUriType = openUriType;
	}

	public String getAdvImgType() {
		return advImgType;
	}

	public void setAdvImgType(String advImgType) {
		this.advImgType = advImgType;
	}

	public String getShowXh() {
		return showXh;
	}

	public void setShowXh(String showXh) {
		this.showXh = showXh;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getUseState() {
		return useState;
	}

	public void setUseState(String useState) {
		this.useState = useState;
	}

	public String getAdvImgS() {
		return advImgS;
	}

	public void setAdvImgS(String advImgS) {
		this.advImgS = advImgS;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAuditer() {
		return auditer;
	}

	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public AdvPositionBean getAdvPositionBean() {
		return advPositionBean;
	}

	public void setAdvPositionBean(AdvPositionBean advPositionBean) {
		this.advPositionBean = advPositionBean;
	}

	public List<AdvAreaDzBean> getAdvAreaDzList() {
		return advAreaDzList;
	}

	public void setAdvAreaDzList(List<AdvAreaDzBean> advAreaDzList) {
		this.advAreaDzList = advAreaDzList;
	}

	public List<AdvBrandDzBean> getAdvBrandDzList() {
		return advBrandDzList;
	}

	public void setAdvBrandDzList(List<AdvBrandDzBean> advBrandDzList) {
		this.advBrandDzList = advBrandDzList;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getAdvAreaNum() {
		return advAreaNum;
	}

	public void setAdvAreaNum(String advAreaNum) {
		this.advAreaNum = advAreaNum;
	}

	public String getAdvBrandNum() {
		return advBrandNum;
	}

	public void setAdvBrandNum(String advBrandNum) {
		this.advBrandNum = advBrandNum;
	}

	public String getAdvAreaName() {
		return advAreaName;
	}

	public void setAdvAreaName(String advAreaName) {
		this.advAreaName = advAreaName;
	}

	public String getAdvBrandName() {
		return advBrandName;
	}

	public void setAdvBrandName(String advBrandName) {
		this.advBrandName = advBrandName;
	}

	public String getUseStateLink() {
		return useStateLink;
	}

	public void setUseStateLink(String useStateLink) {
		this.useStateLink = useStateLink;
	}

	public String getAuditStateLink() {
		return auditStateLink;
	}

	public void setAuditStateLink(String auditStateLink) {
		this.auditStateLink = auditStateLink;
	}



	

	
}
