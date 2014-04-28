/**
 * Title: FloorBean.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-5 
 * @ time 下午3:42:38
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.pojo.floor;

import java.util.List;

/**
 * @author zhanglu
 *
 */
public class FloorBean {
	/**
	 * 楼层ID
	 */
	String floorId;
	/**
	 * 渠道编码
	 */
	String channelNum;
	/**
	 * 楼层编码(规则：两位，例如：01)
	 */
	String floorNum;
	/**
	 * 楼层名称
	 */
	String floorName;
	/**
	 * 楼层序号
	 */
	String floorSeq;
	/**
	 * 楼层图片
	 */
	String floorImage;
	/**
	 * 楼层链接
	 */
	String floorUrl;
	/**
	 * 楼层货架模板
	 */
	String tempNum;
	/**
	 * 备注
	 */
	String floorBz;
	/**
	 * 状态（0：展示 1：不展示）
	 */
	String state;
	/**
	 * 渠道名称
	 */
	String channelName;
	/**
	 * 展示操作按钮
	 */
	String showOper;
	/**
	 * 关联的区块
	 */
	List<FloorBlockDaBean> floorBlockDaList;
	/**
	 * 关联的区块内容
	 */
	List<FloorBlockContentBean> floorBlockContentList;
	/**
	 * @return the floorBlockContentList
	 */
	public List<FloorBlockContentBean> getFloorBlockContentList() {
		return floorBlockContentList;
	}
	/**
	 * @param floorBlockContentList the floorBlockContentList to set
	 */
	public void setFloorBlockContentList(
			List<FloorBlockContentBean> floorBlockContentList) {
		this.floorBlockContentList = floorBlockContentList;
	}
	/**
	 * @return the floorBlockDaList
	 */
	public List<FloorBlockDaBean> getFloorBlockDaList() {
		return floorBlockDaList;
	}
	/**
	 * @param floorBlockDaList the floorBlockDaList to set
	 */
	public void setFloorBlockDaList(List<FloorBlockDaBean> floorBlockDaList) {
		this.floorBlockDaList = floorBlockDaList;
	}
	/**
	 * @return the showOper
	 */
	public String getShowOper() {
		return showOper;
	}
	/**
	 * @param showOper the showOper to set
	 */
	public void setShowOper(String showOper) {
		this.showOper = showOper;
	}
	/**
	 * @return the channelName
	 */
	public String getChannelName() {
		return channelName;
	}
	/**
	 * @param channelName the channelName to set
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
	 * @param floorId the floorId to set
	 */
	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}
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
	 * @return the floorNum
	 */
	public String getFloorNum() {
		return floorNum;
	}
	/**
	 * @param floorNum the floorNum to set
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
	 * @param floorName the floorName to set
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
	 * @param floorSeq the floorSeq to set
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
	 * @param floorImage the floorImage to set
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
	 * @param floorUrl the floorUrl to set
	 */
	public void setFloorUrl(String floorUrl) {
		this.floorUrl = floorUrl;
	}
	/**
	 * @return the tempNum
	 */
	public String getTempNum() {
		return tempNum;
	}
	/**
	 * @param tempNum the tempNum to set
	 */
	public void setTempNum(String tempNum) {
		this.tempNum = tempNum;
	}
	/**
	 * @return the floorBz
	 */
	public String getFloorBz() {
		return floorBz;
	}
	/**
	 * @param floorBz the floorBz to set
	 */
	public void setFloorBz(String floorBz) {
		this.floorBz = floorBz;
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
	

}
