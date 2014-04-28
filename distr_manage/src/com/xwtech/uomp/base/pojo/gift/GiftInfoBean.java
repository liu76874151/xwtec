package com.xwtech.uomp.base.pojo.gift;

import java.io.Serializable;

/**
 * 礼品信息 This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-10-21 下午02:16:00
 */
public class GiftInfoBean implements Serializable {

    private static final long serialVersionUID = 6334218013683857969L;

    private String giftId;

    private String marketSecondPkid;

    private String giftName;

    private Long giftPrice;

    private String imgDir;

    private String giftComment;

    private String state;

    private String giftGetTypes;

    private String bossGiftId;

    private Integer giftNum;

    private String giftSendType;

    private String giftCountState;
    private String channel;

    private int giftType;
    
    private String parentGiftCode;
    
    private String parentGiftName;

    /**
	 * @return the parentGiftCode
	 */
	public String getParentGiftCode() {
		return parentGiftCode;
	}

	/**
	 * @param parentGiftCode the parentGiftCode to set
	 */
	public void setParentGiftCode(String parentGiftCode) {
		this.parentGiftCode = parentGiftCode;
	}

	/**
	 * @return the parentGiftName
	 */
	public String getParentGiftName() {
		return parentGiftName;
	}

	/**
	 * @param parentGiftName the parentGiftName to set
	 */
	public void setParentGiftName(String parentGiftName) {
		this.parentGiftName = parentGiftName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getGiftType() {
	return giftType;
    }

    public void setGiftType(int giftType) {
	this.giftType = giftType;
    }

    public String getGiftId() {
	return giftId;
    }

    public void setGiftId(String giftId) {
	this.giftId = giftId;
    }

    public String getMarketSecondPkid() {
	return marketSecondPkid;
    }

    public void setMarketSecondPkid(String marketSecondPkid) {
	this.marketSecondPkid = marketSecondPkid;
    }

    public String getGiftName() {
	return giftName;
    }

    public void setGiftName(String giftName) {
	this.giftName = giftName == null ? null : giftName.trim();
    }

    public Long getGiftPrice() {
	return giftPrice;
    }

    public void setGiftPrice(Long giftPrice) {
	this.giftPrice = giftPrice;
    }

    public String getImgDir() {
	return imgDir;
    }

    public void setImgDir(String imgDir) {
	this.imgDir = imgDir == null ? null : imgDir.trim();
    }

    public String getGiftComment() {
	return giftComment;
    }

    public void setGiftComment(String giftComment) {
	this.giftComment = giftComment == null ? null : giftComment.trim();
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state == null ? null : state.trim();
    }

    public String getGiftGetTypes() {
	return giftGetTypes;
    }

    public void setGiftGetTypes(String giftGetTypes) {
	this.giftGetTypes = giftGetTypes == null ? null : giftGetTypes.trim();
    }

    public String getBossGiftId() {
	return bossGiftId;
    }

    public void setBossGiftId(String bossGiftId) {
	this.bossGiftId = bossGiftId == null ? null : bossGiftId.trim();
    }

    public Integer getGiftNum() {
	return giftNum;
    }

    public void setGiftNum(Integer giftNum) {
	this.giftNum = giftNum;
    }

    public String getGiftSendType() {
	return giftSendType;
    }

    public void setGiftSendType(String giftSendType) {
	this.giftSendType = giftSendType == null ? null : giftSendType.trim();
    }

    public String getGiftCountState() {
	return giftCountState;
    }

    public void setGiftCountState(String giftCountState) {
	this.giftCountState = giftCountState == null ? null : giftCountState.trim();
    }
}