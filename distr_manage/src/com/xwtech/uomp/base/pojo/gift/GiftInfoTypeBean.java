package com.xwtech.uomp.base.pojo.gift;

import java.io.Serializable;

/**
 * 礼品类型 This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-10-22 下午02:24:04
 */
public class GiftInfoTypeBean implements Serializable {

	private static final long serialVersionUID = 8621958356943000587L;

	private String applyCode;

	private String startTime;

	private String endTime;

	private String state;

	private String productName;

	private String giftSendType;

	private int giftType;
	
	private String giftTypeDesc;

	/**
	 * @return the giftTypeDesc
	 */
	public String getGiftTypeDesc() {
		return giftTypeDesc;
	}

	/**
	 * @param giftTypeDesc the giftTypeDesc to set
	 */
	public void setGiftTypeDesc(String giftTypeDesc) {
		this.giftTypeDesc = giftTypeDesc;
	}

	public int getGiftType() {
		return giftType;
	}

	public void setGiftType(int giftType) {
		this.giftType = giftType;
	}

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode == null ? null : applyCode.trim();
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName == null ? null : productName.trim();
	}

	public String getGiftSendType() {
		return giftSendType;
	}

	public void setGiftSendType(String giftSendType) {
		this.giftSendType = giftSendType == null ? null : giftSendType.trim();
	}
}