/**
 * Title: FloorTemplateBean.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-6 
 * @ time 下午2:28:18
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.pojo.floor;

/**
 * @author zhanglu
 *
 */
public class FloorTemplateBean {

	/**
	 * 模板编号
	 */
	String tempNum;
	/**
	 * 渠道编号
	 */
	String channelNum;
	/**
	 * 模板名称
	 */
	String tempName;
	/**
	 * 示意图路径
	 */
	String tempImg;
	/**
	 * 区块编码信息（0101,0102,0103）
	 */
	String blockNums;
	/**
	 * 状态(0：可用 1：不可用)
	 */
	String state;
	/**
	 * 组件
	 */
	String floorComp;
	/**
	 * 模版启用按钮
	 */
	String userableOper;
	/**
	 * 渠道名称
	 */
	String channelName;
	
	
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
	 * @return the userableOper
	 */
	public String getUserableOper() {
		return userableOper;
	}
	/**
	 * @param userableOper the userableOper to set
	 */
	public void setUserableOper(String userableOper) {
		this.userableOper = userableOper;
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
	 * @return the tempImg
	 */
	public String getTempImg() {
		return tempImg;
	}
	/**
	 * @param tempImg the tempImg to set
	 */
	public void setTempImg(String tempImg) {
		this.tempImg = tempImg;
	}
	/**
	 * @return the blockNums
	 */
	public String getBlockNums() {
		return blockNums;
	}
	/**
	 * @param blockNums the blockNums to set
	 */
	public void setBlockNums(String blockNums) {
		this.blockNums = blockNums;
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
	 * @return the floorComp
	 */
	public String getFloorComp() {
		return floorComp;
	}
	/**
	 * @param floorComp the floorComp to set
	 */
	public void setFloorComp(String floorComp) {
		this.floorComp = floorComp;
	}
	
}
