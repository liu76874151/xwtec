package com.xwtech.uomp.base.pojo.boss;

import java.io.Serializable;

/**
 * boss 禮品包
 * @author zhangel
 *
 */
public class BossGiftInfoBean implements Serializable {
	
	private static final long serialVersionUID = -8397339464276896388L;

	private String marketSecondCode;

	private String giftName;

	private String giftComment;

	private String tchannal;
	private String bossGiftId;

	private Short cityId;
	
	private String bossParentGiftId;
	
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

	public String getBossParentGiftId() {
		return bossParentGiftId;
	}

	public void setBossParentGiftId(String bossParentGiftId) {
		this.bossParentGiftId = bossParentGiftId;
	}

	public String getBossGiftId() {
		return bossGiftId;
	}

	public String getTchannal() {
		return tchannal;
	}

	public void setTchannal(String tchannal) {
		this.tchannal = tchannal;
	}

	public void setBossGiftId(String bossGiftId) {
		this.bossGiftId = bossGiftId == null ? null : bossGiftId.trim();
	}

	public Short getCityId() {
		return cityId;
	}

	public void setCityId(Short cityId) {
		this.cityId = cityId;
	}

	public String getMarketSecondCode() {
		return marketSecondCode;
	}

	public void setMarketSecondCode(String marketSecondCode) {
		this.marketSecondCode = marketSecondCode == null ? null
				: marketSecondCode.trim();
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName == null ? null : giftName.trim();
	}

	public String getGiftComment() {
		return giftComment;
	}

	public void setGiftComment(String giftComment) {
		this.giftComment = giftComment == null ? null : giftComment.trim();
	}

}