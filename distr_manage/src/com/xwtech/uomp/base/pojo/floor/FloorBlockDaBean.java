/**
 * Title: FloorBlockDaBean.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-13 
 * @ time 下午4:48:08
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.pojo.floor;

import java.util.List;

/**
 * @author zhanglu
 *
 */
public class FloorBlockDaBean {

	/**
	 * 区块ID
	 */
	String blockId;
	/**
	 * 楼层ID
	 */
	String floorId;
	/**
	 * 区块编码
	 */
	String blockNum;
	/**
	 * 区块名称
	 */
	String blockName;
	/**
	 * 区块链接
	 */
	String blockUrl;
	/**
	 * 区块描述
	 */
	String blockDesc;
	/**
	 * 登录前数据源
	 */
	String beforeDs;
	/**
	 * 登录后数据源
	 */
	String afterDs;
	/**
	 * 采集状态（0：关闭 1：开启）
	 */
	String collectState;
	/**
	 * 关联内容
	 */
	List<FloorBlockContentBean> floorBlockContentList;
	
	public List<FloorBlockContentBean> getFloorBlockContentList() {
		return floorBlockContentList;
	}
	public void setFloorBlockContentList(
			List<FloorBlockContentBean> floorBlockContentList) {
		this.floorBlockContentList = floorBlockContentList;
	}
	/**
	 * @return the blockId
	 */
	public String getBlockId() {
		return blockId;
	}
	/**
	 * @param blockId the blockId to set
	 */
	public void setBlockId(String blockId) {
		this.blockId = blockId;
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
	 * @return the blockNum
	 */
	public String getBlockNum() {
		return blockNum;
	}
	/**
	 * @param blockNum the blockNum to set
	 */
	public void setBlockNum(String blockNum) {
		this.blockNum = blockNum;
	}
	/**
	 * @return the blockName
	 */
	public String getBlockName() {
		return blockName;
	}
	/**
	 * @param blockName the blockName to set
	 */
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	/**
	 * @return the blockUrl
	 */
	public String getBlockUrl() {
		return blockUrl;
	}
	/**
	 * @param blockUrl the blockUrl to set
	 */
	public void setBlockUrl(String blockUrl) {
		this.blockUrl = blockUrl;
	}
	/**
	 * @return the blockDesc
	 */
	public String getBlockDesc() {
		return blockDesc;
	}
	/**
	 * @param blockDesc the blockDesc to set
	 */
	public void setBlockDesc(String blockDesc) {
		this.blockDesc = blockDesc;
	}
	/**
	 * @return the beforeDs
	 */
	public String getBeforeDs() {
		return beforeDs;
	}
	/**
	 * @param beforeDs the beforeDs to set
	 */
	public void setBeforeDs(String beforeDs) {
		this.beforeDs = beforeDs;
	}
	/**
	 * @return the afterDs
	 */
	public String getAfterDs() {
		return afterDs;
	}
	/**
	 * @param afterDs the afterDs to set
	 */
	public void setAfterDs(String afterDs) {
		this.afterDs = afterDs;
	}
	/**
	 * @return the collectState
	 */
	public String getCollectState() {
		return collectState;
	}
	/**
	 * @param collectState the collectState to set
	 */
	public void setCollectState(String collectState) {
		this.collectState = collectState;
	}
	
}
