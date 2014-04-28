package com.xwtech.uomp.base.pojo.org;

import java.io.Serializable;

/**
 * 目标组织
 * This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-10-16 下午03:34:04
 */
public class TargetGroupInfoBean implements Serializable {

	private static final long serialVersionUID = -6630891611601940236L;

	private Long groupId;

	private String groupName;

	private String description;

	private String city;

	private String state;

	private String groupType;
	
	private String dataSource;
	
	private String importCount;
	
	private String areaName;
	
	private String beginTime;
	
	private String endTime;
	
	private String importOper;
	
	private String phoneNumber;
	

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the importOper
	 */
	public String getImportOper() {
		return importOper;
	}

	/**
	 * @param importOper the importOper to set
	 */
	public void setImportOper(String importOper) {
		this.importOper = importOper;
	}

	/**
	 * @return the dataSource
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return the importCount
	 */
	public String getImportCount() {
		return importCount;
	}

	/**
	 * @param importCount the importCount to set
	 */
	public void setImportCount(String importCount) {
		this.importCount = importCount;
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
	 * @return the beginTime
	 */
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName == null ? null : groupName.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType == null ? null : groupType.trim();
	}
}