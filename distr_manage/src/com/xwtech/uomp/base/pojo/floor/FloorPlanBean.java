/**
 * Title: floorPlan.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-21 
 * @ time 下午4:32:28
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.pojo.floor;

import java.sql.Timestamp;
import java.util.Map;

/**
 * @author zhanglu
 * 
 */
public class FloorPlanBean {

	/**
	 * 方案ID
	 */
	String planId;
	/**
	 * 渠道编号
	 */
	String channelNum;
	/**
	 * 方案编号
	 */
	String planNum;
	/**
	 * 创建人
	 */
	String creater;
	/**
	 * 创建时间
	 */
	Timestamp createTime;
	/**
	 * 状态(0：可用 1：不可用)
	 */
	String state;
	/**
	 * ********楼层信息
	 */
	String floorId;
	String floorNum;
	String floorName;
	String floorSeq;
	String floorImage;
	String floorUrl;
	String floorTempNum;
	String floorBz;
	String channelName;
	String tempName;
	Map<String, Object> arrayMap;
	
	/**
	 * @return the arrayMap
	 */
	public Map<String, Object> getArrayMap() {
		return arrayMap;
	}

	/**
	 * @param arrayMap the arrayMap to set
	 */
	public void setArrayMap(Map<String, Object> arrayMap) {
		this.arrayMap = arrayMap;
	}

	/**
	 * @return the tempName
	 */
	public String getTempName() {
		return tempName;
	}

	/**
	 * @param tempName the tempName to set
	 */
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	/**
	 * @return the channelName
	 */
	public String getChannelName() {
		return channelName;
	}

	/**
	 * @param channelName
	 *            the channelName to set
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/**
	 * @return the floorId
	 */
	public String getFloorId() {
		return floorId;
	}

	/**
	 * @param floorId
	 *            the floorId to set
	 */
	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	/**
	 * @return the floorNum
	 */
	public String getFloorNum() {
		return floorNum;
	}

	/**
	 * @param floorNum
	 *            the floorNum to set
	 */
	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}

	/**
	 * @return the floorName
	 */
	public String getFloorName() {
		return floorName;
	}

	/**
	 * @param floorName
	 *            the floorName to set
	 */
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	/**
	 * @return the floorSeq
	 */
	public String getFloorSeq() {
		return floorSeq;
	}

	/**
	 * @param floorSeq
	 *            the floorSeq to set
	 */
	public void setFloorSeq(String floorSeq) {
		this.floorSeq = floorSeq;
	}

	/**
	 * @return the floorImage
	 */
	public String getFloorImage() {
		return floorImage;
	}

	/**
	 * @param floorImage
	 *            the floorImage to set
	 */
	public void setFloorImage(String floorImage) {
		this.floorImage = floorImage;
	}

	/**
	 * @return the floorUrl
	 */
	public String getFloorUrl() {
		return floorUrl;
	}

	/**
	 * @param floorUrl
	 *            the floorUrl to set
	 */
	public void setFloorUrl(String floorUrl) {
		this.floorUrl = floorUrl;
	}

	/**
	 * @return the floorTempNum
	 */
	public String getFloorTempNum() {
		return floorTempNum;
	}

	/**
	 * @param floorTempNum
	 *            the floorTempNum to set
	 */
	public void setFloorTempNum(String floorTempNum) {
		this.floorTempNum = floorTempNum;
	}

	/**
	 * @return the floorBz
	 */
	public String getFloorBz() {
		return floorBz;
	}

	/**
	 * @param floorBz
	 *            the floorBz to set
	 */
	public void setFloorBz(String floorBz) {
		this.floorBz = floorBz;
	}

	/**
	 * @return the planId
	 */
	public String getPlanId() {
		return planId;
	}

	/**
	 * @param planId
	 *            the planId to set
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
	}

	/**
	 * @return the channelNum
	 */
	public String getChannelNum() {
		return channelNum;
	}

	/**
	 * @param channelNum
	 *            the channelNum to set
	 */
	public void setChannelNum(String channelNum) {
		this.channelNum = channelNum;
	}

	/**
	 * @return the planNum
	 */
	public String getPlanNum() {
		return planNum;
	}

	/**
	 * @param planNum
	 *            the planNum to set
	 */
	public void setPlanNum(String planNum) {
		this.planNum = planNum;
	}

	/**
	 * @return the creater
	 */
	public String getCreater() {
		return creater;
	}

	/**
	 * @param creater
	 *            the creater to set
	 */
	public void setCreater(String creater) {
		this.creater = creater;
	}

	/**
	 * @return the createTime
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

}
