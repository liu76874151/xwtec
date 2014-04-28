package com.xwtech.uomp.base.pojo.adv;

/**
 * 
 * @author Administrator
 * 
 */
public class AdvPositionBean {

	/**
	 * 广告信息编码
	 */
	private String positionNum;
	/**
	 * 渠道编码
	 */
	private String channelNum;

	

	/**
	 * 广告位名称
	 */
	private String positionName;

	/**
	 * 广告位类型（1：多图 ；2：单图；3：文字）
	 */
	private String positionType;

	/**
	 * 广告位示意图
	 */
	private String positionImage;

	/**
	 * 广告位简介
	 */
	private String positionDesc;

	/**
	 * 状态(0：可用；1：删除)
	 */
	private String state;
	/**
	 * 改变状态操作
	 */
	private String stateLink;

	public String getPositionNum() {
		return positionNum;
	}

	public void setPositionNum(String positionNum) {
		this.positionNum = positionNum;
	}






	public String getChannelNum() {
		return channelNum;
	}

	public void setChannelNum(String channelNum) {
		this.channelNum = channelNum;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPositionType() {
		return positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	public String getPositionImage() {
		return positionImage;
	}

	public void setPositionImage(String positionImage) {
		this.positionImage = positionImage;
	}

	public String getPositionDesc() {
		return positionDesc;
	}

	public void setPositionDesc(String positionDesc) {
		this.positionDesc = positionDesc;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateLink() {
		return stateLink;
	}

	public void setStateLink(String stateLink) {
		this.stateLink = stateLink;
	}

}
